/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;;

/**
 *
 * @author ryan
 */

/*
 * This class is responsible for the "cannon" part of the t-shirt cannon.
 * It shoots shirt and raises or lowers the shirt shooter
 */

public class Cannon {
    
    private CSolenoids solMoveTurret = new CSolenoids(Var.chanTurretMoveUpTShirt, Var.chanTurretMoveDownTShirt, true);
    private CSolenoids solShootShirt = new CSolenoids(Var.chanSolShootUpTShirt, Var.chanSolShootDownTShirt, true);
    private CSolenoids solChargeTurret = new CSolenoids(Var.chanSolUpChargeShirt, Var.chanSolDownChargeShirt, false);
    private CButton btChargeTurret = new CButton();
    private CButton btShootShirt = new CButton();
    private CButton btAimTur = new CButton();
    private CButton btChargeTmLower = new CButton(); 
    private CButton btChargeTmHigher = new CButton(); 
    private CButton btEnableKickBack = new CButton();
    private Timer tSolTurretOff = new Timer();
    private Timer tSolChargeTurret = new Timer();
    private boolean bIsCharging = false;
    private boolean bTurretUp = false;
    private int iChargeFactor = 4;
    private int iPrint = 0;
    private String sChargeTm;
    private CPrintDriver printDriver = new CPrintDriver();

    public void run(Joystick joy, Drive driver)
    {        
        btShootShirt.run(joy.getRawButton(Var.buttonShootShirt));
        btAimTur.run(joy.getRawButton(Var.buttonAimDown));
        btChargeTurret.run(joy.getRawButton(Var.buttonChargeShirt));
        btChargeTmLower.run(joy.getRawButton(Var.buttonChrgTmLower));
        btChargeTmHigher.run(joy.getRawButton(Var.buttonChrgTmHigher));
        btEnableKickBack.run(joy.getRawButton(Var.buttonJoyKickBack));
        
        if(btAimTur.gotPressed())
        {
            bTurretUp = !bTurretUp;
            solMoveTurret.set(bTurretUp);
        }
        
        if(btChargeTurret.gotPressed() && Var.bShooting == false)   // Charges turret 
        {
            iPrint = 1;
            bIsCharging = true;
            solChargeTurret.turnOn();
            tSolChargeTurret.start();
        }
       
        if(btChargeTmLower.gotPressed() && (iChargeFactor - 1) > 0) // Decreases charge time
            iChargeFactor--;
        
        if(btChargeTmHigher.gotPressed())   // Increases charge time
            iChargeFactor++;
        		
        if(tSolChargeTurret.get() > iChargeFactor-1)  // Chargin turret for specified amount of time
        {
            iPrint = 2;
            bIsCharging = false;
            solChargeTurret.turnOff();
            tSolChargeTurret.stop();
            tSolChargeTurret.reset();
        }
	
        if(btShootShirt.gotPressed() && bIsCharging == false && solMoveTurret.getUp())    // Shoots shirt
        {
            Var.bShooting = true;
            
            if(btEnableKickBack.getSwitch())    // Creates fake kickback
                driver.setSpeed(Var.kickBackSpeed, -Var.kickBackSpeed);
            
            solShootShirt.turnOn();
            tSolTurretOff.start();
            iPrint = 0;
        }
		
        if(tSolTurretOff.get() > 0.25 && btEnableKickBack.getSwitch())  // Sets speed back to 0
            driver.setSpeed(0,0);
        
        if(tSolTurretOff.get() > 1)
        {	
            Var.bShooting = false;
            solShootShirt.turnOff();
            tSolTurretOff.stop();
            tSolTurretOff.reset();
        }
        
        // Printing to Driver Station 
        
        sChargeTm = Integer.toString(iChargeFactor);
        printDriver.print(Var.iChargeFactorLine, "Charge Factor: " + sChargeTm);
        
        if(btEnableKickBack.getSwitch())
            printDriver.print(Var.iKickBackLine, "Kickback: Enabled");
        
        else if(!btEnableKickBack.getSwitch())
            printDriver.print(Var.iKickBackLine, "Kickback: Disabled");
        
        printChargeStatus(iPrint);
    }
    
    private void printChargeStatus(int i)
    {
        if(i == 0)
            printDriver.print(Var.iChargeStatusLine, "Turret Not Charged");
        
        else if(i == 1)
            printDriver.print(Var.iChargeStatusLine, "Charging Turret");
        
        else if(i == 2)
            printDriver.print(Var.iChargeStatusLine, "Turret Charged!!!");
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
