/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
class MotionRuler implements MotionRulerIF {
    
    static final int LOWER_BOUND_X = 0;
    static final int LOWER_BOUND_Y = 0;
    static final int UPPER_BOUND_X = 1000;
    static final int UPPER_BOUND_Y = 800;
    
    final Point upperLeftCorner = new Point(LOWER_BOUND_X, LOWER_BOUND_Y);
    final Point upperRightCorner = new Point(UPPER_BOUND_X, LOWER_BOUND_Y);
    final Point bottomLeftCorner = new Point(LOWER_BOUND_X, UPPER_BOUND_Y);
    final Point bottomRightCorner = new Point(UPPER_BOUND_X, UPPER_BOUND_Y);
            
    private List<LinearBoundary> walls;
    private List<ThrowableMover> movers;
    private ThrowableMover moverToLaunch;
    private DirectionHandler directionHandler;
    
    static MotionRuler createMotionRuler() {
        return new MotionRuler();
    }
    
    private MotionRuler(){
        this.directionHandler = new DirectionHandler();
        this.movers = new ArrayList<>();
        instantiateBoundaries();
    }
        
    private void instantiateBoundaries(){
        this.walls = new ArrayList<>();
        this.walls.add(LinearBoundary.createLinearBoundary(upperLeftCorner, upperRightCorner));
        this.walls.add(LinearBoundary.createLinearBoundary(upperLeftCorner, bottomLeftCorner));
        this.walls.add(LinearBoundary.createLinearBoundary(bottomLeftCorner, bottomRightCorner));
        this.walls.add(LinearBoundary.createLinearBoundary(upperRightCorner, bottomRightCorner));
    }
    
    @Override
    public void createThrowableMoverFor(ThrowableObject obj) {
        this.moverToLaunch = new ThrowableMover(obj, this);
    }
    
    @Override
    public void launchLastMoverCreated(int percentagePower, Point releasedPoint){
        this.moverToLaunch.launch(percentagePower, releasedPoint);
        this.movers.add(moverToLaunch);
    }
        
    void assignDirectionPoint(ThrowableObject toMove){
        directionHandler.getDirectionFromPointToPoint(toMove);
    }
    
    void assignWallIntersection(ThrowableObject toMove){
        int x1 = toMove.xPosition;
        int y1 = toMove.yPosition;
        int x2 = toMove.xDirection;
        int y2 = toMove.yDirection;
            
        for(LinearBoundary wall : walls){
            int x3 = wall.p1.x;
            int y3 = wall.p1.y;
            int x4 = wall.p2.x;
            int y4 = wall.p2.y;

            double denominator = ((x1 - x2)*(y3 - y4)) - ((y1 - y2)*(x3 - x4));
            
            if(denominator == 0)
                continue;
            
            double t = ((x1 - x3)*(y3 - y4) - (y1 - y3)*(x3 - x4)) / denominator;
            double u = -((x1 - x2)*(y1 - y3) - (y1 - y2)*(x1 - x3)) / denominator;
            
            if(t > 0 && t < 1 && u > 0 && u < 1){
                toMove.xIntersection = (int) (x1 + t*(x2 - x1));
                toMove.yIntersection = (int) (y1 + t*(y2 - y1));
                break;
            }
        }
    }
    
    void movementRequestValidation(ThrowableObject toMove){        
        checkCollisionWithBorder(toMove);
        checkCollisionWithMover(toMove);
    }
    
    private void checkCollisionWithBorder(ThrowableObject toMove){   
        if(wallIntersectionReached(toMove))
            throw new BoundaryHittedException();
    }
    
    private boolean wallIntersectionReached(ThrowableObject toMove){
        if(toMove.yIntersection == LOWER_BOUND_Y){
            if(toMove.yTemp <= toMove.yIntersection)
                return true;
        }else if(toMove.yIntersection == UPPER_BOUND_Y){
            if((toMove.yTemp + toMove.image.getHeight(null)) >= toMove.yIntersection)
                return true;
        }else if(toMove.xIntersection == LOWER_BOUND_X){
            if(toMove.xTemp <= toMove.xIntersection)
                return true;
        }else if(toMove.xIntersection == UPPER_BOUND_X){
            if((toMove.xTemp + toMove.image.getWidth(null)) >= toMove.xIntersection)
                return true;
        }
        
        return false;
    }
    
    private void checkCollisionWithMover(ThrowableObject toMove){
//        for(ThrowableMover m : movers)
//            if(m.isInMyPlace(toMove))
//                throw new HitTheBorderException("Hit an object");
    }

}
