package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class TankTeleOp extends OpMode {
    //constructor dont touch
    public TankTeleOp() {
    }

    @Override
    public void stop() {
    }

    //variable decleration
    //declaration of the motorObjects
    DcMotor motorRight;
    DcMotor motorLeft;
    //powers of the motors
    double leftPower = 0;
    double rightPower = 0;
    //pos of the joySticks
    double leftYpos = 0;
    double rightYpos = 0;
    //pull power
    double motorPullPower = 0;
    //filter term used to determine how sensitive we want the joysticks to be
    double joyFilter = (double) 0;

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    public void wait(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
            stop();
        }
    }

    public void readJoys() {
        leftYpos = gamepad1.left_stick_y / 2;
        rightYpos = gamepad1.right_stick_y / 2;

        if (Math.abs(leftYpos - leftPower) >= joyFilter) {
            leftPower = leftYpos;
        }
        if (Math.abs(rightYpos - rightPower) >= joyFilter) {
            rightPower = rightYpos;
        }
    }

    public void setRobot() {
        //hardware assignment of power variables
        motorLeft.setPower(leftPower);
        motorRight.setPower(rightPower);

    }

    public void dispData() {
        //simple output to the driver's station for debugging
        //telemetry.addData(double.toString(leftPower), "Left Power");
        //telemetry.addData(double.toString(rightPower), "Right Power");
    }

    public void loop() {
        //stop program on back button press
        if (gamepad1.back) stop();
        readJoys();
        setRobot();
        dispData();
        wait(10);
    }
}