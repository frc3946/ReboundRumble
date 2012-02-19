package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.OI;
import com.slidellrobotics.reboundrumble.RobotMap;
import com.slidellrobotics.reboundrumble.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author 3946 Robotics
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static DriveTrain driveTrain = new DriveTrain();
    public static Transmission transmission = new Transmission();
    public static FireMotors leftShootingMotors = new FireMotors("Left",RobotMap.leftFireEncoder, RobotMap.leftShootingMotor);
    public static FireMotors rightShootingMotors = new FireMotors("Right",RobotMap.rightFireEncoder, RobotMap.rightShootingMotor);
    public static FeedBelt feedBelt = new FeedBelt();
    public static FirePiston firePiston = new FirePiston();
    public static PositioningAccelerometer pAccelerometer = new PositioningAccelerometer();
    public static LazySusan lazySusan = new LazySusan();
    public static TrackingCamera camera = new TrackingCamera();
    public static BridgeMounter bridgeMounter = new BridgeMounter();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        SmartDashboard.putData(leftShootingMotors);
        SmartDashboard.putData(rightShootingMotors);
        SmartDashboard.putData(driveTrain);
        // Show what command your subsystem is running on the TheDash
        System.out.println("CommandBase Init..");
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
