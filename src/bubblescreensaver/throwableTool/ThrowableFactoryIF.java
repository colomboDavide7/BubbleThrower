/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

/**
 *
 * @author davidecolombo
 */
public interface ThrowableFactoryIF {
    
    public abstract ThrowableObject getPrototype(String objectType);
    
}
