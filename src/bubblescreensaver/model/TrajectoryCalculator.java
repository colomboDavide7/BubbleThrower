/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.model;

import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author davidecolombo
 */
public class TrajectoryCalculator implements TrajectoryCalculatorIF {
    
    private LinkedList<Point> points;
    private final int MAX_DISTANCE_IN_PIXEL = 300;

    public static TrajectoryCalculator createTrajectoryCalculator(){
        return new TrajectoryCalculator();
    }
    
    private TrajectoryCalculator(){
        points = new LinkedList<>();
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
        Point firstPoint = points.getFirst();
        Point lastPoint  = points.getLast();
        
        int deltaXinPixel = lastPoint.x - firstPoint.x;
        int deltaYinPixel = lastPoint.y - firstPoint.y;
        return Math.sqrt(Math.pow(deltaYinPixel, 2) + Math.pow(deltaXinPixel, 2));
    }
     
    @Override
    public void setReleasedPoint(Point released) {
        this.points.add(released);
    }
    
    @Override
    public void setPressedPoint(Point pressed) {
        this.points.add(pressed);
    }
    
    @Override
    public LinkedList<Point> getPoints() {
        return points;
    }

    @Override
    public void clearPoints() {
        points.clear();
    }
    
}
