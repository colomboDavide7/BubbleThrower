/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.model;

import java.awt.Point;

/**
 *
 * @author davidecolombo
 */
public class TrajectoryCalculator implements TrajectoryCalculatorIF {
    
    private Point pressedPoint;
    private Point releasedPoint;
    private final int MAX_DISTANCE_IN_PIXEL = 300;

    public static TrajectoryCalculator createTrajectoryCalculator(){
        return new TrajectoryCalculator();
    }
    
    private TrajectoryCalculator(){
    }
    
    @Override
    public void setReleasedPoint(Point point) {
        this.releasedPoint = point;
    }
    
    @Override
    public void setPressedPoint(Point point) {
        this.pressedPoint = point;
    }
    
    @Override
    public int getPercentagePower() {
        return calculatePercentagePower();
    }
    
    private int calculatePercentagePower(){
        double distanceInPixel = calculateDistanceInPixelBetweenPoints();
        if(distanceInPixel < MAX_DISTANCE_IN_PIXEL)
            return (int) ((distanceInPixel / (double) MAX_DISTANCE_IN_PIXEL)*100);
        return 100;
    }
    
    private double calculateDistanceInPixelBetweenPoints(){
        int deltaXinPixel = releasedPoint.x - pressedPoint.x;
        int deltaYinPixel = releasedPoint.y - pressedPoint.y;
        return Math.sqrt(Math.pow(deltaYinPixel, 2) + Math.pow(deltaXinPixel, 2));
    }
    
}
