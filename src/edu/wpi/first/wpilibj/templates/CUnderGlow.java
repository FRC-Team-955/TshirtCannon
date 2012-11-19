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
public class CUnderGlow {
	
    private Victor mtGlow = new Victor(Var.chanVicLight);
    private CButton btLight = new CButton();
    private boolean bLight = false;

    public void run(Joystick joy)
    {
        btLight.run(joy.getRawButton(Var.buttonLight));

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
