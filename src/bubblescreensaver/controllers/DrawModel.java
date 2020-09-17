/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.controllers;

import java.awt.Point;
import java.util.Iterator;

/**
 *
 * @author davidecolombo
 */
public class DrawModel {
    
    private Iterator liviginObjects;
    private Point pressedPoint;
    private Point draggedPoint;
    private boolean canDrawLine = false;
    
    void setLineAsDrawable(){
        this.canDrawLine = true;
    }
    
    void setLineAsNotDrawable(){
        this.canDrawLine = false;
    }
    
    void addPressedPoint(Point pressedPoint){
        this.pressedPoint = pressedPoint;
    }
    
    void addDraggedPoint(Point draggedPoint){
        this.draggedPoint = draggedPoint;
    }
    
    void addLivingObjects(Iterator livingObject){
        this.liviginObjects = livingObject;
    }
    
// =============================================================================
    public Iterator getLivingObjects(){
        return this.liviginObjects;
    }
    
    public Point getPressedPoint(){
        return this.pressedPoint;
    }
    
    public Point getDraggedPoint(){
        return this.draggedPoint;
    }
    
    public boolean canDrawLine(){
        return this.canDrawLine;
    }
    
// =============================================================================
    
}
