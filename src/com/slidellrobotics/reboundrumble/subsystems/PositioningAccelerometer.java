/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import com.slidellrobotics.reboundrumble.commands.FindPosition;
import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author gixxy
 */
public class PositioningAccelerometer extends Subsystem {
    private Accelerometer positionAccelerometer;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public PositioningAccelerometer() {
        System.out.println("[PositioningAccelerometer] Starting");
        positionAccelerometer = new Accelerometer(RobotMap.positioningAccelerometer);
        System.out.println("[PositioningAccelerometer] positionAccelerometer initialized");
        System.out.println("[PositioningAccelerometer] Started");
    }
    
    public double getPosition() {
        return positionAccelerometer.getAcceleration();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new FindPosition());
    }
}
