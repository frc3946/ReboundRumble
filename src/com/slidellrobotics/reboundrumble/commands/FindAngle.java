/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.subsystems.TrackingCamera;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author 10491477
 */
public class FindAngle extends CommandBase {
    public FindAngle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(lazySusan);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (TrackingCamera.targetGoal == null){
            lazySusan.setRelay(Relay.Value.kOff);   //turn off
            SmartDashboard.putString("LazySusan", "Off");
            System.out.println("No target set");
            return;
        }
        
        TrackingCamera.horCenter = (TrackingCamera.totalWidth / 2);     //Finds the pixel value of the horizontal center
        TrackingCamera.targetLocale = TrackingCamera.targetGoal.center_mass_x;        //Finds the center of our target
        TrackingCamera.targetDiff = Math.abs(TrackingCamera.targetLocale - TrackingCamera.horCenter); // see how far away we are

        //TODO: tune the 10 pixels to the right number
        //there is always going to be a little error, but we want some small window
        //where the lazy suzan stops moving to we can make an accurate shot.

        if (TrackingCamera.targetDiff < 50) {
            lazySusan.setRelay(Relay.Value.kOff);   //turn off
            SmartDashboard.putString("LazySusan", "Off");
        } else if (TrackingCamera.targetLocale > TrackingCamera.horCenter) {                  //and if we are facing right
            lazySusan.setRelay(Relay.Value.kReverse);   //turn left
            SmartDashboard.putString("LazySusan", "Reverse");
        } else {                                        //if we face left
            lazySusan.setRelay(Relay.Value.kForward);   //turn right
            SmartDashboard.putString("LazySusan", "Forward");
        }
        //TrackingCamera.angleFinished = true;
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
