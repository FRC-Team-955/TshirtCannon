/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;


/**
 *
 * @author RaiderPC
 */
public class UnderGlow {
	
    private Joystick joy;
    private Victor mtGlow = new Victor(Var.chanVicLight);
    private Button btLight = new Button();
    private boolean bLight = false;
    
    public UnderGlow(Joystick joystick)
    {
        joy  = joystick;
    }
    
    public void run()
    {
        btLight.run(joy.getRawButton(Var.btLight));

        if(btLight.gotPressed())
        {
            bLight = !bLight;

            if(bLight)
                mtGlow.set(1);

            else
                mtGlow.set(0);
        }	
    }

    public boolean getLightStatus()
    {
        return bLight;
    }
    
    public void set(boolean bStatus)
    {
        if(bStatus)
            mtGlow.set(1);

        else
            mtGlow.set(0);
    }
}