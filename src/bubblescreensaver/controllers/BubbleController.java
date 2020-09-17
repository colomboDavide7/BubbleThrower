/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.controllers;

import bubblescreensaver.model.BubbleThrowerIF;
import bubblescreensaver.model.TrajectoryCalculatorIF;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;


/**
 *
 * @author davidecolombo
 */
public class BubbleController extends MouseAdapter implements RenderingIF {

    private TrajectoryCalculatorIF calculator;
    private BubbleThrowerIF thrower;
    private DrawModel model;
    
    public static BubbleController createBubbleController(){
        return new BubbleController();
    }
    
    private BubbleController(){
        this.model = new DrawModel();
    }
    
    public void setBubbleThrower(BubbleThrowerIF thrower){
        this.thrower = thrower;
    }
    
    public void setTrajectoryCalculator(TrajectoryCalculatorIF calculator){
        this.calculator = calculator;
    }
    
// =============================================================================
    @Override
    public void mousePressed(MouseEvent evt){
        Point pressedPoint = evt.getPoint();
        thrower.addNewObjectAtLocation(pressedPoint);
        calculator.setPressedPoint(pressedPoint);
        model.addPressedPoint(pressedPoint);
    }
    
    @Override
    public void mouseDragged(MouseEvent evt){
        Point draggedPoint = evt.getPoint();
        model.addDraggedPoint(draggedPoint);
        model.setLineAsDrawable();
    }
    
    @Override
    public void mouseReleased(MouseEvent evt){
        model.setLineAsNotDrawable();
        Point releasedPoint = evt.getPoint();
        calculator.setReleasedPoint(releasedPoint);
        int percentagePower = calculator.getPercentagePower();
        thrower.throwBubble(percentagePower);
    }
    
// =============================================================================
    @Override
    public DrawModel getPreconfigDrawModel() {
        
        Iterator livingObjects = thrower.getLivingObjects();
        model.addLivingObjects(livingObjects);
        
        return model;
    }
    
}
