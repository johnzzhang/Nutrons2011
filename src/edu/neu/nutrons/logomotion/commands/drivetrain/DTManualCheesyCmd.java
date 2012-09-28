/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.nutrons.logomotion.commands.drivetrain;

import edu.neu.nutrons.logomotion.commands.CommandBase;

/**
 *
 * @author NUTRONs
 */
public class DTManualCheesyCmd extends CommandBase {
    
    public DTManualCheesyCmd() {
        requires(dt);
    }

    protected void initialize() {
    }

    protected void execute() {
        // add OI values
        //dt.setDriveCheesy(0, 0, false);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
