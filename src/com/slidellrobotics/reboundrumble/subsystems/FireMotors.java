/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 * @author gixxy
 */
public class FireMotors extends PIDSubsystem {

    private static final double Kp = 0.5;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    private Counter counter;
    private Victor victor;
    double lastTime;
    double newtime;
    double rpms;
    // Initialize your subsystem here
    public FireMotors(int counterChannel, int victorChannel) {
        super("FireMotors", Kp, Ki, Kd);
        counter = new Counter(counterChannel);
        victor = new Victor(victorChannel); 
        enable();
        setSetpoint(1000); //rpms
        lastTime = Timer.getFPGATimestamp();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        //setDefaultCommand(new FireMotors());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        double timespan;
        int counts;
        newtime = Timer.getFPGATimestamp();
        timespan = newtime- lastTime; //milliseconds during counting
        lastTime = newtime;
        if (timespan == 0)
            return 0.0;
        rpms = counter.get()/ 16.0 / timespan * 1000.0; //16 different counts and it is converted into seconds
        counter.reset();
        return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        //FireMotors.set()}
        victor.set(output);
    }

    private void setDefaultCommand(FireMotors fireMotors) {
    }
}
