/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.model;

import bubblescreensaver.throwableObjects.ThrowableObjectIF;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public interface BubbleThrowerIF {
        
    public abstract void addNewObject(Point point);
    
    public abstract void throwBubble(int percentagePower);
    
    public abstract List<ThrowableObjectIF> getLivingObjects();
    
}
