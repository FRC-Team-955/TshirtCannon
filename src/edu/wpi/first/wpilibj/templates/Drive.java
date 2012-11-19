//<<<<<<< HEAD
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
/**
 *
 * @author ryan
 */

/*
 * This class is responsible for driving the t-shirt cannon.
 * It also has a setspeed function which
 * sets the speed of the motors to the disired speed.
 */
         
public class Drive {
    private Victor motorRight = new Victor(Var.chanVicDriveRight);
    private Victor motorLeft = new Victor(Var.chanVicDriveLeft);
    private double mtRightSpeed = 0;
    private double mtLeftSpeed = 0;
    private double x = 0;
    private double y = 0;
    
    public void run(Joystick joy)
    {		
        y = joy.getY() * Math.abs(joy.getY());
        x = joy.getX() * Math.abs(joy.getX());
        
        mtRightSpeed = -y+x;
        mtLeftSpeed = y+x;
        
        if(Var.bShooting != true && Var.bDrive)
        {
            if(Math.abs(mtLeftSpeed) + Math.abs(mtRightSpeed) > 0.1)
            {
                motorRight.set(mtRightSpeed);
                motorLeft.set(mtLeftSpeed);
            }

            else
            {
                motorRight.set(0);
                motorLeft.set(0);
            }
        }
    } 
    
    public double getMtRightSpeed()
    {
        return mtRightSpeed;
    }
    
    public double getMtLeftSpeed()
    {
        return mtLeftSpeed;
    }
    
    public void setSpeed(double motorLeftSpeed, double motorRightSpeed)
    {
        motorRight.set(motorRightSpeed);
        motorLeft.set(motorLeftSpeed);
    }
}