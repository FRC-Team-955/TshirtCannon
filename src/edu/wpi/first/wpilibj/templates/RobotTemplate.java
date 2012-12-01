/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

/*where everything actually takes place.
 * For more information ask Ryan, programming captian.
 */
// SOLENOID BUMPER CHANNEL 7 OR 8 DOES NOT WORK!!!!!!
// Max time to charge befpre valves pop off : 3.85 * 4, ABOUT 15
public class RobotTemplate extends IterativeRobot {
    Joystick ps3Joy = new Joystick(1); 
    Drive drive = new Drive(ps3Joy);
    Cannon cannon = new Cannon(ps3Joy, drive);
    CUnderGlow underGlow = new CUnderGlow(ps3Joy);
    CAutonomous autonomous = new CAutonomous(ps3Joy, drive, cannon, underGlow);
	
    /**
    * This function is run when the robot is first started up and should be
    * used for any initialization code.
    */
    public void robotInit() {
    ps3Joy.setAxisChannel(Joystick.AxisType.kX, Var.chanJoyDrive);
    underGlow.set(true);

    }
        // This function is called when we disable the robot.
    public void disabledInit()
    {
        autonomous.resetAutonomous(); // Resets the replay to false if it was true before
    }
    
    // Called once in autonomous
    public void autonomousInit()
    {
        int iFileType = Var.chnDigInReg;
        
        if(DriverStation.getInstance().getDigitalIn(Var.chnDigInAutoCtr))
            iFileType = Var.chnDigInAutoCtr;
        
        else if(DriverStation.getInstance().getDigitalIn(Var.chnDigInAutoLft))
            iFileType = Var.chnDigInAutoLft;
        
        else if(DriverStation.getInstance().getDigitalIn(Var.chnDigInAutoRght))
            iFileType = Var.chnDigInAutoRght;
        
        autonomous.changeFile(iFileType);
    }
    
    // This function is called periodically during autonomous
    public void autonomousPeriodic() {
        autonomous.replay(Var.sFileType);
    }

    /**
     * This function is called once each time the robot enters operator control. False
     */
    public void teleopPeriodic() {
        drive.run();
        cannon.run();

        
        underGlow.run();
        autonomous.run();
    }
}
/*
 * Some prefixes use in this prject
 * mtSomething = motor
 * iSomething = int
 * sSomething = string 
 * bSomething = boolean
 */
