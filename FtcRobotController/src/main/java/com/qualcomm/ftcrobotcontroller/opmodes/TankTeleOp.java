package com.qualcomm.ftcrobotcontroller.opmodes;

public class TankTeleOp extends coreProfile{
    //filter term used to determine how sensitive we want the joysticks to be
        double joyFilter = 0.1;
    //refresh rate
        int driveRefresh = 10; //time in milliseconds the phone should update the robot
    //speed Control
        double speedGov = .5;//max power percentage for master wheel
        double turnRatio = 1;//slave power is a ratio of masters wheel (change if one wheel is moving faster than the other)
        double pullPercentInc = 5;//percent speed increase of front wheel per second
        double pullCap = .5; //max speed that the front wheel will spin
        double pullPowerInc = pullPercentInc / (Math.pow(driveRefresh,2) * 100);//math don't touch
    //gamepad readings
        double leftYpos = 0;
        double rightYpos = 0;
        double pullPad = 0;







    public TankTeleOp() {
    }//constructor
    public void readJoys() {
        if(gamepad1.y) pullPad += pullPowerInc;
        if(gamepad1.x) pullPad -= pullPowerInc;
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