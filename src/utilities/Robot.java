package utilities;

import core.Drive;
import core.Cannon;

/**
 *
 * @author fauzi
 */
public class Robot {
    
    private MyJoystick m_joy;
    private Drive m_drive;
    private Cannon m_cannon;
    
    public Robot(MyJoystick joytick)
    {
        m_joy = joytick;
        m_drive = new Drive(m_joy);
        m_cannon = new Cannon(m_joy, m_drive);
    }
    
    public void run()
    {
        m_drive.run();
        m_cannon.run();
    }
    
    public boolean getCannonUp()
    {
        return m_cannon.getTurretUpStatus();
    }
    
    public double getMotorLeft()
    {
        return m_drive.getMotorLeft();
    }
    
    public double getMotorRight()
    {
        return m_drive.getMotorRight();
    }
    
    public void setSpeed(double dLeft, double dRight)
    {
        m_drive.setSpeed(dLeft, dRight);
    }
    
    public void setCannonUp(boolean bCannonUp)
    {
        m_cannon.set(bCannonUp);
    }
}
