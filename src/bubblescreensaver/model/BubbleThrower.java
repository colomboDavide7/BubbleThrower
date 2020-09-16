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
public class BubbleThrower implements BubbleThrowerIF, RenderingIF {
    
    private ResourceManagerIF resManager = null;
    private List<ThrowableObjectIF> livingObjects;
    private DrawModel model;
    
    private Point pressed = null;
    private Point released = null;
    
    private int percentagePower;
    private final int MAX_DISTANCE_IN_PIXEL = 300;
    
    public static BubbleThrowerIF createBubbleThrower(){
        return new BubbleThrower();
    }
    
    private BubbleThrower(){
        this.model     = new DrawModel();
        this.livingObjects = new ArrayList<>();
    }
    
// =============================================================================    
    @Override
    public void addNewObject(Point point) {        
        ThrowableObject clone = resManager.getClone();
        
        this.pressed = centerPoint(point, clone.getXOffsetInPixel(), clone.getYOffsetInPixel());
        clone.setLocationInPixel(pressed);
        
        this.livingObjects.add(clone);
        model.addLivingObjects(livingObjects);
    }
    
    private Point centerPoint(Point p, int offsetX, int offsetY){
        Point centeredPoint = new Point();
        centeredPoint.x = p.x - offsetX;
        centeredPoint.y = p.y - offsetY;
        return centeredPoint;
    }
    
    @Override
    public void takeAim(Point point) {
        this.released = point;
        calculatePercentagePower();
        System.out.println("percentage power = " + this.percentagePower + "%");
        model.addPressedAndReleasedPoints(pressed, released);
    }
              
    private void calculatePercentagePower(){
        int deltaXinPixel = released.x - pressed.x;
        int deltaYinPixel = released.y - pressed.y;
        double distanceInPixel = Math.sqrt(Math.pow(deltaYinPixel, 2) + Math.pow(deltaXinPixel, 2));
        if(distanceInPixel >= MAX_DISTANCE_IN_PIXEL)
            this.percentagePower = 100;
        else
            this.percentagePower = (int) ((distanceInPixel / (double) MAX_DISTANCE_IN_PIXEL)*100);
    }
    
    @Override
    public void throwBubble() {
        System.err.println("percentage power = " + this.percentagePower + "%");
        model.clearPressedAndReleasedPoints();
        
    }
        
    @Override
    public void setResourceManager(ResourceManagerIF manager) {
        if(this.resManager == null)
            this.resManager = manager;
    }
    
// =============================================================================
    @Override
    public DrawModel getDrawModel() {
        return this.model;
    }

}
