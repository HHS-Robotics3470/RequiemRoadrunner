package org.firstinspires.ftc.teamcode.Components.Components;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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