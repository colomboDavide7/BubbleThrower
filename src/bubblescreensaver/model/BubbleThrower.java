/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.model;

import bubblescreensaver.throwableObjects.ResourceManagerIF;
import bubblescreensaver.throwableObjects.ThrowableObject;
import bubblescreensaver.throwableObjects.ThrowableObjectIF;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class BubbleThrower implements BubbleThrowerIF {
    
    private ResourceManagerIF resManager = null;
    private List<ThrowableObjectIF> livingObjects;
    
    public static BubbleThrower createBubbleThrower(){
        return new BubbleThrower();
    }
    
    private BubbleThrower(){
        this.livingObjects = new ArrayList<>();
    }
    
    public void setResourceManager(ResourceManagerIF manager) {
        if(this.resManager == null)
            this.resManager = manager;
    }
    
// =============================================================================    
    @Override
    public void addNewObject(Point point) {        
        ThrowableObject clone = resManager.getClone();
        clone.setLocationInPixel(point);
        this.livingObjects.add(clone);
    }
    
    private Point centerPoint(Point p, int offsetX, int offsetY){
        Point centeredPoint = new Point();
        centeredPoint.x = p.x - offsetX;
        centeredPoint.y = p.y - offsetY;
        return centeredPoint;
    }
                  
    @Override
    public void throwBubble() {
//        System.err.println("percentage power = " + this.percentagePower + "%");
 
    }

    @Override
    public List<ThrowableObjectIF> getLivingObjects() {
        return this.livingObjects;
    }
    
}
