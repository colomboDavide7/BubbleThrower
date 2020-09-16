/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableObjects;

/**
 *
 * @author davidecolombo
 */
public interface ResourceManagerIF {
    
    public abstract ThrowableObject getClone();
    
    public abstract void loadResource(String objectType);
    
}
