/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.subsystems.TrackingCamera;

/**
 *
 * @author Allister Wright
 */
public class FindDistance extends CommandBase {  
    double targetHeight = TrackingCamera.targetHeight;   //  Create a few necesarry local variables
    double targetWidth = TrackingCamera.targetWidth;    //  for concise code and calcs.
    
    double targetHeightFt;   //  Target Height in Feet
    double targetWidthFt;   //  Target Width in Feet
    double imageHeight; //  Total Height in Pixels
    double imageWidth; //  Total Width imn Pixels
                      
    double verticalFOV; //  Vertical Field of View in Feet
    double horizontalFOV;  //  Horizontal Field of View in Feet
    double verticalViewingAngle;  //  Vertical Camera Viewing Angle
    double horizontalViewingAngle;   //  Horizontal Camera Viewing Angle
    
    double horizontalRattle;   //  Horizontal off-centerness of center of goal
    double verticalRattle;  //  Vertical off-centerness of center of goal
    
    double centerDistance = 0;   //  Distance Variable to be used in firing Calculation
    double offCenterPixels = 0;
    double offCenterFt = 0;
    double verticalDistanceResult = 0;
    double horizontalDistanceResult = 0;
    double trueDistance = 0;
    double d = 0;
    double pi = 3.14159262;
    
    public FindDistance() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(leftShootingMotors);   //  Sets requires for Left Shooting Motors
        requires(rightShootingMotors);  //  Sets requires for Right hooting Motors
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        imageHeight = TrackingCamera.totalHeight;  //  Target Height from the Tracking Camera's static variable
        imageWidth = TrackingCamera.totalWidth;   //  Target Width from the Tracking Camera's static variable
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (TrackingCamera.targetGoal == null){ //  If no target is found
            leftShootingMotors.setSetpoint(1000);   //  Set Left shooting Motors to about Half Speed
            rightShootingMotors.setSetpoint(1000);  //  Set Right Shooting Motors to about Half Speed
            System.out.println("No target set");    //  Debug Print Statement
            
            System.out.println("Checkpoint 10a");
            
            return;
        }
        
        verticalViewingAngle = 47;      //  Defines the Viewing
        horizontalViewingAngle = 47;    //  Angles of our camera
        
        imageHeight = 480;  //  Image Height
        targetHeight = TrackingCamera.targetGoal.boundingRectHeight;    //  Sets the height of our target.
        targetHeightFt = 1.5;   //  Defines goal's constant ft height
        
        imageWidth = 640;   //  Image Width
        targetWidth = TrackingCamera.targetGoal.boundingRectWidth;  //  Sets the width of our target.
        targetWidthFt = 2.0;    //  Defines goal's constant ft width
        
        verticalFOV = imageHeight*(targetHeightFt/targetHeight);    //  Gets the Foot Value of our Vertical Field of View.
        horizontalFOV = imageWidth*(targetWidthFt/targetWidth); //  Gets the ft value of our horizontal Field of View.
        
        horizontalRattle = Math.abs(TrackingCamera.targetGoal.center_mass_x - (imageWidth/2));  //  Finds the horizontal off-centerness.
        verticalRattle = Math.abs(TrackingCamera.targetGoal.center_mass_y - (imageHeight/2));   //  Finds the vertical off-ceneterness.
        
        verticalDistanceResult = Math.sqrt(4/3)*(verticalFOV/2)/Math.tan(verticalViewingAngle/2);   //  Provides the Result of our Vertically-Based Calculation.
        horizontalDistanceResult = Math.sqrt(3/4)*(horizontalFOV/2)/Math.tan(horizontalViewingAngle/2); //  Provides the Result of our Horizontally-Based Calculation.
        
        centerDistance = (verticalDistanceResult + horizontalDistanceResult) / 2;   //  Take the average to try get a more accurate measurement.
        
        
        offCenterPixels = Math.sqrt((verticalRattle*verticalRattle) + (horizontalRattle*horizontalRattle)); //  Finds the Linear Distance from the Center of the Image to the Center of the Goal.
        offCenterFt = offCenterPixels*(Math.sqrt((verticalFOV*verticalFOV)+(horizontalFOV*horizontalFOV))); //  Converts the above Caluclated measurement into its proper Ft value.
        
        trueDistance = Math.sqrt((centerDistance*centerDistance)+(offCenterFt*offCenterFt));    //  Find the Linear Distance form the Lens of our Camera to the Center of our Goal.
        
        
        //if distance to target is invalid, just set it to some number
        if (TrackingCamera.distanceToTarget > 60 || TrackingCamera.distanceToTarget <= 0)
            TrackingCamera.distanceToTarget = 60;
        
        d = trueDistance;    //  See below Calculation for conciseness
        
        System.out.println("Checkpoint 13");

        TrackingCamera.launchSpeed = 60 * (d / Math.sqrt((11 / 6 - d) / -16.1) / (2 / 3 * pi));  //Calcs the required rpms for firing
        leftShootingMotors.setSetpoint(TrackingCamera.launchSpeed);     //  Sets the shooting Left Shooting Motors
        rightShootingMotors.setSetpoint(TrackingCamera.launchSpeed);    //  Sets the Right Shooting Motors
        
        System.out.println("Checkpoint 14");
        
        /* A String of Debug Print Statements */
        System.out.println();
        System.out.println("Vertcal Distance Result: "+verticalDistanceResult);
        System.out.println("Horizontal Distance Result: "+horizontalDistanceResult);
        System.out.println("Central Distance: "+centerDistance);
        System.out.println("True Distance: "+d);
        System.out.println("Camera Launch Speed: "+TrackingCamera.launchSpeed);
        System.out.println();
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
