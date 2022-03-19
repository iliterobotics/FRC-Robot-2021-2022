package us.ilite.robot.controller;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import us.ilite.common.Distance;
import us.ilite.common.types.EIntakeData;
import us.ilite.robot.commands.DriveStraight;
import us.ilite.robot.commands.TurnToDegree;

public class TwoBallController extends BaseAutonController {
    private DriveStraight mFirstLeg = new DriveStraight(Distance.fromFeet(3.5));
    private Boolean mFirstLegComplete = false;
    private DriveStraight mSecondLeg = new DriveStraight(Distance.fromFeet(-3.0));
    private boolean mSecondLegComplete = false;
    private DriveStraight mThirdLeg = new DriveStraight(Distance.fromFeet(5.0));
    private boolean mThirdLegComplete = false;
    private Timer mTimer;
    public void initialize(Trajectory pTrajectory) {
        //  super.initialize(TrajectoryCommandUtils.getJSONTrajectory());
        mTimer = new Timer();
        mTimer.reset();
        mTimer.start();
        mFirstLeg.init(mTimer.get());
    }

    private static double
            kFirstLegTimeEnd = 2.5,
            kSecondLegTimeEnd = kFirstLegTimeEnd + 2.5,
            kCargoFireTime = kSecondLegTimeEnd + 1.5,
            kThirdLegTimeEnd = kCargoFireTime + 3.0;
    public void updateImpl() {
        double time = mTimer.get();
        if (time < 0.5) {
            intakeCargo();
            SmartDashboard.putString("Auton State", "Intake Out");
        }
        else if (time < kFirstLegTimeEnd) {
            intakeCargo();
            mFirstLegComplete = mFirstLeg.update(time) || time > kFirstLegTimeEnd;
            SmartDashboard.putString("Auton State", "First Leg " + mFirstLegComplete);
        }
        else if (time < kSecondLegTimeEnd) {
            SmartDashboard.putString("Auton State", "Second Leg");
            indexCargo();
            mSecondLegComplete = mSecondLeg.update(time) || time > kSecondLegTimeEnd;
            setIntakeArmEnabled(false);
            db.intake.set(EIntakeData.DESIRED_ROLLER_pct, 0.0);

        }
        else if (time < kCargoFireTime) {
            SmartDashboard.putString("Auton State", "Firing");
            fireCargo();
        }
        else if (time < kThirdLegTimeEnd) {
            SmartDashboard.putString("Auton State", "Leaving Zone");
            mThirdLeg.update(time);
        } else {
            stopDrivetrain();
        }
    }
}