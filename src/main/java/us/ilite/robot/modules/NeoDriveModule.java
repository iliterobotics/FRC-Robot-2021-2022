package us.ilite.robot.modules;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.*;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import us.ilite.common.config.Settings;
import us.ilite.common.lib.control.PIDController;
import us.ilite.common.lib.control.ProfileGains;
import us.ilite.common.lib.util.Units;
import us.ilite.common.types.EMatchMode;
import us.ilite.common.types.drive.EDriveData;
import us.ilite.common.types.sensor.EGyro;
import us.ilite.robot.Enums;
import us.ilite.robot.Robot;
import us.ilite.robot.TrajectoryCommandUtils;
import us.ilite.robot.hardware.ECommonNeutralMode;
import us.ilite.robot.hardware.HardwareUtils;
import us.ilite.robot.hardware.Pigeon;
import us.ilite.robot.hardware.SparkMaxFactory;

import static us.ilite.common.types.drive.EDriveData.*;

public class NeoDriveModule extends Module {
    private CANSparkMax mRightMaster;
    private CANSparkMax mRightFollower;
    private CANSparkMax mLeftMaster;
    private CANSparkMax mLeftFollower;
    private RelativeEncoder mRightEncoder;
    private RelativeEncoder mLeftEncoder;
    private SparkMaxPIDController mRightCtrl;
    private SparkMaxPIDController mLeftCtrl;
    private PIDController mTurnToDegreePID;
    private PIDController mRightPositionPID;
    private PIDController mLeftPositionPID;
    private Pigeon mGyro;

    // ========================================
    // DO NOT MODIFY THESE PHYSICAL CONSTANTS
    // ========================================
    public static final double kGearboxRatio = (12.0 / 40.0) * (14.0 / 40.0);
    public static final double kWheelDiameterFeet = 3.7 / 12.0;
    public static final double kWheelCircumferenceFeet = kWheelDiameterFeet * Math.PI;
    public static final double kDriveNEOPositionFactor = kGearboxRatio * kWheelCircumferenceFeet;
    public static final double kDriveNEOVelocityFactor = kDriveNEOPositionFactor / 60.0;
    public static final double kMaxVelocityRPM = 5676;
    public static final double kPulsesPerRotation = 256.0;
    public static final double kCurrentLimitAmps = 60.0;

    // ========================================
    // DO NOT MODIFY THESE PID CONSTANTS
    // ========================================
    private static final int VELOCITY_PID_SLOT = 1;
    private static final int SMART_MOTION_PID_SLOT = 2;
    //TODO change the smart motion gains once we have tuned it
    public static ProfileGains kSmartMotionGains = new ProfileGains()
            .p(0.25)
            .f(0.00015)
            .maxVelocity(kMaxVelocityRPM * Settings.Input.kMaxAllowedVelocityMultiplier)
            .maxAccel(5676d)
            .slot(SMART_MOTION_PID_SLOT)
            .velocityConversion(kDriveNEOPositionFactor);
    public static ProfileGains kPositionGains = new ProfileGains()
            .maxVelocity(kMaxVelocityRPM * Settings.Input.kMaxAllowedVelocityMultiplier * 12)
            .maxAccel(31*12d) // just under 1g
            .p(0.0275)
            .tolerance(0.2);
    public static ProfileGains kVelocityGains = new ProfileGains()
            .f(0.00015)
            .p(0.00000025)
            .maxVelocity(kMaxVelocityRPM * Settings.Input.kMaxAllowedVelocityMultiplier)
            .slot(VELOCITY_PID_SLOT)
            .velocityConversion(kDriveNEOVelocityFactor);
//    public static ProfileGains kTurnToProfileGains = new ProfileGains().p(0.0285);
    public static ProfileGains kTurnToProfileGains = new ProfileGains().p(0.02).f(0.1);

    // ========================================
    // DO NOT MODIFY THESE OTHER CONSTANTS
    // ========================================
    public static double kTurnSensitivity = 0.85;
    private static double kInitialXPosition = 0;
    private static double kInitialYPosition = 0;
    private DifferentialDriveOdometry mOdometry;
    private DifferentialDrive mDrive;

    public NeoDriveModule() {
        mLeftMaster = SparkMaxFactory.createDefaultSparkMax(Settings.HW.CAN.kDTML1);
        mLeftFollower = SparkMaxFactory.createDefaultSparkMax(Settings.HW.CAN.kDTL3);
        mRightMaster = SparkMaxFactory.createDefaultSparkMax(Settings.HW.CAN.kDTMR2);
        mRightFollower = SparkMaxFactory.createDefaultSparkMax(Settings.HW.CAN.kDTR4);
        mLeftFollower.follow(mLeftMaster);
        mRightFollower.follow(mRightMaster);
        mGyro = new Pigeon(Robot.CLOCK, Settings.HW.CAN.kDTGyro);

        mLeftMaster.setIdleMode(CANSparkMax.IdleMode.kCoast);
        mRightMaster.setIdleMode(CANSparkMax.IdleMode.kCoast);
        mLeftFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);
        mRightFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);
        mRightMaster.setInverted(true);

        mRightEncoder = mRightMaster.getEncoder();
        mLeftEncoder = mLeftMaster.getEncoder();

        mRightCtrl = mRightMaster.getPIDController();
        mLeftCtrl = mLeftMaster.getPIDController();
        mRightCtrl.setOutputRange(-kMaxVelocityRPM, kMaxVelocityRPM);
        mLeftCtrl.setOutputRange(-kMaxVelocityRPM, kMaxVelocityRPM);

        mTurnToDegreePID = new PIDController(kTurnToProfileGains, -180, 180, Settings.kControlLoopPeriod);
        mTurnToDegreePID.setContinuous(true);
        mTurnToDegreePID.setOutputRange(-1, 1);

        mRightPositionPID = new PIDController(kPositionGains,0, 10, Settings.kControlLoopPeriod);
        mRightPositionPID.setOutputRange(-1 , 1);
        mLeftPositionPID = new PIDController(kPositionGains,0, 10, Settings.kControlLoopPeriod);
        mLeftPositionPID.setOutputRange(-1 , 1);

        HardwareUtils.setGains(mLeftCtrl, kVelocityGains);
        HardwareUtils.setGains(mRightCtrl, kVelocityGains);
        HardwareUtils.setGains(mLeftCtrl, kSmartMotionGains);
        HardwareUtils.setGains(mRightCtrl, kSmartMotionGains);

        mOdometry = new DifferentialDriveOdometry(mGyro.getHeading());
        mDrive = new DifferentialDrive(mLeftMaster, mRightMaster);

        mLeftMaster.burnFlash();
        mLeftFollower.burnFlash();
        mRightMaster.burnFlash();
        mRightFollower.burnFlash();
    }
    @Override
    public void modeInit(EMatchMode pMode) {
        mGyro.zeroAll();
        reset();
        resetOdometry(TrajectoryCommandUtils.getJSONTrajectory().getInitialPose());
        kInitialXPosition = mOdometry.getPoseMeters().getX();
        kInitialYPosition = mOdometry.getPoseMeters().getY();
        if(pMode == EMatchMode.AUTONOMOUS) {
            mLeftMaster.setIdleMode(CANSparkMax.IdleMode.kBrake);
            mRightMaster.setIdleMode(CANSparkMax.IdleMode.kBrake);
            mLeftFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);
            mRightFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);
        } else {
            mLeftMaster.setIdleMode(CANSparkMax.IdleMode.kCoast);
            mRightMaster.setIdleMode(CANSparkMax.IdleMode.kCoast);
            mLeftFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);
            mRightFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);
        }
    }
    @Override
    public void readInputs() {
        mGyro.update();
        double odoX = mOdometry.getPoseMeters().getX() - kInitialXPosition;
        double odoY = mOdometry.getPoseMeters().getY() - kInitialYPosition;
        db.drivetrain.set(GET_X_OFFSET_METERS, odoX);
        db.drivetrain.set(GET_Y_OFFSET_METERS, odoY);
        db.drivetrain.set(ACTUAL_HEADING_RADIANS, -mGyro.getHeading().getRadians());
        db.drivetrain.set(ACTUAL_HEADING_DEGREES, -mGyro.getHeading().getDegrees());
        db.drivetrain.set(LEFT_VOLTAGE, mLeftMaster.getVoltageCompensationNominalVoltage());
        db.drivetrain.set(RIGHT_VOLTAGE, mRightMaster.getVoltageCompensationNominalVoltage());
        db.drivetrain.set(LEFT_CURRENT, mLeftMaster.getOutputCurrent());
        db.drivetrain.set(RIGHT_CURRENT, mRightMaster.getOutputCurrent());
        db.drivetrain.set(L_ACTUAL_POS_FT, mLeftEncoder.getPosition() * kDriveNEOPositionFactor);
        db.drivetrain.set(L_ACTUAL_VEL_FT_s, mLeftEncoder.getVelocity() * kDriveNEOVelocityFactor);
        db.drivetrain.set(R_ACTUAL_POS_FT, mRightEncoder.getPosition() * kDriveNEOPositionFactor);
        db.drivetrain.set(R_ACTUAL_VEL_FT_s, mRightEncoder.getVelocity() * kDriveNEOVelocityFactor);
        db.drivetrain.set(L_ACTUAL_POS_meters, Units.feet_to_meters(mLeftEncoder.getPosition() * kDriveNEOPositionFactor));
        db.drivetrain.set(L_ACTUAL_VEL_meters_s, Units.feet_to_meters(mLeftEncoder.getVelocity() * kDriveNEOVelocityFactor));
        db.drivetrain.set(R_ACTUAL_POS_meters, Units.feet_to_meters(mRightEncoder.getPosition() * kDriveNEOPositionFactor));
        db.drivetrain.set(R_ACTUAL_VEL_meters_s, Units.feet_to_meters(mRightEncoder.getVelocity() * kDriveNEOVelocityFactor));
        db.imu.set(EGyro.ACCEL_X, mGyro.getAccelX());
        db.imu.set(EGyro.ACCEL_Y, mGyro.getAccelY());
        db.imu.set(EGyro.PITCH_DEGREES, mGyro.getPitch().getDegrees());
        db.imu.set(EGyro.ROLL_DEGREES, mGyro.getRoll().getDegrees());
        db.imu.set(EGyro.YAW_DEGREES, mGyro.getYaw().getDegrees());
        db.imu.set(EGyro.YAW_OMEGA_DEGREES, mGyro.getYawRate().getDegrees());
    }

    @Override
    public void setOutputs() {
        Enums.EDriveState state = db.drivetrain.get(STATE, Enums.EDriveState.class);
        double throttle = db.drivetrain.safeGet(DESIRED_THROTTLE_PCT, 0.0);
        double turn = db.drivetrain.safeGet(DESIRED_TURN_PCT, 0.0);
        double left = throttle + turn;
        double right = throttle - turn;
        ECommonNeutralMode neutralMode = db.drivetrain.get(NEUTRAL_MODE, ECommonNeutralMode.class);
        if (state == null) return;
        switch (state) {
            case RESET:
                reset();
                break;
            case PERCENT_OUTPUT:
                mLeftMaster.set(left);
                mRightMaster.set(right);
                break;
            case VELOCITY:
                mLeftCtrl.setReference(left * kMaxVelocityRPM, CANSparkMax.ControlType.kVelocity, VELOCITY_PID_SLOT, 0);
                mRightCtrl.setReference(right * kMaxVelocityRPM, CANSparkMax.ControlType.kVelocity, VELOCITY_PID_SLOT, 0);
                break;
            case TURN_TO:
                mTurnToDegreePID.setSetpoint(db.drivetrain.get(DESIRED_TURN_ANGLE_deg));
                double output = mTurnToDegreePID.calculate(db.drivetrain.get(ACTUAL_HEADING_DEGREES), clock.getCurrentTimeInMillis());
                db.drivetrain.set(DESIRED_TURN_PCT, output);
                mLeftMaster.set(output);
                mRightMaster.set(-output);
                break;
            case SMART_MOTION:
                mLeftCtrl.setReference(db.drivetrain.get(L_DESIRED_POS_FT) / kDriveNEOPositionFactor,
                        CANSparkMax.ControlType.kSmartMotion, SMART_MOTION_PID_SLOT, 0 );
                mRightCtrl.setReference(db.drivetrain.get(R_DESIRED_POS_FT) / kDriveNEOPositionFactor,
                        CANSparkMax.ControlType.kSmartMotion, SMART_MOTION_PID_SLOT, 0 );
                break;
            case POSITION:
                mLeftPositionPID.setSetpoint(db.drivetrain.get(L_DESIRED_POS_FT));
                mRightPositionPID.setSetpoint(db.drivetrain.get(R_DESIRED_POS_FT));
                double lMeasurement = db.drivetrain.get(L_ACTUAL_POS_FT);
                double rMeasurement = db.drivetrain.get(R_ACTUAL_POS_FT);
                double leftOutput = mLeftPositionPID.calculate(lMeasurement, clock.getCurrentTimeInMillis());
                double rightOutput = mRightPositionPID.calculate(rMeasurement, clock.getCurrentTimeInMillis());
                mLeftMaster.set(leftOutput);
                mRightMaster.set(rightOutput);
                break;
            case PATH_FOLLOWING_RAMSETE:
                mLeftMaster.set(db.drivetrain.get(L_DESIRED_DRIVE_FT_SEC) / 24.0);
                mRightMaster.set(db.drivetrain.get(R_DESIRED_DRIVE_FT_SEC) / 24.0);
                mDrive.feed();
                break;
        }
    }

    private void reset() {
        mLeftEncoder.setPosition(0.0);
        mRightEncoder.setPosition(0.0);
        mLeftMaster.set(0.0);
        mRightMaster.set(0.0);
    }

    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose The pose to which to set the odometry.
     */
    public void resetOdometry(Pose2d pose) {
        mLeftEncoder.setPosition(0);
        mRightEncoder.setPosition(0);
        mOdometry.resetPosition(pose, mGyro.getHeading());
    }
}