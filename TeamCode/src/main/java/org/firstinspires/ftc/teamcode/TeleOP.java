package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Components.RobotHardware;


@TeleOp(name="The Only TeleOP", group="Linear OpMode")
public class TeleOP extends LinearOpMode {
    //Button states
    private boolean b1state = false;
    private boolean b2state = false;
    private boolean y1state = false;
    private boolean y2state = false;
    private boolean x2state = false;
    private boolean a1state = false;
    private boolean a3state = false;
    private boolean a4state = false;
    private boolean xstate = false;
    private boolean dpadLeftState = false;
    private boolean bdpadUpState = false;
    private ElapsedTime mStateTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        RobotHardware robot = new RobotHardware(this);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        int intake_state = 0;
        int transfer_state = 0;
        int deposit_state = 0;

        waitForStart();
        robot.init();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //driver hub output
            telemetry.addData("Lift position", robot.lifts.getLiftPosition());
            telemetry.addData("Extendo position", robot.lifts.getExtendoPosition());
            telemetry.addData("matric", intake_state);
            telemetry.update();

            //drivetrain control
            robot.mecnum.brake(gamepad1);
            robot.mecnum.driveRobot(gamepad1);

            // vertical lift control
            if (gamepad2.right_bumper) {
                robot.lifts.raiseLift();
            }
            else if (gamepad2.left_bumper) {
                robot.lifts.lowerLift();
            }
            else {
                robot.lifts.stopLiftVertical();
            }

            //horizontal extendo control
            if (gamepad1.right_bumper) {
                robot.lifts.Bextendo_out();
                robot.intake.fourBarIntakingReady();
                robot.intake.clawIntakeOpen();
            }
            else if (gamepad1.left_bumper) {
                robot.lifts.Bextendo_in();
                robot.intake.fourBarSpit();
            }

            //grab or intake
            if (gamepad1.a && !a1state) {
                a1state = true;
                intake_state = 0;
                mStateTime.reset();
            }
            if (a1state) {
                switch (intake_state) {
                    case 0:
                        robot.intake.fourBarIntaking();
                        robot.intake.clawIntakeOpen();
                        robot.intake.pitchIntaking();
                        if (mStateTime.seconds() >= 0.3) {
                            mStateTime.reset();
                            intake_state++;
                        }
                        break;

                    case 1:
                        robot.intake.clawIntakeClose();
                        if (mStateTime.seconds() >= 0.425) {
                            mStateTime.reset();
                            intake_state++;
                        }
                        break;

                    case 2:
                        robot.intake.pitchRest();
                        robot.intake.fourBarIntakingReady();
                        a1state = false;
                        break;
                }
            }

            //transfer button
            if (gamepad1.b && !b2state)
            {
                b2state = true;
                transfer_state = 0;
                mStateTime.reset();
            }
            if (b2state)
            {
                switch (transfer_state) {
                    case 0:
                        robot.lifts.Bextendo_transfer();
                        robot.intake.fourBarTransfer();
                        robot.intake.pitchTransfer();
                        robot.claw.clawOpen();
                        robot.claw.armDown();
                        robot.claw.wristDown();
                        if (mStateTime.seconds() >= 1.3) {
                            mStateTime.reset();
                            transfer_state++;
                        }
                        break;
                    case 1:
                        robot.claw.clawClose();
                        if (mStateTime.seconds() >= 0.5) {
                            mStateTime.reset();
                            transfer_state++;
                        }
                        break;
                    case 2:
                        robot.intake.clawIntakeOpen();
                        if (mStateTime.seconds() >= 1) {
                            mStateTime.reset();
                            transfer_state++;
                        }
                    case 3:
                        robot.claw.armRest();
                        robot.claw.wristRest();
                        if (mStateTime.seconds() >= 1) {
                            mStateTime.reset();
                            transfer_state++;
                        }
                        break;
                    case 4:
                        robot.lifts.Bextendo_in();
                        robot.intake.pitchRest();
                        b2state = false;
                }
            }

            //deposit button
            if (gamepad1.x && !xstate)
            {
                xstate = true;
                deposit_state = 0;
                mStateTime.reset();
            }
            if (xstate)
            {
                switch (deposit_state)
                {
                    case 0:
                        robot.claw.armSpec();
                        robot.claw.wristSpec();
                        if (mStateTime.seconds() >= 1) {
                            mStateTime.reset();
                            deposit_state++;
                        }
                        break;
                    case 1:
                        robot.claw.clawOpen();
                        if (mStateTime.seconds() >= 1)
                        {
                            mStateTime.reset();
                            deposit_state++;
                        }
                    case 2:
                        robot.claw.armRest();
                        robot.claw.wristRest();
                        xstate = false;
                }
            }

            //outtake toggle button
            if (gamepad2.y && !y1state)
            {
                robot.claw.swing();
                y1state = true;
            }
            else if (!gamepad2.y && y1state)
            {
                y1state = false;
            }

            if (gamepad2.b && !b1state)
            {
                robot.claw.toggleClaw();
                b1state = true;
            }
            else if (!gamepad2.b && b1state)
            {
                b1state = false;
            }

            if (gamepad1.y && !y2state)
            {
                robot.intake.toggleWrist();
                y2state = true;
            }
            else if (!gamepad1.y && y2state)
            {
                y2state = false;
            }

            if (gamepad1.dpad_up)
            {
                robot.claw.armSample();
                robot.claw.wristSAMPLE();
            }

        }
    }
}