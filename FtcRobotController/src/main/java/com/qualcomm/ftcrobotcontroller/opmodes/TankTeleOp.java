package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;



public class TankTeleOp extends OpMode {
    //constructor dont touch
    public TankTeleOp() {
    }

    //variable decleration
    //declaration of the motorObjects
    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motorPull;
    //powers of the motors
    double leftPower = 0;
    double rightPower = 0;
    double pullPower = 0;
    //pos of the joySticks
    double leftYpos = 0;
    double rightYpos = 0;
    //filter term used to determine how sensitive we want the joysticks to be
    double joyFilter = (double) .1;

    //speedControl
    double speedGov = .5;
    double govCorrect = 0;

    //refresh rate
    int driveRefresh = 10;


    @Override
    public void init() {
        //getting names fom config file
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorPull = hardwareMap.dcMotor.get("motorPull");
        motorPull.setDirection(DcMotor.Direction.REVERSE);
    }

    //cheap wait function
    private void wait(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
            stop();
        }
    }

    public void readJoys() {
        //stop robot
        if (gamepad1.back) stop();
        //front wheel spin control
        while(gamepad1.y){
            pullPower += .01;
            wait(500);
        }
        while(gamepad1.x) {
            pullPower += -.01;
            wait(500);
        }
        if (pullPower < 0) pullPower = 0;
        if (pullPower > .5) pullPower = .5;
        //joystick to wheel controls
        leftYpos = gamepad1.left_stick_y * speedGov;//speed control
        rightYpos = gamepad1.right_stick_y * speedGov - govCorrect;

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
        motorPull.setPower(pullPower);

    }

    public void dispData() {
    }

    public void loop() {
        readJoys();
        setRobot();
        dispData();
        wait(driveRefresh);
    }
}