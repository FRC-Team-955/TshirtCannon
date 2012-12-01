package edu.wpi.first.wpilibj.templates;

// @author Fauzi

class CJoyEmulator{
    private double m_dTimer = 0;
    private double m_dMtLeft = 0;
    private double m_dMtRight = 0;
    private boolean m_bTurUp = false;
    private boolean m_bUnderGlowStat = false;
    
    public  void add(double dTimer, double dMtLeft, double dMtRight, boolean  bTurUpStat, boolean bGlowStat)
    {
        m_dTimer = dTimer;
        m_dMtLeft = dMtLeft;
        m_dMtRight = dMtRight;
        m_bTurUp =  bTurUpStat;
        m_bUnderGlowStat = bGlowStat;
    }
    
    public double getTimer()
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
    
    public boolean getTurUp()
    {
        return m_bTurUp;
    }
    
    public boolean getGlowStat()
    {
        return m_bUnderGlowStat;
    }
}