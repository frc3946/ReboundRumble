/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands.AutonomousCommands;

import com.slidellrobotics.reboundrumble.commands.CommandBase;
import com.slidellrobotics.reboundrumble.subsystems.TrackingCamera;

/**
 *
 * @author 10491477
 */
public class AutoAim extends CommandBase {
    
    public AutoAim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        //requires(lazySusan);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        camera.initDefaultCommand();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(TrackingCamera.targetDiff < 10) {
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
