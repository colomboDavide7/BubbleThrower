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
public class ThrowableFactory implements ThrowableFactoryIF {
    
    @Override
    public ThrowableObject getThrowableObject(String type) {
        try{
            switch(type){
                case "bubble":
                    return Bubble.createNewBubble();
                    
                default:
                    throw new Exception("Invalid object type: " + type
                                      + "\nValid types are: bubble"
                                      + "\nquitting JVM");
            }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            System.exit(0);
            return null;
        }
    }
    
}
