/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableObjects;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author davidecolombo
 */
public class ResourceManager implements ResourceManagerIF {
    
    // SINGLETON
    private static ResourceManager theInstance = null;
    
    private ResourceManager(){
    }
    
    public synchronized static ResourceManager getInstance(){
        if(theInstance == null)
            return new ResourceManager();
        return theInstance;
    }
    
// =============================================================================
    
    private ThrowableObject prototype  = null;
    private ThrowableFactoryIF factory = null;
    
    @Override
    public ThrowableObject getClone() {
        return this.prototype.clone();
    }
    
    @Override
    public void loadResource(String objectType) {
        File[] allFiles   = getFilesFromWorkingDir();
        File searchedFile = searchFileNameThatContainsType(allFiles, objectType);
            
        if(searchedFile != null)
            createThrowablePrototype(searchedFile.getName(), 
                                     objectType);
    }
    
    private File[] getFilesFromWorkingDir(){
        try{
            String dirName  = System.getProperty("user.dir");
            File workingDir = new File(dirName);
            File[] allFiles = workingDir.listFiles();
            return allFiles;
        }catch(Exception ex){
            System.err.println("Error reading directory contents");
            System.exit(0);
        }
        return null;
    }
    
    private File searchFileNameThatContainsType(File[] allFiles, String objectType){
        for(int i = 0; i < allFiles.length; i++)
                if(!allFiles[i].isDirectory())
                    if(allFiles[i].getName().contains(objectType))
                        return allFiles[i];
        return null;
    }
    
    private void createThrowablePrototype(String filename, String objectType){
        try{
            Image image = readImageFromFile(filename);
            this.factory = new ThrowableFactory();
            this.prototype = factory.getThrowableObject(objectType);
            this.prototype.setImage(image);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.err.println("Error reading the file: " + objectType);
            System.exit(0);
        }
    }
    
    private Image readImageFromFile(String filename){
        try {
            Image image = ImageIO.read(new File(filename));
            return image;
        } catch (IOException ex) {
            System.out.println("Error loading the image: " + filename);
            System.exit(0);
        }
        return null;
    }
    
// =============================================================================
    
}
