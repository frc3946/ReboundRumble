/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import com.slidellrobotics.reboundrumble.commands.FindAccelerometerSpeed;
//import com.slidellrobotics.reboundrumble.commands.FindAccelerometerSpeed;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Gus Michel
 */
public class PositioningAccelerometer extends Subsystem {

    private ADXL345_I2C positionAccelerometer;
    double  GS_TO_MPS = 9.8; // Gravity units to meters per second
    
    double lastTime;
    double newtime;
    double timespan;
    
    double velocityX;    
    double velocityY;
    double velocityZ;
    
    double distanceX;    
    double distanceY;
    double distanceZ;

    public PositioningAccelerometer() {
        System.out.println("[PositioningAccelerometer] Starting");
        positionAccelerometer = new ADXL345_I2C(RobotMap.positioningAccelerometer, ADXL345_I2C.DataFormat_Range.k16G);
        System.out.println("[PositioningAccelerometer] positionAccelerometer initialized");
        System.out.println("[PositioningAccelerometer] Started");

        velocityX = 0;
        velocityY = 0;
        velocityZ = 0;
        
        distanceX= 0;   
        distanceY= 0;
        distanceZ= 0;
        lastTime = Timer.getFPGATimestamp();
    }

    /**
     * Get the Acceleration of given Accelerometer Axis (in Gs)
     *
     * @return Acceleration of Specified Accelerometer Axis
     */
    public double getPosition(ADXL345_I2C.Axes Axis) {
        return positionAccelerometer.getAcceleration(Axis);
    }

    /**
     * Get the Acceleration of all Accelerometer Axes (in Gs)
     *
     * @return Acceleration of all Axes
     */
    public ADXL345_I2C.AllAxes getAxes() {
        return positionAccelerometer.getAccelerations();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new FindAccelerometerSpeed());
    }

    public void calculate() {
        newtime = Timer.getFPGATimestamp();
        timespan = newtime - lastTime;
        lastTime = newtime;
        

        ADXL345_I2C.AllAxes allAxes= positionAccelerometer.getAccelerations();
        
        //convert all accelerations to meters per second
        allAxes.XAxis=allAxes.XAxis*GS_TO_MPS;
        allAxes.YAxis=allAxes.YAxis*GS_TO_MPS;
        allAxes.ZAxis=allAxes.ZAxis*GS_TO_MPS;
        
        //calculate new distance, do this before updateing velocity
        distanceX= distanceX + velocityX*timespan + 0.5*allAxes.XAxis*timespan*timespan;   
        distanceY= distanceY + velocityY*timespan + 0.5*allAxes.YAxis*timespan*timespan; 
        distanceZ= distanceZ + velocityZ*timespan + 0.5*allAxes.ZAxis*timespan*timespan;
        
        //Calculate new velocity
        velocityX = velocityX + allAxes.XAxis * timespan;
        velocityY = velocityY + allAxes.YAxis * timespan;
        velocityZ = velocityZ + allAxes.ZAxis * timespan;
        
       
        
        //SmartDashboard.putDouble("Vx", velocityX);
        //SmartDashboard.putDouble("Vy", velocityY);
        //SmartDashboard.putDouble("Vz", velocityZ);
        //SmartDashboard.putDouble("Ax", allAxes.XAxis);
        //SmartDashboard.putDouble("Ay", allAxes.YAxis);
        //SmartDashboard.putDouble("Az", allAxes.ZAxis);
        //SmartDashboard.putDouble("Dx", distanceX);
        //SmartDashboard.putDouble("Dy", distanceY);
        //SmartDashboard.putDouble("Dz", distanceZ);
        
        


    }
}

