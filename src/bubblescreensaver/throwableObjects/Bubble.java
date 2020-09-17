/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableObjects;

import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author davidecolombo
 */
public class Bubble extends ThrowableObject {

    
    static Bubble createNewBubble(){
        return new Bubble();
    }
    
    private Bubble(){
        locationInPixel = new Point();
    }
    
    @Override
    public ThrowableObject clone() {
        Bubble clone = new Bubble();
        Image clonedImage = cloneImage();
        clone.setImage(clonedImage);
        return clone;
    }

    @Override
    public Image getImage() {
        return super.image;
    }

    @Override
    public int getXLocationInPixel() {
        return super.locationInPixel.x;
    }

    @Override
    public int getYLocationInPixel() {
        return super.locationInPixel.y;
    }
     
}
