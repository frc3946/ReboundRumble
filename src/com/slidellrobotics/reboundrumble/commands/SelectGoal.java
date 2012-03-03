/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.subsystems.TrackingCamera;

/**
 *
 * @author 10491477
 */
public class SelectGoal extends CommandBase {
    public SelectGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    //int lgi = TrackingCamera.leftGoalIndex;
    //int rgi = TrackingCamera.rightGoalIndex;
    
    // Called just before this Command runs the first time
    protected void initialize() {
        TrackingCamera.targetGoal = null;
        
        if (TrackingCamera.reports == null) {
             System.out.println("No image reports");
            return;
        }
        
        //TODO set to 4 for comp
        if (TrackingCamera.reports.length < 3) {
            if(TrackingCamera.reports.length > 0) {
                System.out.println("Not enough goals");
                TrackingCamera.targetGoal = TrackingCamera.reports[0];
            }
        } else {
            TrackingCamera.leftGoal = TrackingCamera.reports[0];     //Recognizes the
            TrackingCamera.rightGoal = TrackingCamera.reports[0];    //middle goals.
            
            for(int i = 1; i < 4; i++) {
                if(TrackingCamera.reports[i].center_mass_x < TrackingCamera.leftGoal.center_mass_x) {
                    TrackingCamera.leftGoal = TrackingCamera.reports[i];
                }
                if(TrackingCamera.reports[i].center_mass_x > TrackingCamera.leftGoal.center_mass_x) {
                    TrackingCamera.rightGoal = TrackingCamera.reports[i];
                }
            }
            
            //we we have four goals in view index 1 is the left and index 2 is right
            
            double leftWidth = TrackingCamera.leftGoal.boundingRectWidth;     //Finds the widths of
            double rightWidth = TrackingCamera.rightGoal.boundingRectWidth;   //both middle goals.
            if (leftWidth <= rightWidth) {        //
                TrackingCamera.targetGoal = TrackingCamera.rightGoal;             //Decides which goal we are
            } if (rightWidth > leftWidth) {        //closer to and targets it.
                TrackingCamera.targetGoal = TrackingCamera.leftGoal;              //
            }
        }
        //TrackingCamera.selectFinished = true;
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
