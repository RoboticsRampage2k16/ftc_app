package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
public class coreProfile extends OpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    Servo rotoDrop;
    Servo servoDrop;
    Servo servoLeft;
    Servo servoRight;
    ColorSensor colorLight;
    OpticalDistanceSensor distLeft;
    OpticalDistanceSensor distRight;
    OpticalDistanceSensor distFront;
    //------------------------

    double leftPower = 0;
    double rightPower = 0;
    double rotoPos = 1;
    double leftPos = 1;
    double rightPos = .2;
    double dropPos = 0;
    //------------------------
    public coreProfile(){}//constructor

    public void init() {
        //getting names fom config file
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        rotoDrop = hardwareMap.servo.get("rotoDrop");
        servoLeft = hardwareMap.servo.get("servoLeft");
        servoRight = hardwareMap.servo.get("servoRight");
        servoDrop = hardwareMap.servo.get("servoDrop");
        distFront = hardwareMap.opticalDistanceSensor.get("distFront");
        distLeft = hardwareMap.opticalDistanceSensor.get("distLeft");
        distRight = hardwareMap.opticalDistanceSensor.get("distRight");
        colorLight = hardwareMap.colorSensor.get("colorLight");
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
        rotoDrop.setPosition(rotoPos);
        servoLeft.setPosition(leftPos);
        servoRight.setPosition(rightPos);
        servoDrop.setPosition(dropPos);
        wait(100);
    } //hardware assign physical vals

    public void dispData() {
        telemetry.addData("rotoPos",rotoPos);
        telemetry.addData("dropPos",dropPos);
        telemetry.addData("leftPos",leftPos);
        telemetry.addData("rightPos",rightPos);
        telemetry.addData("leftMotor",motorLeft.getCurrentPosition());
        telemetry.addData("rightMotor",motorRight.getCurrentPosition());
        telemetry.addData("distFront",distFront.getLightDetected());
        telemetry.addData("distLeftt",distLeft.getLightDetected());
        telemetry.addData("distRt",distFront.getLightDetected());
    }// display any telemetry data

    public void loop(){}//fault of app creator

    public void stop(){}
}
