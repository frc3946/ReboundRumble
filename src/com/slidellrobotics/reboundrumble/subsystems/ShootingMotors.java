/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import com.slidellrobotics.reboundrumble.commands.SetShootingMotors;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author gixxy
 */
public class ShootingMotors extends Subsystem {
    private Victor motor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public ShootingMotors() {
        System.out.println("[ShootingMotors] Starting");
        motor = new Victor(RobotMap.shootingMotors);
        System.out.println("[ShootingMotors] motor initialized");
        System.out.println("[ShootingMotors] Started");
    }
    
    /**
     * Set speed of the motors
     * @param speed 
     */
    public void setSpeed(double speed) {
        motor.set(speed);
        SmartDashboard.putDouble("Shooting Motors", Math.floor(motor.get()*10)); //Floored to remote Decimal, *10 for readability
        //System.out.println("[ShootingMotors] Speed set to "+topMotor.get()); //uncomment for use with debugging
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new SetShootingMotors());
    }
}
