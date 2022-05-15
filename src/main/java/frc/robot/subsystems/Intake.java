package frc.robot.subsystems;

import static frc.robot.Constants.Intake.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase{
    //initializing motor and solonoid
    private static DoubleSolenoid intakesol = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
    private static WPI_TalonSRX intakeMoter = new WPI_TalonSRX(3);

    @Override
    public void periodic(){

    }

    public void intakeSub(){
        retract();
        
    }
    public void extend(){

        intakesol.set(OUT);
    
    }

    public void retract(){

        intakesol.set(IN);

    }

    public void runIntake(double spd){
        intakeMoter.set(spd);

    }
}
