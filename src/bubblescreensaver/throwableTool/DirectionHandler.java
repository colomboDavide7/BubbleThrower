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
    
    DirectionHandler(Point source){
        instantiateDirections(source);
    }
    
    private void instantiateDirections(Point source){
        possibleDirections = new ArrayList<>();
        for(int angle = 0; angle < 360; angle++)
            possibleDirections.add(new Direction(angle, source));
    }
    
    Point getDirectionFromPointToPoint(Point from, Point to){
        double angleInDegrees = calculateAngleInDegreesFromSlope(from, to);
        for(Direction d : possibleDirections)
            if(d.directionMatch((int) angleInDegrees))
                return d.getDirectionPoint();
        return null;
    }
    
    private double calculateAngleInDegreesFromSlope(Point from, Point to){
        int deltaXinPixel = to.x - from.x;
        int deltaYinPixel = to.y - from.y;
        
        if(deltaXinPixel < 3 && deltaXinPixel > -3)
            return INFINITY_SLOPE;
        
        if(deltaYinPixel < 3 && deltaYinPixel > -3)
            return 0;
        
        double slope = (-deltaYinPixel / (double) deltaXinPixel);
        
        return getAngleInDegrees(slope, deltaXinPixel, deltaYinPixel);
    }
    
    private double getAngleInDegrees(double slope, int deltaXInPixel, int deltaYInPixel){
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
        int deltaInPixel = 0;
        boolean horizontalMirroring = toMove.wallIntersection.y == MotionRuler.LOWER_BOUND_Y || 
                                      toMove.wallIntersection.y == MotionRuler.UPPER_BOUND_Y;
        boolean verticalMirroring   = toMove.wallIntersection.x == MotionRuler.LOWER_BOUND_X || 
                                      toMove.wallIntersection.x == MotionRuler.UPPER_BOUND_X;
        if(horizontalMirroring)
            deltaInPixel = toMove.wallIntersection.x - toMove.locationInPixel.x;
        else if(verticalMirroring)
           deltaInPixel = toMove.wallIntersection.y - toMove.locationInPixel.y;
        
        toMove.locationInPixel.x = toMove.wallIntersection.x + deltaInPixel;
        toMove.locationInPixel.y = toMove.wallIntersection.y + deltaInPixel;
    }
    
}
