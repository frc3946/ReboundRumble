/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.subsystems;

import com.slidellrobotics.reboundrumble.RobotMap;
import com.slidellrobotics.reboundrumble.commands.ProcessTurnTableGyro;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author gixxy
 */
public class GyroTurnTable extends Subsystem {

    Gyro gyro;
    public double angleToBasket;
    public GyroTurnTable() {
        gyro= new Gyro(RobotMap.gyroTurntable);
          angleToBasket =0;
          System.out.println("Gyro inited");
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public double getAngle(){
        return gyro.getAngle();
    }
    
    public void updateAngle(){
     angleToBasket=gyro.getAngle();   
    }
            
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new ProcessTurnTableGyro());
    }
}