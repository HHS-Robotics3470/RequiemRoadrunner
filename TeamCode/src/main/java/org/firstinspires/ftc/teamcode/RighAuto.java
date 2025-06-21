package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Components.RobotHardware;
import org.firstinspires.ftc.teamcode.Components.Claw;
import org.firstinspires.ftc.teamcode.Components.Intake;
import org.firstinspires.ftc.teamcode.Components.Lifts;
import org.firstinspires.ftc.teamcode.Components.Component;
@Config @Autonomous(name = "RighAuto no work", group = "Autonomous")
public final class RighAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 62.25, Math.toRadians(-90));
        Pose2d WallIntake = new Pose2d(-37, 48, Math.toRadians(90));
        Pose2d Score1 = new Pose2d(1.5, 27.75, Math.toRadians(90));
        Pose2d Score2 = new Pose2d(.5, 27.75, Math.toRadians(90));
        Pose2d Score3 = new Pose2d(-1, 27.75, Math.toRadians(90));
        Pose2d Score4 = new Pose2d(-4.5, 27.75, Math.toRadians(90));
        Pose2d Score5 = new Pose2d(-6.25, 27.75, Math.toRadians(90));

        RobotHardware robot = new RobotHardware(this);
        robot.init();
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);


        while (!opModeIsActive() && !isStopRequested()) {
            robot.claw.clawClose();
            robot.claw.armRest();
            robot.lifts.Bextendo_in();
        }

        waitForStart();




        if (opModeIsActive()) {


            robot.claw.armUp();
            robot.claw.wristUP();
            robot.claw.clawClose();



            // Preload
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .strafeTo(new Vector2d(0,32))
                            .build()
            );

            robot.claw.clawOpen();
            sleep(300);


            // pushing
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(new Vector2d(0, 32), Math.toRadians(-90)))

                            .setTangent(Math.toRadians(90))
                            .splineToLinearHeading(new Pose2d(new Vector2d(-35, 44), Math.toRadians(90)), Math.toRadians(-90))

                            .setTangent(Math.toRadians(-90))
                            .splineToConstantHeading(new Vector2d(-35, 12), Math.toRadians(-90))

                            .setTangent(Math.toRadians(-90))
                            .splineToConstantHeading(new Vector2d(-46, 13), Math.toRadians(90))

                            .waitSeconds(0.1)

                            .strafeTo(new Vector2d(-46,56), new TranslationalVelConstraint(80))

                            .setTangent(Math.toRadians(-90))
                            .splineToConstantHeading(new Vector2d(-46, 12), Math.toRadians(-90))

                            .setTangent(Math.toRadians(-90))
                            .splineToConstantHeading(new Vector2d(-60, 12), Math.toRadians(90))

                            .waitSeconds(0.1)

                            .strafeTo(new Vector2d(-60,56), new TranslationalVelConstraint(80))
                            .build());

            robot.claw.armRest();
            robot.claw.clawOpen();
            //First Grab
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(new Vector2d(-60, 56), Math.toRadians(90)))

                            .setTangent(Math.toRadians(90))
                            .splineToLinearHeading(new Pose2d(new Vector2d(-2, 32), Math.toRadians(-90)), Math.toRadians(90))

                            .build());
//1st spec score
            robot.claw.clawOpen();
            robot.claw.armUp();
            robot.claw.wristUP();
            sleep(300);

            Actions.runBlocking(drive.actionBuilder(new Pose2d(new Vector2d(-2, 32), Math.toRadians(-90)))

                    .setTangent(Math.toRadians(90))
                    .splineToLinearHeading(new Pose2d(new Vector2d(-60, 63), Math.toRadians(180)), Math.toRadians(180))

                    .build());

            //Second Grab
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(new Vector2d(-60, 63), Math.toRadians(180)))

                            .setTangent(Math.toRadians(-90))
                            .splineToLinearHeading(new Pose2d(new Vector2d(-4, 32), Math.toRadians(-90)), Math.toRadians(-90))

                            .build());

            //Second Spec Score
            Actions.runBlocking(drive.actionBuilder(new Pose2d(new Vector2d(-4, 32), Math.toRadians(-90)))

                    .setTangent(Math.toRadians(90))
                    .splineToLinearHeading(new Pose2d(new Vector2d(-60, 63), Math.toRadians(180)), Math.toRadians(180))

                    .build());

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(new Vector2d(-60, 63), Math.toRadians(180)))

                            .setTangent(Math.toRadians(-90))
                            .splineToLinearHeading(new Pose2d(new Vector2d(-6, 32), Math.toRadians(-90)), Math.toRadians(-90))

                            .build());

            Actions.runBlocking(drive.actionBuilder(new Pose2d(new Vector2d(-6, 32), Math.toRadians(-90)))

                    .setTangent(Math.toRadians(90))
                    .splineToLinearHeading(new Pose2d(new Vector2d(-60, 63), Math.toRadians(180)), Math.toRadians(180))

                    .build());

        }}}