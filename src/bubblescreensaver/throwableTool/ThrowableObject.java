/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author davidecolombo
 */
public abstract class ThrowableObject {
    
    Image image;
    Point locationInPixel;
    Point direction;
    Point locationToCheck;
    Point wallIntersection;
    
    public abstract ThrowableObject clone(Point locationInPixel);
    
    public abstract Image getImage();
    
    public abstract int getXLocationInPixel();
    
    public abstract int getYLocationInPixel();
    
    protected Image cloneImage(){
        BufferedImage clonedImage = new BufferedImage(this.image.getWidth(null), 
                                                      this.image.getHeight(null), 
                                                      BufferedImage.TYPE_INT_ARGB);
        Graphics g = clonedImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return clonedImage;
    }
    
    protected void setImage(Image image){
        this.image = image;
    }
    
    protected void setLocationInPixel(Point locationInPixel){
        this.locationInPixel = locationInPixel;
        this.locationToCheck = new Point(locationInPixel);
    }
    
}
