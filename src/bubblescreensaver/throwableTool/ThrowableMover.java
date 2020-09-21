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
    
    private MotionRuler ruler;
    private ThrowableObject toMove;
    private DirectionHandler directionHandler;
    private int percentagePower;
    
    ThrowableMover(ThrowableObject toMove, MotionRuler ruler, DirectionHandler directionHandler){
        this.toMove = toMove;
        this.ruler = ruler;
        this.directionHandler = directionHandler;
    }
    
    void launch(int percentagePower, Point releasedPoint){
        this.percentagePower = percentagePower;
        toMove.direction = directionHandler.getDirectionFromPointToPoint(releasedPoint, 
                                                                         toMove.locationInPixel);
        System.out.println("INITIAL DIRECTION X = " + toMove.direction.x + 
                           "INITIAL DIRECTION Y = " + toMove.direction.y);
        
        ruler.assignWallIntersection(toMove);
        
        System.out.println("WALL INTERSECTION X = " + toMove.wallIntersection.x + 
                           "WALL INTERSECTION Y = " + toMove.wallIntersection.y);
        
        super.start();
    }
    
    @Override
    public void run(){
        for(;;){
            move();
        }
    }
    
    private void move(){
        try{
            int stepsToDo = getStepsToDo();
            int deltaXInPixel = (toMove.direction.x - toMove.locationInPixel.x) / stepsToDo;
            int deltaYInPixel = (toMove.direction.y - toMove.locationInPixel.y) / stepsToDo;            
            int doneSteps = 0;
            
            while(doneSteps < stepsToDo){
                doneSteps++;
                moveLocation(deltaXInPixel, deltaYInPixel);
                Thread.sleep(1000/FPS);
            }
        }catch(InterruptedException ex){
        }
    }
    
    private int getStepsToDo(){
        return FPS*TOTAL_TIME_IN_SECOND;
    }
    
    private void moveLocation(int deltaXInPixel, int deltaYInPixel){
        try{
            toMove.locationToCheck.x = toMove.locationToCheck.x + deltaXInPixel;
            toMove.locationToCheck.y = toMove.locationToCheck.y + deltaYInPixel;
            ruler.checkLocationForMover(toMove);
        }catch(BoundaryHittedException ex){
            System.err.println("\n\nHIT THE BORDER\n\n");
//            directionHandler.reflectLocationAcrossIntersection(toMove);
//            directionHandler.setPressedPoint(toMove.wallIntersection);
//            directionHandler.setReleasedPoint(toMove.locationInPixel);
//            directionHandler.assignDirectionPoint(toMove);
//            ruler.assignWallIntersection(toMove);
        }
    }
    
}
