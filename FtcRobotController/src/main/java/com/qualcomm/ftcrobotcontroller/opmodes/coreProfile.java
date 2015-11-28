package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class coreProfile extends OpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorPull;
    Servo buttonArm;
    ColorSensor lightCalc;
    OpticalDistanceSensor rightDistance;
    OpticalDistanceSensor forwardDistance;
    //------------------------
    double leftPower = 0;
    double rightPower = 0;
    double pullPower = 0;
    double armPos = 0;
    //------------------------
    public coreProfile(){}//constructor
    public void init() {
        //getting names fom config file
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorPull = hardwareMap.dcMotor.get("motorPull");
        motorPull.setDirection(DcMotor.Direction.REVERSE);
    }//config file mapping
    public void wait(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
            stop();
        }
    }//cheap wait function
    public void setRobot() {
        motorLeft.setPower(leftPower);
        motorRight.setPower(rightPower);
        motorPull.setPower(pullPower);
        buttonArm.setPosition(armPos);
    } //hardware assign physical vals
    public void loop(){}//fault of app creator
}
