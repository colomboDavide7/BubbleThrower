/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.model;

import bubblescreensaver.throwableObjects.ResourceManagerIF;
import java.awt.Point;

/**
 *
 * @author davidecolombo
 */
public interface BubbleThrowerIF {
    
    public abstract void setResourceManager(ResourceManagerIF manager);
    
    public abstract void addNewObject(Point point);
    
    public abstract void takeAim(Point point);
    
    public abstract void throwBubble();
    
}
