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
       // requires(leftShootingMotors);
        //requires(rightShootingMotors);
        //requires(camera);
       // requires(lazySusan);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //lazySusan.setRelay(Value.kReverse);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("third");
         if(oi.thirdJoystick.getX() < -.5) {
            CommandBase.lazySusan.disable();
            CommandBase.lazySusan.setRelay(Value.kForward);
            System.out.println("3rd lazy forward");
        } else if(oi.thirdJoystick.getX() > .5) {
            CommandBase.lazySusan.disable();
            CommandBase.lazySusan.setRelay(Value.kReverse);
            System.out.println("3rd lazy reverse");
        } else {
            CommandBase.lazySusan.enable();
        }
        
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
