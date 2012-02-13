/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author gixxy
 */
public class TurretGyro extends Subsystem {
    Gyro turretGyro;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public TurretGyro() {
        System.out.println("[TurretGyro] Starting");
        turretGyro = new Gyro(RobotMap.turretGyro);
        System.out.println("[TurretGyro] turretGyro initialized");
        System.out.println("[TurretGyro] Started");
    }
    
    public double getAngle() {
        return turretGyro.getAngle();
    }
    
    public void setSensitivity(double sensitivity) {
        turretGyro.setSensitivity(sensitivity);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
