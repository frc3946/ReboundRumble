/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.RobotMap;

/**
 *
 * @author Gus Michel
 */
public class LazySusanRight extends CommandBase {
    
    public LazySusanRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
       requires(camera);
       requires(lazySusan);
       requires(leftShootingMotors);
       requires(rightShootingMotors);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        lazySusan.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        lazySusan.getSpike().set(RobotMap.susanRight);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        lazySusan.getSpike().set(RobotMap.susanOff);
        lazySusan.enable();
        lazySusan.setSetpoint(lazySusan.getGyro().getAngle());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
