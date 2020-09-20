/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
class MotionRuler {
    
    private final int LOWER_BOUND_X = 0;
    private final int LOWER_BOUND_Y = 0;
    private final int UPPER_BOUND_X = 1000;
    private final int UPPER_BOUND_Y = 800;
    
    private List<ThrowableMover> movers;
    
    static MotionRuler createMotionRuler(){
        return new MotionRuler();
    }
    
    private MotionRuler(){
        this.movers = new ArrayList<>();
    }
        
    void addMover(ThrowableMover mover){
        movers.add(mover);
    }
    
    void checkLocationForMover(ThrowableObject toMove){        
        checkCollisionWithBorder(toMove);
        checkCollisionWithMover(toMove);
    }
    
    private void checkCollisionWithBorder(ThrowableObject toMove){        
        if(toMove.checkOnLeftSide(LOWER_BOUND_X)  || toMove.checkOnRightSide(UPPER_BOUND_X) ||
           toMove.checkOnTopSide(LOWER_BOUND_Y)   || toMove.checkOnBottomSide(UPPER_BOUND_Y))
            throw new ViolatedRuleException("Hit the border");
    }
    
    private void checkCollisionWithMover(ThrowableObject toMove){
        for(ThrowableMover m : movers)
            if(m.isInMyPlace(toMove))
                throw new ViolatedRuleException("Hit an object");
    }
    
}
