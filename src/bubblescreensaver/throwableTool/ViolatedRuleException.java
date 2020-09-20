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
public class ViolatedRuleException extends RuntimeException {

    /**
     * Creates a new instance of <code>ViolatedRuleException</code> without
     * detail message.
     */
    public ViolatedRuleException() {
    }

    /**
     * Constructs an instance of <code>ViolatedRuleException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ViolatedRuleException(String msg) {
        super(msg);
    }
}
