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
public interface TrajectoryCalculatorIF {
    
    public abstract void clearPoints();
    
    public abstract void setReleasedPoint(Point released);
    
    public abstract void setPressedPoint(Point pressed);
    
    public abstract LinkedList<Point> getPoints();
    
    public abstract int getPercentagePower();
    
    
}
