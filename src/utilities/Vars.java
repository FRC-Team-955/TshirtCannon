package utilities;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 * This class has all the variables that are used the set the 
 * channels, slots or speed parameters for any object for BunnyBot 2012.
 * We have a separate class just so we can change the channel more easily rather
 * than searching through the class itself.
 * @author Fauzi
 */
public class Vars {
    
    // PWM
    public static final int chnVicDrvLeft = 1;
    public static final int chnVicDrvRight = 2;
    public static final int chnVicLight = 3;

    // Solenoids
    public static final int chnSolShootUpTShirt = 1;
    public static final int chnSolShootDownTShirt = 2;
    public static final int chnTurretMoveUpTShirt = 3;
    public static final int chnTurretMoveDownTShirt = 4;
    public static final int chnSolUpChargeShirt = 5;
    public static final int chnSolDownChargeShirt = 6;
    
    // Joysticks and buttons
    public static final int btAimCannon = 1;
    public static final int btKickBack = 2;
    public static final int btShootShirt = 4;
    public static final int btChrgTmLower = 5;
    public static final int btChrgTmHigher = 6;
    public static final int btChrgShirt = 8;
    public static final int btRecord = 9;
    public static final int btChangeFile = 11;
    public static final int btReplay = 12;
    
    // Other
    private static boolean bDrive = true;
    public static boolean bShooting = false;
    public static final int iPs3Buttons = 13;
    
    // DriverStation Autonomous Button Channels
    public static final int stDigInAutoCtr = 1;
    public static final int stDigInAutoLft = 2;
    public static final int stDigInAutoRght = 3;
    public static final int stDigInReg = 4;
    
    // Printing to Driverstation lines, 2-6 are available only
    public static final int prChargeFactorLine = 2;
    public static final int prChargeStatusLine = 3;
    public static final int prKickBackLine = 4;
    public static final int prDriveStatusLine = 5;
    public static final int prAutonomousStatLine = 6;
    
    
    /**
     * Sets the double to be only at the hundreth's place, ex. 12.34.
     */
    public static double fnSetPrecision(double dDouble)
    {
        return (Double.valueOf(Math.floor(dDouble * 10 + 0.5) / 10)).doubleValue();
    }
    
    /**
     * Disables ability for user to drive robot.
     */
    public static void fnDisableDrive()
    {
        bDrive = false;
    }
    
    /**
     * Enables ability for the user to drive the robot.
     */
    public static void fnEnableDrive()
    {
        bDrive = true;
    }
    
    /**
     * Checks if the user has as the ability to drive the robot.
     */
    public static boolean fnCanDrive()
    {
        return bDrive;
    }
    
    /**
     * Gets the button status from the driverstation, 1 - 8 available.
     * @param iChan
     * @return 
     */
    public static boolean fnDriverGetDigitalIn(int iChan)
    {
        return DriverStation.getInstance().getDigitalIn(iChan);
    }
    
    /**
     * Prints specified message to the driver station on the corresponding line
     * 2-6 are available.
     */
    public static void fnPrintToDriverstation(int iLine, String sMessage)
    {
        switch(iLine)
        {
            case 2:
            {
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, "");
                DriverStationLCD.getInstance().updateLCD(); 
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, sMessage);
                break;
            }
                
            case 3:
            {
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "");
                DriverStationLCD.getInstance().updateLCD(); 
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, sMessage);
                break;
            }
                
            case 4:
            {
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4, 1, "");
                DriverStationLCD.getInstance().updateLCD(); 
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4, 1, sMessage);
                break;
            }
                
            case 5:
            {
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser5, 1, "");
                DriverStationLCD.getInstance().updateLCD(); 
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser5, 1, sMessage);
                break;
            }
                
            case 6:
            {
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser6, 1, sMessage);
                DriverStationLCD.getInstance().updateLCD(); 
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser6, 1, sMessage);
                break;
            }
        }
        
        DriverStationLCD.getInstance().updateLCD(); 
    }
}