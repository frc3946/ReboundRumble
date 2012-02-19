/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.commands.FilterImage;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Allister Wright
 */
public class TrackingCamera extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public AxisCamera camera;
    private ColorImage pic;
    boolean freePic = false;

    public TrackingCamera() {
        camera = AxisCamera.getInstance("10.39.46.11");
        System.out.println("Camera init");
        
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new FilterImage());
    }

    public ColorImage getImageFromCamera() throws NIVisionException {
       
        
        try {
           
            if (camera.freshImage()) {
                try {
                     camera.getImage();                  
                    
                } catch (AxisCameraException ex) {
                     System.out.println(ex);
                } catch (NIVisionException ex) {
                    System.out.println(ex);
                }
            }
        } catch (Exception ex) {
            //System.out.println(ex);
            //System.out.println("TrackingCamera Error");
        }
        return pic;
    }
}