/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 * @author Gus Michel
 * 
 */
public class FireMotors extends PIDSubsystem {

    private static double Kp = 0.00001;
    private static  double Ki = 0.0;
    private static  double Kd = 0.0;
    
    private Counter counter;
    private Jaguar victor;
    double lastTime;
    double newtime;
    
    static final int rpmsFilterMax = 5;
    Double[] rpmsFilter= new Double[rpmsFilterMax];
    int rpmsFilterIndex = 0;
    
    double rpms=0;
    double victorSetting=0;
    
    String Name;
    
    // Initialize your subsystem here
    public FireMotors(String Name,int counterChannel, int victorChannel) {
        super(Name, Kp, Ki, Kd);
        counter = new Counter(counterChannel);
        victor = new Jaguar(victorChannel); 
        enable();
        setSetpoint(1000); //rpms
        lastTime = Timer.getFPGATimestamp();
        counter.start();
        getPIDController().setOutputRange(-.1, .1);
        setSetpointRange(0,2000);
        this.Name = Name; //set class scope name to the Name passed in the constructor
        System.out.println(Name +  "Firemotor init");
        for (int index =0;index < rpmsFilterMax;index++){
             rpmsFilter[index]=Double.valueOf(0);
            
        }
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
         timespan = newtime- lastTime; //number of seconds during counting 
         if (timespan <= 0.130) {
             //System.out.println("Firemotor return PID timspan 0");
            return rpms; //don't recalculate, just give what ever it was
        }
         
        counts = counter.get(); //number of counts since last update
       counter.reset();        //reset counts ASAP so we don't miss any
       //  System.out.println("1");
              
        lastTime = newtime;
       
        //System.out.println("2");
        if(Name.equals("Left")) {
            rpms = counts/ 8.0 / timespan*60.0;
        } else {
            rpms = counts/ 7.0 / timespan*60.0;
        }
        //8 counts per revolution, 60 seconds per minute  
          //System.out.println(Name + ": " +  rpms);
          //System.out.println("     Counts: "+counts);
          //System.out.println("          Timespan: "+timespan);
          //System.out.println();
        //sometimes the counter goes nuts
        //this deboundes it
        if (rpms > 1750)
            rpms = 1750;
        
        //    System.out.println("3");
        rpmsFilter[rpmsFilterIndex]=Double.valueOf(rpms);
        rpmsFilterIndex++;
        if (rpmsFilterIndex >= rpmsFilterMax){
            rpmsFilterIndex=0;
        }
        //System.out.println("4");
       rpms =0;
        for (int index =0;index < rpmsFilterMax;index++){
             rpms = rpms + rpmsFilter[index].doubleValue();
             //System.out.println(index);
        }
        rpms = rpms / rpmsFilterMax;
      
        return rpms;
        
    }
    
    protected void usePIDOutput(double output) {
        //When the PID system thinks there is no error then
        //set point is equal to rpms and output = 0        
        //soft start if we not moving, only set power to 10%
       
         victorSetting=victor.get()-output;
        
        
        if (Math.abs(victorSetting) < .3){
           
                victorSetting = -.3;
            
        }
          //System.out.println("Use PID "+victorSetting);
        victor.set(victorSetting);
        //victor.set(getSetpoint());

        
    }
    
    public void updateStatus(){
        SmartDashboard.putDouble(Name + " RPMS",rpms);   
        SmartDashboard.putDouble(Name + " victor",victor.get()); 
        SmartDashboard.putDouble(Name + " victor Setting",victorSetting); 
        
    }
}
