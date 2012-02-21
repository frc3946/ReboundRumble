/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slidellrobotics.reboundrumble.commands.AutonomousCommands;

import com.slidellrobotics.reboundrumble.commands.HighGear;
import com.slidellrobotics.reboundrumble.commands.LowGear;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author 10491477
 */
public class AutoMountBridge extends CommandGroup {
    
    public AutoMountBridge() {
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
        
        addSequential(new AutoDropArm());
        addSequential(new AutoTimer(.3));
        addSequential(new LowGear());
        addParallel(new AutoTankDrive(.9, .9));
        addSequential(new AutoTimer(.65));
        addSequential(new AutoStowArm());
        addSequential(new AutoTimer(6.8));
        addSequential(new HighGear());
    }
}
