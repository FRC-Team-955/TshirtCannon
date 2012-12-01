/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author fauzi
 */
public class CRecorder {

    private String sRecordStaus = "";
    private boolean bRecStarted = false;
    private CFileWriter fileWriter;
    private CTimer tmRecord = new CTimer();
    private Drive driver;
    private Cannon shooter;
    private CUnderGlow underGlow;
    
    public CRecorder(Drive drive, Cannon shoot, CUnderGlow glow)
    {
        driver = drive;
        shooter = shoot;
        underGlow = glow;
    }
    
    public String record(String sFileName, boolean bAutoEditMode)
    {
        if(bAutoEditMode == false && sFileName != Var.sRegOutput)
            sRecordStaus = "Can't Edit Autofile";
        
        else
        {
            if(!bRecStarted)
            {
                sRecordStaus = "Recording";
                fileWriter = new CFileWriter(sFileName);
                tmRecord.start();
                bRecStarted = true;
            }

            fileWriter.writeData(tmRecord.get(), driver.getMtLeftSpeed(), driver.getMtRightSpeed(), shooter.getTurretUpStatus(), underGlow.getLightStatus());
        }
        
        return sRecordStaus;
    }
    
    public void reset()
    {
        if(bRecStarted)
        {
            fileWriter.writeDouble(Var.dENDSIGNAL);
            fileWriter.close();
            tmRecord.reset(true);
            bRecStarted = false;
        }
    }
}
