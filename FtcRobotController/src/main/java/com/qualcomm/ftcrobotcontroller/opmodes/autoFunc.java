package com.qualcomm.ftcrobotcontroller.opmodes;

public class autoFunc extends coreProfile {
    double distRat = 1;
    int driveRefresh = 10;
    double distanceError = 5;

    public void setWheels(double mSpeed, double sRatio) {
        motorLeft.setPower(mSpeed);
        motorRight.setPower(mSpeed * sRatio);
        dispData();
    }
    public void setWheelSpeed(){

    }
    public void zeroWheelEncoders() {
        motorLeft.setTargetPosition(0);
        motorRight.setTargetPosition(0);
    }

    public double distToEnc(double distance) {
        return distance * distRat;
    }

    public double encToDist(double Encode) {
        return Encode / distRat;
    }

    public void PATH(double leftDistance, double rightDistance, double mSpeed, double sRatio) {
        zeroWheelEncoders();
        setWheels(mSpeed, sRatio);
        while (motorLeft.getPower() != 0 && motorRight.getPower() != 0) {
            if (Math.abs(encToDist(motorLeft.getCurrentPosition()) - leftDistance) < distanceError) {
                motorLeft.setPower(0);
            }
            if (Math.abs(encToDist(motorRight.getCurrentPosition()) - rightDistance) < distanceError) {
                motorLeft.setPower(0);
            }
            wait(driveRefresh);
            dispData();
        }
        //zeroWheelEncoders();
    }

    public void RIGHT(){}

    public void LEFT(){}
}