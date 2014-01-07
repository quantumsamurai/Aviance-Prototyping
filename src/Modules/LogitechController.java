/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

/**
 *
 * @author Darvin
 */

import edu.wpi.first.wpilibj.Joystick;


/**
 * Handle input from the Logitech Controller, which has two physical modes.
 * <p>
 * The Logitech controller has the following axis assignments in <em>D MODE</em>:
 * <ul>
 * <li>Axis 1 - Left Joystick horizontal (X) axis</li>
 * <li>Axis 2 - Left Joystick vertical (Y) axis</li>
 * <li>Axis 3 - Right Joystick horizontal (X) axis</li>
 * <li>Axis 4 - Right Joystick vertical (Y) axis</li>
 * <li>Axis 5 - D-Pad Horizontal</li>
 * <li>Axis 6 - D-Pad Vertical</li>
 * <ul>
 * In <em>X MODE</em>:
 * <ul>
 * <li>Axis 1 - Left Joystick horizontal (X) axis</li>
 * <li>Axis 2 - Left Joystick vertical (Y) axis</li>
 * <li>Axis 3 - Left Trigger</li>
 * <li>Axis 4 - Right Joystick horizontal (X) axis</li>
 * <li>Axis 5 - Right Joystick vertical (Y) axis</li>
 * <li>Axis 6 - Right Trigger</li>
 * <ul>
 */
public class LogitechController extends Joystick {

    protected static final class DModeAxes {
        public static final int LEFT_JOYSTICK_HORIZONTAL = 1;
        public static final int LEFT_JOYSTICK_VERTICAL = 2;
        public static final int RIGHT_JOYSTICK_HORIZONTAL = 3;
        public static final int RIGHT_JOYSTICK_VERTICAL = 4;
        public static final int D_PAD_HORIZONTAL = 5;
        public static final int D_PAD_VERTICAL = 6;
    }

    protected static final class XModeAxes {
        public static final int LEFT_JOYSTICK_HORIZONTAL = 1;
        public static final int LEFT_JOYSTICK_VERTICAL = 2;
        public static final int LEFT_TRIGGER = 3;
        public static final int RIGHT_JOYSTICK_HORIZONTAL = 4;
        public static final int RIGHT_JOYSTICK_VERTICAL = 5;
        public static final int RIGHT_TRIGGER = 6;
    }

 
    public static final Mode DEFAULT_MODE = Mode.D;


   
    public static final class Mode {
      
        public static final Mode X = new Mode("X");//X Mode
       
        public static final Mode D = new Mode("D"); // D Mode
        private final String value;

        private Mode( String value ) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
private Mode mode = DEFAULT_MODE; //set mode default
public LogitechController( int port,
                               Mode mode ) {
        super(port);
        this.setMode(mode);
       
    }

  // set mode of controller
    public void setMode( Mode mode ) {
        this.mode = mode != null ? mode : DEFAULT_MODE;
        System.out.println("Setting Logitech Controller mode to " + this.mode);
    }


    public Mode getMode() {
        return mode; // get mode of controller
    }



}
    

