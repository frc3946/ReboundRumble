/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import edu.wpi.first.wpilibj.ADXL345_I2C.Axes;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author gixxy
 */
public class FindPosition extends CommandBase {
    
    public FindPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(pAccelerometer); //p = positioning
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putDouble("Accelerometer X", pAccelerometer.getPosition(Axes.kX));
        SmartDashboard.putDouble("Accelerometer Y", pAccelerometer.getPosition(Axes.kY));
        SmartDashboard.putDouble("Accelerometer Z", pAccelerometer.getPosition(Axes.kZ));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
