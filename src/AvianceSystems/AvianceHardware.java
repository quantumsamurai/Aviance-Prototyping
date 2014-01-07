/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AvianceSystems;

import InsightLT.InsightLT;
import Modules.LogitechController;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Darvin
 */
public class AvianceHardware {
   // hardware will be accessed depending on type
    
    // PWM ports for each element
  
    
    //toggle variables
     private static boolean motorStatus;
private static boolean previousButton = false;
private static boolean currentButton = false;
    
    //ADXL345 Accelerometer I2C
    public static final int accelerometerI2CPort = 1;
   
    
 
    //Digital I/O ports
    
    
    //joystick buttons for features

    
    //array representation of I/O
    public static final PWM[] pwm = new PWM[10]; //the hardware needs to be initialized...  (an empty array, no objects) how may ports are there?? 10
    public static final Relay[] relays = new Relay[8]; // empty relay array
    
    //Individualized hardware
    public static LogitechController joystick1 = new LogitechController(1,LogitechController.Mode.X); 
    public static LogitechController joystick2 = new LogitechController(2,LogitechController.Mode.X); 
    
    // encoder setup
     
    //gyro port
    public static final int gyro_port = 1;
    
    //Insight Display
    public static final InsightLT display = new InsightLT(InsightLT.TWO_ONE_LINE_ZONES); 
    
    // System Status
    public static final String Active ="Active";
    public static final String Diasbled = "Disabled";
    
   
   /***
    * This is responsible for initializing hardware that isn't already when the class is loaded into the VM
    */
    static{
    
     
    }
      public static boolean toggleJoystick1(int buttonnumber){
      previousButton = currentButton;
currentButton = joystick1.getRawButton(buttonnumber);

if (currentButton && !previousButton) 
{motorStatus = motorStatus ? false : true;

}

return motorStatus;
    }
        public static boolean toggleJoystick2(int buttonnumber){
      previousButton = currentButton;
currentButton = joystick2.getRawButton(buttonnumber);

if (currentButton && !previousButton) 
{motorStatus = motorStatus ? false : true;

}

return motorStatus;
    }
    
}
