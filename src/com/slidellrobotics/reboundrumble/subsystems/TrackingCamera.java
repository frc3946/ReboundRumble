/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.commands.ProcessImage;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.*;

/**
 *
 * @author Allister Wright
 */
public class TrackingCamera extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public static AxisCamera camera;
    public static ColorImage pic;
    public static ParticleAnalysisReport[] reports;
    //public static int leftGoalIndex = 0, rightGoalIndex = 0;
    public static CriteriaCollection cc = new CriteriaCollection();
    public static BinaryImage thresholdHSL;
    public static BinaryImage convexHullImage;
    public static BinaryImage boundImage;
    //public static BinaryImage bigObjectsImage;
    public static double totalWidth = 640, totalHeight = 480;
    public static ParticleAnalysisReport targetGoal, leftGoal, rightGoal;    
    public static double targetLocale, horCenter, targetDiff, launchSpeed;
    public static double centerDistance;
    public static double distanceToTarget, targetHeight, targetHeightFeet = 1.5;
    public static double horFOV, vertFOV, cameraVertFOV = 47, cameraHorizFOV = 47;
    public static double d1, d2, d, targetWidth, targetWidthFeet = 2;
    //public static boolean getFinished = false, selectFinished = false;
    //public static boolean angleFinished = false, distanceFinished = false;
    
    public TrackingCamera() {
        if(camera == null) {
            TrackingCamera.camera = AxisCamera.getInstance("10.39.46.11");
        }
        if(camera != null) {
            System.out.println("Camera init");     
        } else {
            System.out.println("Camera Failure");  
        }
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ProcessImage());
    }

    public ColorImage getImageFromCamera() throws NIVisionException {
        try {
            if(camera!=null) {
                if (camera.freshImage()) {
                    try {
                        System.out.println("sent image");
                        return camera.getImage();                      
                    } catch (AxisCameraException ex) {
                        System.out.println("getImageFromCamera"+ex);
                    } catch (NIVisionException ex) {
                        System.out.println(ex);
                    }
                }
            } else {
                System.out.println("CAMERA IS STILL NULL!!!!");
            }
        } catch (Exception ex) {
            System.out.println("Get Image From Camera: "+ex);
        }
        System.out.println("return null");
        return null;
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
    public double distanceToRMP(double distance) {
        //load the test data when practing with the real launcher
        // points MUST be inorder of closest to furthest away
        CalibrationPoint calibrationPoints[] = {new CalibrationPoint(5, 100),
            new CalibrationPoint(10, 200),
            new CalibrationPoint(15, 500),
            new CalibrationPoint(40, 1500)};

        //find the two calibration points were the input distance is between them
        int upperIndex;
        
        
        upperIndex = 1;
        while(upperIndex < calibrationPoints.length) {
            if (distance <= calibrationPoints[upperIndex].distance &&
                distance > calibrationPoints[upperIndex-1].distance) {
                break;
            }
            upperIndex++;
        }
        
        //interpolate the point
      
        if (upperIndex >= calibrationPoints.length){
        upperIndex=calibrationPoints.length-1;
        System.out.println("Error calibration");
        }
        System.out.println("rpms "+calibrationPoints[upperIndex].rpms);
        double slope =  (calibrationPoints[upperIndex].rpms - calibrationPoints[upperIndex - 1].rpms)
                      / (calibrationPoints[upperIndex].distance - calibrationPoints[upperIndex - 1].distance);
        double intercept = calibrationPoints[upperIndex].rpms - slope * calibrationPoints[upperIndex].distance;
        return slope * distance + intercept;
    }
}