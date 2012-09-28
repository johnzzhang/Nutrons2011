
package edu.neu.nutrons.logomotion;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.neu.nutrons.lib.Utils;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    // Constants.
    private final double PAD_DEADBAND = 0.1;
    
    // Driver.
    private Joystick driverPad = new Joystick(RobotMap.PAD_DRIVER);
    private DriverStationEnhancedIO io = DriverStation.getInstance().getEnhancedIO();
    
    // Operator.
    private Joystick operatorPad = new Joystick(RobotMap.PAD_OPERATOR);
    
    // Methods.
    public double getDriveLeft() {
        return -Utils.deadband(driverPad.getRawAxis(2), PAD_DEADBAND, 0);
    }

    public double getDriveRight() {
        return -Utils.deadband(driverPad.getRawAxis(4), PAD_DEADBAND, 0);
    }
    
    public double getDriveThrottle() {
        //return -Utils.deadband(driverPad.getRawAxis(2), PAD_DEADBAND, 0);
        return getIOAnalog(1);
    }

    public double getDriveWheel() {
        //return Utils.deadband(driverPad.getRawAxis(3), PAD_DEADBAND, 0);
        return getIOAnalog(5);
    }

    public boolean getDriveQuickTurn() {
        //return driverPad.getRawButton(6);
        return getIODigital(3);
    }

    private double getIOAnalog(int port) {
        double in;
        try {
            in = io.getAnalogIn(port);
        }
        catch(EnhancedIOException ex) {
            return 0;
        }
        double refined = setCapAndBand(setScaleAnalog(in));
        return refined;
    }
    
    private boolean getIODigital(int port) {
        boolean in = false;
        try {
            in = !io.getDigital(port); //active low
        }
        catch(EnhancedIOException ex) {
        }
        return in;
    }
    
    private double setCapAndBand(double value) {
        value = Utils.deadband(value, .075, -1);
        value = Utils.deadband(value, .075, 0);
        value = Utils.deadband(value, .075, 1);
        return Utils.limit(value, -1, 1);
    }

    private double setScaleAnalog(double voltageIn) {
        double normalized = (2 * voltageIn / 3.25) - 1;
        return normalized;
    }
    
}

