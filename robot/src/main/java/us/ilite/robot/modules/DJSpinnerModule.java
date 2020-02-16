package us.ilite.robot.modules;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.flybotix.hfr.util.log.ILog;
import com.flybotix.hfr.util.log.Logger;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import us.ilite.common.config.Settings;
import us.ilite.common.types.EColorData;
import us.ilite.robot.hardware.TalonSRXFactory;

public class DJSpinnerModule extends Module {

    public static final double TARGET_ROTATION_COUNT = 32.0;
    private ILog mLog = Logger.createLog( this.getClass());

    public enum EColorMatch {
        RED( 0.561, 0.232, 0.114 ),
        YELLOW( 0.361, 0.524, 0.113 ),
        BLUE(  0.143, 0.427, 0.429 ),
        GREEN(  0.197, 0.561, 0.240 ),
        NONE(0, 0, 0 );
        public Color color;
        public double r;
        public double g;
        public double b;

        EColorMatch(double r, double g, double b) {
            color = new Color(r,g,b);
        }

        public Color getColorMatch() {
            return ColorMatch.makeColor(r, g, b);
        }

        public EColorMatch nextColor() {
            if(this.ordinal() == GREEN.ordinal()) {
                return RED;
            } else if (this.ordinal() == NONE.ordinal() ){
                return NONE;
            }
            else {
                return EColorMatch.values()[ordinal()+1];
            }
        }

        public static EColorMatch from(ColorMatchResult pResult) {
            for(EColorMatch cm : values()) {
                if(equal(cm.color, pResult.color)) {
                    return cm;
                }
            }
            return NONE;
        }

        private static boolean equal(Color a, Color b) {
            //TODO - this is where we put the thresholding for different color
            // e.g. if abs(a.red - b.red) <= 0.1 then they are close
            return a.equals(b);
        }
    }

    public enum EIsFinished {
        YES,
        NO
    }

    public enum EColorWheelState {
        OFF (0.0),
        ROTATION (0.2),
        POSITION (0.2);

        public double power;
        private EColorWheelState(double _power) {
            power = _power;
        }

        public double getPower(){
            return this.power;
        }
        public static EColorWheelState valueOf(double pOrdinal) {
            return values()[(int)pOrdinal];
        }
    }

    private VictorSPX mVictor;
    private ColorSensorV3 mColorSensorV3;
    private final ColorMatch mColorMatcher = new ColorMatch();
    private EColorMatch eDesiredColorState;
    private EColorMatch eCurrentColorState;
    private EColorMatch eLastColorState;
    private double mSolidStateCounter;
    private double mColorChangeCounter;
    private EColorWheelState eColorWheelState;
    private boolean mUpdatingFlag;


    public DJSpinnerModule() {

        I2C.Port i2cPort = I2C.Port.kOnboard;
        mColorSensorV3 = new ColorSensorV3(i2cPort);

        mSolidStateCounter = 0;
        eColorWheelState = EColorWheelState.OFF;
        eCurrentColorState = EColorMatch.NONE;
        eLastColorState = EColorMatch.NONE;
        eDesiredColorState = EColorMatch.NONE;
        mColorChangeCounter = 0;
        mVictor = TalonSRXFactory.createDefaultVictor(Settings.Hardware.CAN.kDJSpinnerVictorID);
        mVictor.setNeutralMode(NeutralMode.Brake);
        mUpdatingFlag = false;


        for(EColorMatch cm : EColorMatch.values()) {
            if(cm != EColorMatch.NONE) {
                mColorMatcher.addColorMatch(cm.color);
            }
        }
    }


    @Override
    public void readInputs(double pNow) {
        Color c = mColorSensorV3.getColor();
        db.color.set(EColorData.MEAURED_BLUE, c.blue);
        db.color.set(EColorData.MEAURED_GREEN, c.green);
        db.color.set(EColorData.MEAURED_RED, c.red);
        ColorMatchResult match = mColorMatcher.matchClosestColor(c);
        db.color.set(EColorData.SENSED_COLOR, EColorMatch.from(match));
        if ( !mUpdatingFlag ) {
            eCurrentColorState = EColorMatch.from(match);
        }
        else {
            mUpdatingFlag = false;
        }
        db.color.set(EColorData.WHEEL_ROTATION_COUNT, mColorChangeCounter);
        db.color.set(EColorData.CURRENT_MOTOR_POWER , mVictor.getMotorOutputPercent());
    }

    @Override
    public void setOutputs(double pNow) {
        mVictor.set(ControlMode.PercentOutput, db.color.get(EColorData.DESIRED_MOTOR_POWER));

        if( db.color.get(EColorData.COLOR_WHEEL_MOTOR_STATE) == EColorWheelState.ROTATION.ordinal() ) {
            if (eLastColorState.nextColor() == eCurrentColorState) {
                mColorChangeCounter++;
            }
            else if ( eCurrentColorState != eLastColorState ) {
                mUpdatingFlag = true;
                eCurrentColorState = eLastColorState;
            }
            else {
                eLastColorState = eCurrentColorState;
            }
        }
    }


}
