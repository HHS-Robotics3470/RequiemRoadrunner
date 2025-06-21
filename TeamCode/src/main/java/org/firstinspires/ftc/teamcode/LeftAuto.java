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
import org.opencv.core.Mat;

@Autonomous(name = "Left Auto", group = "Autonomous")
public class LeftAuto extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();
Pose2d startPos = new Pose2d(0, 0, Math.PI/2);
    Pose2d testPos = new Pose2d(11, 38, Math.PI/2);
    Pose2d parkPos = new Pose2d(48, 10, -Math.PI/2);

    @Override
    public void runOpMode() {
        //initialize hardware and roadrunner
        RobotHardware robot = new RobotHardware(this);
        robot.init();
        MecanumDrive drive = new MecanumDrive(this.hardwareMap, startPos);

        //build trajectories
        Action traj1 = drive.actionBuilder(startPos)
                .splineToConstantHeading(testPos.component1(), Math.PI/2)
                .turnTo(3*Math.PI/4)
                .lineToX(4)
                .build();


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        robot.claw.clawClose();
        robot.claw.armSample();
        robot.claw.wristSAMPLE();
        Actions.runBlocking(traj1);
        sleep(100);
        robot.claw.clawOpen();
        sleep(300);

        Action traj2 = drive.actionBuilder(drive.localizer.getPose())
                .lineToX(25)
                .splineToLinearHeading(parkPos, -Math.PI/2)
                .build();
        Actions.runBlocking(traj2);
        robot.claw.armUp();
        sleep(100);

    }
}
