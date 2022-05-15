package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Delivery extends SubsystemBase {
    private WPI_TalonSRX deliverywheel = new WPI_TalonSRX(2);
    private WPI_TalonSRX deliverystar =  new WPI_TalonSRX(4);
    
    public void runDelivery(double spd){
        deliverywheel.set(-spd);
        deliverystar.set(spd);
    }
}
