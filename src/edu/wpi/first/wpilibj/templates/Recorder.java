/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;


/**
 *
 * @author fauzi
 */
import edu.wpi.first.wpilibj.Timer;
public class Recorder {

    private String m_sRecordStaus = "";
    private boolean m_bRecStarted = false;
    private FileWriter m_fileWriter;
    private Timer m_tmRecord = new Timer();
    private Robot m_bot;
    
    public Recorder(Robot robot)
    {
        m_bot = robot;
    }
    
    public String record(String sFileName)
    {
        if(!m_bRecStarted)
        {
            m_fileWriter = new FileWriter(sFileName);
            m_tmRecord.start();
            m_bRecStarted = true;
        }
        
        m_sRecordStaus = "Recording: " + Var.setPrecision(m_tmRecord.get());
        m_fileWriter.writeData(m_tmRecord.get(), m_bot.getMtLeft(), m_bot.getMtRight(), m_bot.getShootUp(), m_bot.getUnderGlow());

        return m_sRecordStaus;
    }
    
    public void reset()
    {
        if(m_bRecStarted)
        {
            m_fileWriter.writeDouble(Var.dENDSIGNAL);
            m_fileWriter.close();
            m_tmRecord.stop();
            m_bRecStarted = false;
        }
    }
}
