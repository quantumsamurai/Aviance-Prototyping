/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AvianceSystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.communication.FRCControl;
import edu.wpi.first.wpilibj.communication.UsageReporting;

/**
 *
 * @author Darvin
 */
public class AvianceRobot extends AvianceThread{
    private AvianceThreadManager threadManager = AvianceThreadManager.getInstance();
    public static DriverStation driverStation= DriverStation.getInstance();
    private Watchdog watchdog = Watchdog.getInstance();
    public static final String systemThreadGroup = "roboxsysthreads";
    public static final String teleopThreads = "teleoperatedthreads";
    public static final String continiousThreads = "continiousthreads";
    public static final String[] autonomousRoutines = new String[5];
    public static final long sleepTime = 10;
    
   public AvianceRobot() { 
        watchdog.setEnabled(false);
        FRCControl.observeUserProgramStarting();
        UsageReporting.report(UsageReporting.kResourceType_Language, UsageReporting.kLanguage_Java);
 
        threadManager.createGroup(teleopThreads);
        threadManager.createGroup(systemThreadGroup);
        
        threadManager.addThread(systemThreadGroup, this);
        threadManager.startThreads(systemThreadGroup);
        
       
        for(int i = 0; i < autonomousRoutines.length; i++)
            threadManager.createGroup( autonomousRoutines[i] = "autonomousRoutine" + i );
        
        
        System.out.println("Constructing bot...");
        
    }
    
    
    public void run(){
        boolean error = false;
        
        System.out.println("Starting robot manager");
        
        int autonomousRoutine = 1;
        boolean autonomous;
        boolean teleoperated;
        boolean fms;
        boolean disabled; 
        
        try{
            while(true){
                
                disabled = driverStation.isDisabled();              //
                autonomous = driverStation.isAutonomous();             
                teleoperated = driverStation.isOperatorControl();   
                fms = driverStation.isFMSAttached();                // Field Management System,
               
                
                //*********************** over here we should have a function that somehow returns which routine we want; i suggest another module that constantly poles the joystick..
                //                        we have a lot of unused buttons on there, we can have a system to select routine based on button press
                
                if(disabled){
                    while(driverStation.isDisabled())
                        AvianceThreadHousekeeping(sleepTime);
                }
                
                else if (autonomous){
                    threadManager.startThreads(autonomousRoutines[autonomousRoutine]);
                    
                    while(driverStation.isEnabled())
                        AvianceThreadHousekeeping(sleepTime);
                    threadManager.interruptThreads(autonomousRoutines[autonomousRoutine]); 
                    
                }
                
                else if (teleoperated){
                    threadManager.startThreads(teleopThreads);
                    
                    while(driverStation.isEnabled())
                        AvianceThreadHousekeeping(sleepTime);
                    threadManager.interruptThreads(teleopThreads);
                }
                //throw new Exception();
            }
        }
        catch(AvianceThreadInterruptedException e){
            System.out.println("Robot has completed all operations [interrupt() was invoked]"
                    + "and the robot now terminates. The details of the invoke are as follows (The following is NOT a bug)");
            e.printStackTrace();
            System.out.println("--------------------------------------------- "
            + "Interrupt invoked ^^---------------------------------------------");
        }
        catch(Throwable t){
            t.printStackTrace();
            error = true;
        }
        finally{ // worse,case senario this should be printing....
            if (error) System.out.println("OKAY, so something went wrong [unhandled exception]... hopefully the output up there is useful ^_^");
            System.out.println("As a reminder, the AvianceRobot only stops continious, autonomous and teleop threads.. \n"
                    + "All other threads not part of the system, teleop or autonomous groups are left running and are responsible for manually checking the state of the robot\n"
                    + "System threads are NOT interrupted");
            threadManager.interruptThreads(autonomousRoutines[autonomousRoutine]);
            threadManager.interruptThreads(teleopThreads);
            threadManager.interruptThreads(continiousThreads);
            
            

        }
    }
    
}
