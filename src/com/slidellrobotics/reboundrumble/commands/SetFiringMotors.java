/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

/**
 *
 * @author Gus Michel
 */
public class SetFiringMotors extends CommandBase {
    int setup;
    
    public SetFiringMotors(int num) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        setup = num;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if(setup == 1) {
            leftShootingMotors.setSetpoint(1000);
        } else {
            leftShootingMotors.setSetpoint(2000);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
