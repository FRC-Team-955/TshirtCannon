package utilities;

import core.Drive;
import core.Cannon;

/**
 * This class encapsulates the robot systems.
 * @author fauzi
 */
public class Robot {
    
    private static MyJoystick m_joy;
    private static Drive m_drive;
    private static Cannon m_cannon;
    
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
    
    /**
     * Stops the robot and sets everything to false or zero, does not disable 
     * the ability to use it though. 
     */
    public void stopRobot()
    {
        setSpeed(0, 0);
        setCannonUp(false);
    }
    
    /**
     * Get the status of the cannon up, true means it's up.
     * @return 
     */
    public boolean getCannonUp()
    {
        return m_cannon.getTurretUpStatus();
    }
    
    /**
     * Returns the speed of the left motor.
     * @return 
     */
    public double getMotorLeft()
    {
        return m_drive.getMotorLeft();
    }
    
    /**
     * Returns the speed of the right motor.
     * @return 
     */
    public double getMotorRight()
    {
        return m_drive.getMotorRight();
    }
    
    /**
     * Set the speed of the left and right motors, values allowed are -1 to 1.
     * @param dLeft
     * @param dRight 
     */
    public void setSpeed(double dLeft, double dRight)
    {
        m_drive.setSpeed(dLeft, dRight);
    }
    
    /**
     * Sets the cannon up, true means it's up.
     * @param bRetrieveVal 
     */
    public void setCannonUp(boolean bRetrieveVal)
    {
        m_cannon.set(bRetrieveVal);
    }
}