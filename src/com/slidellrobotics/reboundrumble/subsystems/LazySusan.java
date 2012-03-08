/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import com.slidellrobotics.reboundrumble.commands.CommandBase;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Gus Michel
 */
public class LazySusan extends PIDSubsystem {

    private static final double Kp = 1.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    
    private Relay susanWindow;
    private Gyro susanGyro;
    
    private final String dashName = "Lazy Susan";

    // Initialize your subsystem here
    public LazySusan() {
        super("LazySusan2", Kp, Ki, Kd);
        System.out.println("[LazySusan] Starting");
        susanWindow = new Relay(RobotMap.susanSpike);
        System.out.println("[LazySusan] susanWindow initalized");
        susanGyro = new Gyro(RobotMap.turretGyro);
        System.out.println("[LazySusan] susanGyro initalized");
        System.out.println("[LazySusan] Started");
        setSetpoint(0);
        enable();
        getPIDController().setOutputRange(30, 30);
        getPIDController().setInputRange(-360, 360);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        // System.out.println("gyro: " + susanGyro.getAngle());
        return susanGyro.getAngle();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        output = getSetpoint()-susanGyro.getAngle();
       // System.out.println("lazy output: "+output);
        if(output > 15.0) {
          this.setRelay(RobotMap.susanLeft);
          //System.out.println("Left");
        } else if(output < -15.0) {
          this.setRelay(RobotMap.susanRight);
          //System.out.println("Right");
        } else {
           this.setRelay(RobotMap.susanOff);
        }
    }
    
    public void setRelay(Value value) {
        susanWindow.set(value);
        if(value.equals(RobotMap.susanRight)) {
            SmartDashboard.putString(dashName, "Right");
        } else if(value.equals(RobotMap.susanLeft)) {
            SmartDashboard.putString(dashName, "Left");
        } else {
            SmartDashboard.putString(dashName, "Off");
        }
    }
    
    public Relay getSpike() {
        return susanWindow;
    }
}
