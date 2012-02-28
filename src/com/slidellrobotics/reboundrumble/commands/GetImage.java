/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.subsystems.TrackingCamera;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author 10491477
 */
public class GetImage extends CommandBase {
    
    double totalWidth, totalHeight;
    ParticleAnalysisReport[] reports;
    ColorImage pic;
    BinaryImage thresholdHSL;
    BinaryImage convexHullImage;
        
    public GetImage() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //TODO: move to initialize
        try {
            pic = camera.getImageFromCamera();      //Declares pic variable
            System.out.println("got image");
            totalWidth = pic.getWidth();
            totalHeight = pic.getHeight();
            
            System.out.println("threshold");
            thresholdHSL = pic.thresholdHSL(165, 185, 30, 120, 60, 110);      //Sets a Blue light threshold
            
            System.out.println("Convex");
            convexHullImage = thresholdHSL.convexHull(false);        //Fills in the bounding boxes for the targets            
            
            //TODO: Ordered?
            reports = convexHullImage.getOrderedParticleAnalysisReports();        //Sets "reports" to the nuber of particles
            System.out.println("Reports: "+reports.length);
        } catch (NIVisionException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        //TODO: Pics not freed
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; //todo: this does not exit
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
