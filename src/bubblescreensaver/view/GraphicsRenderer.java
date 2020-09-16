/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.view;

import bubblescreensaver.model.DrawModel;
import bubblescreensaver.model.RenderingIF;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
class GraphicsRenderer extends Thread {
    
    private Display display = null;
    private int refreshTimeInMillis;
    private RenderingIF interactor = null;
    
    static GraphicsRenderer createNewRenderer(){
        return new GraphicsRenderer();
    }
    
    private GraphicsRenderer(){
        // do nothing
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
            // do nothing
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
        DrawModel model = interactor.getDrawModel();
        
        List<Point> bubbles = model.getBubbleList();
        if(bubbles != null)
            drawPoints(g, bubbles);
        
        Point clickedPoint = model.getPressedPoint();
        Point draggedPoint = model.getReleasedPoint();
        if(clickedPoint != null && draggedPoint != null)
            drawLine(g, clickedPoint, draggedPoint);

    }
    
// =============================================================================
    private void drawPoints(Graphics g, List<Point> points){
        if(points.isEmpty())
            return;
        
        g.setColor(Color.BLACK);
        for(Point p : points)
            g.fillOval(p.x, p.y, 5, 5);
    }
    
    private void drawLine(Graphics g, Point clicked, Point dragged){
        g.setColor(Color.RED);
        g.drawLine(dragged.x, dragged.y, clicked.x, clicked.y);
    }
    
}
