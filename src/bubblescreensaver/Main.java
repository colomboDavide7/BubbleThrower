/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver;

import bubblescreensaver.controllers.BubbleController;
import bubblescreensaver.model.BubbleThrower;
import bubblescreensaver.model.BubbleThrowerIF;
import bubblescreensaver.model.RenderingIF;
import bubblescreensaver.throwableObjects.ResourceManager;
import bubblescreensaver.throwableObjects.ResourceManagerIF;
import bubblescreensaver.view.DisplayFrame;

/**
 *
 * @author davidecolombo
 */
public class Main {
    
    private static final int DISPLAY_WIDTH_IN_PIXEL  = 1000;
    private static final int DISPLAY_HEIGHT_IN_PIXEL = 800;
    private static final int REFRESH_TIME_IN_MILLIS  = 100;
    
    public static void main(String[] args){
        
        String objectType = args[0];
        ResourceManagerIF resManager = ResourceManager.getInstance();
        resManager.loadResource(objectType);
        
        // View
        DisplayFrame frame = DisplayFrame.createNewDisplayFrame(DISPLAY_WIDTH_IN_PIXEL, 
                                                                DISPLAY_HEIGHT_IN_PIXEL, 
                                                                REFRESH_TIME_IN_MILLIS);
        // Model
        BubbleThrowerIF thrower = BubbleThrower.createBubbleThrower();
        thrower.setResourceManager(resManager);
        
        // Controller
        BubbleController controller = BubbleController.createBubbleController();
        controller.setObjectThrower(thrower);
        
        frame.setInteractor((RenderingIF) thrower);
        frame.addMouseListener(controller);
        frame.addMouseMotionListener(controller);
        frame.startRenderingThread();
    }
    
}
