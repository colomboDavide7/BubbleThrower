/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author davidecolombo
 */
 class Bubble extends ThrowableObject {

    static Bubble createNewBubble(Image image){
        return new Bubble(image);
    }
    
    private Bubble(Image image){
        super.image = image;
        initInstanceVariables();
    }
    
    private Bubble(){
        initInstanceVariables();
    }
    
    private void initInstanceVariables(){
        locationInPixel = new Point();
        direction = new Point();
        locationToCheck = new Point();
        wallIntersection = new Point();
    }
    
    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public int getXLocationInPixel() {
        return locationToCheck.x;
    }

    @Override
    public int getYLocationInPixel() {
        return locationToCheck.y;
    }
    
    @Override
    public ThrowableObject clone(Point locationInPixel) {
        Bubble clone = new Bubble();
        Image clonedImage = cloneImage();
        clone.setImage(clonedImage);
        clone.setLocationInPixel(locationInPixel);
        return clone;
    }
    
}
