/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author gixxy
 */
public class FirePiston extends Subsystem {
    private DoubleSolenoid pistonSolenoid;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public FirePiston() {
        System.out.println("[LoadPiston] Starting");
        pistonSolenoid = new DoubleSolenoid(RobotMap.firePiston,RobotMap.stowPiston);
        System.out.println("[LoadPiston] pistonSolenoid initialized");
        System.out.println("[LoadPiston] Started");
    }
    
    /**
     * Extends piston
     */
    public void fire() {
        pistonSolenoid.set(DoubleSolenoid.Value.kForward);
        SmartDashboard.putBoolean("Load Piston", true);
        //System.out.println("[FirePiston] Fired"); //uncomment for use with debugging
    }
    
    /**
     * Retracks piston
     */
    public void stow() {
        pistonSolenoid.set(DoubleSolenoid.Value.kReverse);
        Timer.delay(4); //allows time for SmartDashboard to register the piston was infact launched (basicly for nice appearences)
        SmartDashboard.putBoolean("Load Piston", false);
        //System.out.println("[FirePiston] Fired"); //uncomment for use with debugging
    }
    
    /**
     * Get the state of the piston
     * @return value of the Piston's Solenoid
     */
    public Value get() {
        return pistonSolenoid.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
