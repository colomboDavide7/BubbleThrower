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
class LinearBoundary {
    
    Point p1;
    Point p2;
    
    static LinearBoundary createLinearBoundary(Point p1, Point p2){
        return new LinearBoundary(p1, p2);
    }
    
    private LinearBoundary(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    
}
