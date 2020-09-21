/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.controllers;

import bubblescreensaver.throwableTool.MotionRulerIF;
import bubblescreensaver.throwableTool.ThrowableObject;
import java.awt.Image;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author davidecolombo
 */
public class BubbleController extends MouseAdapter implements RenderingIF {

    private ThrowableObject prototype;
    private MotionRulerIF motionRuler;
    private DrawModel model;
    
    public static BubbleController createBubbleController(ThrowableObject prototype){
        return new BubbleController(prototype);
    }
    
    private BubbleController(ThrowableObject prototype){
        this.prototype = prototype;
        this.model = new DrawModel();
        this.motionRuler = MotionRulerIF.createMotionRuler();
    }
    
// =============================================================================
    @Override
    public void mousePressed(MouseEvent evt){
        Point centeredLocation = this.getCenteredLocation(evt.getPoint());
        ThrowableObject clone = prototype.clone(centeredLocation);
        model.addPressedPoint(centeredLocation);
        motionRuler.createThrowableMoverFor(clone);
        model.addLivingObjects(clone);
    }
    
    private Point getCenteredLocation(Point locationToCenter){
        Image image = prototype.getImage();
        int xLocationInPixel = locationToCenter.x - image.getWidth(null) / 2;
        int yLocationInPixel = locationToCenter.y - image.getHeight(null) / 2;
        return new Point(xLocationInPixel, yLocationInPixel);
    }
    
    @Override
    public void mouseDragged(MouseEvent evt){
        model.addDraggedPoint(this.getCenteredLocation(evt.getPoint()));
        model.setLineAsDrawable();
    }
    
    @Override
    public void mouseReleased(MouseEvent evt){
        Point centeredPoint = this.getCenteredLocation(evt.getPoint());
        model.setLineAsNotDrawable();
        motionRuler.launchLastMoverCreated(model.getPercentagePower(), 
                                           centeredPoint);
    }
    
// =============================================================================
    @Override
    public DrawModel getPreconfigDrawModel() {
        return model;
    }
    
}
