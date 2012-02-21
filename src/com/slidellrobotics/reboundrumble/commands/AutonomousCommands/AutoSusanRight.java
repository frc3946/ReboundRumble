/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands.AutonomousCommands;

import com.slidellrobotics.reboundrumble.commands.CommandBase;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author 10491477
 */
public class AutoSusanRight extends CommandBase {
    private Value right, stop;   
    
    public AutoSusanRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(lazySusan);
        right = Relay.Value.kForward;
        stop = Relay.Value.kOff;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        lazySusan.setRelay(right);
        Timer.delay(4);
        lazySusan.setRelay(stop);
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
