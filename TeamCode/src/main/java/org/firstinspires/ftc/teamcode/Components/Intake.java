package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake implements Component{

    public final double PITCH_DOWN = 0.08;
    public final double PITCH_UP = 0.05;
    public final double PITCH_SPECIMEN_REST = 0.2;
    public final double PITCH_TRANSFER_SAMPLE = 0.15;
    public final double PITCH_INTAKING = 0.4;
    public final double PITCH_TRANSFER = 0;
    public final double PITCH_REST = 0.12;
    private final double INTAKE_CLAW_OPEN_POSITION = 0.05;
    private final double INTAKE_CLAW_CLOSE_POSITION = 0.015;

    public final double SWEEPER_INITIAL_POSITION = 0.008;
    public final double SWEEPER_FINAL_POSITION = 0.34; //0.053;
    public final double SWEEPER_AUTO_POSITION = 0.34;
    public final double FOUR_PITCH_TUCK = 0.035;
    public final double FOUR_PITCH_TRANSFER = 0;
    public final double FOUR_PITCH_SPIT = 0.1;
    public final double FOUR_PITCH_INTAKING = 0.143;
    public final double FOUR_PITCH_INTAKING_READY = 0.1825;

    private boolean ifPressed;

    // Declare a CRServo object for the continuous intake motor
    //public CRServo intakeMotor;
    public Servo clawIntake;

    // Declare a Servo object for the intake pitch control
    public Servo intakePitch;
    public Servo intakeWrist;
    public Servo fourBarPitch;

    // Initialize the intake motor and pitch servo using RobotHardware
    @Override
    public void init(HardwareMap hardwareMap) {
        clawIntake = hardwareMap.get(Servo.class, "claw intake");
        intakePitch = hardwareMap.get(Servo.class, "intake pitch");
        fourBarPitch = hardwareMap.get(Servo.class, "fourBar");
        intakeWrist = hardwareMap.get(Servo.class, "intakeWrist");

        fourBarPitch.setDirection(Servo.Direction.FORWARD);
        intakeWrist.setDirection(Servo.Direction.REVERSE);

        intakePitch.setPosition(PITCH_REST);
        clawIntake.setPosition(INTAKE_CLAW_CLOSE_POSITION);
        intakeWrist.setPosition(SWEEPER_INITIAL_POSITION);
        fourBarPitch.setPosition(FOUR_PITCH_TRANSFER);

        ifPressed = false;


    }

    public void pitchUp()
    {
        intakePitch.setPosition(PITCH_UP);
    }

    public void pitchSpecimenRest()
    {
        intakePitch.setPosition(PITCH_SPECIMEN_REST);
    }

    public void pitchDown()
    {
        intakePitch.setPosition(PITCH_DOWN);
    }

    public void pitchRest(){
        intakePitch.setPosition(PITCH_REST);
    }

    //for claw intake
    public void pitchTransfer()
    {
        intakePitch.setDirection(Servo.Direction.FORWARD);
        intakePitch.setPosition(PITCH_TRANSFER);
    }

    public void pitchIntaking()
    {
        intakePitch.setPosition(PITCH_INTAKING);
    }

    public void clawIntakeClose()
    {
        clawIntake.setPosition(INTAKE_CLAW_CLOSE_POSITION);
    }
    public void clawIntakeOpen()
    {
        clawIntake.setPosition(INTAKE_CLAW_OPEN_POSITION);
    }


    public void fourBarIntaking()
    {
        fourBarPitch.setPosition(FOUR_PITCH_INTAKING);
    }
    public void fourBarIntakingReady()
    {
        fourBarPitch.setPosition(FOUR_PITCH_INTAKING_READY);
    }
    public void fourBarTransfer()
    {
        fourBarPitch.setPosition(FOUR_PITCH_TRANSFER);
    }
    public void fourBarSpit() { fourBarPitch.setPosition(FOUR_PITCH_SPIT); }

    public void sweeperInitial(){
        intakeWrist.setPosition(SWEEPER_INITIAL_POSITION);
    }

    public void sweeperFinal() {intakeWrist.setPosition(SWEEPER_FINAL_POSITION);}

    public void tuckin (){
fourBarPitch.setPosition(FOUR_PITCH_TUCK);
    }
    public void tuckout (){
        fourBarPitch.setPosition(FOUR_PITCH_TRANSFER);
    }

    public void toggleWrist (){
        if (ifPressed){
           sweeperInitial();
           ifPressed = false;
        }
        else {
            sweeperFinal();
            ifPressed = true;
        }
    }



}