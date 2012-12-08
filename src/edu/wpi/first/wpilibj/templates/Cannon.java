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
    
    private Solenoids solMoveTurret = new Solenoids(Var.chanTurretMoveUpTShirt, Var.chanTurretMoveDownTShirt, true);
    private Solenoids solShootShirt = new Solenoids(Var.chanSolShootUpTShirt, Var.chanSolShootDownTShirt, true);
    private Solenoids solChargeTurret = new Solenoids(Var.chanSolUpChargeShirt, Var.chanSolDownChargeShirt, false);
    private Button btChargeTurret = new Button();
    private Button btShootShirt = new Button();
    private Button btAimTur = new Button();
    private Button btChargeTmLower = new Button(); 
    private Button btChargeTmHigher = new Button(); 
    private Button btEnableKickBack = new Button();
    private Timer tSolTurretOff = new Timer();
    private Timer tSolChargeTurret = new Timer();
    private boolean bIsCharging = false;
    private boolean bTurretUp = false;
    private int iChargeFactor = 4;
    private int iPrint = 0;
    private String sChargeTm;
    private PrintDriver printDriver = new PrintDriver();
    private Joystick joy;
    private Drive driver;

    public Cannon(Joystick joystick, Drive drive)
    {
        joy = joystick;
        driver = drive;
    }
    
    public void run()
    {        
        btShootShirt.run(joy.getRawButton(Var.btShootShirt));
        btAimTur.run(joy.getRawButton(Var.btAimDown));
        btChargeTurret.run(joy.getRawButton(Var.btChargeShirt));
        btChargeTmLower.run(joy.getRawButton(Var.btChrgTmLower));
        btChargeTmHigher.run(joy.getRawButton(Var.btChrgTmHigher));
        btEnableKickBack.run(joy.getRawButton(Var.btJoyKickBack));
        
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
