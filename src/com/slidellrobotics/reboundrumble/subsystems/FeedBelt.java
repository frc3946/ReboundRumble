/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Gus Michel
 */
public class FeedBelt extends Subsystem {
    private Relay feedBeltSpike;
    private Value state;
    
    private final String dashName = "Feed Belt";
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public FeedBelt() {
        System.out.println("[FeedBelt] Starting");
        feedBeltSpike = new Relay(RobotMap.feedBeltSpike);
        state = Value.kOff;
        System.out.println("[FeedBelt] feedBeltSpike  initialized");
        SmartDashboard.putString(dashName, "Off");
        System.out.println("[FeedBelt] Started");
    }
    
    /**
     * Set the State of the Ball intake belt.
     * @param state Boolean value of the state (on=true, off=false)
     */
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setIntake() {
        feedBeltSpike.set(Relay.Value.kReverse);
        state = Value.kReverse;
        SmartDashboard.putString(dashName, "Intake");
    }
    
    public void setOuttake() {
        feedBeltSpike.set(Relay.Value.kForward);
        state = Value.kForward;
        SmartDashboard.putString(dashName, "Outtake");
    }
    
    public void setStopped() {
        feedBeltSpike.set(Relay.Value.kOff);
        state = Value.kOff;
        SmartDashboard.putString(dashName, "Off");
    }
    
    public Value getState() {
        return state;
    }
}
