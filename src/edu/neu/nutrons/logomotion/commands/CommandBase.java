package edu.neu.nutrons.logomotion.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.neu.nutrons.logomotion.OI;
import edu.neu.nutrons.logomotion.subsystems.DriveTrain;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static DriveTrain dt;

    public static void init() {
        
        oi = new OI();
        // make sure this works... else change back to
        // public static DriveTrain dt = new DriveTrain();
        dt = new DriveTrain();
        
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
