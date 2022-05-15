package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Drivetrain.*;

public class Drivetrain extends SubsystemBase{

    //set the ports of the motors and defines the type
    private CANSparkMax Lb = new CANSparkMax(16, MotorType.kBrushless);
    private CANSparkMax Lf = new CANSparkMax(17, MotorType.kBrushless);
    private CANSparkMax Rb = new CANSparkMax(14, MotorType.kBrushless);
    private CANSparkMax Rf = new CANSparkMax(10, MotorType.kBrushless);

    //sets a group for the right and left of the drivtrain
    //I believe that order and quantity does not matter
    private MotorControllerGroup Left = new MotorControllerGroup(Lb, Lf);
    private MotorControllerGroup Right = new MotorControllerGroup(Rb, Rf);

    //creates a differential drive based on the left and right side
    private DifferentialDrive diffdrive = new DifferentialDrive(Left, Right);

    //initializes the solinoid and forward and reverse ports
    private DoubleSolenoid shift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 5);

    public void drivetrain(){

        Right.setInverted(true);

    }

    public void drive(double spd, double rot){
    
    diffdrive.arcadeDrive(spd, rot);        

    }

    //call to shift
    public void shiftDown(){
        shift.set(DOWN);

    }

    public void shiftUP(){
        shift.set(UP);
    }
    
}
