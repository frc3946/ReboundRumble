/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author 10491477
 */
public class FindAngle extends CommandBase {
    private double targetWidth;                 //
    private double totalWidth;                  //
    private double horFOV;                      //  
    private double targetLocale;                //  Variable Constructors
    private double horCenter;                   //
    private Relay susanRelay;                   //
    ParticleAnalysisReport targetGoal;          //
    
    public FindAngle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(camera);
        requires(lazySusan);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        horCenter = (totalWidth/2);     //Finds the pixel value of the horizontal center
        targetLocale = targetGoal.center_mass_x;        //Finds the center of our target
        while(targetLocale != horCenter) {              //While we are not aimed at the goal
            if(targetLocale > horCenter) {                  //and if we are facing right
                lazySusan.setRelay(Relay.Value.kReverse);   //turn left
            } else {                                        //if we face left
                lazySusan.setRelay(Relay.Value.kForward);   //turn right
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;           //never finsihed
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
