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
    static final int btShootShirt = 4;
    static final int btAimUp = 1;
    static final int btAimDown = 3;
    static final int btChargeShirt = 8;
    static final int btChrgTmLower = 5;
    static final int btChrgTmHigher = 6;
    static final int btJoyKickBack = 12;
    static int btRecord = 9;
    static int btReplay = 2;
    static final int btLight = 11;
    
    // Other
    //static boolean bRecord = false;
    static boolean bDrive = true;
    static boolean bShooting = false;
    static final double kickBackSpeed = 1;
    static final double jagurRampSpeed = 0.1;
    static final double dEndSignal = -10;
    static final int chanJoyDrive = 3;
    static boolean bAnotherIsPressed = false;
    static final String sAutoOutput = "file:///TshirtCannonAuto.txt";
    
    // Printing to Driverstation lines, 2-6 are available only
    static final int iChargeFactorLine = 2;
    static final int iChargeStatusLine = 3;
    static final int iKickBackLine = 4;
    static final int iRecordStatusLine = 5;
}
