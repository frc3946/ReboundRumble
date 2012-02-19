/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Gus Michel
 */
public class Transmission extends Subsystem {
    private DoubleSolenoid gearShifter;
    
    public Transmission() {
        System.out.println("[Transmission] Starting");
        gearShifter = new DoubleSolenoid(RobotMap.highGear,RobotMap.lowGear);
        System.out.println("[Transmission] gearShifter initialized");
        System.out.println("[Transmission] Started");
    }
    
    public void setHighGear() {
        gearShifter.set(DoubleSolenoid.Value.kForward);
        //System.out.println("[Transmission] High Gear Set"); //uncomment for use with debugging
        SmartDashboard.putString("Transmission", "High Gear");
    }
    
    public void setLowGear() {
        gearShifter.set(DoubleSolenoid.Value.kReverse);
        //System.out.println("[Transmission] Low Gear Set"); //uncomment for use with debugging
        SmartDashboard.putString("Transmission", "Low Gear");
    }
    
    public Value getGear() {
        return gearShifter.get();
    }
    
    public void initDefaultCommand() {
        
    }
}
