
package edu.neu.nutrons.logomotion.subsystems;

import edu.neu.nutrons.lib.LinearVictor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.neu.nutrons.logomotion.RobotMap;

/**
 * Drive Train Subsystem.
 * Moves the robot.
 */
public class DriveTrain extends Subsystem {
    // Constants.
    private final double LEFT_SCALE = 1.0;
    private final double RIGHT_SCALE = 1.0;
    private final double HIGH_GEAR_T_SENS = 1.5;
    private final double LOW_GEAR_T_SENS = 1.3;
    
    // Actual robot parts
    private final LinearVictor lMot = new LinearVictor(RobotMap.L_DRIVE_MOTOR);
    private final LinearVictor rMot = new LinearVictor(RobotMap.R_DRIVE_MOTOR);
    
    // Other variables.
    private double tSens = LOW_GEAR_T_SENS;
    
    
    // constructor
    public DriveTrain() {
        
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void setDriveLR(double lPower, double rPower) {
        lMot.set(LEFT_SCALE * -lPower);
        rMot.set(RIGHT_SCALE * rPower);
    }
    
    public void setDriveCar(double throttle, double wheel) {
        setDriveLR(throttle + wheel, throttle - wheel);
    }
    
    public void setDriveCheesy(double throttle, double wheel, boolean quickTurn) {
        double angular_power;
        double overPower;
        double rPower;
        double lPower;
        if(quickTurn) {
            overPower = 1.0;
            angular_power = wheel;
        }
        else {
            overPower = 0.0;
            angular_power = Math.abs(throttle) * wheel * tSens;
        }
        rPower = lPower = throttle;
        lPower += angular_power;
        rPower -= angular_power;
        if(lPower > 1.0) {
            rPower -= overPower * (lPower - 1.0);
            lPower = 1.0;
        }
        else if(rPower > 1.0) {
            lPower -= overPower * (rPower - 1.0);
            rPower = 1.0;
        }
        else if(lPower < -1.0) {
            rPower += overPower * (-1.0 - lPower);
            lPower = -1.0;
        }
        else if(rPower < -1.0) {
            lPower += overPower * (-1.0 - rPower);
            rPower = -1.0;
        }
        setDriveLR(lPower, rPower);
    }
    
    public void setDriveStop() {
        setDriveLR(0, 0);
    }
}
