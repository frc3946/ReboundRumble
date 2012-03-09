/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.subsystems.TrackingCamera;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Allister Wright
 */
public class FindAngle extends CommandBase {
    private double lastTime = 0;
    private double thisTime;
    private double timeLapse;
    
    public FindAngle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        //requires(lazySusan);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (TrackingCamera.targetGoal == null){
            //lazySusan.setRelay(Relay.Value.kOff);   //turn off
            SmartDashboard.putString("LazySusan", "Off");
            //System.out.println("No target set");
            return;
        }
        
        System.out.println("Checkpoint 8");
        
        TrackingCamera.horCenter = (TrackingCamera.totalWidth / 2);     //Finds the pixel value of the horizontal center
        TrackingCamera.targetLocale = TrackingCamera.targetGoal.center_mass_x;        //Finds the center of our target
        TrackingCamera.targetDiff = Math.abs(TrackingCamera.targetLocale - TrackingCamera.horCenter); // see how far away we are

        //TODO: tune the 10 pixels to the right number
        //there is always going to be a little error, but we want some small window
        //where the lazy suzan stops moving to we can make an accurate shot.

        lastTime = Timer.getFPGATimestamp();
        System.out.println("Targe Diff: "+TrackingCamera.targetDiff);
        if (TrackingCamera.targetDiff < 50 ) {
            if (TrackingCamera.targetDiff < 15) {
                lazySusan.setRelay(Relay.Value.kOff);   //turn off
                
                System.out.println("Checkpoint 9a");
                
               // SmartDashboard.putString("LazySusan", "Off");
            } else if (TrackingCamera.targetLocale > TrackingCamera.horCenter) {                  //and if we are facing right
                lazySusan.setRelay(Relay.Value.kForward);   //turn left
                
                System.out.println("Checkpoint 9b");
                
               // SmartDashboard.putString("LazySusan", "Right");

            } else {                                        //if we face left
                lazySusan.setRelay(Relay.Value.kReverse);   //turn right
                
                System.out.println("Checkpoint 9c");
                
               // SmartDashboard.putString("LazySusan", "Left");
            }
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //TrackingCamera.angleFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        //lazySusan.setRelay(Relay.Value.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
