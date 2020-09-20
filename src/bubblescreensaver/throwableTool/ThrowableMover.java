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
    
    private int percentagePowerPush;
    private Point destinationInPixel;
    
    static ThrowableMover createMover(ThrowableObject toMove, MotionRuler ruler){
        return new ThrowableMover(toMove, ruler);
    }
    
    private ThrowableMover(ThrowableObject toMove, MotionRuler ruler){
        this.toMove = toMove;
        this.ruler = ruler;
    }
    
    void setPercentagePowerPush(int percentagePower){
        this.percentagePowerPush = percentagePower;
    }
    
    void setDestinationInPixel(Point destinationInPixel){
        this.destinationInPixel = destinationInPixel;
    }
    
    boolean isInMyPlace(ThrowableObject toMove){
        return toMove.isInMyPlace(toMove);
    }
        
    @Override
    public void run(){
        
        for(;;){
            move();
        }
    }
    
    private void move(){
        try{
            int doneSteps = 0;
            int stepsToDo = getStepsToDo();
            
            Point currentLocation = toMove.getLocationInPixel();
            
            int deltaXInPixel = (destinationInPixel.x - currentLocation.x) / stepsToDo;
            int deltaYInPixel = (destinationInPixel.y - currentLocation.y) / stepsToDo;
            
            while(doneSteps < stepsToDo){
                doneSteps++;
                moveLocation(deltaXInPixel, deltaYInPixel);
                Thread.sleep(1000);
            }
        }catch(InterruptedException ex){
            
        }
    }
    
    private int getStepsToDo(){
        return FPS*TOTAL_TIME_IN_SECOND;
    }
    
    private void moveLocation(int deltaXInPixel, int deltaYInPixel){
        try{
            Point currentLocation = toMove.getLocationInPixel();
            Point locationToCheck = new Point(currentLocation.x + deltaXInPixel, 
                                              currentLocation.y + deltaYInPixel);
            toMove.setLocationToCheck(locationToCheck);
            ruler.checkLocationForMover(toMove);
            toMove.setLocationInPixel(currentLocation);
        }catch(ViolatedRuleException ex){
            System.err.println(ex.getMessage());
        }
    }
    
}
