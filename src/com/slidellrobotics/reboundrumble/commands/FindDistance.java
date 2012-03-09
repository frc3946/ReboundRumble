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
    double tgtHght = TrackingCamera.targetHeight;   //  Create a few necesarry local variables
    double tgtWdth = TrackingCamera.targetWidth;    //  for concise code and calcs.
    
    double tgtHghtFt;   //  Target Height in Feet
    double tgtWdthFt;   //  Target Width in Feet
    double ttlHght; //  Total Height in Pixels
    double ttlWdth; //  Total Width imn Pixels
                      
    double vertFOV; //  Verticle Field of View in Feet
    double horFOV;  //  Horizontal Field of View in Feet
    double vertVA;  //  Verticle Camera Viewing Angle
    double horVA;   //  Horizontal Camera Viewing Angle
    
    double leftRight;   //  Horizontal off-centerness of center of goal
    double upDown;  //  Verticle off-centerness of center of goal
    double wdth1Px; //  Distance from the center of a Goal to the nearest Horizontal edge
    double hght1Px; //  Distance from the center of a Goal to the nearest Verticle edge
    double horTheta1;   //  Horizontal Angle from the Edge to Camera to center of Goal
    double vertTheta1;  //  Verticle Angle from the Edge to Camera to center of Goal
    double horHypot;    //  Length in feet from camera to Horizontal Edge of Field of View
    double vertHypot;   //  Length in feet from camera to Verticle Edge of Field of View
    
    double d = 0;   //  Distance Variable to be used in firing Calculation
    double pi = 3.1415926;  //  Slightly shorter version using Pi
    
    public FindDistance() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(leftShootingMotors);   //  Sets requires for Left Shooting Motors
        requires(rightShootingMotors);  //  Sets requires for Right hooting Motors
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        ttlHght = TrackingCamera.totalHeight;  //  Target Height from the Tracking Camera's static variable
        ttlWdth = TrackingCamera.totalWidth;   //  Target Width from the Tracking Camera's static variable
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
        ttlHght = 480;  //  Image Height
        ttlWdth = 640;  //  Image Width
        tgtHght = TrackingCamera.targetGoal.boundingRectHeight; //  Sets the height of our target.
        tgtHghtFt = 1.5;    //  Defines goal's constant ft height
        vertFOV = tgtHghtFt / tgtHght * ttlHght;    //  Gets the Foot Value of our Verticle Field of View

        vertVA = 47;    //  Defines the Viewing
        horVA = 47;     //  Angles of our camera
        
        System.out.println("Checkpoint 10b");

        tgtWdth = TrackingCamera.targetGoal.boundingRectWidth;  //  Sets the width of our target.
        tgtWdthFt = 2.0;    //  Defines goal's constant ft width
        horFOV = tgtWdthFt / tgtWdth * ttlWdth; //  Gets the ft value of our horizontal Field of View

        leftRight = Math.abs(TrackingCamera.targetGoal.center_mass_x - (ttlWdth/2));    //  Finds the horizontal off-centerness
        upDown = Math.abs(TrackingCamera.targetGoal.center_mass_y - (ttlHght/2));   //  Finds the Verticle off-ceneterness
        
        wdth1Px = (ttlWdth/2) - leftRight;  //  Defines the distance from the Horizontal Edge to center of Goal in Pixels
        hght1Px = (ttlHght/2) - upDown; //  Defines the distance from the Verticle Edge to center of Goal in Pixels
        
        System.out.println("Checkpoint 11");
        
        horHypot = (horFOV/2 /23.5);    //  Finds the Distance from our Camera to the Horizontal Edge of our Field of View
        vertHypot = (vertFOV/2 /23.5);  //  Finds the Distance from our Camera to the Verticle Edge of our Field of View
        
        horTheta1 = (wdth1Px / horHypot);   //  Finds the angle from Horizontal Edge<>camera<>center of goal
        vertTheta1 = (hght1Px / vertHypot); //  Finds the angle from Verticle Edge<>camera<>center of goal
        
        TrackingCamera.d1 = (hght1Px) / Math.toDegrees(Math.tan(vertTheta1));   //  Gets a distance from the center of our goal using Horizontal Theta
        TrackingCamera.d2 = (wdth1Px) / Math.toDegrees(Math.tan(horTheta1));    //  Double checks distance with a Vertcial Theta
        
        System.out.println("Checkpoint 12");

        TrackingCamera.distanceToTarget = (TrackingCamera.d1 + TrackingCamera.d2) / 2;  //  Take the average to try get a more accurate measurement
        
        //if distance to target is invalid, just set it to some number
        if (TrackingCamera.distanceToTarget > 60 || TrackingCamera.distanceToTarget <= 0)
            TrackingCamera.distanceToTarget = 60;
        
        d = TrackingCamera.distanceToTarget;    //  See below Calculation for conciseness
        
        System.out.println("Checkpoint 13");

        TrackingCamera.launchSpeed = 60 * (d / Math.sqrt((11 / 6 - d) / -16.1) / (2 / 3 * pi));  //Calcs the required rpms for firing
        leftShootingMotors.setSetpoint(TrackingCamera.launchSpeed);     //  Sets the shooting Left Shooting Motors
        rightShootingMotors.setSetpoint(TrackingCamera.launchSpeed);    //  Sets the Right Shooting Motors
        
        System.out.println("Checkpoint 14");
        
        /* A String of Debug Print Statements */
        System.out.println();
        System.out.println("Vertcal Distance Result: "+TrackingCamera.d1);
        System.out.println("Horizontal Distance Result: "+TrackingCamera.d2);
        System.out.println("Average Distance: "+d);
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
