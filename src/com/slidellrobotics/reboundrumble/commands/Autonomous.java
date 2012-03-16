/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands;

import com.slidellrobotics.reboundrumble.commands.AutonomousCommands.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Gus Michel
 */
public class Autonomous extends CommandGroup {
    Command processImage = new ProcessImage();
    public Autonomous() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        /**@TODO Find out how many degrees it is from the turret side to about center. So that we can have the robot front face goal and not worry so much about hitting the side, even though we did fix the sprocket issue.
        addSequential(new AutoAim());
        addSequential(new AutoFire());
        addParallel(new AutoLoad());
        addSequential(new AutoAim());
        addSequential(new AutoFire());
        /*
        addSequential(new HighGear());
        //addSequential(new AutoSusanRight());
        addParallel(new AutoAim());
        addSequential(new AutoTankDrive(-.8 ,-.8));
        addSequential(new AutoTimer(1.8));
        //addSequential(new AutoTankDrive(.7, .7));
        //addSequential(new AutoTimer(3.6));
        //addSequential(new AutoTankDrive(.3, 1));
        //addSequential(new AutoTimer(2.15));
        //addSequential(new AutoTankDrive(.8, .8));
        //addSequential(new AutoTimer(1.8));
        //addSequential(new AutoTankDrive(0, 0));
        //addSequential(new AutoCircleBridge());
        addSequential(new AutoDropArm());
        Timer.delay(.4);
        addSequential(new AutoStowArm());
         */
    }   
}
