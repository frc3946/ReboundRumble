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
    public Relay spike;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public FeedBelt() {
        System.out.println("[FeedBelt] Starting");
        spike = new Relay(RobotMap.feedBeltSpike);
        System.out.println("[FeedBelt] spike  initialized");
        System.out.println("[FeedBelt] Started");
    }
    
    public void setBelt(boolean state) {
        if(state == true) {
            spike.set(Relay.Value.kForward);
            SmartDashboard.putBoolean("Feed Belt", true);
            System.out.println("[FeedBelt] spike set to ON");
        } else {
            spike.set(Relay.Value.kOff);
            SmartDashboard.putBoolean("Feed Belt", false);
            System.out.println("[FeedBelt] spike set to OFF");
        }
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
