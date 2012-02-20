
package com.slidellrobotics.reboundrumble;

import com.slidellrobotics.reboundrumble.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick leftJoystick = new Joystick(RobotMap.leftJoystick); //Left Joystick
    private Button intakeFeedBelt = new JoystickButton(leftJoystick, RobotMap.intakeFeedBeltButton);
    private Button outtakeFeedBelt = new JoystickButton(leftJoystick, RobotMap.outtakeFeedBeltButton);
    private Button dropBridge = new JoystickButton(leftJoystick, RobotMap.dropBridgeButton);
    private Button stowBridge = new JoystickButton(leftJoystick, RobotMap.stowBridgeButton);
    private Button turnSusanLeft = new JoystickButton(leftJoystick, RobotMap.leftLazySusanButton);
    private Button turnSusanRight = new JoystickButton(leftJoystick, RobotMap.rightLazySusanButton);
    
    private Joystick rightJoystick = new Joystick(RobotMap.rightJoystick); //Right Joystick
    private Button fireBall = new JoystickButton(rightJoystick, RobotMap.fireButton); //Button to fire the ball
    private Button shiftHighGear = new JoystickButton(rightJoystick, RobotMap.highGearShiftButton); //Button to shift to High Gear
    private Button shiftLowGear = new JoystickButton(rightJoystick, RobotMap.lowGearShiftButton); //Button to shift to Low Gear
    private Button arcadeMode = new JoystickButton(rightJoystick, RobotMap.arcadeModeButton);
    
    //SmartDashBoard Buttons
    private InternalButton smartDashboardButton1 = new InternalButton();
    
    
    public OI() {
        arcadeMode.whileHeld(new ArcadeDrive());
        shiftHighGear.whenPressed(new HighGear());
        shiftLowGear.whenPressed(new LowGear());
        intakeFeedBelt.whenPressed(new SetFeedBeltIntake());
        outtakeFeedBelt.whenPressed(new SetFeedBeltOuttake());
        fireBall.whileHeld(new FireBall());
        dropBridge.whileHeld(new DropBridgeMounter());
        stowBridge.whileHeld(new StowBridgeMounter());
        turnSusanLeft.whileHeld(new LazySusanLeft());
        turnSusanRight.whileHeld(new LazySusanRight());
        
        //SmartDashboard Buttons
        SmartDashboard.putData("SetFiringMotors",smartDashboardButton1);
        smartDashboardButton1.whenPressed(new SetFiringMotors(1) );
    }
    
    /**
     * Get the Left Joystick Object
     * @return left joystick object
     */
    public Joystick getLeftJoystick() {
        return leftJoystick;
    }
    
    /**
     * Get the Right Joystick Object
     * @return right joystick object
     */
    public Joystick getRightJoystick() {
        return rightJoystick;
    }
    
    /**
     * get Fire Button Object
     * @return fire button object
     */
    public Button getFireButton() {
        return fireBall;
    }
    
    /**
     * get High Gear Shift Button
     * @return high gear shift button
     */
    public Button getHighGearShift() {
        return shiftHighGear;
    }
    
    /**
     * get Low Gear Shift Button
     * @return low gear shift button 
     */
    public Button getLowGearShift() {
        return shiftLowGear;
    }
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}
