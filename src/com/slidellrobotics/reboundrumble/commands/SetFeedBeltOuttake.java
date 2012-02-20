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
public class SetFeedBeltOuttake extends CommandBase {
    private Value startState;
    
    
    public SetFeedBeltOuttake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(feedBelt);
        startState = feedBelt.getState();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        startState = feedBelt.getState();
        if(startState.equals(Value.kOff)) {
            feedBelt.setOuttake();
        } else {
            feedBelt.setStopped();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(!feedBelt.getState().equals(startState)) {
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
