package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Components.RobotHardware;


@Autonomous(name = "Left Auto", group = "Autonomous")
public class LeftAuto extends LinearOpMode{

   ElapsedTime time = new ElapsedTime();

    Pose2d startPos = new Pose2d(0,0,0);
    Pose2d testPos = new Pose2d(25, 0,0);
    Pose2d testPos2 = new Pose2d(30, 0,0);

    Pose2d testPos3 = new Pose2d(10, 0,0);


    @Override
    public void runOpMode(){
        //initialize hardware and roadrunner
        RobotHardware robot = new RobotHardware(this);
        robot.init();
        MecanumDrive drive = new MecanumDrive(this.hardwareMap, startPos);

        //build trajectories
        Action traj1 = drive.actionBuilder(startPos)
                .splineToConstantHeading(testPos.component1(), 0)
                .build();
        Action traj2 = drive.actionBuilder(testPos)
                .splineToConstantHeading(testPos2.component1(), 0)
                .build();
        Action traj3 = drive.actionBuilder(testPos2)
                .splineToConstantHeading(testPos3.component1(), 0)
                .build();
//        Action traj3 = drive.actionBuilder(testPos2)
//                .splineToLinearHeading(startPos, 0)
//                .build();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        robot.claw.clawClose();
        robot.claw.armUp();
        robot.claw.wristUP();
        Actions.runBlocking(traj1);
        Actions.runBlocking(traj2);
        robot.claw.clawOpen();
        Actions.runBlocking(traj3);
        robot.claw.armRest();
        robot.claw.wristRest();



    }
}
