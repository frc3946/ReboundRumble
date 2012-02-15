/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author gixxy
 */
public class FireMotors extends PIDSubsystem {

    private static double Kp = 0.0000005;
    private static  double Ki = 0.0;
    private static  double Kd = 0.0;
    
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
        counter.start();
        
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
        if (timespan == 0) {
            return 0.0;
        }
        counts = counter.get();
        rpms = counts/ 16.0 / timespan*60.0; //16 different counts and it is converted into seconds
        counter.reset();
       
        SmartDashboard.putDouble("RPMS",rpms);
        SmartDashboard.putDouble("TimeSpan",timespan);
        SmartDashboard.putDouble("Counts",counts);
         
        return rpms;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        //FireMotors.set()}        
        victor.set(victor.get()+output);
        SmartDashboard.putDouble("PID Fire Motor", output);
    }

    private void setDefaultCommand(FireMotors fireMotors) {
    }
}
