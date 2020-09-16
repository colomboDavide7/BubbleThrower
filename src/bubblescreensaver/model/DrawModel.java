/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.model;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author davidecolombo
 */
public class DrawModel {
    
    private List<Point> bubblePoints  = null;
    private Point pressed = null;
    private Point released = null;
    
    void clearPressedAndReleasedPoints(){
        this.pressed = null;
        this.released = null;
    }
    
    void addBubble(Point point){
        if(bubblePoints == null)
            bubblePoints = new ArrayList<>();
        bubblePoints.add(point);
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
    public List<Point> getBubbleList(){
        return this.bubblePoints;
    }
    
    public Point getPressedPoint(){
        return this.pressed;
    }
    
    public Point getReleasedPoint(){
        return this.released;
    }
    
// =============================================================================
    
}
