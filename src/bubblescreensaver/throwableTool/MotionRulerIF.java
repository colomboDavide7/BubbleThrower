/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Point;

/**
 *
 * @author davidecolombo
 */
public interface MotionRulerIF {
    
    static MotionRulerIF createMotionRuler(){
        return MotionRuler.createMotionRuler();
    }
    
    public abstract void createThrowableMoverFor(ThrowableObject obj);
    
    public abstract void launchLastMoverCreated(int percentagePower, Point releasedPoint);
    
}
