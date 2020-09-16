/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author davidecolombo
 */
public abstract class ThrowableObject implements ThrowableObjectIF {
    
    protected Image image;
    protected Point locationInPixel;
    
    public void setLocationInPixel(Point location){
        this.locationInPixel = location;
    }
    
    public int getXOffsetInPixel(){
        return this.image.getWidth(null) / 2;
    }
    
    public int getYOffsetInPixel(){
        return this.image.getHeight(null) / 2;
    }
    
    public abstract ThrowableObject clone();
    
    protected void setImage(Image image){
        this.image = image;
    }
    
    protected Image cloneImage(){
        BufferedImage clonedImage = new BufferedImage(this.image.getWidth(null), 
                                                      this.image.getHeight(null), 
                                                      BufferedImage.TYPE_INT_ARGB);
        Graphics g = clonedImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return clonedImage;
    }
}


//    private int[] getOffsetsInPixel(){
//        int[] offsets = new int[2];
//        offsets[0] = this.image.getWidth(null) / 2;
//        offsets[1] = this.image.getHeight(null) / 2;
//        return offsets;
//    }