/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AvianceSystems;

/**
 *
 * @author Darvin
 */
public class AvianceThread extends Thread {
protected boolean interrupted = false;
 protected long sleeptime = 10;
 
 public AvianceThread(){
 super();
 interrupted = false;
 }
 
    public void run(){
    startup();
    try{
    iteration();
    
    AvianceThreadHousekeeping(sleeptime);}
    catch(AvianceThreadInterruptedException e){
    reset();
    interrupted = false;
    }
    
    }
    
       protected void AvianceThreadHousekeeping(long sleepTime) throws AvianceThreadInterruptedException{
        if (interrupted){
            throw new AvianceThreadInterruptedException();
        }
        try{
            Thread.sleep(sleepTime);
        }
        catch(InterruptedException e){
            throw new AvianceThreadInterruptedException();
        }
    }
    public void interrupt(){
            if(Thread.currentThread().isAlive()){
            interrupted = true;
            super.interrupt();
        }
    }
    protected void startup(){}
    protected void iteration(){}
    protected void reset(){}
    
}
