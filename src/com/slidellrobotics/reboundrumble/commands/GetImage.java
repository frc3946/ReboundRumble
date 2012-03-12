/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.subsystems.TrackingCamera;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 *
 * @author Allister Wright
 */
public class GetImage extends CommandBase {
    static int stepNo =1;
    public GetImage() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        stepNo = 1;
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
        try {
            switch(stepNo){
            case 1:
                TrackingCamera.pic = camera.getImageFromCamera();
                TrackingCamera.totalWidth = TrackingCamera.pic.getWidth();
                TrackingCamera.totalHeight = TrackingCamera.pic.getHeight();
                stepNo++;
                break;
            case 2:
                TrackingCamera.thresholdHSL = TrackingCamera.pic.thresholdHSL(150, 185, 244, 255, 2, 20);      //Sets a Blue light threshold
                stepNo++;
                break;
            case 3:
                TrackingCamera.convexHullImage = TrackingCamera.thresholdHSL.convexHull(false);        //Fills in the bounding boxes for the targets            
                stepNo++;
                break;
            case 4:
                TrackingCamera.boundImage = TrackingCamera.convexHullImage.particleFilter(TrackingCamera.cc);
                stepNo++;
                break;
            case 5:
                TrackingCamera.reports = TrackingCamera.boundImage.getOrderedParticleAnalysisReports();
                System.out.println("Reports: "+TrackingCamera.reports.length);
                stepNo++;
                break;
            }
        } catch (NIVisionException ex) {
            System.out.println(ex);
            stepNo = 6;
        } catch (Exception ex) {
            System.out.println(ex);
            stepNo = 6;
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //todo this can just be set to true
        if(stepNo >= 6) {
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
