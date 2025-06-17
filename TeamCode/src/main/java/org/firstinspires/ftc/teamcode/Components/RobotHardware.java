package org.firstinspires.ftc.teamcode.Components;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.TouchSensor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.TouchSensor;
import java.util.ArrayList;
import java.util.List;

public class RobotHardware {
    public LinearOpMode myOpMode;

    public RobotHardware(LinearOpMode opMode) {
        myOpMode = opMode;

    }

    //SubSystems
    public Mecnum mecnum = new Mecnum();
    public Intake intake = new Intake();
    public Claw claw = new Claw();
    public Lifts lifts = new Lifts();
    public Component[] components = {mecnum, lifts, intake, claw};


    public void init() {

        for (int i = 0; i < components.length; i++)
        {
            components[i].init(myOpMode.hardwareMap);
        }

        myOpMode.telemetry.addData("status", "initialized");
        myOpMode.telemetry.update();
    }


}