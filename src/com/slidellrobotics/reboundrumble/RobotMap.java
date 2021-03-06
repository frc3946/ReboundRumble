package com.slidellrobotics.reboundrumble;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;

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
    
    //Controls
    public static final int rightJoystick = 1; //Main Driver Right Joystick
    public static final int leftJoystick = 2; //Main Driver Left Joystick
    public static final int thirdJoystick = 3; //Possible third Joystick for secondary Driver Control (maybe use this for ajusting the Lazy Susan and ball shooter/feed.)
    
    //Right Joystick
    public static final int balanceModeButton = 2; //Button that activates Balance Drive Mode.
    public static final int arcadeModeButton = 3; //Button that activates Arcade Drive Mode.
    public static final int lowGearShiftButton = 4; //Button to shift into Low Gear
    public static final int highGearShiftButton = 5; //Button to shift into High Gear
    
    //Left Joystick
    public static final int stowBridgeButton = 4; //Button to stow BridgeMounter
    public static final int dropBridgeButton = 5; //Button to drop BridgeMounter
    
    //Third Joystick
    public static final int fireButton = 1; //Button to fire Phumatic piston
    public static final int ToggleTurretButton = 2; //Button to toggle the LazySusan PID
    public static final int outtakeFeedBeltButton = 3; //Button to set Ball Feed to remove balls
    public static final int intakeFeedBeltButton = 4; //Button to set Ball Feed to load balls
    public static final int leftLazySusanButton = 5; //Button to turn Lazy Susan Turret Left
    public static final int rightLazySusanButton = 6; //Button to turn Lazy Susan Turret Right
    
    //Motors
    public static final int leftDriveMotor = 1; //Left Jaguar's Port
    public static final int rightDriveMotor = 2; //Right Jaguar's Port
    public static final int leftShootingMotor = 3; //Left Shooting Victor Port
    public static final int rightShootingMotor = 4; //Right Shooting Victor Port

    //Digital IO
    public static final int leftFireEncoder = 1;
    public static final int rightFireEncoder = 2;
    
    //Solenoid
    public static final int highGear = 1; //Transmission Solenoid's A valve port
    public static final int lowGear = 2; //Transmission Solenoid's B valve port
    public static final int firePiston = 3; //FirePiston Solenoid's A valve port
    public static final int stowPiston = 4; //FirePiston Solenoid's B valve port
    
    //Relay
    public static final int feedBeltSpike = 1; //Where Feed Belt Spike is attacked to Sidecar
    public static final int susanSpike = 2; //For the Lazy Susan's Window Motor
    public static final int bridgeSpike = 3; //Bridge Window Motor
    public static final int compressorSpike = 4; //Compressor
    
    // Analog
    public static final int turretGyro = 1; //Turret Gyro Port
    public static final int balanceGyro = 2; //Balancing Chasis Gyro
    
    //Accelerometer I2C
    public static final int positioningAccelerometer = 1; //Positioning Accelerometer port on Sidecar
    
    //Lazy Susan Relay Map
    public static final Relay.Value susanRight = Relay.Value.kForward;
    public static final Relay.Value susanLeft = Relay.Value.kReverse;
    public static final Relay.Value susanOff = Relay.Value.kOff;
}
