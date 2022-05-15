// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */


 // we make finals upper case
public final class Constants {
    //controller ports
    public static final int DRIVER_PORT = 0;
    public static final int MANIPULATOR_PORT = 1;

    //initialize intake solinoid values
    public static final class Intake{
        public static final Value IN = Value.kForward;
        public static final Value OUT = Value.kReverse;
        public static final double SPEED = 0.2;
    }
    //initialize intake solinoid values
    public static final class Drivetrain{
        public static final Value DOWN = Value.kForward;
        public static final Value UP = Value.kReverse;

    }
}
