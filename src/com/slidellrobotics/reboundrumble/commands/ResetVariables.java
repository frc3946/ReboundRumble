/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;

/**
 *
 * @author 10491477
 */
public class ResetVariables extends CommandBase {
    ColorImage pic;
    BinaryImage convexHullImage;
    BinaryImage thresholdHSL;
    
    public ResetVariables() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //need to free memory on all pic variables
        //TODO: does not free variables from 
        try {
            if (pic != null) {
                pic.free();
            } 
            if (convexHullImage != null) {
                convexHullImage.free();
            } 
            if (thresholdHSL != null) {
                thresholdHSL.free();
            }
        } catch (Exception ex) {
            System.out.println(ex);
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
    }
}
