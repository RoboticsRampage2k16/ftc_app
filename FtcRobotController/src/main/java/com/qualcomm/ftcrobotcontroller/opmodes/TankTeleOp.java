package com.qualcomm.ftcrobotcontroller.opmodes;
public class TankTeleOp extends coreProfile {
    //filter term used to determine how sensitive we want the joysticks to be
        double joyFilter = 0.1;
    //refresh rate
        int driveRefresh = 10; //time in milliseconds the phone should update the robot
    //speed Control
        double speedGov = .5;//max power percentage for master wheel
        double turnRatio = 1;//slave power is a ratio of masters wheel (change if one wheel is moving faster than the other)
        double servoJerk = .03;

    public TankTeleOp() {
    }//constructor

    public void readJoys() {
        rightPower = -gamepad1.right_stick_y;
        leftPower = -gamepad1.left_stick_y;
        if (gamepad1.a) leftPos-=servoJerk;
        if (gamepad1.y) leftPos+=servoJerk;
        if (gamepad1.x) rightPos-=servoJerk;
        if (gamepad1.b) rightPos+=servoJerk;
        if (gamepad1.dpad_left) rotoPos+=servoJerk;
        if (gamepad1.dpad_right) rotoPos-=servoJerk;
        if (gamepad1.dpad_down) speedGov -= .01;
        if (gamepad1.dpad_up) speedGov += .01;

        filterVals();
    }//scans gamepad for input

    public void filterVals(){
        leftPower *= speedGov;//speed control
        rightPower *= (speedGov * turnRatio);
        if (Math.abs(leftPower) <= joyFilter) leftPower = 0;
        if (Math.abs(rightPower) <= joyFilter) rightPower = 0;
        if (leftPos < .6) leftPos = .6;
        if (leftPos > 1) leftPos = 1;
        if (rightPos < .4) rightPos = .4;
        if (rightPos > 1) rightPos = 1;
        if (rotoPos < 0) rotoPos = 0;
        if (rotoPos > 1) rotoPos = 1;
        if (dropPos < 0) dropPos = 0;
        if (dropPos > 1) dropPos = 1;
    }//filter gamepad values

    public void loop() {
        readJoys();
        setRobot();
        dispData();
        wait(driveRefresh);
    } //game loop for continuous reading

}