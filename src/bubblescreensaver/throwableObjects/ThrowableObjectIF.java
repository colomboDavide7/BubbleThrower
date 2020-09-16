/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableObjects;

import java.awt.Image;

/**
 *
 * @author davidecolombo
 */
public interface ThrowableObjectIF {
    
    public abstract int getXLocationInPixel();
    
    public abstract int getYLocationInPixel();
    
    public abstract Image getImage();
    
}
