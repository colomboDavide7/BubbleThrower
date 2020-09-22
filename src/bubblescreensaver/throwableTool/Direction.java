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
class Direction {
    
    static double radiansToDegrees(double angleInRadians){
        return (double) ((angleInRadians / Math.PI) * 180);
    }
    
    static double degreesToRadians(double angleInDegrees){
        return (double) ((angleInDegrees / 180) * Math.PI);
    }
    
    private int xDirection;
    private int yDirection;
    private double angleInDegrees;
    
    Direction(double angleInDegrees){
        this.angleInDegrees = angleInDegrees;
        calculateDirectionPoint();
    }
    
    private void calculateDirectionPoint(){
        double angleInRadians = degreesToRadians(this.angleInDegrees);
        double modulus = Math.sqrt(Math.pow(MotionRuler.UPPER_BOUND_X - MotionRuler.LOWER_BOUND_X, 2) + 
                                   Math.pow(MotionRuler.UPPER_BOUND_Y - MotionRuler.LOWER_BOUND_Y, 2));
        this.xDirection = (int) (Math.cos(angleInRadians)*modulus);
        this.yDirection = (int) (Math.sin(-angleInRadians)*modulus);
    }
    
    int getXDirection(){
        return this.xDirection;
    }
    
    int getYDirection(){
        return this.yDirection;
    }
    
    boolean directionMatch(int angleInDegrees){
        return this.angleInDegrees == angleInDegrees;
    }
        
}
