/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author gixxy
 */
public class ProcessAccelerometer extends CommandBase {

    double timespan;
    double lastTime;
    double newtime;
    double velocityX;
    protected double velocityY;
    
    ADXL345_I2C.AllAxes allaxes;
    Timer timer;
    
    public ProcessAccelerometer() {
        // Use requires() here to declare subsystem dependencies
        requires(accelerometer);
        
        timespan = .02;
        velocityX = 0;
        velocityY = 0;
        lastTime = Timer.getFPGATimestamp();
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        newtime = Timer.getFPGATimestamp();
        timespan =  newtime- lastTime;
        lastTime = newtime;
        
        allaxes = accelerometer.GetAcceleration();
        velocityX = velocityX + allaxes.XAxis * timespan;
        velocityY = velocityY + allaxes.YAxis * timespan;
        
        theDash.log(velocityX,"X");
        theDash.log(velocityY,"Y");
        theDash.log(allaxes.XAxis,"X axis");
        theDash.log(allaxes.YAxis,"Y axis");
        theDash.log(Timer.getFPGATimestamp(),"FPGATimestamp");
        theDash.log(newtime,"timespan");
     }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}