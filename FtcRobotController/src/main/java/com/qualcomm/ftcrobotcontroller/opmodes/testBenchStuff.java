
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
public class testBenchStuff extends OpMode {
    //vars
        DcMotor motorTest;
        Servo servoTest;
        ColorSensor colorTest;
        GyroSensor gyroTest;
        IrSeekerSensor irTest;
        OpticalDistanceSensor distTest;
        TouchSensor touchTest;
    public void init(){
        colorTest = hardwareMap.colorSensor.get("colorTest");
        gyroTest = hardwareMap.gyroSensor.get("gyroTest");
        irTest = hardwareMap.irSeekerSensor.get("irTest");
        distTest = hardwareMap.opticalDistanceSensor.get("distTest");
        touchTest = hardwareMap.touchSensor.get("touchTest");
        motorTest = hardwareMap.dcMotor.get("motorTest");
        servoTest = hardwareMap.servo.get("servoTest");

    }
    public void dispData(){
        telemetry.addData("head",gyroTest.getHeading());
        telemetry.addData("ColorVals", colorTest.argb());
        telemetry.addData("touch", touchTest.getValue());
        telemetry.addData("distance", distTest.getLightDetected());
        telemetry.addData("motRot", motorTest.getCurrentPosition());
        telemetry.addData("servPos", servoTest.getPosition());
    }
    public void loop(){
        motorTest.setPower(gamepad1.left_stick_x);
        dispData();
    }
}