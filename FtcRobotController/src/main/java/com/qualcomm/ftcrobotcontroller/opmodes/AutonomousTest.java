package com.qualcomm.ftcrobotcontroller.opmodes;
public class AutonomousTest extends autoFunc {
    public double DistPath = 1;
    public void start(){
        zeroWheelEncoders();
        leftPower = 0;
        rightPower = 0;
        setRobot();
        FIRST();
    }

    public void FIRST(){
        wait(12000);
        leftPower = .2;
        rightPower = .2;
        setRobot();
        wait(5000);
        leftPower = 0;
        rightPower = 0;
        setRobot();
    }
}