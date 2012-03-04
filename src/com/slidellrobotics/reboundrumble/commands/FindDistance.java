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
public class FindDistance extends CommandBase {  
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
        if (TrackingCamera.targetGoal == null){
            leftShootingMotors.setSetpoint(1000);
            rightShootingMotors.setSetpoint(1000);            
            System.out.println("No target set");
            return;
        }
        
        TrackingCamera.targetHeight = TrackingCamera.targetGoal.boundingRectHeight;   //Sets the height of our target.
        TrackingCamera.targetHeightFeet = 1.5;
        TrackingCamera.vertFOV = TrackingCamera.targetHeightFeet / TrackingCamera.targetHeight * TrackingCamera.totalHeight; // //Gets the foot equivalent of our vertical Field of View

        TrackingCamera.cameraVertFOV = 47;
        TrackingCamera.cameraHorizFOV = 47;

        TrackingCamera.targetWidth = TrackingCamera.targetGoal.boundingRectWidth;   //Sets the height of our target.
        TrackingCamera.targetWidthFeet = 2.0;
        TrackingCamera.horFOV = TrackingCamera.targetWidthFeet / TrackingCamera.targetWidth * TrackingCamera.totalWidth;


        TrackingCamera.d1 = (TrackingCamera.vertFOV / 2) / Math.tan(TrackingCamera.cameraVertFOV / 2);
        TrackingCamera.d2 = (TrackingCamera.horFOV / 2) / Math.tan(TrackingCamera.cameraHorizFOV / 2);

        TrackingCamera.distanceToTarget = (TrackingCamera.d1 + TrackingCamera.d2) / 2;  //take the average to try get a more accurate measurement
        //if distance to target is invalid, justset it to some number
        if (TrackingCamera.distanceToTarget > 60 || TrackingCamera.distanceToTarget <= 0)
            TrackingCamera.distanceToTarget = 60;
        
        TrackingCamera.d = TrackingCamera.distanceToTarget;

        TrackingCamera.launchSpeed = 60 * (TrackingCamera.d / Math.sqrt(((11 / 6) - TrackingCamera.d) / -16.1) / ((2 / 3) * 3.1415926));  //Calcs the required rpms for firing
        leftShootingMotors.setSetpoint(TrackingCamera.launchSpeed);
        rightShootingMotors.setSetpoint(TrackingCamera.launchSpeed);
        
        System.out.println();
        System.out.println("D1: "+TrackingCamera.d1);
        System.out.println("D2: "+TrackingCamera.d2);
        System.out.println("D: "+TrackingCamera.d);
        System.out.println("Camera Launch Speed: "+TrackingCamera.launchSpeed);
        System.out.println();
        
        //TrackingCamera.distanceFinished = true;
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
