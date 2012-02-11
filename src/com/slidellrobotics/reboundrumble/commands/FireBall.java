/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 *
 * @author gixxy
 */
public class FireBall extends CommandBase {
    
    public FireBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(firePiston);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        firePiston.fire();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(firePiston.get() == Value.kForward) {
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        firePiston.stow();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
