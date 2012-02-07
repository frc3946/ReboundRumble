/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.commands.ProcessAccelerometer;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author gixxy
 */
public class Accelerometer extends Subsystem {

    ADXL345_I2C accelerometer;
    int slot;

    public void Accelerometer() {
        //look for accelerometer by trying all slots
        for (slot = 1; slot <= 4; slot++) {
            accelerometer = new ADXL345_I2C(slot, ADXL345_I2C.DataFormat_Range.k2G);
            if (accelerometer != null) {
                break;
            }
        }
    }

public ADXL345_I2C.AllAxes GetAcceleration() {
        if (accelerometer != null){
            return accelerometer.getAccelerations();
        }
        else{
            ADXL345_I2C.AllAxes temp = new ADXL345_I2C.AllAxes();
            temp.XAxis = slot;
            return temp;                   
        }
        
        
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ProcessAccelerometer());
    }
}