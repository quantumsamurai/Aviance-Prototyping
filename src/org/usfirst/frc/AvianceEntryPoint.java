package org.usfirst.frc;

/*
 * StartApplication.java
 *
 */
import AvianceSystems.AvianceRobot;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 * The startApp method of this class is called by the VM to start the
 * application.
 * 
 * The manifest specifies this class as MIDlet-1, which means it will
 * be selected for execution.
 */
public class AvianceEntryPoint extends MIDlet {

    protected void startApp() throws MIDletStateChangeException {
        AvianceRobot Aviancerobot = new AvianceRobot();
      
        
        
        try{
            Thread.sleep(86400000); // equivalent to one day
      }catch(InterruptedException e){
          System.out.println("There is something wrong with the program");
          System.out.println("IT: Have you tried turning it off and on again?" );
          System.out.println("Mood: Fustration");
      }
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
    }
    
}
