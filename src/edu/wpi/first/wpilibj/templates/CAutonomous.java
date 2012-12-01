/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author fauzi
 */
public class CAutonomous {
    
    // CONSTANTS
    private final String sAutoCenter = "file:///autoval.txt";
    private final String sAutoLeft = "file:///autoLeft.txt";
    private final String sAutoRight = "file:///autoRight.txt";
    
    private CSpecialButton btRecord = new CSpecialButton();
    private CSpecialButton btReplay = new CSpecialButton();
    private CButton btChangeMode = new CButton();
    private boolean bAnotherIsPressed = false; 
    private boolean bAutoEditMode = false;
    private int iFileMode = 0;
    private String sAutonmousStatus = "Doing Nothing";
    private String sFileType = "Reg: ";
    private CRecorder recorder;
    private CReplayer replayer; 
    private Joystick joy;
    
    
    public CAutonomous(Joystick joystick, Drive drive, Cannon shoot, CUnderGlow underGlow)
    {
        joy = joystick;
        recorder = new CRecorder(drive, shoot, underGlow);
        replayer = new CReplayer(drive, shoot, underGlow);
    }
    
    public void run()
    {
        bAnotherIsPressed = btRecord.run(joy.getRawButton(Var.btRecord), bAnotherIsPressed);
        bAnotherIsPressed = btReplay.run(joy.getRawButton(Var.btReplay), bAnotherIsPressed);
        btChangeMode.run(joy.getRawButton(Var.btChangeFile));
        
        if(!btRecord.getSwitch() && !btReplay.getSwitch())
        {
            if(btChangeMode.gotPressed())
            {
                if(++iFileMode >= Var.iFileMax)
                    iFileMode = 0;

                changeFile(iFileMode); // Changes the file 
            }
        }

        if(btRecord.getSwitch())   
            sAutonmousStatus = record(Var.sFileType);		

        else if(btReplay.getSwitch())
            sAutonmousStatus = replay(Var.sFileType);
        
        else
        {
            sAutonmousStatus = "Doing Nothing";
            reset();
        }
        
        Var.drvStationPrinter.print(Var.iRecordStatusLine, sFileType + sAutonmousStatus);
    }
    
    public String replay(String sFileName)
    {
        return replayer.replay(sFileName);
    }
    
    private String record(String sFileName)
    {
        return recorder.record(sFileName, bAutoEditMode);
    }
    
    private void reset() // Resets timer and booleans so that you can record or replay again
    {  
        Var.bDrive = true;
        replayer.reset();
        recorder.reset();
    }
    
    public void resetAutonomous()
    {
        if(btReplay.getSwitch())
            bAnotherIsPressed = btReplay.setOppisite();
        
        else if(btRecord.getSwitch())
            bAnotherIsPressed = btRecord.setOppisite();

        reset();
    }
    
    public void changeFile(int iFileType)
    {
        switch (iFileType)
        {
            case Var.chnDigInReg:   // 0
            {
                sFileType = "Reg: ";
                Var.sFileType = Var.sRegOutput;
                break;
            }

            case Var.chnDigInAutoCtr:   // 1
            {
                sFileType = "AutoCenter: ";
                Var.sFileType = sAutoCenter; 
                break;
            } 

            case Var.chnDigInAutoLft:   // 2
            {
                sFileType = "AutoLeft: ";
                Var.sFileType = sAutoLeft;
                break;
            }

            case Var.chnDigInAutoRght:  // 3
            {
                sFileType = "AutoRight: ";
                Var.sFileType = sAutoRight; 
                break;
            }
                
            default:
            {
                sFileType = "AutoCenter: ";
                Var.sFileType = sAutoCenter; 
                break;
            }
        }
    }
}
