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
    
    private Point pressedPoint  = null;
    private Point releasedPoint = null;
    
    private int percentagePower;
    private final int MAX_DISTANCE_IN_PIXEL = 300;

    public static TrajectoryCalculator createTrajectoryCalculator(){
        return new TrajectoryCalculator();
    }
    
    private TrajectoryCalculator(){
    }
    
    int getPercentagePower() {
        calculatePercentagePower();
        return this.percentagePower;
    }
    
    private void calculatePercentagePower(){
        double distanceInPixel = calculateDistanceInPixelBetweenPoints();
        if(distanceInPixel >= MAX_DISTANCE_IN_PIXEL)
            this.percentagePower = 100;
        else
            this.percentagePower = (int) ((distanceInPixel / (double) MAX_DISTANCE_IN_PIXEL)*100);
    }
    
    private double calculateDistanceInPixelBetweenPoints(){
        int deltaXinPixel = releasedPoint.x - pressedPoint.x;
        int deltaYinPixel = releasedPoint.y - pressedPoint.y;
        return Math.sqrt(Math.pow(deltaYinPixel, 2) + Math.pow(deltaXinPixel, 2));
    }

    public void clearPressedAndReleasedPoints(){
        this.pressedPoint  = null;
        this.releasedPoint = null;
    }
    
    @Override
    public void setReleasedPoint(Point released) {
        this.releasedPoint = released;
    }

    @Override
    public void setPressedPoint(Point pressed) {
        this.pressedPoint = pressed;
    }

    @Override
    public Point getPressedPoint() {
        return this.pressedPoint;
    }

    @Override
    public Point getReleasedPoint() {
        return this.releasedPoint;
    }
    
}
