/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Fauzi
 */

/*
 * This class has all the variables that are used the set the 
 * channels, slots or speed parameters for any object in the t-shirt cannon.
 * We have a seperate class just so we can change the channel more easily rather
 * than seaching through the class itself.
 */
public class Var {
    
    // PWM
    static final int chanVicDriveRight = 1;
    static final int chanVicDriveLeft = 2;
    static final int chanVicLight = 3;
	
    // Solenoids
    static final int chanSolShootUpTShirt = 1;
    static final int chanSolShootDownTShirt = 2;
    static final int chanTurretMoveUpTShirt = 3;
    static final int chanTurretMoveDownTShirt = 4;
    static final int chanSolUpChargeShirt = 5;
    static final int chanSolDownChargeShirt = 6;
    
    // Joysticks and buttons
    static final int buttonShootShirt = 4;
    static final int buttonAimUp = 1;
    static final int buttonAimDown = 3;
    static final int buttonChargeShirt = 8;
    static final int buttonChrgTmLower = 5;
    static final int buttonChrgTmHigher = 6;
    static final int buttonJoyKickBack = 2;
    static int buttonRecord = 9;
    static int buttonReplay = 12;
    static int buttonClearList = 10;
    static final int buttonLight = 11;
    
    // Other
    //static boolean bRecord = false;
    static boolean bDrive = true;
    static boolean bShooting = false;
    static final double kickBackSpeed = 1;
    static final double jagurRampSpeed = 0.1;
    static final int chanJoyDrive = 3;
    
    // Printing to Driverstation lines, 2-6 are available only
    static final int iChargeFactorLine = 2;
    static final int iChargeStatusLine = 3;
    static final int iKickBackLine = 4;
    static final int iCRecordStatus = 5;
}
