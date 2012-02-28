/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author 10491477
 */
public class SelectGoal extends CommandBase {
    ParticleAnalysisReport targetGoal;
    ParticleAnalysisReport[] reports;
    ParticleAnalysisReport leftGoal;    
    ParticleAnalysisReport rightGoal;  
        
    public SelectGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //TODO: this does not really need to execute over and over again. move to iniialize
        //reset target goal
        targetGoal = null;
        
        if (reports == null) {
             System.out.println("No image reports");
            return;
        }
        
        //TODO set to 4 for comp
        if (reports.length < 3) {
            if(reports.length > 0) {
                System.out.println("Not enough goals");
                targetGoal = reports[0];
            }
        } else {
            //we we have four goals in view index 1 is the left and index 2 is right
            leftGoal = reports[1];     //Recognizes the
            rightGoal = reports[2];    //middle goals.
            double leftWidth = leftGoal.boundingRectWidth;     //Finds the widths of
            double rightWidth = rightGoal.boundingRectWidth;   //both middle goals.
            if (leftWidth <= rightWidth) {        //
                targetGoal = rightGoal;             //Decides which goal we are
            } if (rightWidth > leftWidth) {        //closer to and targets it.
                targetGoal = leftGoal;              //
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //TODO: Does not exit, this should be true
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
