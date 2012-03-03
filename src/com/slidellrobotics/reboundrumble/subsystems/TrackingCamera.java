/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.commands.FilterImage;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author Allister Wright
 */
public class TrackingCamera extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public AxisCamera camera;
    public static ColorImage pic;
    public static ParticleAnalysisReport[] reports;
    public static BinaryImage thresholdHSL;
    public static BinaryImage convexHullImage;
    //public static BinaryImage bigObjectsImage;
    public static double totalWidth = 640, totalHeight = 480;
    public static ParticleAnalysisReport targetGoal, leftGoal, rightGoal;    
    public static double targetLocale, horCenter, targetDiff, launchSpeed;
    public static double distanceToTarget, targetHeight, targetHeightFeet = 1.5;
    public static double horFOV, vertFOV, cameraVertFOV = 47, cameraHorizFOV = 47;
    public static double d1, d2, d, targetWidth, targetWidthFeet = 2;
    public static boolean getFinished = false, selectFinished = false;
    public static boolean angleFinished = false, distanceFinished = false;
    
    public TrackingCamera() {
        camera = AxisCamera.getInstance("10.39.46.11");
        System.out.println("Camera init");        
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        
        setDefaultCommand(new FilterImage());
    }

    public ColorImage getImageFromCamera() throws NIVisionException {
        try {
            if (camera.freshImage()) {
                try {
                    return camera.getImage();                      
                } catch (AxisCameraException ex) {
                     System.out.println("getImageFromCamera"+ex);
                } catch (NIVisionException ex) {
                    System.out.println(ex);
                }
            }
        } catch (Exception ex) {
            //System.out.println(ex);
            //System.out.println("TrackingCamera Error");
        }
        return null;
    }
}