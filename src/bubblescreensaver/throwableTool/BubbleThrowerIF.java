/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Point;
import java.util.Iterator;

/**
 *
 * @author davidecolombo
 */
public interface BubbleThrowerIF {
        
    public abstract void addThrowableObjectAtLocation(Point point);
    
    public abstract void setReleasedPoint(Point point);
    
    public abstract void throwBubble();
    
    public abstract Iterator getLivingObjects();
    
}
