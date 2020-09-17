/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.view;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author davidecolombo
 */
class Display extends JPanel {
                
    private Dimension displayDim; 
    private GraphicsRenderer renderer;
    
    static Display createNewDisplay(){
        return new Display();
    }
    
    private Display(){
        configureDisplay();
    }
    
    private void configureDisplay(){    
        setLayout(null);
        setName(" ");
        setOpaque(false);
    }
    
// =============================================================================
    void setDisplayDimension(int widthInPixel, int heightInPixel){
        displayDim = new Dimension(widthInPixel, heightInPixel);
        setDisplayMinMaxAndPreferredDimensions();
    }
    
    private void setDisplayMinMaxAndPreferredDimensions(){
        setMinimumSize(displayDim);
        setMaximumSize(displayDim);
        setPreferredSize(displayDim);
    }
    
    void setGraphicsRenderer(GraphicsRenderer renderer){
        this.renderer = renderer;
    }
    
// =============================================================================
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(renderer != null)
            renderer.draw(g);
    }
    
// =============================================================================
    
}
