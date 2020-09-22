/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Image;

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
        super.velocityInPixel = 5;
    }
    
    private Bubble(){
    }
    
    @Override
    public Image getImage() {
        return this.image;
    }
    
    @Override
    public int getXTempInPixel() {
        return super.xTemp;
    }

    @Override
    public int getYTempInPixel() {
        return super.yTemp;
    }
    
    @Override
    public ThrowableObject clone(int xPos, int yPos) {
        Bubble clone = new Bubble();
        Image clonedImage = cloneImage();
        clone.setImage(clonedImage);
        clone.xPosition = xPos;
        clone.yPosition = yPos;
        clone.xTemp = xPos;
        clone.yTemp = yPos;
        return clone;
    }

}
