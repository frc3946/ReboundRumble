/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 *
 * @author Gus Michel
 */
public class SusanPIDToggle extends CommandBase {
    
    public SusanPIDToggle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(camera);
        requires(lazySusan);
        requires(leftShootingMotors);
        requires(rightShootingMotors);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(lazySusan.isEnabled()) {
            lazySusan.disable();
            lazySusan.setRelay(RobotMap.susanOff);
        } else {
            lazySusan.setSetpoint(lazySusan.getGyro().getAngle());
            lazySusan.enable();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
