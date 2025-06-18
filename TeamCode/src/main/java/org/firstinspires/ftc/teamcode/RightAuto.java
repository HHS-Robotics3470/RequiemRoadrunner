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
    Pose2d testPos3 = new Pose2d(14, 10, 0);
    Pose2d testPos4 = new Pose2d(30, -17, -Math.PI/2);
    Pose2d testPos5 = new Pose2d(47, -17, -Math.PI);
    Pose2d testPos6 = new Pose2d(47, -30, -Math.PI);
    Pose2d testPos7 = new Pose2d(14, -30, -Math.PI);
    Pose2d testPos8 = new Pose2d(47, -30, -Math.PI);
    Pose2d testPos9 = new Pose2d(47, -42, -Math.PI);
    Pose2d testPos10 = new Pose2d(14, -42,-Math.PI);
    Pose2d testPos11 = new Pose2d(47, -42, -Math.PI);
    Pose2d testPos12 = new Pose2d(47, -50, -Math.PI);
    Pose2d testPos13 = new Pose2d(14, -50, -Math.PI);

    @Override
    public void runOpMode() {
        //initialize hardware and roadrunner
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
                .splineToConstantHeading(testPos4.component1(), 0)
                .build();
        //go forward to go infront of block
        Action traj5 = drive.actionBuilder(testPos4)
                .splineToConstantHeading(testPos5.component1(), 0)
                .build();
        //go in front of block 1
        Action traj6 = drive.actionBuilder(testPos5)
                .splineToConstantHeading(testPos6.component1(), 0)
                .build();
        //push block 1 into specimen zone
        Action traj7 = drive.actionBuilder(testPos6)
                .lineToX(testPos7.position.x)
                .build();
        //go back
        Action traj8 = drive.actionBuilder(testPos7)
                .splineToConstantHeading(testPos8.component1(), 0)
                .build();
        //go to the right to in front of block 2
        Action traj9 = drive.actionBuilder(testPos8)
                .splineToConstantHeading(testPos9.component1(), 0)
                .build();
        //push block 2 into specimen zone
        Action traj10 = drive.actionBuilder(testPos9)
                .lineToX(testPos10.position.x)
                .build();
        //go back
        Action traj11 = drive.actionBuilder(testPos10)
                .splineToConstantHeading(testPos11.component1(), 0)
                .build();
        //go to the right to in front of block 3
        Action traj12 = drive.actionBuilder(testPos11)
                .splineToConstantHeading(testPos12.component1(), 0)
                .build();
        //push block 3 into specimen zone
        Action traj13 = drive.actionBuilder(testPos12)
                .lineToX(testPos13.position.x)
                .build();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

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
    }
}
