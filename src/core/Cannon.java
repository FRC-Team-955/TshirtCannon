/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import utilities.MyJoystick;
import utilities.MySolenoid;
import utilities.Vars;

/**
 *
 * @author ryan
 */

/*
 * This class is responsible for the "cannon" part of the t-shirt cannon.
 * It shoots shirt and raises or lowers the shirt shooter
 * Also has the motor for controlling the Underglow
 */

public class Cannon {
    
    private Victor mtUnderGlow = new Victor(Vars.chnVicLight);
    private MySolenoid solMoveTurret = new MySolenoid(Vars.chnTurretMoveUpTShirt, Vars.chnTurretMoveDownTShirt, false);
    private MySolenoid solShootShirt = new MySolenoid(Vars.chnSolShootUpTShirt, Vars.chnSolShootDownTShirt, false);
    private MySolenoid solChargeTurret = new MySolenoid(Vars.chnSolUpChargeShirt, Vars.chnSolDownChargeShirt, true);
    private Timer tmTurretOff = new Timer();
    private Timer tmChargeTurret = new Timer();
    private boolean bIsCharging = false;
    private boolean bTurretUp = false;
    private boolean bKickBack = false;
    private int iChargeTime = 4;    // In seconds
    private String sCannonStat = "";
    private String sKickBackStat = "";
    private String sChargeTm = "";
    private MyJoystick joy;
    private Drive driver;

    public Cannon(MyJoystick joystick, Drive drive)
    {
        joy = joystick;
        driver = drive;
    }
    
    public void run()
    {
        mtUnderGlow.set(1);
        
        if(joy.gotPressed(Vars.btKickBack))
        {
            bKickBack = !bKickBack;
            
            if(bKickBack)
                sKickBackStat = "Kickback: Enabled";
            
            else
                sKickBackStat = "Kickback: Disabled";
        }
        
        if(joy.gotPressed(Vars.btAimDown))
        {
            bTurretUp = !bTurretUp;
            solMoveTurret.set(bTurretUp);
        }
        
        if(joy.gotPressed(Vars.btChrgShirt))   // Charges turret 
        {
            sCannonStat = "Charging Cannon";
            bIsCharging = true;
            solChargeTurret.turnOn();
            tmChargeTurret.start();
        }
       
        if(joy.gotPressed(Vars.btChrgTmLower) && (iChargeTime - 1) > 0) // Decreases charge time
            iChargeTime--;
        
        if(joy.gotPressed(Vars.btChrgTmHigher))   // Increases charge time
            iChargeTime++;
        		
        if(tmChargeTurret.get() > iChargeTime-1)  // Chargin turret for specified amount of time
        {
            sCannonStat = "Cannon Charged";
            bIsCharging = false;
            solChargeTurret.turnOff();
            tmChargeTurret.stop();
            tmChargeTurret.reset();
        }

        if(joy.gotPressed(Vars.btShootShirt) && bIsCharging == false && solMoveTurret.getUp())    // Shoots shirt
        {
            Vars.bShooting = true;
            
            if(bKickBack)    // Creates fake kickback
                driver.setSpeed(Vars.kickBackSpeed, -Vars.kickBackSpeed);
          
            solShootShirt.turnOn();
            tmTurretOff.start();
            sCannonStat = "Cannon NOT Charged";
        }

        if(tmTurretOff.get() > 0.25 && bKickBack)  // Sets speed back to 0
            driver.setSpeed(0,0);
        
        if(tmTurretOff.get() > 1)
        {	
            Vars.bShooting = false;
            solShootShirt.turnOff();
            tmTurretOff.stop();
            tmTurretOff.reset();
        }
        
        // Printing to Driver Station 
        sChargeTm = Integer.toString(iChargeTime);
        Vars.fnPrintToDriverstation(Vars.iChargeFactorLine, "Charge Factor: " + sChargeTm);
        Vars.fnPrintToDriverstation(Vars.iKickBackLine, sKickBackStat);
        Vars.fnPrintToDriverstation(Vars.iChargeStatusLine, sCannonStat);
    }
    
    public void set(boolean bUp)
    {
        if(bUp)
            solMoveTurret.turnOn();
        
        else
            solMoveTurret.turnOff();
    }
    
    public boolean getTurretUpStatus()
    {
        return bTurretUp;
    }
}