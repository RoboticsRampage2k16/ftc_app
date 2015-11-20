package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
This is an attempt at getting the robot to record a set of instructions by way of joystick input
then carry out this motion by itself in an autonomous state after pressing and letting go of the start button
 */

public class MachineLearningTeleTank extends OpMode {
    //declaration of the motorObjects
    double leftPower = 0;
    double rightPower = 0;
    double leftYpos = 0;
    double rightYpos = 0;
    double joyFilter = 0.05;
    double driveRefresh = 20;
    double readSpeed = 10;
    double startDelay = 10;
    int leftMotorArIndex = 0;
    int rightMotorArIndex = 0;
    int maxOP = 30000 / driveRefresh;

    //arrays used to log motorVals at certain times
    double [] leftMotorTime = new double[maxOP]; //chose double because that's the default varType used by the developers to store time
    double [] leftPowerLog = new double[maxOP];
    int leftMotorArIndex;
    double [] rightMotorTime = new double[maxOP];
    double [] rightPowerLog = new double[maxOP];
    int rightMotorArIndex;

    //constructor don't touch
    public MachineLearningTeleTank() {
    }

    @Override
    public void init() {//sets all the initial values of the variables
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);// in case we need this
    }

    @Override//game loop where you drive the robot and data is recorded
    public void loop() {
        leftYpos =  gamepad1.left_stick_y / 2;
        rightYpos =  gamepad1.right_stick_y / 2;
        //stop program on back button press
        if (gamepad1.back)
            stop();
        //should run generated code on start button press
        if (gamepad1.start)
            runAuto();
        //filters out tiny movements in the joysticks and sets the motorPowers accordingly
        if (Math.abs(leftYpos-leftPower) >= joyFilter)
            leftPower = leftYpos;
        if (Math.abs(rightYpos-rightPower) >= joyFilter)
            rightPower = rightYpos;
        logState();
        setRobot();
        displayDriveData();
        wait(driveRefresh);
    }

    //umm... stops the robot i think
    public void stop() {
        leftPower = 0;
        rightPower = 0;
        setRobot();
    }

    //the actual function thats going to run the "generated autonomous code"
    public void runAuto(){
        this.resetStartTime();//im assuming this resets the clock
        leftMotorArIndex = 0;
        rightMotorArIndex = 0;
        //waits for you to let go of the start button before executing the auto code
        while (gamepad1.start){
            wait(10);
        }
        while (leftMotorArIndex < maxOP && rightMotorArIndex < maxOP){
            if (this.time >= leftMotorTime[leftMotorArIndex]){//constantly looks to see if the motor value is behind schedule
                leftPower = leftPowerLog[leftMotorArIndex];   //should set motors accordingly once the reference time from the initial drive
                leftMotorArIndex+=1;                          //has been met or passed
            }
            if (this.time >= rightMotorTime[rightMotorArIndex]) {
                rightPower = rightPowerLog[rightMotorArIndex];
                rightMotorArIndex += 1;
            }
            setRobot();
            wait(readSpeed);//well have to work out how fast it should refresh
        } 
        stop();//upon running out of instructions or a second press of the start button the robot should stop
    }

    //cheap wait function i put together
    public void wait(int waitTime){
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
            stop();
        }
    }

    //saves me a line when i know im setting the power for both motors
    public void setRobot(){
        //hardware assignment of power variables
        motorLeft.setPower(leftPower);
        motorRight.setPower(rightPower);
    }

    public void logState(){
        rightMotorTime[rightMotorArIndex] = this.time;
        rightPowerLog[rightMotorArIndex]= rightPower;
        rightMotorArIndex ++;
        leftMotorTime[leftMotorArIndex] = this.time;
        leftPowerLog[leftMotorArIndex]= leftPower;
        leftMotorArIndex ++;
    }
    
    public void displayDriveData(){
    //simple output to the driver's station for debugging
    telemetry.addData(double.toString(leftPower), "Left Power");
    telemetry.addData(double.toString(rightPower), "Right Power");
    telemetry.addData(Integer.toString(leftMotorArIndex), "logLen");
    }
}