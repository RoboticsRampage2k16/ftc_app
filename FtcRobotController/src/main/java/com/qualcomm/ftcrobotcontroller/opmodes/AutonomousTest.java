package com.qualcomm.ftcrobotcontroller.opmodes;

public class AutonomousTest extends autoFunc {
    public void loop(){
        START_STATE();
        stop();
    }
    public void START_STATE(){
        PATH(10, 10, .5, 1);
    }
}
