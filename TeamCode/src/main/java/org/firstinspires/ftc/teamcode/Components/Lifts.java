package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Lifts implements Component{
    //Lift constants
    private final int LIFT_LOW = 0;
    private final int LIFT_HIGH = -1600;
    private final double EXTENDO_IN = 0;

    private final double EXTENDO_TRANSFER = 0.075;
    private final double EXTENDO_OUT = 0.3;
    // Vertical Lift motors
    private DcMotorEx lLift;
    private DcMotorEx rLift;

    // Vertical Lift sensors
    public TouchSensor touch1;
    public TouchSensor touch2;

    // Horizontal extension servos
    public Servo Lextendo;
    public Servo Rextendo;

    // Init function
    @Override
    public void init(HardwareMap hardwareMap) {
        // get hardware objects from hardwaremap
        lLift = hardwareMap.get(DcMotorEx.class, "lLift");
        rLift = hardwareMap.get(DcMotorEx.class, "rLift");
        Rextendo =  hardwareMap.get(Servo.class, "Rextendo");
        Lextendo =  hardwareMap.get(Servo.class, "Lextendo");
        touch1 = hardwareMap.get(TouchSensor.class, "touch1");
        touch2 = hardwareMap.get(TouchSensor.class, "touch2");

        //Set up moters and servos
        lLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Lextendo.setDirection(Servo.Direction.REVERSE);

        lLift.setDirection(DcMotorSimple.Direction.REVERSE);
        rLift.setDirection(DcMotorSimple.Direction.FORWARD);

        //Initialize moters and servos to starting positon
        Lextendo.setPosition(EXTENDO_IN);
        Rextendo.setPosition(EXTENDO_IN);

//        moveLiftsToZero();
    }

    // Raise Lift function
    public void raiseLift() {
        if (lLift.getCurrentPosition() > LIFT_HIGH && rLift.getCurrentPosition() > LIFT_HIGH) {
            rLift.setTargetPosition(LIFT_HIGH);
            lLift.setTargetPosition(LIFT_HIGH);
            rLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rLift.setPower(-1);
            lLift.setPower(-1);

        }
    }

    // Lower Lift function
    public void lowerLift() {
        if (true) {
            rLift.setTargetPosition(LIFT_LOW);
            lLift.setTargetPosition(LIFT_LOW);
            rLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rLift.setPower(0.5);
            lLift.setPower(0.5);
        }
    }

    // Stop Lift function
    public void stopLiftVertical() {
        rLift.setTargetPosition(rLift.getCurrentPosition());
        lLift.setTargetPosition(lLift.getCurrentPosition());
        lLift.setPower(0);
        rLift.setPower(0);
    }

    //extendo functions
    public void Bextendo_in() {
       Rextendo.setPosition(EXTENDO_IN);
       Lextendo.setPosition(EXTENDO_IN);
    }
    public void Bextendo_out() {
       Rextendo.setPosition(EXTENDO_OUT);
       Lextendo.setPosition(EXTENDO_OUT);
    }

    public void Bextendo_transfer() {
        Rextendo.setPosition(EXTENDO_TRANSFER);
        Lextendo.setPosition(EXTENDO_TRANSFER);
    }

    //moves lifts down based on touch sensor input
   public void moveLiftsToZero() {
        lLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lLift.setPower(-1);
        rLift.setPower(-1);

        while (!touch1.isPressed() && !touch2.isPressed()) {
            // Wait until one of the touch sensors is pressed
        }
//
        lLift.setPower(0);
        rLift.setPower(0);

        lLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void GoToPositionVertical(int target)
    {
        while (lLift.getCurrentPosition() != target && rLift.getCurrentPosition() != target)
        {
            lLift.setTargetPosition(target);
            rLift.setTargetPosition(target);

            lLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            lLift.setPower(1);
            rLift.setPower(1);
        }
    }



    //lift position output
    public int getLiftPosition(){
        return lLift.getCurrentPosition();
    }
    public double getExtendoPosition(){
        return Lextendo.getPosition();
    }
}