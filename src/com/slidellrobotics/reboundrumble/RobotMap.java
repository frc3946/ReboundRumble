package com.slidellrobotics.reboundrumble;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    //PWM
    public static final int leftDriveMotor = 1;
    public static final int rightDriveMotor = 2;
    public static final int topShootingMotor = 3;
    public static final int bottomShootingMotor = 4;
    
    //Controls
    public static final int leftJoystick = 2;
    public static final int rightJoystick = 1;
    public static final int fireButton = 1;
    public static final int highGearShiftButton = 5;
    public static final int lowGearShiftButton = 4;
    public static final int changeFeedBeltButton = 2;
    
    //Solenoid
    public static final int highGear = 1;
    public static final int lowGear = 2;
    
    //Relay
    public static final int feedBeltSpike = 1;
    
    // Analog
    public static final int gyroTurntable = 1;
}