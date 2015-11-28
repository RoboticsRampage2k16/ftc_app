package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class JoyStickTest extends OpMode {
    public void init(){

    }

    public void loop(){
        telemetry.addData("leftJoyx", gamepad1.left_stick_x);
    }

    public void stop(){

    }
}
