/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Gus Michel
 */
public class BridgeMounter extends Subsystem {
    private Relay windowSpike;
    
    public BridgeMounter() {
        System.out.println("[BridgeMounter] Starting");
        windowSpike = new Relay(RobotMap.bridgeSpike);
        System.out.println("[BridgeMounter] windowSpike  initialized");
        System.out.println("[BridgeMounter] Started");
    }
    
    public void drop() {
        windowSpike.set(Relay.Value.kForward);
    }
    
    public void autoDrop() {
        windowSpike.set(Relay.Value.kForward);
        Timer.delay(4);
        windowSpike.set(Relay.Value.kOff);
    }
    
    public void stow() {
        windowSpike.set(Relay.Value.kReverse);
    }
    
    public void autoStow() {
        windowSpike.set(Relay.Value.kReverse);
        Timer.delay(4);
        windowSpike.set(Relay.Value.kOff);
    }
    
    public void stop() {
        windowSpike.set(Relay.Value.kOff);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
