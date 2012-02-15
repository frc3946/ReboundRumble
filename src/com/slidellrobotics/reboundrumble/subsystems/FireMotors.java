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

    private static double Kp = 0.00001;
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
        
        
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
      
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        
        double timespan;
        int counts;
        
        // these three lines are time critial
        newtime = Timer.getFPGATimestamp();
        counts = counter.get(); //number of counts since last update
        counter.reset();        //reset counts ASAP so we don't miss any
        
        timespan = newtime- lastTime; //number of seconds during counting        
        lastTime = newtime;
        if (timespan == 0) 
            return 0.0;
       
        rpms = counts/ 8.0 / timespan*60.0; //8 counts per revolution, 60 seconds per minute       
       
        //this would update both left and right motors!!!
        //TODO: remove next three lines
        SmartDashboard.putDouble("RPMS",rpms);
        SmartDashboard.putDouble("TimeSpan",timespan);
        SmartDashboard.putDouble("Counts",counts);
         
        return rpms;
    }
    
    protected void usePIDOutput(double output) {
        //When the PID system thinks there is no error then
        //set point is equal to rpms and output = 0
               
        victor.set(victor.get()+output);
        
        //this would update both left and right motors!!!
        //TODO: remove next line
        SmartDashboard.putDouble("PID Fire Motor", output);
    }

    private void setDefaultCommand(FireMotors fireMotors) {
    }
}
