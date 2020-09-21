/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.controllers;

import bubblescreensaver.throwableTool.ThrowableObject;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class DrawModel {
    
    private final int MAX_DISTANCE_IN_PIXEL = 300;
    
    private List<ThrowableObject> liviginObjects = new ArrayList<>();
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
    
    void addLivingObjects(ThrowableObject obj){
        this.liviginObjects.add(obj);
    }
    
    int getPercentagePower() {
        return calculatePercentagePower();
    }
    
    private int calculatePercentagePower(){
        double distanceInPixel = calculateDistanceInPixelBetweenPoints();
        if(distanceInPixel < MAX_DISTANCE_IN_PIXEL)
            return (int) ((distanceInPixel / (double) MAX_DISTANCE_IN_PIXEL)*100);
        return 100;
    }
    
    private double calculateDistanceInPixelBetweenPoints(){
        int deltaXinPixel = draggedPoint.x - pressedPoint.x;
        int deltaYinPixel = draggedPoint.y - pressedPoint.y;
        return Math.sqrt(Math.pow(deltaYinPixel, 2) + Math.pow(deltaXinPixel, 2));
    }
    
// =============================================================================
    public Iterator getLivingObjects(){
        return this.liviginObjects.iterator();
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
