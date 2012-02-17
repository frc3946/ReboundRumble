/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author gixxy
 */
public class BridgeMounter extends Subsystem {
    private Relay windowSpike;
    
    public BridgeMounter() {
        System.out.println("[BridgeMounter] Starting");
        windowSpike = new Relay(RobotMap.bridgeSpike);
        System.out.println("[BridgeMounter] windowSpike  initialized");
        System.out.println("[BridgeMounter] Started");
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
