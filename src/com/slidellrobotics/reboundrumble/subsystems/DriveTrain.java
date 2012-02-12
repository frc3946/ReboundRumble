
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import com.slidellrobotics.reboundrumble.commands.TankDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    private Jaguar leftJaguars;
    private Jaguar rightJaguars;
    private RobotDrive robotDrive;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public DriveTrain() {
        System.out.println("[DriveTrain] Starting");
        leftJaguars = new Jaguar(RobotMap.leftDriveMotor);
        System.out.println("[DriveTrain] leftJaguars initialized");
        rightJaguars = new Jaguar(RobotMap.rightDriveMotor);
        System.out.println("[DriveTrain] rightJaguars initialized");
        robotDrive = new RobotDrive(leftJaguars, rightJaguars);
        System.out.println("[DriveTrain] robotDrive initialized");
        System.out.println("[DriveTrain] Started");
    }
    
    /**
     * Drive with two Joysticks
     * @param leftSpeed speed for left Jaguars
     * @param rightSpeed speed for right Jaguars
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        robotDrive.tankDrive(leftSpeed,rightSpeed);
        SmartDashboard.putDouble("Left Speed", leftJaguars.getSpeed()*10); //Speed Multipled by 10 for clarity
        //System.out.println("[DriveTrain] Left Speed "+leftJaguars.getSpeed()); //uncomment for use with debugging
        SmartDashboard.putDouble("Right Speed", rightJaguars.getSpeed()*10); //Speed Multipled by 10 for clarity
        //System.out.println("[DriveTrain] Right Speed "+rightJaguars.getSpeed()); //uncomment for use with debugging
    }
    
    /**
     * Drive with one Joystick
     * @param forward Speed to move
     * @param turn How much/Which side to turn
     */
    public void arcadeDrive(double forward, double turn) {
        robotDrive.arcadeDrive(forward, turn);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }
}

