/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.view;

import bubblescreensaver.controllers.RenderingIF;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;

/**
 *
 * @author davidecolombo
 */
public class DisplayFrame extends JFrame {
    
    private Display display;
    private GraphicsRenderer renderer;
    
    public static DisplayFrame createNewDisplayFrame(int widthInPixel, 
                                                     int heigthInPixel, 
                                                     int waitingTimeInMillis){
        return new DisplayFrame(widthInPixel, 
                                heigthInPixel, 
                                waitingTimeInMillis);
    }
    
    private DisplayFrame(int widthInPixel, int heigthInPixel, int waitingTimeInMillis){
        
        configureDisplay(widthInPixel, heigthInPixel);
        configureRenderingThread(waitingTimeInMillis);
        configureDisplayFrame();
    }
    
    private void configureDisplay(int widthInPixel, int heigthInPixel){
        display = Display.createNewDisplay();
        display.setDisplayDimension(widthInPixel, heigthInPixel);
    }
    
    private void configureRenderingThread(int refreshTimeInMillis){
        this.renderer = GraphicsRenderer.createNewRenderer();
        this.renderer.setRefreshTimeInMillis(refreshTimeInMillis);
    }
    
    private void configureDisplayFrame(){
                
        renderer.setDisplay(display);
        display.setGraphicsRenderer(renderer);
                
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                getContentPane().add(display);
                
                setTitle("The Bubble Thrower");
                pack();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
            }
        });
    }
    
// =============================================================================
    public void startRenderingThread(){
        renderer.start();
    }
    
    @Override
    public void addMouseListener(MouseListener listener){
        super.addMouseListener(listener);
    }
    
    @Override
    public void addMouseMotionListener(MouseMotionListener listener){
        super.addMouseMotionListener(listener);
    }
    
    public void setInteractor(RenderingIF interactor){
        this.renderer.setInteractor(interactor);
    }
    
// =============================================================================
    
}
