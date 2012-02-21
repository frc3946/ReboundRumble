/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import edu.wpi.first.wpilibj.Relay.Value;

/**
 *
 * @author Gus Michel
 */
public class LazySusanLeft extends CommandBase {
    
    public LazySusanLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(leftShootingMotors);
        requires(rightShootingMotors);
        requires(camera);
        requires(lazySusan);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        lazySusan.setRelay(Value.kReverse);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
        lazySusan.setRelay(Value.kOff);
    }
}
