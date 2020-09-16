/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.model;

import java.awt.Point;
import java.util.Observable;

/**
 *
 * @author davidecolombo
 */
public class BubbleThrower extends Observable 
                           implements BubbleThrowerIF, RenderingIF {
    
    private DrawModel model = null;
    private Point pressed = null;
    private Point released = null;
    private int percentagePower;
    private final int MAX_DISTANCE_IN_PIXEL = 300;
    
    public static BubbleThrowerIF createBubbleThrower(){
        return new BubbleThrower();
    }
    
    private BubbleThrower(){
        this.model = new DrawModel();
    }
    
// =============================================================================
    @Override
    public void addBubble(Point point) {
        model.addBubble(point);
        this.pressed = point;
    }
    
    @Override
    public void takeAim(Point point) {
        this.released = point;
        calculatePercentagePower();
        System.out.println("percentage power = " + this.percentagePower + "%");
        model.addPressedAndReleasedPoints(pressed, released);
    }
              
    private void calculatePercentagePower(){
        int deltaXinPixel = released.x - pressed.x;
        int deltaYinPixel = released.y - pressed.y;
        double distanceInPixel = Math.sqrt(Math.pow(deltaYinPixel, 2) + Math.pow(deltaXinPixel, 2));
        if(distanceInPixel >= MAX_DISTANCE_IN_PIXEL)
            this.percentagePower = 100;
        else
            this.percentagePower = (int) ((distanceInPixel / (double) MAX_DISTANCE_IN_PIXEL)*100);
    }
    
    @Override
    public void throwBubble() {
        model.clearPressedAndReleasedPoints();
        System.err.println("percentage power = " + this.percentagePower + "%");
        // Lancio un Thread che agisce sulla struttura dati della Bubble specifica
        
        // Aggiorno posizione e velocità con cui si muove la Bubble
        
    }
    
// =============================================================================
    @Override
    public DrawModel getDrawModel() {
        return this.model;
    }
    
}
