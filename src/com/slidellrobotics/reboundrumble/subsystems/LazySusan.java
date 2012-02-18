/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author gixxy
 */
public class LazySusan extends Subsystem {
    private Relay susanWindow;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public LazySusan() {
        System.out.println("[LazySusan] Starting");
        susanWindow = new Relay(RobotMap.susanSpike);
        System.out.println("[LazySusan] susanWindow initalized");
        System.out.println("[LazySusan] Started");
    }
    
    /**
     * Set the relay for the Lazy Susan window motor
     * @param value Relay.Value value for the Relay.
     */
    public void setRelay(Value value) {
        susanWindow.set(value);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
