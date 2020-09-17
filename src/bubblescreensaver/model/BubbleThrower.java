/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.model;

import bubblescreensaver.throwableObjects.ThrowableFactory;
import bubblescreensaver.throwableObjects.ThrowableFactoryIF;
import bubblescreensaver.throwableObjects.ThrowableObject;
import bubblescreensaver.throwableObjects.ThrowableObjectIF;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class BubbleThrower implements BubbleThrowerIF {
    
    private List<ThrowableObjectIF> livingObjects;
    private TrajectoryCalculator calculator;
    private ThrowableObject prototype;
    
    public static BubbleThrower createBubbleThrower(){
        return new BubbleThrower();
    }
    
    private BubbleThrower(){
        this.livingObjects = new ArrayList<>();
        this.calculator = TrajectoryCalculator.createTrajectoryCalculator();
    }
    
    public void setPrototype(ThrowableObject prototype){
        this.prototype = prototype;
    }
    
// =============================================================================    
    @Override
    public void addNewObjectAtLocation(Point point) {        
        ThrowableObject clone = prototype.clone();
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
        int percentagePower = calculator.getPercentagePower();
        System.out.println("percentage power = " + percentagePower + "%");
    }

    @Override
    public Iterator getLivingObjects() {
        return this.livingObjects.iterator();
    }
    
    @Override
    public void setReleasedPoint(Point point) {
        this.calculator.setReleasedPoint(point);
    }

    @Override
    public void setPressedPoint(Point point) {
        this.calculator.setPressedPoint(point);
    }
    
}
