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
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Components.RobotHardware;


@Autonomous(name = "Right Auto", group = "Autonomous")
public class RightAuto extends LinearOpMode{

    Pose2d startPos = new Pose2d(0,0,0);
    Pose2d testPos = new Pose2d(20, 10, 0);
    Pose2d testPos2 = new Pose2d(20, 20, 0);

    @Override
    public void runOpMode(){
        //initialize hardware and roadrunner
        RobotHardware robot = new RobotHardware(this);
        robot.init();
        MecanumDrive drive = new MecanumDrive(this.hardwareMap, startPos);

        //build trajectories
        Action traj1 = drive.actionBuilder(startPos)
                .splineToLinearHeading(testPos, 0)
                .build();
        Action traj2 = drive.actionBuilder(testPos)
                .splineToLinearHeading(testPos2, 0)
                .build();
        Action traj3 = drive.actionBuilder(testPos2)
                .splineToLinearHeading(startPos, 0)
                .build();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        Actions.runBlocking(traj1);
        //robot.claw.open
        Actions.runBlocking(traj2);
        //robot action
        Actions.runBlocking(traj3);

    }
}
