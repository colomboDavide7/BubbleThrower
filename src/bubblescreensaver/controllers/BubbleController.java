/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.controllers;

import bubblescreensaver.model.BubbleThrowerIF;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author davidecolombo
 */
public class BubbleController extends MouseAdapter {

    private BubbleThrowerIF thrower = null;
    
    public static BubbleController createBubbleController(BubbleThrowerIF thrower){
        return new BubbleController(thrower);
    }
    
    private BubbleController(BubbleThrowerIF thrower){
        this.thrower = thrower;
    }
    
// =============================================================================
    @Override
    public void mousePressed(MouseEvent evt){
        Point clickedPoint = evt.getPoint();
        thrower.addBubble(clickedPoint);
    }
    
    @Override
    public void mouseReleased(MouseEvent evt){
        thrower.throwBubble();
    }
    
    @Override
    public void mouseDragged(MouseEvent evt){
        Point releasedPoint = evt.getPoint();
        thrower.takeAim(releasedPoint);
    }
    
// =============================================================================
    
}
