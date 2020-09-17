/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.view;

import bubblescreensaver.controllers.DrawModel;
import bubblescreensaver.controllers.RenderingIF;
import bubblescreensaver.throwableObjects.ThrowableObjectIF;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

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
        
        List<ThrowableObjectIF> livingObjects = model.getLivingObjects();
        drawLivingObjects(g, livingObjects);
        
        LinkedList<Point> points = model.getPoints();
        drawLineBetweenPoints(g, points);
    }
    
// =============================================================================
    private void drawLivingObjects(Graphics g, List<ThrowableObjectIF> livingObjects){
        for(ThrowableObjectIF obj : livingObjects)
            g.drawImage(obj.getImage(),
                        obj.getXLocationInPixel(),
                        obj.getYLocationInPixel(),
                        null);
    }
    
    private void drawLineBetweenPoints(Graphics g, LinkedList<Point> points){
        try{
            g.setColor(Color.RED);
            Point firstPoint = points.getFirst();
            Point lastPoint  = points.getLast();
            g.drawLine(firstPoint.x, firstPoint.y, lastPoint.x, lastPoint.y);
        }catch(NoSuchElementException ex){
            // no points in the list
        }
    }
    
}
