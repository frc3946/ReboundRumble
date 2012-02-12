/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author gixxy
 */
public class FeedBelt extends Subsystem {
    public Relay feedBeltSpike;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public FeedBelt() {
        System.out.println("[FeedBelt] Starting");
        feedBeltSpike = new Relay(RobotMap.feedBeltSpike);
        System.out.println("[FeedBelt] feedBeltSpike  initialized");
        System.out.println("[FeedBelt] Started");
    }
    
    /**
     * Set the State of the Ball intake belt.
     * @param state Boolean value of the state (on=true, off=false)
     */
    public void setBelt(boolean state) {
        if(state == true) {
            feedBeltSpike.set(Relay.Value.kForward);
            SmartDashboard.putBoolean("Feed Belt", true);
            //System.out.println("[FeedBelt] spike set to ON"); //uncomment for use with debugging
        } else {
            feedBeltSpike.set(Relay.Value.kOff);
            SmartDashboard.putBoolean("Feed Belt", false);
            //System.out.println("[FeedBelt] spike set to OFF"); //uncomment for use with debugging
        }
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
