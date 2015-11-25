
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;


public class FtcOpModeRegister implements OpModeRegister {

  public void register(OpModeManager manager) {
    manager.register("TankTeleOp",TankTeleOp.class);
    manager.register("JoyStickTest", JoyStickTest.class);
  }
}
