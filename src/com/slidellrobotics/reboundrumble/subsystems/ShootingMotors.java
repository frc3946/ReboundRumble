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
    private Victor topMotor;
    private Victor bottomMotor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public ShootingMotors() {
        System.out.println("[ShootingMotors] Starting");
        topMotor = new Victor(RobotMap.topShootingMotor);
        System.out.println("[ShootingMotors] topMotor initialized");
        bottomMotor = new Victor(RobotMap.bottomShootingMotor);
        System.out.println("[ShootingMotors] bottomMotor initialized");
        System.out.println("[ShootingMotors] Started");
    }
    
    public void setSpeed(double speed) {
        topMotor.set(speed);
        bottomMotor.set(speed);
        if(topMotor.get() == bottomMotor.get()) {
            SmartDashboard.putDouble("Shooting Motors", topMotor.get()*10);
            //System.out.println("[ShootingMotors] Speed set to "+topMotor.get()); //uncomment for use with debugging
        } else {
            SmartDashboard.putString("Shooting Motors", "Speeds not equal. Top: "+topMotor.get()+" Bottom: "+bottomMotor.get());
            //System.out.println("[ShootingMotors] [SEVERE] MOTOR SPEEDS NOT EQUAL. Top: "+topMotor.get()+" Bottom: "+bottomMotor.get()); //uncomment for use with debugging
        }
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new SetShootingMotors());
    }
}
