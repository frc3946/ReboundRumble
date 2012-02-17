/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import com.slidellrobotics.reboundrumble.commands.TankDrive;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author gixxy
 */
public class DriveTrain extends PIDSubsystem {

    private static final double Kp = 0.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    
    private Jaguar leftJaguars;
    private Jaguar rightJaguars;
    private RobotDrive robotDrive;
    
    private Gyro balanceGyro;
    private double gyroSpeed;
    private double gyroAngle;
    // Initialize your subsystem here
    public DriveTrain() {
        super("DriveTrain2", Kp, Ki, Kd);
        
        System.out.println("[DriveTrain] Starting");
        leftJaguars = new Jaguar(RobotMap.leftDriveMotor);
        System.out.println("[DriveTrain] leftJaguars initialized");
        rightJaguars = new Jaguar(RobotMap.rightDriveMotor);
        System.out.println("[DriveTrain] rightJaguars initialized");
        robotDrive = new RobotDrive(leftJaguars, rightJaguars);
        System.out.println("[DriveTrain] robotDrive initialized");
        balanceGyro = new Gyro(RobotMap.balanceGyro);
        System.out.println("[DriveTrain] balanceGyro initialized");
        System.out.println("[DriveTrain] Started");
        
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new TankDrive());
    }
    
    protected double returnPIDInput() {
        return balanceGyro.getAngle();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        balanceDrive(output);
    }
    
    /**
     * Drive with two Joysticks
     * @param leftSpeed speed for left Jaguars
     * @param rightSpeed speed for right Jaguars
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        robotDrive.tankDrive(leftSpeed,rightSpeed);
        SmartDashboard.putDouble("Left Speed", leftJaguars.getSpeed()*-10); //Speed Multipled by 10 for clarity (negative for direction)
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
        SmartDashboard.putDouble("Left Speed", leftJaguars.getSpeed()*-10); //Speed Multipled by 10 for clarity (negative for direction)
        //System.out.println("[DriveTrain] Left Speed "+leftJaguars.getSpeed()); //uncomment for use with debugging
        SmartDashboard.putDouble("Right Speed", rightJaguars.getSpeed()*10); //Speed Multipled by 10 for clarity
        //System.out.println("[DriveTrain] Right Speed "+rightJaguars.getSpeed()); //uncomment for use with debugging
    }
    
    /**
     * Drive Straight for use with PID Gyro
     * @param speed Speed to drive forward
     */
    public void balanceDrive(double speed) {
        robotDrive.tankDrive(speed, speed);
        SmartDashboard.putDouble("Left Speed", leftJaguars.getSpeed()*-10); //Speed Multipled by 10 for clarity (negative for direction)
        //System.out.println("[DriveTrain] Left Speed "+leftJaguars.getSpeed()); //uncomment for use with debugging
        SmartDashboard.putDouble("Right Speed", rightJaguars.getSpeed()*10); //Speed Multipled by 10 for clarity
        //System.out.println("[DriveTrain] Right Speed "+rightJaguars.getSpeed()); //uncomment for use with debugging
    }
}
