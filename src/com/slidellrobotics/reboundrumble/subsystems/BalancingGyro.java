/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author gixxy
 */
public class BalancingGyro extends Subsystem {
    private Gyro balanceGyro;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public BalancingGyro() {
        System.out.println("[BalancingGyro] Starting");
        balanceGyro = new Gyro(RobotMap.balanceGyro);
        System.out.println("[BalancingGyro] balanceGyro initialized");
        balanceGyro.reset();
        System.out.println("[BalancingGyro] balanceGyro reset");
        System.out.println("[BalancingGyro] Started");
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setZero() {
        balanceGyro.reset();
        //System.out.println("[BalancingGyro] balanceGyro reset"); //uncomment for debugging
    }
    
    public double getAngle() {
        SmartDashboard.putDouble("Balancing Gyro", balanceGyro.getAngle());
        //System.out.println("[BalancingGyro] balanceGyro angle" + balanceGyro.getAngle()); //uncomment for debugging
        return balanceGyro.getAngle();
    }
}
