/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class BubbleThrower implements BubbleThrowerIF {
    
    private MotionRuler ruler;
    private TrajectoryCalculator calculator;
    private ThrowableObject prototype;
    private ThrowableMover tempMover;
    private List<ThrowableObjectIF> livingObjects;
    
    public static BubbleThrower createBubbleThrower(){
        return new BubbleThrower();
    }
    
    private BubbleThrower(){
        this.livingObjects = new ArrayList<>();
        this.ruler = MotionRuler.createMotionRuler();
        this.calculator = TrajectoryCalculator.createTrajectoryCalculator();
    }
    
    public void setPrototype(ThrowableObject prototype){
        this.prototype = prototype;
    }
    
// =============================================================================    
    @Override
    public void addThrowableObjectAtLocation(Point point) {
        ThrowableObject clone = this.prototype.clone();
        clone.setLocationInPixel(point);
        clone.centerLocation();
        livingObjects.add(clone);
        tempMover = ThrowableMover.createMover(clone, ruler);
        calculator.setPressedPoint(point);
    }
    
    @Override
    public void throwBubble() {
        int percentagePowerPush = calculator.getPercentagePower();
        tempMover.setPercentagePowerPush(percentagePowerPush);
        //tempMover.start();
        ruler.addMover(tempMover);
    }
    
    @Override
    public Iterator<ThrowableObjectIF> getLivingObjects() {
        return this.livingObjects.iterator();
    }
    
    @Override
    public void setReleasedPoint(Point point) {
        calculator.setReleasedPoint(point);
    }
    
}
