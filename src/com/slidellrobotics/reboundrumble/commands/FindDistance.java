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
public class FindDistance extends CommandBase {
    double totalWidth = 0;
    double totalHeight = 0;
    double launchSpeed=0;
    double distanceToTarget=0;
    ParticleAnalysisReport targetGoal = null;
        
    public FindDistance() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(leftShootingMotors);
        requires(rightShootingMotors);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (targetGoal == null){
            leftShootingMotors.setSetpoint(100);
            rightShootingMotors.setSetpoint(100);
            System.out.println("Setpoint is: " + rightShootingMotors.getSetpoint());
            System.out.println("No target set");
            return;
        }
        
        double targetHeight = targetGoal.boundingRectHeight;   //Sets the height of our target.
        double targetHeightFeet = 1.5;
        double vertFOV = targetHeightFeet / targetHeight * totalHeight; // //Gets the foot equivalent of our vertical Field of View

        double camearVerticalFOV = 47;
        double cameraHorizontalFOV = 47;

        double targetWidth = targetGoal.boundingRectWidth;   //Sets the height of our target.
        double targetWidthFeet = 2.0;
        double horFOV = targetWidthFeet / targetWidth * totalWidth;


        double d1 = (vertFOV / 2) / Math.tan(camearVerticalFOV / 2);
        double d2 = (horFOV / 2) / Math.tan(cameraHorizontalFOV / 2);

        distanceToTarget = (d1 + d2) / 2;  //take the average to try get a more accurate measurement
        //if distance to target is invalid, justset it to some number
        if (distanceToTarget > 60 || distanceToTarget <= 0)
            distanceToTarget = 60;
        
        double d = distanceToTarget;

        launchSpeed = 60 * (d / Math.sqrt(((11 / 6) - d) / -16.1) / ((2 / 3) * 3.1415926));  //Calcs the required rpms for firing
        leftShootingMotors.setSetpoint(launchSpeed);
        rightShootingMotors.setSetpoint(launchSpeed);
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
