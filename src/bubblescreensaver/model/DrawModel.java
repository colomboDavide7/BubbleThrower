/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.model;

import bubblescreensaver.throwableObjects.ThrowableObjectIF;
import java.util.List;
import java.awt.Point;

/**
 *
 * @author davidecolombo
 */
public class DrawModel {
    
    private List<ThrowableObjectIF> liviginObjects;
    private Point pressed = null;
    private Point released = null;
    
    void clearPressedAndReleasedPoints(){
        this.pressed = null;
        this.released = null;
    }
    
    void addLivingObjects(List<ThrowableObjectIF> livingObject){
        this.liviginObjects = livingObject;
    }
        
    void addClickedPoint(Point point){
        this.pressed = point;
    }
    
    void addDraggedPoint(Point point){
        this.released = point;
    }
    
    void addPressedAndReleasedPoints(Point pressed, Point released){
        this.pressed = pressed;
        this.released = released;
    }
    
// =============================================================================
    public List<ThrowableObjectIF> getLivingObjects(){
        return this.liviginObjects;
    }
    
    public Point getPressedPoint(){
        return this.pressed;
    }
    
    public Point getReleasedPoint(){
        return this.released;
    }
    
// =============================================================================
    
}
