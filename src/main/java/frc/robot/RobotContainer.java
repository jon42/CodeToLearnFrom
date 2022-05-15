// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.lang.ModuleLayer.Controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Logitech.Ports;
import frc.robot.subsystems.Delivery;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static frc.robot.Logitech.Ports.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //creates an instance of the classes
    private static final Intake intakeSub = new Intake();
    private static final Drivetrain driveSub = new Drivetrain();
    private static final Delivery deliverySub = new Delivery();

    private SendableChooser<Command> autoChooser;

  //cotroller button initialization
  //D signifies driver
  //this code is set up only using driver controller
  private Logitech driver = new Logitech(Constants.DRIVER_PORT);
  private JoystickButton DA = new JoystickButton(driver, Ports.A);
  private JoystickButton DB = new JoystickButton(driver, Ports.B);
  private JoystickButton DX = new JoystickButton(driver, Ports.X);
  private JoystickButton DY = new JoystickButton(driver, Ports.Y);
  private JoystickButton Dstart = new JoystickButton(driver, Ports.START);
  private JoystickButton Dback = new JoystickButton(driver, Ports.BACK);
  private JoystickButton DleftBumper = new JoystickButton(driver, Ports.LEFT_BUMPER);
  private JoystickButton DrightBumper = new JoystickButton(driver, Ports.RIGHT_BUMPER);
  // The robot's subsystems and commands are defined here...

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the stuff here
    configureButtonBindings();
    configureDefaultCommands();
    ConfigureAutos();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

    //put default commands and button bindings in here

    private void configureDefaultCommands(){
      driveSub.setDefaultCommand(new RunCommand(
      () ->{ 
        driveSub.drive(
        driver.getRawAxis(LeftStick.X)
        ,driver.getRawAxis(LeftStick.Y)
      );  }
      , driveSub));
    }

  private void configureButtonBindings() {



      //When creating these try to make it in a way that you would like using
      //Think about the format that you would most like to use
      // most of the whenheld and when pressed is simple so just think of what would make the most sense for you

      //if you think that you want to make something happen frequently in the code or something that would take up space in
      //robot container make a file for it and have it extend another command type
      //I dont have that added to this file yet but if you would like me to show you how I can

    DA.whenHeld(new RunCommand(
    () -> {
      intakeSub.extend();
    if (DX.get()){
      intakeSub.runIntake(Constants.Intake.SPEED);
    }
    }, intakeSub)

    );
    DA.whenReleased(new InstantCommand(
      () -> {

        intakeSub.retract();
        intakeSub.runIntake(0);

      }, intakeSub));

      DB.whenHeld(new RunCommand(
        () -> {
          deliverySub.runDelivery(0.2);
        }, deliverySub));
        DB.whenReleased(new InstantCommand(
          () -> { deliverySub.runDelivery(0);
          }
          ));
  }
  //configures the autochooser and adds options
  public void ConfigureAutos(){

    autoChooser = new SendableChooser<>();
//add default opption
    autoChooser.setDefaultOption("None", null);

//add whatever commands you would like for seperate autos
//make simple auto like extend the intake etc.
    autoChooser.addOption("simple auto", 
    new InstantCommand(
      () -> driveSub.drive(-0.2, 0)
    )
    );
  }
    
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public SendableChooser<Command> getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoChooser; // m_autoCommand;
  }
}
 