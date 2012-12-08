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
public class Robot {
    
    private Joystick m_joy;
    private Drive m_drive;
    private Cannon m_shooter;
    private UnderGlow m_underGlow;
    
    public Robot(Joystick joytick)
    {
        m_joy = joytick;
        m_drive = new Drive(m_joy);
        m_shooter = new Cannon(m_joy, m_drive);
        m_underGlow = new UnderGlow(m_joy);
    }
    
    public void run()
    {
        m_drive.run();
        m_shooter.run();
        m_underGlow.run();
    }
    
    public double getMtLeft()
    {
        return m_drive.getMtLeftSpeed();
    }
    
    public double getMtRight()
    {
        return m_drive.getMtRightSpeed();
    }
    
    public boolean getShootUp()
    {
        return m_shooter.getTurretUpStatus();
    }
    
    public boolean getUnderGlow()
    {
        return m_underGlow.getLightStatus();
    }
    
    public void setSpeed(double dLeft, double dRight)
    {
        m_drive.setSpeed(dLeft, dRight);
    }
    
    public void setShootUp(boolean bOut)
    {
        m_shooter.set(bOut);
    }
    
    public void setUnderGlow(boolean bOn)
    {
        m_underGlow.set(bOn);
    }
}
