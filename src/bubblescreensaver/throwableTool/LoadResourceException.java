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
public class LoadResourceException extends RuntimeException {

    /**
     * Creates a new instance of <code>LoadResourceException</code> without
     * detail message.
     */
    public LoadResourceException() {
    }

    /**
     * Constructs an instance of <code>LoadResourceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public LoadResourceException(String msg) {
        super(msg);
    }
}
