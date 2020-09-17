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
        this.factory = new ThrowableFactory();
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
        try{
            File[] allFiles   = getFilesFromWorkingDir();
            File searchedFile = searchFileNameThatContainsType(allFiles, objectType);
            createThrowablePrototype(searchedFile.getName(), objectType);
        }catch(LoadResourceException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    private File[] getFilesFromWorkingDir(){
        String dirName  = System.getProperty("user.dir");
        File workingDir = new File(dirName);
        File[] allFiles = workingDir.listFiles();
        return allFiles;
    }
    
    private File searchFileNameThatContainsType(File[] allFiles, String objectType){
        for(int i = 0; i < allFiles.length; i++)
                if(!allFiles[i].isDirectory())
                    if(allFiles[i].getName().contains(objectType))
                        return allFiles[i];
        throw new LoadResourceException("There's no file that contains \"" + objectType + "\" in its name.");
    }
    
    private void createThrowablePrototype(String filename, String objectType){
        Image image    = readImageFromFile(filename);
        this.prototype = factory.getThrowableObject(objectType);
        this.prototype.setImage(image);
    }
    
    private Image readImageFromFile(String filename){
        try {
            Image image = ImageIO.read(new File(filename));
            return image;
        } catch (IOException ex) {
           throw new LoadResourceException("Error reading the Image file: " + filename
                                         + "\nIOException has occured with this message:"
                                         + "\n" + ex.getMessage());
        }
    }
    
// =============================================================================
    
}
