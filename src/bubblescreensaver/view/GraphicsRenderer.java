/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.view;

import bubblescreensaver.controllers.DrawModel;
import bubblescreensaver.controllers.RenderingIF;
import bubblescreensaver.throwableTool.ThrowableObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

/**
 *
 * @author davidecolombo
 */
class GraphicsRenderer extends Thread {
    
    private Display display;
    private int refreshTimeInMillis;
    private RenderingIF interactor;
    
    static GraphicsRenderer createNewRenderer(){
        return new GraphicsRenderer();
    }
    
    private GraphicsRenderer(){
    }
    
    void setRefreshTimeInMillis(int time){
        this.refreshTimeInMillis = time;
    }
    
    void setDisplay(Display display){
        this.display = display;
    }
    
    void setInteractor(RenderingIF interactor){
        this.interactor = interactor;
    }
    
// =============================================================================
    @Override
    public void run() {
        try{
            for(;;){
                this.tick();
                Thread.sleep(refreshTimeInMillis);
            }
        }catch(InterruptedException ex){
        }
    }
    
    private void tick(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(display != null)
                    display.repaint();
            }
        });
    }
    
    void draw(Graphics g){
        DrawModel model = interactor.getPreconfigDrawModel();
        
        Iterator<ThrowableObject> livingObjects = model.getLivingObjects();
        drawLivingObjects(g, livingObjects);
        
        if(model.canDrawLine()){
            Point pressedPoint = model.getPressedPoint();
            Point draggedPoint = model.getDraggedPoint();
            drawLineBetweenPoints(g, pressedPoint, draggedPoint);
        }
    }
    
// =============================================================================
    private void drawLivingObjects(Graphics g, Iterator<ThrowableObject> iterator){
        while(iterator.hasNext()){
            ThrowableObject obj = iterator.next();
            g.drawImage(obj.getImage(),
                        obj.getXTempInPixel(),
                        obj.getYTempInPixel(),
                        null);
        }
    }
    
    private void drawLineBetweenPoints(Graphics g, Point pressedPoint, Point draggedPoint){
        g.setColor(Color.RED);
        g.drawLine(pressedPoint.x, pressedPoint.y, draggedPoint.x, draggedPoint.y);
    }
    
}
