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

@Autonomous(name = "Right Auto", group = "Autonomous")
public class RightAuto extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
    Pose2d startPos = new Pose2d(0, 0, 0);
    Pose2d testPos = new Pose2d(20, 10, 0);
    Pose2d testPos2 = new Pose2d(38, 10, 0);
    Pose2d testPos3 = new Pose2d(13, 10, 0);
    Pose2d testPos4 = new Pose2d(30, -17, 0);
    Pose2d testPos5 = new Pose2d(47, -17, 0);
    Pose2d testPos6 = new Pose2d(47, -30, 0);
    Pose2d testPos7 = new Pose2d(14, -30, 0);
    Pose2d testPos8 = new Pose2d(47, -30, 0);
    Pose2d testPos9 = new Pose2d(47, -42, 0);
    Pose2d testPos10 = new Pose2d(14, -42, 0);
    Pose2d testPos11 = new Pose2d(47, -42, 0);
    Pose2d testPos12 = new Pose2d(47, -50, 0);
    Pose2d testPos13 = new Pose2d(14, -50, 0);
    Pose2d testPos14 = new Pose2d(7, -30, Math.toRadians(180));
    Pose2d testPos15 = new Pose2d(20, 10, 0);
    Pose2d testPos16 = new Pose2d(16, 10, 0);
    Pose2d testPos17 = new Pose2d(2, -100, 0);


    @Override
    public void runOpMode() {
        //initialize hardware and roadrunner

        waitForStart();

        RobotHardware robot = new RobotHardware(this);
        robot.init();
        MecanumDrive drive = new MecanumDrive(this.hardwareMap, startPos);

        //build trajectories
        //start to cage
        Action traj1 = drive.actionBuilder(startPos)
                .splineToConstantHeading(testPos.component1(), 0)
                .build();
        //score on cage
        Action traj2 = drive.actionBuilder(testPos)
                .splineToConstantHeading(testPos2.component1(), 0)
                .build();
        //back up to not break
        Action traj3 = drive.actionBuilder(testPos2)
                .splineToLinearHeading(testPos3, 0)
                .build();
        //go right to go forward to push
        Action traj4 = drive.actionBuilder(testPos3)
                .splineToLinearHeading(testPos4, 0)
                .build();
        //go forward to go infront of block
        Action traj5 = drive.actionBuilder(testPos4)
                .splineToLinearHeading(testPos5, 0)
                .build();
        //go in front of block 1
        Action traj6 = drive.actionBuilder(testPos5)
                .splineToLinearHeading(testPos6, 0)
                .build();
        //push block 1 into specimen zone
        Action traj7 = drive.actionBuilder(testPos6)
                .lineToX(testPos7.position.x)
                .turnTo(testPos7.heading)
                .build();
        //go back
        Action traj8 = drive.actionBuilder(testPos7)
                .splineToLinearHeading(testPos8, 0)
                .build();
        //go to the right to in front of block 2
        Action traj9 = drive.actionBuilder(testPos8)
                .splineToLinearHeading(testPos9, 0)
                .build();
        //push block 2 into specimen zone
        Action traj10 = drive.actionBuilder(testPos9)
                .lineToX(testPos10.position.x)
                .turnTo(testPos10.heading)
                .build();
        //go back
        Action traj11 = drive.actionBuilder(testPos10)
                .splineToLinearHeading(testPos11, 0)
                .build();
        //go to the right to in front of block 3
        Action traj12 = drive.actionBuilder(testPos11)
                .splineToLinearHeading(testPos12, 0)
                .build();
        //push block 3 into specimen zone
        Action traj13 = drive.actionBuilder(testPos12)
                .lineToX(testPos13.position.x)
                .turnTo(testPos13.heading)
                .build();
        Action traj14 = drive.actionBuilder(testPos13)
                .splineToLinearHeading(testPos14, 0)
                .build();
        Action traj15 = drive.actionBuilder(testPos14)
                .splineToLinearHeading(testPos15, 0)
                .build();
        Action traj16 = drive.actionBuilder(testPos15)
                .splineToLinearHeading(testPos16, 0)
                .build();
        Action traj17 = drive.actionBuilder(testPos16)
                .splineToLinearHeading(testPos3, 0)
                .build();
        Action traj18 = drive.actionBuilder(testPos3)
                .splineToConstantHeading(testPos17.component1(), 0)
                .build();

        telemetry.addData("Status", "Initialized");
        telemetry.update();



        robot.claw.clawClose();
        robot.intake.tuckin();
        robot.claw.armUp();
        robot.claw.wristUP();
        Actions.runBlocking(traj1);
        Actions.runBlocking(traj2);
        robot.claw.clawOpen();
        Actions.runBlocking(traj3);
        robot.claw.armRest();

        Actions.runBlocking(traj4);
        Actions.runBlocking(traj5);
        Actions.runBlocking(traj6);
        Actions.runBlocking(traj7);
        Actions.runBlocking(traj8);
        Actions.runBlocking(traj9);
        Actions.runBlocking(traj10);
        Actions.runBlocking(traj11);
        Actions.runBlocking(traj12);
        Actions.runBlocking(traj13);
        robot.claw.wristRest();
        robot.claw.clawOpen();
        Actions.runBlocking(traj14);
        sleep(2000);
        robot.claw.clawClose();
        robot.claw.armUp();

        Actions.runBlocking(traj15);

        Actions.runBlocking(traj16);
        robot.claw.clawOpen();
        Actions.runBlocking(traj17);
        Actions.runBlocking(traj18);
    }
}
