/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.slidellrobotics.reboundrumble;


import com.slidellrobotics.reboundrumble.commands.Autonomous;
import com.slidellrobotics.reboundrumble.commands.CommandBase;
import com.slidellrobotics.reboundrumble.subsystems.TrackingCamera;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Main extends IterativeRobot {
    private double startTime;
    Command autonomousCommand = new Autonomous();
    Relay compressor = new Relay(RobotMap.compressorSpike);
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        //autonomousCommand = new ExampleCommand();
        //TODO make default IterativeRobot.disabledInit() method... Overload me!      
        // Initialize all subsystems
        CommandBase.init();
        SmartDashboard.putData(Scheduler.getInstance());
        compressor.set(Relay.Value.kForward);
        TrackingCamera.cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 30, 600, false);
        TrackingCamera.cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 20, 420, false);
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
        
        
    }
     public void updateStatus(){
      CommandBase.leftShootingMotors.updateStatus();
      CommandBase.rightShootingMotors.updateStatus();
     
    }
}
