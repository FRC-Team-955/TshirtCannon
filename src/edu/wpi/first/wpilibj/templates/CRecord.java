package edu.wpi.first.wpilibj.templates;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * This class will hopefully be responsible for recording the robots 
 * movements and "replaying" them.
 */
//package recording;

import edu.wpi.first.wpilibj.*;
//test
/**
 *
 * @author Fauzi
 */

public class CRecord {

    Timer trRecord = new Timer();
    Timer trReplay = new Timer();
    CJoystickMimic joyEmu = new CJoystickMimic();
    double x = 0;
    double y = 0;
    CButton btRecord = new CButton();
    CButton btReplay = new CButton();
    CButton btClear = new CButton();
    boolean bRecStart = false;
    boolean bRepStart = false;
    boolean bRepDone = false;
    boolean printReg = false;
    int iPrintWhat = 6;
    CPrintDriver printToDriverSt = new CPrintDriver();
   
    public void run(Joystick joy, Cannon cannon, Drive driver, CUnderGlow underGlow)
    {
        btRecord.run(joy.getRawButton(Var.buttonRecord));
        btReplay.run(joy.getRawButton(Var.buttonReplay));
        btClear.run(joy.getRawButton(Var.buttonClearList));
        
        if(btClear.gotPressed())
                clearAll();
        
        if(btRecord.getSwitch())    // Records the joystics X's and Y's into a linked list
        {            
            try
            {
                iPrintWhat = 1;
                
                if(printReg)
                    System.out.println("Recording");

                if(!bRecStart)
                {
                    trRecord.start();
                    bRecStart = true;
                }

                y = joy.getY() * Math.abs(joy.getY());
                x = joy.getX() * Math.abs(joy.getX());
                
                joyEmu.add(trRecord.get(), driver.getMtLeftSpeed(), driver.getMtRightSpeed(), cannon.getTurretUpStatus(), underGlow.getLightStatus());
            }
            
            catch(OutOfMemoryError E)
            {
                iPrintWhat = 5;
                
                if(printReg)
                    System.out.println("Out of memory, cant Record");
            }
        }
		
        else if(btReplay.getSwitch())   // Inputs the values into the drive class, etc...
        {               
            Var.bDrive = false;
            
            if(joyEmu.isEmpty() == false)
            {
                if(!bRepStart)
                {
                    trReplay.start();
                    bRepStart = true;
                }

                if(trReplay.get() <= trRecord.get() && bRepDone == false)
                {
                    iPrintWhat = 2;
                    driver.setSpeed(joyEmu.getMtLeft(-1), joyEmu.getMtRight(-1));
                    cannon.set(joyEmu.getTurret(-1));
                    underGlow.set(joyEmu.getLight(-1));

                    if(trReplay.get() > joyEmu.getTmr(-1))
                        joyEmu.getNext();
                    
                    if(printReg)
                        System.out.println("Replaying");
                }

                if(joyEmu.getIter() == joyEmu.size()-1) 
                {
                    iPrintWhat = 3;
                    driver.setSpeed(0,0);
                    bRepDone = true;
                    
                    if(printReg)
                        System.out.println("Done");
                }
            }
            
            else
            {
                iPrintWhat = 4;
                
                if(printReg)
                    System.out.println("Nothing Recorded");
            }
        }
        
        else
        {
            Var.bDrive = true;
            bRecStart = false;
            bRepStart = false;
            bRepDone = false;
            trReplay.stop();
            trReplay.reset();
            trRecord.stop();
            joyEmu.reset();
            iPrintWhat = 6;
            
            if(printReg)
                System.out.println("Doing Nothing");
        }
        
        printStatus(iPrintWhat);
    }
    
    private void clearAll() // To clear the memory so you can record something else
    {
        trRecord.stop();
        trRecord.reset();
        trReplay.stop();
        trReplay.reset();
        joyEmu.deleteAll();
        x = 0;
        y = 0;
        bRecStart = false;
        bRepStart = false;
        bRepDone = false;
    }
    
    private void printStatus(int i)
    {
        if(i == 1)
            printToDriverSt.print(Var.iCRecordStatus, "Recording");
        
        else if(i == 2)
            printToDriverSt.print(Var.iCRecordStatus, "Replaying");
        
        else if(i == 3)
            printToDriverSt.print(Var.iCRecordStatus, "Done");
        
        else if(i == 4)
            printToDriverSt.print(Var.iCRecordStatus, "Nothing Recorded");
        
        else if(i == 5)
            printToDriverSt.print(Var.iCRecordStatus, "Out of memory, can't Record");
        
        else if(i == 6)
            printToDriverSt.print(Var.iCRecordStatus, "Doing Nothing");
    }
}
