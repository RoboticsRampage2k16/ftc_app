package com.qualcomm.ftcrobotcontroller.opmodes;
public class AutonomousFar extends autoFunc {
    public double DistPath = 10/2;
    public void start(){
        zeroWheelEncoders();
        leftPower = 0;
        rightPower = 0;
        setRobot();
        FIRST();
    }

    public void FIRST(){
        wait(12000);
        PATH(DistPath, DistPath,.2,1);

    }
}
