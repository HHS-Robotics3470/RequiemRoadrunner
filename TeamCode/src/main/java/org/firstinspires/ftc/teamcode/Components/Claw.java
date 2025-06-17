package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Claw implements Component{
    private Servo clawServo;     // Servo to open/close the claw
    private Servo armRight;
    private Servo armLeft;
    private Servo wrist;// Servo to adjust the pitch of the claw
    private boolean ifSwinged = false;
    private boolean ifOpen = false;

    private final double CLAW_OPEN_POSITION = 0.056;   // Adjust as needed for your claw design
    private final double CLAW_CLOSE_POSITION = 0.0;  // Adjust as needed for your claw design
    private final double ARM_UP_POSITION = 0.379;    // Adjust as needed for your pitch servo
    private final double ARM_SPECIMEN_POSITION = 0.379;
    private final double ARM_DOWN_POSITION = 0;
    private final double ARM_REST_POSITION = 0.28;
    private final double WRIST_UP_POSITION = 0.11;
    private final double WRIST_SPECIMEN_POSITION = 0.45;
    private final double WRIST_DOWN_POSITION = 0;
    private final double WRIST_REST_POSITION = 0.2;

    ;// Adjust as needed for your pitch servo
    @Override
    public void init(HardwareMap hardwareMap) {
        wrist = hardwareMap.get(Servo.class, "wrist");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        armRight = hardwareMap.get(Servo.class, "arm right");
        armLeft = hardwareMap.get(Servo.class, "arm left");

        armRight.setDirection(Servo.Direction.REVERSE);
        armLeft.setDirection(Servo.Direction.FORWARD);

        clawServo.setPosition(CLAW_CLOSE_POSITION);
        ifOpen = false;
        armRight.setPosition(ARM_REST_POSITION);
        armLeft.setPosition(ARM_REST_POSITION);
        wrist.setPosition(WRIST_REST_POSITION);

        ifSwinged = false;
    }

    // Method to open the claw
    public void clawOpen() {
        clawServo.setPosition(CLAW_OPEN_POSITION);
    }

    // Method to close the claw
    public void clawClose() {
        clawServo.setPosition(CLAW_CLOSE_POSITION);
    }

    // Method to set the pitch of the claw up
    public void armUp() {
        armRight.setPosition(ARM_UP_POSITION);
        armLeft.setPosition(ARM_UP_POSITION);
    }

    // Method to set the pitch of the claw down
    public void armDown() {
        armRight.setPosition(ARM_DOWN_POSITION);
        armLeft.setPosition(ARM_DOWN_POSITION);
    }

    public void armSpec() {
        armRight.setPosition(ARM_SPECIMEN_POSITION);
        armLeft.setPosition(ARM_SPECIMEN_POSITION);
    }

    public void wristSpec() {
        wrist.setPosition(WRIST_SPECIMEN_POSITION);
    }

    public void wristRest()
    {
        wrist.setPosition(WRIST_REST_POSITION);
    }

    public void armRest(){
        armRight.setPosition(ARM_REST_POSITION);
        armLeft.setPosition(ARM_REST_POSITION);
    }

    public void wristUP(){
        wrist.setPosition(WRIST_UP_POSITION);
    }

    public void wristDown() {
        wrist.setPosition(WRIST_DOWN_POSITION);
    }


    // Method to toggle the claw's open/close state
    public void toggleClaw() {
        if (ifOpen) {
            clawClose();
            ifOpen = false;
        } else {
            clawOpen();
            ifOpen = true;
        }
    }


    public void swing()
    {
        if (!ifSwinged)
        {
            armUp();
            wristUP();
            clawClose();
            ifSwinged = true;
        }
        else
        {
            armRest();
            wristRest();
            ifSwinged = false;
        }
    }

}