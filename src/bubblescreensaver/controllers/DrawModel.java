/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.controllers;

import bubblescreensaver.throwableObjects.ThrowableObjectIF;
import java.util.List;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author davidecolombo
 */
public class DrawModel {
    
    private List<ThrowableObjectIF> liviginObjects;
    private LinkedList<Point> points;
    
    void addLivingObjects(List<ThrowableObjectIF> livingObject){
        this.liviginObjects = livingObject;
    }
        
    void addPoints(LinkedList<Point> points){
        this.points = points;
    }
    
// =============================================================================
    public List<ThrowableObjectIF> getLivingObjects(){
        return this.liviginObjects;
    }
    
    public LinkedList<Point> getPoints(){
        return this.points;
    }
    
// =============================================================================
    
}
