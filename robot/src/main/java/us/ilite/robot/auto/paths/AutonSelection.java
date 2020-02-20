package us.ilite.robot.auto.paths;

import com.team319.trajectory.Path;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import us.ilite.robot.controller.*;

import java.util.*;

public class AutonSelection {
    private static Map<String, Path> kAllPaths = BobUtils.getAvailablePaths();
    public static ShuffleboardTab mAutonConfiguration = Shuffleboard.getTab("Auton Config");
//    public static Integer mControllerNumber = mAutonConfiguration.add("Controller Number", 0)
//            .withWidget(BuiltInWidgets.kNumberSlider)
//            .withProperties(Map.of("min", 0, "max", getAutonControllers().size() - 1, "block increment", 1))
//            .withSize(4, 1)
//            .withPosition(0, 1)
//            .getEntry().getNumber(0).intValue();

    public static double mDelaySeconds = mAutonConfiguration.add("Path Delay Seconds", 0)
            .withPosition(2, 3)
            .getEntry()
            .getDouble(0.0);

//    public static int mPathNumber = mAutonConfiguration.add("Path Number", 0)
//            .withWidget(BuiltInWidgets.kNumberSlider)
//            .withProperties(Map.of("min", 0, "max", getAutonControllerFromIndex(mControllerNumber).getPathsFromController().size() - 1, "block increment", 1))
//            .withSize(2, 1)
//            .withPosition(0, 3)
//            .getEntry()
//            .getNumber(0)
//            .intValue();

    public static BaseAutonController mSelectedAutonController;
    public static BaseAutonController mPreviouslySelectedAutonController;
    public static double mDelayCycleCount = mDelaySeconds / 0.02;

    private SendableChooser<BaseAutonController> mSendableAutonControllers = new SendableChooser<>();
    private SendableChooser<Path> mSendablePaths = new SendableChooser<>();
    /**
     * Update these Test Controllers and Paths whenever new ones are added
     */
    private BaseAutonController[] mAutonControllers = {
            new LineAutonController(),
            new ShootIntakeController()
    };

    private Path[] mAutonPaths = (Path[]) BobUtils.getAvailablePaths().values().toArray();

//    static {
//        mAutonConfiguration.addPersistent("Path Selection", "Select controller/path by clicking on the slider dot and using arrow keys").withPosition(6, 0).withSize(4, 1);
//        int pathIndex = 0;
//        for (Map.Entry<String, Path> entry : getAutonControllerFromIndex(mControllerNumber).getPathsFromController().entrySet()) {
//            mAutonConfiguration.add(entry.getKey(), pathIndex)
//                    .withSize(2, 1)
//                    .withPosition(2 * pathIndex, 2).getEntry().clearPersistent();
//            pathIndex++;
//        }
//    }

    public AutonSelection() {
        // TODO - do this after selection from shuffleboard
        //  setActivePath(kAllPaths.get((String) kAllPaths.keySet().toArray()[mPathNumber]));
//        int displayIndex = 0;
//        for (Map.Entry<String, BaseAutonController> entry : getAutonControllers().entrySet()) {
//            mAutonConfiguration.add(String.format("%s", displayIndex), entry.getKey())
//                    .withSize(2, 1)
//                    .withPosition(2 * displayIndex, 0).getEntry().clearPersistent();
//            displayIndex++;
//        }
        updateAutonControllers();
        updatePaths();
    }

//    public static BaseAutonController getAutonControllerFromIndex(int index) {
//        return getAutonControllers().get((String) getAutonControllers().keySet().toArray()[index]);
//    }
//
//    public static Map<String, BaseAutonController> getAutonControllers() {
//        Map<String, BaseAutonController> mControllers = new HashMap<>();
//        mControllers.put("AutonCalibration", new AutonCalibration());
//        mControllers.put("LineController", new LineAutonController());
//        return mControllers;
//    }

    public void updateAutonControllers() {
        for (BaseAutonController c : mAutonControllers) {
            mSendableAutonControllers.addOption(c.getClass().getSimpleName(), c);
        }
        mAutonConfiguration.add("Choose Auton Controller", mSendableAutonControllers);
    }

    public void updatePaths() {
        for (Path p : mAutonPaths) {
            mSendablePaths.addOption(p.getClass().getSimpleName(), p);
        }
        mAutonConfiguration.add("Choose Your Path", mSendablePaths);
    }
}
