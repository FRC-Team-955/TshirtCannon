/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author fauzi
 */
public class Replayer {
    
    private boolean m_bRepStarted = false;
    private boolean m_bDoneReplay = false;
    private JoyEmulator m_joyAuto = new JoyEmulator();
    private Timer m_tmReplay = new Timer();
    private String m_sReplayStatus = "";
    private FileReader m_fileReader;
    private Robot m_bot;
    
    public Replayer(Robot robot)
    {
        m_bot = robot;
    }
    
    public String replay(String sFileName)
    {
        Var.bDrive = false;
         
        if(!m_bRepStarted)
        {
            m_fileReader = new FileReader(sFileName);
            m_joyAuto.add(m_fileReader.readDouble(), m_fileReader.readDouble(), m_fileReader.readDouble(), m_fileReader.readBoolean(), m_fileReader.readBoolean());
            m_tmReplay.start();
            m_bRepStarted = true;
        }

        if(!m_bDoneReplay)
        {
            m_sReplayStatus = "Replaying: " + Var.setPrecision(m_tmReplay.get());
            m_bot.setSpeed(m_joyAuto.getMtLeft(), m_joyAuto.getMtRight());
            m_bot.setShootUp(m_joyAuto.getTurUp());
            m_bot.setUnderGlow(m_joyAuto.getGlowStat());

            if(m_tmReplay.get() >= m_joyAuto.getTimer())
            {
                double dTemp = m_fileReader.readDouble(); // Temp var to see if we're done replay

                if(dTemp < Var.dENDSIGNAL+1) // If true, means we're done replaying
                    m_bDoneReplay = true;

                else
                    m_joyAuto.add(dTemp, m_fileReader.readDouble(), m_fileReader.readDouble(), m_fileReader.readBoolean(), m_fileReader.readBoolean());
            }
        }

        else
        {
            m_sReplayStatus = "Done Replaying";
            m_bot.setSpeed(0, 0);
            m_fileReader.close();
            m_tmReplay.stop();
        }
        
        return m_sReplayStatus;
    }
    
    public void reset()
    {
        if(m_bRepStarted)
        {
            if(!m_fileReader.isClosed())
                m_fileReader.close();
            
            m_tmReplay.stop();
            m_bDoneReplay = false;
            m_bRepStarted = false;
        }
    }
}
