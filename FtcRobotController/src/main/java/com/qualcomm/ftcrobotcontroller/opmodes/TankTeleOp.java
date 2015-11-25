package com.qualcomm.ftcrobotcontroller.opmodes;

public class TankTeleOp extends coreProfile{
    //filter term used to determine how sensitive we want the joysticks to be
    double joyFilter = 0.1;
    //refresh rate
    int driveRefresh = 10;
    //gamepad readings
    double leftYpos = 0;
    double rightYpos = 0;
    double pullPad = 0;
    //speedControl
    double speedGov = .5;
    double govCorrect = 0;
    double turnRatio = 1;
    double pullInc = .01/(500/driveRefresh);
    double pullCap = .5;

    public TankTeleOp() {
    }//constructer
    public void readJoys() {
        if(gamepad1.y) pullPad += pullInc;
        if(gamepad1.x) pullPad -= pullInc;
        rightYpos = gamepad1.right_stick_y;
        leftYpos = gamepad1.left_stick_y;
        filterVals();
    }//scans gamepad for input
    public void filterVals(){
        leftYpos *= speedGov;//speed control
        rightYpos *= (speedGov * turnRatio);
        if (Math.abs(leftYpos) <= joyFilter) {
            leftYpos = 0;
        }
        if (pullPad < 0) pullPad = 0;
        if (pullPad > pullCap) pullPad = pullCap;
    }//filter gamepad values
    public void mapJoys(){
        pullPower = pullPad;
        leftPower = leftYpos;
        rightPower = rightYpos;
    }//map gamepad vals to physical vals
    public void setRobot() {
        motorLeft.setPower(leftPower);
        motorRight.setPower(rightPower);
        motorPull.setPower(pullPower);
    } //hardware assign physical vals
    public void dispData() {
    }// display any telemetry data
    public void loop() {
        readJoys();
        mapJoys();
        setRobot();
        dispData();
        wait(driveRefresh);
    } //game loop for continuous reading
}