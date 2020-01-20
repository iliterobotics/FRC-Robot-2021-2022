package us.ilite.robot.modules;

import com.flybotix.hfr.codex.Codex;
import com.flybotix.hfr.util.log.ILog;
import com.flybotix.hfr.util.log.Logger;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import us.ilite.common.config.AbstractSystemSettingsUtils;
import us.ilite.common.config.Settings;
import us.ilite.common.lib.control.PIDController;
import us.ilite.common.lib.control.ProfileGains;
import us.ilite.common.types.EMatchMode;
import us.ilite.common.types.ETargetingData;
import us.ilite.common.types.drive.EDriveData;
import us.ilite.common.types.sensor.EPowerDistPanel;
import us.ilite.robot.Robot;
import us.ilite.robot.hardware.*;
import us.ilite.robot.loops.Loop;

/**
 * Class for running all drivetrain train control operations from both autonomous and
 * driver-control.
 * TODO Support for rotation trajectories
 * TODO Turn-to-heading with Motion Magic
 */
public class DriveModule extends Loop {
	private final ILog mLogger = Logger.createLog(DriveModule.class);

	public static double kGearboxRatio = (12.0 / 80.0) * (42.0 / 80.0);
	public static double kClosedLoopVoltageRampRate = 0.0;
	public static double kOpenLoopVoltageRampRate = 0.1;
	public static int kCurrentLimitAmps = 50;
	public static int kCurrentLimitTriggerDurationMs = 100;
	public static double kWheelDiameterInches = 6.0;
	public static double kWheelDiameterFeet = kWheelDiameterInches / 12.0;
	public static double kWheelCircumference = kWheelDiameterInches * Math.PI;
	public static double kDefaultRampRate = 120.0; // in V/sec
	public static double kTicksPerRotation = 1.0;
	public static double kEffectiveWheelbase = 23.25;
	public static double kTurnCircumference = kEffectiveWheelbase * Math.PI;
	public static double kInchesPerDegree = kTurnCircumference / 360.0;
	public static double kWheelTurnsPerDegree = kInchesPerDegree / kWheelDiameterInches;
	// =============================================================================
	// Closed-Loop Velocity Constants
	// =============================================================================
	public static ProfileGains kDistancePID = new ProfileGains().p(1.0).maxVelocity(5676d).maxAccel(56760d);
	public static ProfileGains kVelocityPID = new ProfileGains().p(1.0).maxVelocity(5676d).maxAccel(56760d);
	public static ProfileGains kTurnToProfileGains = new ProfileGains().f(0.085);
	public static double kTurnSensitivity = 0.85;


	// =============================================================================
	// Heading Gains
	// =============================================================================
	public static ProfileGains kDriveHeadingGains = new ProfileGains().p(0.03);
	public static double kDriveLinearPercentOutputLimit = 0.5;

	public static EPowerDistPanel[] kPdpSlots = new EPowerDistPanel[]{
			/* Left */
			EPowerDistPanel.CURRENT1,
			EPowerDistPanel.CURRENT2,

			/* Right */
			EPowerDistPanel.CURRENT13,
			EPowerDistPanel.CURRENT14,

	};
	
	
	private IDriveHardware mDriveHardware;
	private Rotation2d mGyroOffset = new Rotation2d();

	private EDriveState mDriveState;
	private DriveMessage mDriveMessage;
	private double mTargetTrackingThrottle = 0;

	private PIDController mTargetAngleLockPid;

	private double mPreviousTime = 0;

	public DriveModule()
	{
		if(AbstractSystemSettingsUtils.isPracticeBot()) {
		} else {
			this.mDriveHardware = new NeoDriveHardware(kGearboxRatio);
		}

		this.mDriveHardware.init();
	}

	@Override
	public void modeInit(EMatchMode pMode, double pNow) {
		mTargetAngleLockPid = new PIDController(Settings.kTargetAngleLockGains, Settings.kTargetAngleLockMinInput, Settings.kTargetAngleLockMaxInput, Settings.kControlLoopPeriod);
		mTargetAngleLockPid.setOutputRange(Settings.kTargetAngleLockMinPower, Settings.kTargetAngleLockMaxPower);
		mTargetAngleLockPid.setSetpoint(0);
		mTargetAngleLockPid.reset();
		mDriveHardware.zero();

	  	setDriveMessage(DriveMessage.kNeutral);
	  	setDriveState(EDriveState.NORMAL);

//	  	startCsvLogging();
	}

	@Override
	public void readInputs(double pNow) {

		Robot.DATA.drivetrain.set(EDriveData.LEFT_POS_INCHES, mDriveHardware.getLeftInches());
		Robot.DATA.drivetrain.set(EDriveData.RIGHT_POS_INCHES, mDriveHardware.getRightInches());
//		Robot.mData.drivetrain.set(EDriveData.LEFT_VEL_IPS, mDriveHardware.getLeftVelInches());
//		Robot.mData.drivetrain.set(EDriveData.RIGHT_VEL_IPS, mDriveHardware.getRightVelInches());
		Robot.DATA.drivetrain.set(EDriveData.LEFT_VEL_TICKS, (double)mDriveHardware.getLeftVelTicks());
		Robot.DATA.drivetrain.set(EDriveData.RIGHT_VEL_TICKS, (double)mDriveHardware.getRightVelTicks());

		Robot.DATA.drivetrain.set(EDriveData.LEFT_MESSAGE_OUTPUT, mDriveMessage.getLeftOutput());
		Robot.DATA.drivetrain.set(EDriveData.RIGHT_MESSAGE_OUTPUT, mDriveMessage.getRightOutput());
		Robot.DATA.drivetrain.set(EDriveData.LEFT_MESSAGE_CONTROL_MODE, (double)mDriveMessage.getMode().ordinal());
		Robot.DATA.drivetrain.set(EDriveData.RIGHT_MESSAGE_CONTROL_MODE, (double)mDriveMessage.getMode().ordinal());
		Robot.DATA.drivetrain.set(EDriveData.LEFT_MESSAGE_NEUTRAL_MODE, (double)mDriveMessage.getNeutral().ordinal());
		Robot.DATA.drivetrain.set(EDriveData.RIGHT_MESSAGE_NEUTRAL_MODE, (double)mDriveMessage.getNeutral().ordinal());
//
//		mData.imu.set(EGyro.YAW_DEGREES, mDriveHardware.getImu().getHeading().getDegrees());

//		SimpleNetworkTable.writeCodexToSmartDashboard(EDriveData.class, mData.drivetrain, mClock.getCurrentTime());
	}

	@Override
	public void setOutputs(double pNow) {
        if(mDriveState != EDriveState.NORMAL) {
			mLogger.error("Invalid drivetrain state - maybe you meant to run this a high frequency?");
			mDriveState = EDriveState.NORMAL;
		} else {
			mDriveHardware.set(mDriveMessage);
		}

		mPreviousTime = pNow;
	}
	
	@Override
	public void shutdown(double pNow) {
		mDriveHardware.zero();
	}

	@Override
	public void loop(double pNow) {
//		mUpdateTimer.start();
		switch(mDriveState) {
			case PATH_FOLLOWING:
			case TARGET_ANGLE_LOCK:

				Codex<Double, ETargetingData> targetData = Robot.DATA.limelight;
				double pidOutput;
				if(mTargetAngleLockPid != null && targetData != null && targetData.isSet(ETargetingData.tv) && targetData.get(ETargetingData.tx) != null) {

					//if there is a target in the limelight's fov, lock onto target using feedback loop
					pidOutput = mTargetAngleLockPid.calculate(-1.0 * targetData.get(ETargetingData.tx), pNow - mPreviousTime);
					pidOutput = pidOutput + (Math.signum(pidOutput) * Settings.kTargetAngleLockFrictionFeedforward);

					mDriveMessage = new DriveMessage().throttle(mTargetTrackingThrottle).turn(pidOutput).calculateCurvature();
					// If we've already seen the target and lose tracking, exit.
				}

				break;
			case NORMAL:
				break;
			default:
				mLogger.warn("Got drivetrain state: " + mDriveState+" which is unhandled");
				break;
		}
		mDriveHardware.set(mDriveMessage);
		mPreviousTime = pNow;
//		mUpdateTimer.stop();
	}

	public synchronized void setTargetAngleLock() {
		mDriveState = EDriveState.TARGET_ANGLE_LOCK;
		mDriveHardware.configureMode(ECommonControlMode.PERCENT_OUTPUT);
		mDriveHardware.set(DriveMessage.kNeutral);
	}

	public synchronized void setPathFollowing() {
		mDriveState = EDriveState.PATH_FOLLOWING;
		mDriveHardware.configureMode(ECommonControlMode.VELOCITY);
	}

	public synchronized void setNormal() {
		mDriveState = EDriveState.NORMAL;
	}

	public synchronized void setTargetTrackingThrottle(double pTargetTrackingThrottle) {
		mTargetTrackingThrottle = pTargetTrackingThrottle;
	}


	@Override
	public boolean checkModule(double pNow) {
        return mDriveHardware.checkHardware();
	}


	public synchronized void zero() {
		mDriveHardware.zero();
	}

	private void setDriveState(EDriveState pDriveState) {
		this.mDriveState = pDriveState;
	}

	public synchronized void setDriveMessage(DriveMessage pDriveMessage) {
		this.mDriveMessage = pDriveMessage;
	}


	public synchronized IDriveHardware getDriveHardware() {
	    return mDriveHardware;
    }

    public synchronized DriveMessage getDriveMessage() {
		return mDriveMessage;
	}

	public boolean isCurrentLimiting() {
		return EPowerDistPanel.isAboveCurrentThreshold(kCurrentLimitAmps, Robot.DATA.pdp, kPdpSlots);
	}

	public static class Conversions {

		public static double rotationsToInches(double rotations) {
			return rotations * (kWheelDiameterInches * Math.PI);
		}

		public static double rpmToInchesPerSecond(double rpm) {
			return rotationsToInches(rpm) / 60;
		}

		public static double inchesToRotations(double inches) {
			return inches / kWheelCircumference;
		}

		public static double inchesPerSecondToRpm(double inches_per_second) {
			return inchesToRotations(inches_per_second) * 60;
		}

		public static double radiansPerSecondToTicksPer100ms(double rad_s) {
			return rad_s / (Math.PI * 2.0) * kTicksPerRotation / 10.0;
		}

		public static double ticksToRotations(double ticks) {
			return ticks / kTicksPerRotation;
		}

		public static double ticksToInches(double ticks) {
			return ticksToRotations(ticks) * kWheelCircumference;
		}

		public static int inchesToTicks(double inches) {
			return (int)(inchesToRotations(inches) * kTicksPerRotation);
		}

		public static double ticksPer100msToRotationsPerSecond(double ticks) {
			return ticks / kTicksPerRotation * 10.0;
		}

		public static double ticksPer100msToInchesPerSecond(double ticks) {
			return ticksPer100msToRotationsPerSecond(ticks) * kWheelCircumference;
		}

		public static double ticksPer100msToRadiansPerSecond(double ticks) {
			return ticksPer100msToRotationsPerSecond(ticks) * (Math.PI * 2.0);
		}

	}
}