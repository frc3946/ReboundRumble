/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import com.slidellrobotics.reboundrumble.commands.TankDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author gixxy
 */
public class DriveTrain extends Subsystem {
    private Jaguar leftJaguar;
    private Jaguar rightJaguar;
    private RobotDrive robotDrive;
    
    public DriveTrain() {
        leftJaguar = new Jaguar(RobotMap.leftMotor);
        rightJaguar = new Jaguar(RobotMap.rightMotor);
        robotDrive = new RobotDrive(leftJaguar, rightJaguar);
        System.out.println("Drive Train Init");
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new TankDrive());
    }
    
    public void drive(double leftSpeed, double rightSpeed) {
        robotDrive.tankDrive(leftSpeed, rightSpeed);
    }
    
    public double getLeftSpeed() {
        return leftJaguar.getSpeed() * -1; //motors are inverted. *-1 is needed to change it to proper speed representation
    }
    
    public double getRightSpeed() {
        return rightJaguar.getSpeed();
    }
    
    public double getRawLeftSpeed() {
        return leftJaguar.getSpeed();
    }
    
    public double getRawRightSpeed() {
        return rightJaguar.getSpeed();
    }
}