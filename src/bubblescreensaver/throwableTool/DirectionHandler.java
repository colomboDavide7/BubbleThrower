/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
class DirectionHandler {
    
    private List<Direction> possibleDirections;
    private final int INFINITY_SLOPE = 100000;
    
    DirectionHandler(){
        instantiateDirections();
    }
    
    private void instantiateDirections(){
        possibleDirections = new ArrayList<>();
        for(int angle = 0; angle < 360; angle++)
            possibleDirections.add(new Direction(angle));
    }
    
    void getDirectionFromPointToPoint(ThrowableObject toMove){
        double angleInDegrees = calculateAngleInDegreesFromSlope(toMove);
        for(Direction d : possibleDirections)
            if(d.directionMatch((int) angleInDegrees)){
                toMove.xDirection = d.getXDirection();
                toMove.yDirection = d.getYDirection();
                return;
            }
    }
    
    private double calculateAngleInDegreesFromSlope(ThrowableObject toMove){
        int deltaXinPixel = toMove.xPosition - toMove.xIntersection;
        int deltaYinPixel = toMove.yPosition - toMove.yIntersection;
        
        if(deltaXinPixel < 3 && deltaXinPixel > -3)
            return INFINITY_SLOPE;
        
        if(deltaYinPixel < 3 && deltaYinPixel > -3)
            return 0;
        
        double slope = (-deltaYinPixel / (double) deltaXinPixel);
        
        return applyHeuristicCorrectionToAngle(slope, deltaXinPixel, deltaYinPixel);
    }
    
    private double applyHeuristicCorrectionToAngle(double slope, int deltaXInPixel, int deltaYInPixel){
        double angleInDegrees = Direction.radiansToDegrees(Math.atan(slope));
        if(slope > 0 && slope < this.INFINITY_SLOPE){
            if(deltaXInPixel < 0 && deltaYInPixel > 0)
                angleInDegrees += 180;
        }else if(slope < 0){
            if(deltaXInPixel < 0 && deltaYInPixel < 0)
                angleInDegrees += 180;
            else if (deltaXInPixel > 0 && deltaYInPixel > 0)
                angleInDegrees += 360;
        }else if(slope == INFINITY_SLOPE){
            if(deltaYInPixel > 0)
                angleInDegrees += 180.0;
        }else{
            if(deltaXInPixel < 0)
                angleInDegrees += 180.0;
        }
        
        return angleInDegrees;
    }
    
    void reflectLocationAcrossIntersection(ThrowableObject toMove){
        int deltaInPixel;
        boolean horizontalMirroring = toMove.yIntersection == MotionRuler.LOWER_BOUND_Y || 
                                      toMove.yIntersection == MotionRuler.UPPER_BOUND_Y;
        
        boolean verticalMirroring   = toMove.xIntersection == MotionRuler.LOWER_BOUND_X || 
                                      toMove.xIntersection == MotionRuler.UPPER_BOUND_X;
        if(horizontalMirroring){
            deltaInPixel = toMove.xIntersection - toMove.xPosition;
            toMove.xPosition = toMove.xIntersection + deltaInPixel;            
        }else if(verticalMirroring){
           deltaInPixel = toMove.yIntersection - toMove.yPosition;
           toMove.yPosition = toMove.yIntersection + deltaInPixel;
        }
    }
    
}
