/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Point;

/**
 *
 * @author davidecolombo
 */
class ThrowableMover extends Thread {
    
    private final int FPS = 15;
    private final int TOTAL_TIME_IN_SECOND = 5;
    private final int STEPS_TO_DO = FPS*TOTAL_TIME_IN_SECOND;
        
    private MotionRuler ruler;
    private ThrowableObject toMove;
    private int percentagePower;
    private boolean stopMoving = false;
    private int doneSteps = 0;
    
    ThrowableMover(ThrowableObject toMove, MotionRuler ruler){
        this.toMove = toMove;
        this.ruler = ruler;
    }
    
    void launch(int percentagePower, Point from){
        this.percentagePower = percentagePower;
        
        toMove.xIntersection = from.x;
        toMove.yIntersection = from.y;
        ruler.assignDirectionPoint(toMove);
        ruler.assignWallIntersection(toMove);
        super.start();
    }
    
    @Override
    public void run(){
        for(;;){
            move();
        }
    }
    
    private void move(){
        this.stopMoving = false;
        this.doneSteps = 0;
        
        int deltaXInPixel = (toMove.xIntersection - toMove.xPosition) / STEPS_TO_DO;
        int deltaYInPixel = (toMove.yIntersection - toMove.yPosition) / STEPS_TO_DO; 

        while(doneSteps < STEPS_TO_DO){
            if(stopMoving)
                return;
            this.doTheStep(deltaXInPixel, deltaYInPixel);
        }
    }
    
    private void doTheStep(int dx, int dy){
        try{
            doneSteps++;
            toMove.addDeltaToXTemp(dx);
            toMove.addDeltaToYTemp(dy);
            ruler.movementRequestValidation(toMove);
            Thread.sleep(1000/FPS);
        }catch(BoundaryHittedException ex){
            handleBoundaryHittedException();
        }catch(InterruptedException ex){
            // do nothing
        }
    }
    
    private void handleBoundaryHittedException(){
        this.stopMoving = true;
        ruler.assignDirectionPoint(toMove);
        resetObjectPositions();
        ruler.assignWallIntersection(toMove);
    }
        
    private void resetObjectPositions(){
        toMove.setXPositionEqualToXIntersection();
        toMove.setYPositionEqualToYIntersection();
        toMove.setXTempEqualToXIntersection();
        toMove.setYTempEqualToYIntersection();
    }
    
}

// ===========================================================================================================
// debug
//            System.out.println("DIRECTION X = " + toMove.xDirection + 
//                               "DIRECTION Y = " + toMove.yDirection);
//            System.out.println("WALL INTERSECTION X = " + toMove.xIntersection + 
//                               "WALL INTERSECTION Y = " + toMove.yIntersection);
//            System.out.println("LOCATION X = " + toMove.xPosition + 
//                               "LOCATION Y = " + toMove.yPosition);
//            System.err.println("DELTA X = " + deltaXInPixel);
//            System.err.println("DELTA Y = " + deltaYInPixel);