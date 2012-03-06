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

/**
 *
 * @author Gus Michel
 */
public class LazySusan extends PIDSubsystem {

    private static final double Kp = 1.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    
    //private Relay susanWindow;
    private Gyro susanGyro;

    // Initialize your subsystem here
    public LazySusan() {
        super("LazySusan2", Kp, Ki, Kd);
        System.out.println("[LazySusan] Starting");
        
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
         System.out.println("gyro: " + susanGyro.getAngle());
        return susanGyro.getAngle();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        output = getSetpoint()-susanGyro.getAngle();
        System.out.println("lazy output: "+output);
        if(output > 15.0) {
          CommandBase.susanWindow.set(Relay.Value.kForward);
          System.out.println("Forward, left");
        } else if(output < -15.0) {
          CommandBase.susanWindow.set(Relay.Value.kReverse);
          System.out.println("Reserve, right");
        } else {
           CommandBase.susanWindow.set(Relay.Value.kOff);
        }
    }
    
    public void setRelay(Value value) {
        if (CommandBase.susanWindow != null){
        CommandBase.susanWindow.set(value);
        }
    }
}
