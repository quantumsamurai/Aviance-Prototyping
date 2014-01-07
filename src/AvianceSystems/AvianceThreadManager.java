
package AvianceSystems;

import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Darvin
 */
public class AvianceThreadManager {
    private static final AvianceThreadManager threadManager = new AvianceThreadManager();
    private Hashtable threadGroups = new Hashtable();
    
      private AvianceThreadManager(){}
      
      
      
      
      public boolean interruptThreads(String groupID){
         if(!threadGroups.containsKey(groupID)){
              return false;}
        else{ System.out.println("interrupting all threads in group: " + groupID);
            ((Vector) threadGroups.get(groupID)).setElementAt((false ? Boolean.TRUE : Boolean.FALSE), 0);
            for(int c = 1; c < ((Vector) threadGroups.get(groupID)).size(); c++){
                ((AvianceThread) ((Vector) threadGroups.get(groupID)).elementAt(c)).interrupt();
                 }
            return true;
              }
             }
       
      
      
      
      
      public boolean startThreads(String groupID){
         if(!threadGroups.containsKey(groupID)){ 
            System.err.println("Thread group not found: " + groupID);
            return false;
        }
        else{
        System.out.println("starting all threads in group: " + groupID);
             ((Vector) threadGroups.get(groupID)).setElementAt((true ? Boolean.TRUE : Boolean.FALSE), 0);
                for(int c = 1; c < ((Vector) threadGroups.get(groupID)).size(); c++){
                if(!((AvianceThread) ((Vector) threadGroups.get(groupID)).elementAt(c)).isAlive()){
                new Thread(((AvianceThread) ((Vector) threadGroups.get(groupID)).elementAt(c))).start(); 
                }
            }
                return true;
            }
            }   
      
      
      
       public boolean addThread(String groupID, AvianceThread thread){
        
        
        System.out.println("adding " + thread.getName() + " to " + groupID);
        
        if (!threadGroups.containsKey(groupID)){
            this.createGroup(groupID);
            System.out.println("creating the threadgroup..." + groupID);
        }
        {
            if(((Vector) threadGroups.get(groupID)).contains(thread)){
                return false;
            }
            ((Vector) threadGroups.get(groupID)).addElement(thread);
            if(((Boolean) ((Vector) threadGroups.get(groupID)).elementAt(0)).booleanValue() && !thread.isAlive()){
                thread.start();
            }
            return true;
        }
    }
       
       
       
       public void removeThread(String groupID, AvianceThread thread){
        if (threadGroups.containsKey(groupID)){
            if(((Vector) threadGroups.get(groupID)).contains(thread)){
                ((Vector) threadGroups.get(groupID)).removeElement(thread);
            }
        }
    }
       
       
           public void removeGroup(String groupID){
        if (threadGroups.containsKey(groupID)){
            interruptThreads(groupID);
            threadGroups.remove(groupID);
        }
    }
    
           
           
               public boolean createGroup(String groupID){
        if(!threadGroups.containsKey(groupID)){
            threadGroups.put(groupID, new Vector());
            ((Vector) threadGroups.get(groupID)).addElement(Boolean.FALSE);
            return true;
        }
        else return false;
    }
               
               
               
               
        public static AvianceThreadManager getInstance(){
        return threadManager;
    }
                   
                   
                   
                   
    
}
