package utilities;

/**
 * 
 * @author Fauzi
 */ 
public class BotData{
    
    private double m_dTimer = 0;
    private double m_dMtLeft = 0;
    private double m_dMtRight = 0;
    private boolean m_bTurretUp = false;
    
    public void setValues(double dTimer, double dMtLeft, double dMtRight, boolean bTurretUp)
    {
        m_dTimer = dTimer;
        m_dMtLeft = dMtLeft;
        m_dMtRight = dMtRight;
        m_bTurretUp = bTurretUp;
    }
    
    public void setValues(double dTimer, Robot bot)
    {
        m_dTimer = dTimer;
        m_dMtLeft = bot.getMotorLeft();
        m_dMtRight = bot.getMotorRight();
        m_bTurretUp = bot.getCannonUp();
    }
    
    public void setValues(BotData emu)
    {
        m_dTimer = emu.getTime();
        m_dMtLeft = emu.getMtLeft();
        m_dMtRight = emu.getMtRight();
        m_bTurretUp = emu.getTurretUp();
    }
    
    public double getTime()
    {
        return m_dTimer;
    }
    
    public double getMtLeft()
    {
        return m_dMtLeft;
    }
    
    public double getMtRight()
    {
        return m_dMtRight;
    }
    
    public boolean getTurretUp()
    {
        return m_bTurretUp;
    }
}