/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.RobotMap;
import com.slidellrobotics.reboundrumble.subsystems.TrackingCamera;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Allister Wright
 */
public class ProcessImage extends CommandBase {
    static int stepNo =1;
    
    private static double lastTime = 0;
    private static double thisTime = 0;
    private static double timeLapse = 0;
    
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
    
    public ProcessImage() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(camera);
        requires(lazySusan);
        requires(leftShootingMotors);
        requires(rightShootingMotors);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        /*try {
            TrackingCamera.pic = camera.getImageFromCamera();
            //Declares pic variable
            //System.out.println("Got image");
            TrackingCamera.totalWidth = TrackingCamera.pic.getWidth();
            TrackingCamera.totalHeight = TrackingCamera.pic.getHeight();

            //System.out.println("Threshold");
            TrackingCamera.thresholdHSL = TrackingCamera.pic.thresholdHSL(150, 185, 244, 255, 2, 20);      //Sets a Blue light threshold
            
            System.out.println("Checkpoint 1");
            
            //System.out.println("Removing Small Objects");
            //TrackingCamera.bigObjectsImage = TrackingCamera.thresholdHSL.removeSmallObjects(false, 1);
            
            //System.out.println("Convex");
            TrackingCamera.convexHullImage = TrackingCamera.thresholdHSL.convexHull(false);        //Fills in the bounding boxes for the targets            

            System.out.println("Checkpoint Alpha");
            
           // System.out.println("Bounding Box Criteria");
            TrackingCamera.boundImage = TrackingCamera.convexHullImage.particleFilter(TrackingCamera.cc);
            
            System.out.println("Checkpoint Bravo");
            
            //TODO: Ordered?
            TrackingCamera.reports = TrackingCamera.boundImage.getOrderedParticleAnalysisReports();        //Sets "reports" to the nuber of particles
            
            System.out.println("Checkpoint Charlie");
            
            System.out.println("Reports: "+TrackingCamera.reports.length);
        } catch (NIVisionException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println("Checkpoint 2");
        
        try {
            if (TrackingCamera.pic != null) {
                TrackingCamera.pic.free();
            } 
            if (TrackingCamera.convexHullImage != null) {
                TrackingCamera.convexHullImage.free();
            } 
            if (TrackingCamera.thresholdHSL != null) {
                TrackingCamera.thresholdHSL.free();
            }
            if (TrackingCamera.boundImage != null) {
                TrackingCamera.boundImage.free();
            }
        } catch (Exception ex) {
            System.out.println("Memory: "+ex);
        }
        System.out.println("Checkpoint 3");
        */
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        thisTime = Timer.getFPGATimestamp();
        timeLapse = thisTime - lastTime;
        
        if(timeLapse >= 1.0) {
            getImage();
            if(stepNo == 6) {
                if(TrackingCamera.reports != null) {
                    selectGoal();
                    findAngle();
                    findDistance();
                } else {    //  If no goals are found
                System.out.println("Goal Selection and Analysis Aborted");  //  Print a notifier
                }
                stepNo++;
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //todo this can just be set to true
        if(stepNo == 7) {
            return true;
        } else if(stepNo > 7) {
            System.out.println("Error Exit");
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        try {
            if (TrackingCamera.pic != null) {
                TrackingCamera.pic.free();
            } 
            if (TrackingCamera.convexHullImage != null) {
                TrackingCamera.convexHullImage.free();
            } 
            if (TrackingCamera.thresholdHSL != null) {
                TrackingCamera.thresholdHSL.free();
            }
            if (TrackingCamera.boundImage != null) {
                TrackingCamera.boundImage.free();
            }
        } catch (Exception ex) {
            System.out.println("Memory: "+ex);
        }
        lastTime = thisTime;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
    
    private void getImage() {
        System.out.println("getImage");
        try {
            switch(stepNo){
            case 1:
                System.out.println("Running Case: 1");
                TrackingCamera.pic = camera.getImageFromCamera();
                System.out.println("Ran Case: 1.1");
                if(TrackingCamera.pic != null) {
                    TrackingCamera.totalWidth = TrackingCamera.pic.getWidth();
                System.out.println("Ran Case: 1.2");
                TrackingCamera.totalHeight = TrackingCamera.pic.getHeight();
                System.out.println("Ran Case: 1.3");
                } else {
                    break;
                }
                stepNo++;
                break;
            case 2:
                System.out.println("Running Case: 2");
                TrackingCamera.thresholdHSL = TrackingCamera.pic.thresholdHSL(150, 185, 244, 255, 2, 20);      //Sets a Blue light threshold
                System.out.println("Ran Case: 2.1");
                stepNo++;
                break;
            case 3:
                System.out.println("Running Case: 3");
                TrackingCamera.convexHullImage = TrackingCamera.thresholdHSL.convexHull(false);        //Fills in the bounding boxes for the targets            
                System.out.println("Ran Case: 3.1");
                stepNo++;
                break;
            case 4:
                System.out.println("Running Case: 4");
                TrackingCamera.boundImage = TrackingCamera.convexHullImage.particleFilter(TrackingCamera.cc);
                System.out.println("Ran Case: 4.1");
                stepNo++;
                break;
            case 5:
                System.out.println("Running Case: 5");
                TrackingCamera.reports = TrackingCamera.boundImage.getOrderedParticleAnalysisReports();
                System.out.println("Ran Case: 5.1");
                System.out.println("Reports: "+TrackingCamera.reports.length);
                System.out.println("Ran Case: 5.2");
                stepNo++;
                break;
            }
        } catch (NIVisionException ex) {
            System.out.println(ex);
            stepNo = 60;
        } catch (Exception ex) {
            System.out.println(ex);
            stepNo = 60;
        }
    }
    
    private void selectGoal() {
        System.out.println("selectGoal");
        TrackingCamera.targetGoal = null;
        
        if (TrackingCamera.reports.length == 0) {
            return;
        } if (TrackingCamera.reports.length == 1) {
            System.out.println("Not enough goals");
            TrackingCamera.targetGoal = TrackingCamera.reports[0];
        } else {
            TrackingCamera.leftGoal = TrackingCamera.reports[0];     //Recognizes the
            TrackingCamera.rightGoal = TrackingCamera.reports[0];    //middle goals.
            int maxIndex = TrackingCamera.reports.length;
            if (maxIndex > 4) {
                maxIndex=4;
            }
            for(int i = 1; i <= maxIndex; i++) {
                if(TrackingCamera.reports[i].center_mass_x < TrackingCamera.leftGoal.center_mass_x) {
                    TrackingCamera.leftGoal = TrackingCamera.reports[i];
                } if(TrackingCamera.reports[i].center_mass_x > TrackingCamera.leftGoal.center_mass_x) {
                    TrackingCamera.rightGoal = TrackingCamera.reports[i];
                }
            }
            //We have four goals in view index 1 is the left and index 2 is right
            double leftWidth = TrackingCamera.leftGoal.boundingRectWidth;     //Finds the widths of
            double rightWidth = TrackingCamera.rightGoal.boundingRectWidth;   //both middle goals.
            if (leftWidth <= rightWidth) {
                TrackingCamera.targetGoal = TrackingCamera.rightGoal;   //Decides which goal we are
            } else {                                                    //closer to and targets it.
                TrackingCamera.targetGoal = TrackingCamera.leftGoal;    //
            }
            System.out.println("Target Selected");
        }
    }
    
    private void findAngle() {
        System.out.println("findAngle");
        if (TrackingCamera.targetGoal == null){
            return;
        }
        TrackingCamera.horCenter = (TrackingCamera.totalWidth / 2);     //Finds the pixel value of the horizontal center
        TrackingCamera.targetLocale = TrackingCamera.targetGoal.center_mass_x;        //Finds the center of our target
        TrackingCamera.targetDiff = Math.abs(TrackingCamera.targetLocale - TrackingCamera.horCenter); // see how far away we are

        //TODO: tune the 10 pixels to the right number
        //there is always going to be a little error, but we want some small window
        //where the lazy suzan stops moving to we can make an accurate shot.

        System.out.println("Targe Diff: "+TrackingCamera.targetDiff);
        if (TrackingCamera.targetDiff < 280 ) {
            if (TrackingCamera.targetDiff < 15) {
                lazySusan.setRelay(RobotMap.susanOff);   //turn off
            } else if (TrackingCamera.targetLocale < TrackingCamera.horCenter) { //and if we are facing right
                lazySusan.setRelay(RobotMap.susanLeft);   //turn left
            } else {                                        //if we face left
                lazySusan.setRelay(RobotMap.susanRight);   //turn right
            }
        }
    }
    
    private void findDistance() {
        System.out.println("findDistance");
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
        
        verticalRattle = Math.abs(TrackingCamera.targetGoal.center_mass_y - (imageHeight/2));   //  Finds the vertical off-ceneterness.
        horizontalRattle = Math.abs(TrackingCamera.targetGoal.center_mass_x - (imageWidth/2));  //  Finds the horizontal off-centerness.
        
        verticalDistanceResult = Math.sqrt(4/3)*(verticalFOV/2)/Math.tan(verticalViewingAngle/2);   //  Provides the Result of our Vertically-Based Calculation.
        horizontalDistanceResult = Math.sqrt(3/4)*(horizontalFOV/2)/Math.tan(horizontalViewingAngle/2); //  Provides the Result of our Horizontally-Based Calculation.
        
        centerDistance = (verticalDistanceResult + horizontalDistanceResult) / 2;   //  Take the average to try get a more accurate measurement.
        
        
        offCenterPixels = Math.sqrt((verticalRattle*verticalRattle) + (horizontalRattle*horizontalRattle)); //  Finds the Linear Distance from the Center of the Image to the Center of the Goal.
        offCenterFt = offCenterPixels*(Math.sqrt((verticalFOV*verticalFOV)+(horizontalFOV*horizontalFOV))); //  Converts the above Caluclated measurement into its proper Ft value.
        
        trueDistance = Math.sqrt((centerDistance*centerDistance)+(offCenterFt*offCenterFt));    //  Find the Linear Distance form the Lens of our Camera to the Center of our Goal.
        
        
        //if distance to target is invalid, just set it to some number
        if (TrackingCamera.distanceToTarget > 60 || TrackingCamera.distanceToTarget <= 0) {
            TrackingCamera.distanceToTarget = 60;
        }
        
        d = trueDistance;    //  See below Calculation for conciseness
        
        TrackingCamera.launchSpeed = 60 * (d / Math.sqrt((11 / 6 - d) / -16.1) / (2 / 3 * pi));  //Calcs the required rpms for firing
        leftShootingMotors.setSetpoint(TrackingCamera.launchSpeed);     //  Sets the shooting Left Shooting Motors
        rightShootingMotors.setSetpoint(TrackingCamera.launchSpeed);    //  Sets the Right Shooting Motors

        /* A String of Debug Print Statements */
        System.out.println();
        System.out.println("Vertcal Distance Result: "+verticalDistanceResult);
        System.out.println("Horizontal Distance Result: "+horizontalDistanceResult);
        System.out.println("Central Distance: "+centerDistance);
        System.out.println("True Distance: "+d);
        System.out.println("Camera Launch Speed: "+TrackingCamera.launchSpeed);
        System.out.println();
        
        SmartDashboard.putDouble("Vertical Distance Result", verticalDistanceResult);
        SmartDashboard.putDouble("Horizontal Distance Result", horizontalDistanceResult);
        SmartDashboard.putDouble("Center Point Distance", centerDistance);
        SmartDashboard.putDouble("Distance", d);
        SmartDashboard.putDouble("Camera Launch Speed", TrackingCamera.launchSpeed);
    }
}
