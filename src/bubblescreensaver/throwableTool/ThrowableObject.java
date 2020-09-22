/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author davidecolombo
 */
public abstract class ThrowableObject {
    
    protected int velocityInPixel;
    protected Image image;
    
    protected int xTemp;
    protected int yTemp;
    protected int xPosition;
    protected int yPosition;
    protected int xDirection;
    protected int yDirection;
    protected int xIntersection;
    protected int yIntersection;
    
    public abstract ThrowableObject clone(int xPos, int yPos);
    
    public abstract Image getImage();
    
    public abstract int getXTempInPixel();
    
    public abstract int getYTempInPixel();
        
    protected synchronized void setXTempInPixel(int xTemp){
        this.xTemp = xTemp;
    }
    
    protected synchronized void setYTempInPixel(int yTemp){
        this.yTemp = yTemp;
    }
    
    protected synchronized void addDeltaToXTemp(int delta){
        this.xTemp += delta;
    }
    
    protected synchronized void addDeltaToYTemp(int delta){
        this.yTemp += delta;
    }
    
    protected synchronized void setXTempEqualToXIntersection(){
        this.xTemp = this.xIntersection;
    }
    
    protected synchronized void setYTempEqualToYIntersection(){
        this.yTemp = this.yIntersection;
    }
    
    protected void setXPositionEqualToXIntersection(){
        this.xPosition = this.xIntersection;
    }
    
    protected void setYPositionEqualToYIntersection(){
        this.yPosition = this.yIntersection;
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
    
    protected void setImage(Image image){
        this.image = image;
    }
    
}
