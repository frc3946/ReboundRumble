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
public class FirePiston extends Subsystem {
    private DoubleSolenoid pistonSolenoid;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public FirePiston() {
        System.out.println("[FirePiston] Starting");
        pistonSolenoid = new DoubleSolenoid(RobotMap.firePiston,RobotMap.stowPiston);
        System.out.println("pistonSolenoid initialized");
        System.out.println("[FirePiston] Started");
    }
    
    public void fire() {
        pistonSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void stow() {
        pistonSolenoid.set(DoubleSolenoid.Value.kReverse); //may need to be changed to reverse
    }
    
    public Value get() {
        return pistonSolenoid.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
