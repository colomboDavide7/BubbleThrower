/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Point;

/**
 *
 * @author davidecolombo
 */
public class BoundaryHittedException extends RuntimeException {

    public static final String LEFT   = "LEFT";
    public static final String RIGHT  = "RIGHT";
    public static final String TOP    = "TOP";
    public static final String BOTTOM = "BOTTOM";
    
    private String hittedBorder;
    private Point intersectionPoint;
    
    public BoundaryHittedException(){
    
    }
    
    public BoundaryHittedException(String hittedBorder) {
        this.hittedBorder = hittedBorder;
    }
    
    public BoundaryHittedException(Point intersectionPoint){
        this.intersectionPoint = intersectionPoint;
    }
    
    public Point getIntersectionPoint(){
        return this.intersectionPoint;
    }
    
    public String getHittedBorder(){
        return this.hittedBorder;
    }
    
}
