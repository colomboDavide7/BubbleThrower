/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.controllers;

import bubblescreensaver.model.BubbleThrowerIF;
import bubblescreensaver.model.TrajectoryCalculatorIF;
import bubblescreensaver.throwableObjects.ThrowableObjectIF;

import java.util.List;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *
 * @author davidecolombo
 */
public class BubbleController extends MouseAdapter implements RenderingIF {

    private TrajectoryCalculatorIF calculator = null;
    private BubbleThrowerIF thrower = null;
    private DrawModel model = null;
    
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
        thrower.addNewObject(pressedPoint);
        calculator.setPressedPoint(pressedPoint);
    }
    
    @Override
    public void mouseDragged(MouseEvent evt){
        Point releasedPoint = evt.getPoint();
        calculator.setReleasedPoint(releasedPoint);
    }
    
    @Override
    public void mouseReleased(MouseEvent evt){
        thrower.throwBubble();
        calculator.clearPressedAndReleasedPoints();
    }
    
// =============================================================================
    @Override
    public DrawModel getPreconfigDrawModel() {
        
        List<ThrowableObjectIF> livingObjects = thrower.getLivingObjects();
        model.addLivingObjects(livingObjects);
        
        Point pressedPoint = calculator.getPressedPoint();
        model.addPressedPoint(pressedPoint);
        
        Point releasedPoint = calculator.getReleasedPoint();
        model.addReleasedPoint(releasedPoint);
        
        return model;
    }
    
}
