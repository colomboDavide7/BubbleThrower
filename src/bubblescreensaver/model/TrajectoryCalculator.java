/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.model;

/**
 *
 * @author davidecolombo
 */
class TrajectoryCalculator {
    
    int clickedX;
    int clickedY;
    int draggedX;
    int draggedY;
    
    private final int MAXIMUM_DISTANCE_IN_PIXEL = 100;
    
    int getPercentageForce(){
        int dx = draggedX - clickedX;
        int dy = draggedY - clickedY;
        
        int distanceInPixel = (int) Math.sqrt(Math.pow(dy, 2) + Math.pow(dx, 2));
        
        if(distanceInPixel >= MAXIMUM_DISTANCE_IN_PIXEL)
            return 100;
        else
            return (int) (distanceInPixel / MAXIMUM_DISTANCE_IN_PIXEL) * 100;
    }
    
}
