/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author gixxy
 */
public class Transmission extends Subsystem {
    DoubleSolenoid gearShifter;
    boolean currentGear; //true == HighGear
    // Put methods for controlling this subsystem
    // here. Call these from Commands.    
    public Transmission() {
        gearShifter = new DoubleSolenoid(RobotMap.gearSolinoid, RobotMap.gearSolinoid+1);
        System.out.println("Transmission Inited");
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        //setDefaultCommand(new AutoTransmission());
    }
    
    public void setHighGear() {
        gearShifter.set(DoubleSolenoid.Value.kForward);
        currentGear = true;
    }
    
    public void setLowGear() {
        gearShifter.set(DoubleSolenoid.Value.kReverse);
        currentGear = false;
    }
    
    public Value getGear() {
        return gearShifter.get();
    }
    
    public String getGearString() {
        if(getGear() == Value.kForward) {
            return "High Gear";
        } else {
            return "Low Gear";
        }
    }
}