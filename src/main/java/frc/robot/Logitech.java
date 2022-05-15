package frc.robot;


import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Logitech;

/*/**
 * class for the logitech controller mapping the port numbers
 */
public class Logitech extends Joystick {


    public static class Ports {

        // trigger ports
        public static final int LEFT_TRIGGER = 2;
        public static final int RIGHT_TRIGGER = 3;

        // button ports
        public static final int A = 1;
        public static final int B = 2;
        public static final int X = 3;
        public static final int Y = 4;

        // bumper ports
        public static final int LEFT_BUMPER = 5;
        public static final int RIGHT_BUMPER = 6;

        // back and start buttons ports
        public static final int BACK = 7;
        public static final int START = 8;

        // axis for left joystick
        // we no longer put this in a different class we just set them like normal
        
        public static class LeftStick {
            public static final int X = 0;
            public static final int Y = 1;
            public static final int BUTTON = 9;
        }

        public static class RightStick {
            public static final int X = 4;
            public static final int Y = 5;
            public static final int BUTTON = 10;

        }
    }

    // map recording deadband values for each axis
    private HashMap<Integer, Double> deadbandMap = new HashMap<>();

    public Logitech(int port) {
        super(port);
    }

    /**
     * modifies output of getRawAxis so that upwards on the joysticks outputs
     * positive values in alignment with convention
     * 
     * @param axis the axis number
     * @return the value of the axis
     * 

     I believe that the value still needs to be reversed with the way this code is formatted
     */
    @Override
    public double getRawAxis(int axis) {
        double value;
        if (axis == Ports.LeftStick.Y || axis == Ports.RightStick.Y)
            value = -super.getRawAxis(axis);
        else
            value = super.getRawAxis(axis);

        if (deadbandMap.containsKey(axis))
            return Math.abs(value) > deadbandMap.get(axis) ? value : 0;
            return value;
    }

    //to set the deadband when the robot is initialized you call this method with the stick axis you want and the deadband
    public void setDeadband(int axis, double deadband) {
        if (axis < 0 || axis > 5 || deadband < 0 || deadband > 1)
            throw new IllegalArgumentException();
        deadbandMap.put(axis, deadband);

    }
}
