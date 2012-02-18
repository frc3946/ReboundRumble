/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import com.slidellrobotics.reboundrumble.commands.FindAccelerometerSpeed;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author gixxy
 */
public class PositioningAccelerometer extends Subsystem {
    private ADXL345_I2C positionAccelerometer;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public PositioningAccelerometer() {
        System.out.println("[PositioningAccelerometer] Starting");
        positionAccelerometer = new ADXL345_I2C(RobotMap.positioningAccelerometer,ADXL345_I2C.DataFormat_Range.k16G);
        System.out.println("[PositioningAccelerometer] positionAccelerometer initialized");
        System.out.println("[PositioningAccelerometer] Started");
    }
    
    /**
     * Get the Acceleration of given Accelerometer Axis (in Gs)
     * @return Acceleration of Specified Accelerometer Axis
     */
    public double getPosition(ADXL345_I2C.Axes Axis) {
        return positionAccelerometer.getAcceleration(Axis);
    }
    
    /**
     * Get the Acceleration of all Accelerometer Axes (in Gs)
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
}
//double timespan;
//    double lastTime;
//    double newtime;
//    double velocityX;
//    protected double velocityY;
//    
//    ADXL345_I2C.AllAxes allaxes;
//    Timer timer;
//    
//    public ProcessAccelerometer() {
//       // Use requires() here to declare subsystem dependencies
//       requires(accelerometer);
//       
//       timespan = .02;
//       velocityX = 0;
//       velocityY = 0;
//        lastTime = Timer.getFPGATimestamp();
//       
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//        newtime = Timer.getFPGATimestamp();
//        timespan =  newtime- lastTime;
//        lastTime = newtime;
//        
//        allaxes = accelerometer.GetAcceleration();
//        velocityX = velocityX + allaxes.XAxis * timespan;
//        velocityY = velocityY + allaxes.YAxis * timespan;
//        
//        SmartDashboard.putDouble("X", velocityX);
//        SmartDashboard.putDouble("Y", velocityY);
//        SmartDashboard.putDouble("X axis", allaxes.XAxis);
//        SmartDashboard.putDouble("Y axis", allaxes.YAxis);
//        SmartDashboard.putDouble("FPGATimestamp", newtime);
//        SmartDashboard.putDouble("timespan",timespan);
//     }