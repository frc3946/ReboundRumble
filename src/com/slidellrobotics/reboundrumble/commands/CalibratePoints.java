/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.subsystems.TrackingCamera;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Allister Wright
 */
public class CalibratePoints extends CommandBase {
    private final double timeDelay = 1;
    
    double distanceToTarget=0;
    double lastTime=0;
    
    ParticleAnalysisReport[] reports = null;

    public CalibratePoints() {
        requires(camera);
        requires(lazySusan);
        requires(leftShootingMotors);
        requires(rightShootingMotors);
        System.out.println("Filter image Init");    //States that the camera initialized
        lastTime = Timer.getFPGATimestamp();
    }

    protected void initialize() {
    }

    protected void execute() {
        try {
            //run this at a slower pace to not eat up all the processor time
            if (Timer.getFPGATimestamp() - lastTime > timeDelay) {
                updateStatus();    
            }
        } catch (Exception ex) {
             System.out.println("Image loop failure.");
        }
    }

    protected boolean isFinished() {
        return false;                //never ends
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    public void updateStatus() {
        try {
            SmartDashboard.putDouble("Pic Height: ", TrackingCamera.totalHeight);
            SmartDashboard.putDouble("Pic Width: ", TrackingCamera.totalWidth);
            SmartDashboard.putDouble("launchSpeed: ", TrackingCamera.launchSpeed);
            SmartDashboard.putDouble("Distance: ", TrackingCamera.distanceToTarget);

            if (reports != null) {
                for (int i = 0; i < reports.length; i++) {                          //Systematically prints the                                   
                    System.out.println("Particle: " + i + ": Center of mass x:" + //geometric center
                            reports[i].center_mass_x);                                       //of mass per particle.
                }
            }
        } catch (Exception ex) {
        }
    }

    private class CalibrationPoint {

        double distance;
        double rpms;

        public CalibrationPoint(double distance, double rpms) {
            this.distance = distance;
            this.rpms = rpms;
        }
    }

    //just incase the formula doesn't work out here is a test based
    //interpolation function
    private double distanceToRMP(double distance) {

        //load the test data when practing with the real launcher
        // points MUST be inorder of closest to furthest away
        CalibrationPoint calibrationPoints[] = {
            new CalibrationPoint(5, 100),
            new CalibrationPoint(10, 200),
            new CalibrationPoint(15, 500),
            new CalibrationPoint(40, 1500)
        };

        //find the two calibration points were the input distance is between them
        int upperIndex;
        for (upperIndex = 1; upperIndex < calibrationPoints.length; upperIndex++) {
            if (distance <= calibrationPoints[upperIndex].distance &&
                distance > calibrationPoints[upperIndex-1].distance) {
                break;
            }
        }
        
        //interpolate the point
        double slope =  (calibrationPoints[upperIndex].rpms - calibrationPoints[upperIndex - 1].rpms)
                      / (calibrationPoints[upperIndex].distance - calibrationPoints[upperIndex - 1].distance);
        double intercept = calibrationPoints[upperIndex].rpms - slope * calibrationPoints[upperIndex].distance;
        return slope * distance + intercept;
    }
} 