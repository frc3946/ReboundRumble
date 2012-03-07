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
    double tgtHght = TrackingCamera.targetHeight;           //  Create a few necesarry local variables
    double tgtWdth = TrackingCamera.targetWidth;            //  for concise code and calcs.
    double tgtHghtFt = TrackingCamera.targetHeightFeet; //  Create a few
    double tgtWdthFt = TrackingCamera.targetWidthFeet;  //  lcoal variables
    double ttlHght = TrackingCamera.totalHeight;        //  purely for
    double ttlWdth = TrackingCamera.totalWidth;         //  more concise
                                                        //  lines
    double vertFOV = TrackingCamera.vertFOV;            //
    double horFOV = TrackingCamera.horFOV;              //
    double vertVA = TrackingCamera.cameraVertFOV;       //
    double horVA = TrackingCamera.cameraHorizFOV;       //
    
    double leftRight = 0;   //
    double upDown = 0;      //  A few newer 
    double wdth1Px = 0;     //  variables to
    double hght1Px = 0;     //  allow for more
    double horThet1 = 0;    //  accurate distances
    double vertThet1 = 0;   //
    
    double d = 0;           //  A small calc variable
    double pi = Math.PI;
    
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
        
        tgtHght = TrackingCamera.targetGoal.boundingRectHeight; //  Sets the height of our target.
        tgtHghtFt = 1.5;                                        //  Defines goal's constant ft height
        vertFOV = tgtHghtFt / tgtHght * ttlHght;                //  Gets the foot equivalent of our vertical Field of View

        vertVA = 47*pi/180;    //  Defines the Viewing
        horVA = 47*pi/180;     //  Angles of our camera

        tgtWdth = TrackingCamera.targetGoal.boundingRectWidth;  //  Sets the width of our target.
        tgtWdthFt = 2.0;                                        //  Defines goal's constant ft width
        horFOV = tgtWdthFt / tgtWdth * ttlWdth;                 //  Gets the ft value of our horizontal Field of View

        leftRight = Math.abs(TrackingCamera.targetGoal.center_mass_x - (ttlHght/2));    //  Finds the horizontal off-centerness
        upDown = Math.abs(TrackingCamera.targetGoal.center_mass_y - (ttlWdth/2));       //  Finds the vertical off-ceneterness
        
        wdth1Px = (ttlHght/2) - leftRight;  //  Defines the distance from the sides of our view and the center of the goal
        hght1Px = (ttlWdth/2) - upDown;     //  Defines the distance from the top / bottom of our view in Pixels.
        
        horThet1 = horVA * wdth1Px/ttlWdth;     //  Finds the angle from Horizontal Edge<>camera<>center of goal
        vertThet1 = vertVA * hght1Px/ttlHght;   //  Finds the angle from Vertical Edge<>camera<>center of goal
        
        //TrackingCamera.targetGoal.center_mass_x * TrackingCamera.horFOV / TrackingCamera.totalWidth;
        
        TrackingCamera.d1 = (vertFOV / 2) / Math.tan(vertThet1);    //  Gets a distance from the center of our goal using Horizontal Theta
        TrackingCamera.d2 = (horFOV / 2) / Math.tan(horThet1);      //  Double checks distance with a Vertcial Theta

        TrackingCamera.distanceToTarget = (TrackingCamera.d1 + TrackingCamera.d2) / 2;  //  Take the average to try get a more accurate measurement
        //if distance to target is invalid, justset it to some number
        if (TrackingCamera.distanceToTarget > 60 || TrackingCamera.distanceToTarget <= 0)
            TrackingCamera.distanceToTarget = 60;
        
        d = TrackingCamera.distanceToTarget;    //  Calc Conciseness

        TrackingCamera.launchSpeed = 60 * (d / Math.sqrt((11 / 6 - d) / -16.1) / (2 / 3 * pi));  //Calcs the required rpms for firing
        leftShootingMotors.setSetpoint(TrackingCamera.launchSpeed);     //  Sets the shooting Left Shooting Motors
        rightShootingMotors.setSetpoint(TrackingCamera.launchSpeed);    //  Sets the Right Shooting Motors
        
        System.out.println();
        System.out.println("D1: "+TrackingCamera.d1);
        System.out.println("D2: "+TrackingCamera.d2);
        System.out.println("D: "+d);
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
