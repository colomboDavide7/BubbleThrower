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
public abstract class ThrowableObject implements ThrowableObjectIF {
    
    protected Image image;
    protected Point locationInPixel;
    protected Point locationToCheck;
    
    protected boolean isInMyPlace(ThrowableObject anotherObject){
        Point TheOtherUpperLeftCorner = anotherObject.locationToCheck;
        Point TheOtherBottomRightCorner = anotherObject.getBottomRightCorner(TheOtherUpperLeftCorner);
        Point myUpperLeftCorner = this.locationToCheck;
        Point myBottomRightCorner = this.getBottomRightCorner(myUpperLeftCorner);
        
        boolean TheOtherUpperYInsideMyPlace = myUpperLeftCorner.y <= TheOtherUpperLeftCorner.y && 
                                              myBottomRightCorner.y >= TheOtherUpperLeftCorner.y;
        boolean TheOtherBottomYInsideMyPlace = myUpperLeftCorner.y <= TheOtherBottomRightCorner.y && 
                                               myBottomRightCorner.y >= TheOtherBottomRightCorner.y;
        
        boolean insideMyHorizontalOffsets = TheOtherUpperYInsideMyPlace || TheOtherBottomYInsideMyPlace;
        
        boolean TheOtherUpperXInsideMyPlace = myUpperLeftCorner.x <= TheOtherUpperLeftCorner.x &&
                                              myBottomRightCorner.x >= TheOtherUpperLeftCorner.x;
        boolean TheOtherBottomXInsideMyPlace = myUpperLeftCorner.x <= TheOtherBottomRightCorner.x &&
                                              myBottomRightCorner.x >= TheOtherBottomRightCorner.x;
        
        boolean insideMyVerticalOffsets = TheOtherUpperXInsideMyPlace || TheOtherBottomXInsideMyPlace;
        
        return insideMyVerticalOffsets && insideMyHorizontalOffsets;
    }
    
    protected boolean checkOnLeftSide(int lowerBoundX){
        return this.locationToCheck.x <= lowerBoundX;
    }
    
    protected boolean checkOnRightSide(int upperBoundX){
        return (this.locationToCheck.x + this.getWidth()) >= upperBoundX;
    }
    
    protected boolean checkOnTopSide(int lowerBoundY){
        return this.locationToCheck.y <= lowerBoundY;
    }
    
    protected boolean checkOnBottomSide(int upperBoundY){
        return (this.locationToCheck.y + this.getHeight()) >= upperBoundY;
    }
    
    protected Point getUpperRightCorner(Point upperLeftCorner){
        return new Point(upperLeftCorner.x + this.getWidth(), 
                         upperLeftCorner.y);
    }
    
    protected Point getBottomLeftCorner(Point upperLeftCorner){
        return new Point(upperLeftCorner.x, 
                         upperLeftCorner.y + this.getHeight());
    }
    
    protected Point getBottomRightCorner(Point upperLeftCorner){
        return new Point(upperLeftCorner.x + this.getWidth(), 
                         upperLeftCorner.y + this.getHeight());
    }
    
    protected void setLocationToCheck(Point location){
        this.locationToCheck = location;
    }
    
    protected Point getLocationToCheck(){
        return this.locationToCheck;
    }
    
    protected void setLocationInPixel(Point location){
        this.locationInPixel = location;
    }
    
    protected Point getLocationInPixel(){
        return this.locationInPixel;
    }
    
    protected void centerLocation(){
        this.locationInPixel.x = this.locationInPixel.x - this.getXOffsetInPixel();
        this.locationInPixel.y = this.locationInPixel.y - this.getYOffsetInPixel();
    }
    
    protected int getXOffsetInPixel(){
        return this.image.getWidth(null) / 2;
    }
    
    protected int getYOffsetInPixel(){
        return this.image.getHeight(null) / 2;
    }
    
    protected int getWidth(){
        return this.image.getWidth(null);
    }
    
    protected int getHeight(){
        return this.image.getHeight(null);
    }
    
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
    
    public abstract ThrowableObject clone();
    
}
