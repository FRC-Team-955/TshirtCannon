package core;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import utilities.MyJoystick;
import utilities.MySolenoid;
import utilities.Vars;

/**
 * This class is responsible for the "cannon" part of the t-shirt cannon.
 * It shoots shirt and raises or lowers the shirt shooter.
 * @author Fauzi
 */

public class Cannon {
    
    // CONSTANTS
    private static final int ikickBackSpeed = 1;
    
    private MySolenoid solMoveTurret = new MySolenoid(Vars.chnTurretMoveUpTShirt, Vars.chnTurretMoveDownTShirt, false);
    private MySolenoid solShootShirt = new MySolenoid(Vars.chnSolShootUpTShirt, Vars.chnSolShootDownTShirt, false);
    private MySolenoid solChargeTurret = new MySolenoid(Vars.chnSolUpChargeShirt, Vars.chnSolDownChargeShirt, true);
    private Timer tmTurretOff = new Timer();
    private Timer tmChargeTurret = new Timer();
    private int iChargeTime = 4;    // In seconds
    private String sCannonStat = "";
    private String sKickBackStat = "";
    private MyJoystick joy;
    private Drive driver;

    public Cannon(MyJoystick joystick, Drive drive)
    {
        joy = joystick;
        driver = drive;
    }
    
    public void run()
    {
        updateChargeTime();
        updateKickback();
        updateCannonPos();        
        
        if(joy.gotPressed(Vars.btChrgShirt))   // Charges turret 
        {
            sCannonStat = "Charging Cannon";
            joy.flipSwitch(Vars.btChrgShirt);
            solChargeTurret.turnOn();
            tmChargeTurret.start();
        }
        
        if(tmChargeTurret.get() > iChargeTime-1)  // Charging turret for specified amount of time
        {
            sCannonStat = "Cannon Charged";
            joy.flipSwitch(Vars.btChrgShirt);
            solChargeTurret.turnOff();
            tmChargeTurret.stop();
            tmChargeTurret.reset();
        }

        if(joy.gotPressed(Vars.btShootShirt) && !joy.getSwitch(Vars.btChrgShirt) && solMoveTurret.getUp())    // Shoots shirt
        {
            Vars.bShooting = true;
            
            if(joy.getSwitch(Vars.btKickBack))    // Creates fake kickback
                driver.setSpeed(ikickBackSpeed, -ikickBackSpeed);
          
            solShootShirt.turnOn();
            tmTurretOff.start();
            sCannonStat = "Cannon NOT Charged";
        }

        if(tmTurretOff.get() > 0.25 && joy.getSwitch(Vars.btKickBack))  // Sets speed back to 0
            driver.setSpeed(0,0);
        
        if(tmTurretOff.get() > 1)
        {	
            Vars.bShooting = false;
            solShootShirt.turnOff();
            tmTurretOff.stop();
            tmTurretOff.reset();
        }
        
        // Printing to Driver Station 
        Vars.fnPrintToDriverstation(Vars.prChargeFactorLine, "Charge Factor: " + iChargeTime);
        Vars.fnPrintToDriverstation(Vars.prKickBackLine, sKickBackStat);
        Vars.fnPrintToDriverstation(Vars.prChargeStatusLine, sCannonStat);
    }
    
    public void set(boolean bUp)
    {
        solMoveTurret.set(bUp);
    }
    
    public boolean getTurretUpStatus()
    {
        return joy.getSwitch(Vars.btAimCannon);
    }
    
    private void updateChargeTime()
    {
        if(joy.gotPressed(Vars.btChrgTmLower) && (iChargeTime - 1) > 0) // Decreases charge time
            iChargeTime--;
        
        if(joy.gotPressed(Vars.btChrgTmHigher))   // Increases charge time
            iChargeTime++;
    }
    
    private void updateKickback()
    {
        if(joy.gotPressed(Vars.btKickBack))
        {
            joy.flipSwitch(Vars.btKickBack);
            
            if(joy.getSwitch(Vars.btKickBack))
                sKickBackStat = "Kickback: Enabled";
            
            else
                sKickBackStat = "Kickback: Disabled";
        }
    }
    
    private void updateCannonPos()
    {
        if(joy.gotPressed(Vars.btAimCannon))
        {
            joy.flipSwitch(Vars.btAimCannon);
            solMoveTurret.set(joy.getSwitch(Vars.btAimCannon));
        }
    }
}