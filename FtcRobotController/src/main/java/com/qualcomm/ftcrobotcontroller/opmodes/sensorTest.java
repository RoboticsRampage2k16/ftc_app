package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Angry on 12/12/2015.
 */
public class sensorTest extends coreProfile{
    public void loop(){
        telemetry.addData("Fdist",distFront.getLightDetectedRaw());
    }
}
