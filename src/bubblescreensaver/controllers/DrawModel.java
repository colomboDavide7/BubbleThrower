/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.controllers;

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
        
    void addPressedPoint(Point point){
        this.pressed = point;
    }
    
    void addReleasedPoint(Point point){
        this.released = point;
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
