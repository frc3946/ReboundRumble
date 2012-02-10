/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

/**
 *
 * @author gixxy
 */
public class SetSpeedBelt extends CommandBase {
    private boolean state = false;
    private boolean previousState = true;
    
    
    public SetSpeedBelt() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(feedBelt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        state = !state;
        feedBelt.setBelt(state);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(state != previousState) {
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        previousState = state;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
