/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.subsystems.TrackingCamera;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 *
 * @author 10491477
 */
public class GetImage extends CommandBase {
    public GetImage() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        try {
            TrackingCamera.pic = camera.getImageFromCamera();      //Declares pic variable
            System.out.println("Got image");
            TrackingCamera.totalWidth = TrackingCamera.pic.getWidth();
            TrackingCamera.totalHeight = TrackingCamera.pic.getHeight();

            System.out.println("Threshold");
            TrackingCamera.thresholdHSL = TrackingCamera.pic.thresholdHSL(160, 180, 230, 255, 0, 15);      //Sets a Blue light threshold

            System.out.println("Removing Small Objects");
            TrackingCamera.bigObjectsImage = TrackingCamera.thresholdHSL.removeSmallObjects(false, 1);
            
            System.out.println("Convex");
            TrackingCamera.convexHullImage = TrackingCamera.bigObjectsImage.convexHull(false);        //Fills in the bounding boxes for the targets            

            //TODO: Ordered?
            TrackingCamera.reports = TrackingCamera.convexHullImage.getOrderedParticleAnalysisReports();        //Sets "reports" to the nuber of particles
            System.out.println("Reports: "+TrackingCamera.reports.length);
        } catch (NIVisionException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

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
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //todo this can just be set to true
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
