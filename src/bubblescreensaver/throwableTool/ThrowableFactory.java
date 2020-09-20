/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblescreensaver.throwableTool;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author davidecolombo
 */
public class ThrowableFactory implements ThrowableFactoryIF {
        
    private String objectType;
    private ThrowableObject prototype;
    
    public static ThrowableFactoryIF createThrowableFactory(){
        return new ThrowableFactory();
    }
    
    private ThrowableFactory(){
    }
    
    @Override
    public ThrowableObject getPrototype(String objectType) {
        this.objectType = objectType;
        this.loadResource();
        return this.prototype;
    }
    
    private void loadResource() {
        try{
            File[] allFiles   = getFilesFromWorkingDir();
            File searchedFile = searchFileNameThatContainsType(allFiles);
            createThrowablePrototype(searchedFile.getName());
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
    
    private File searchFileNameThatContainsType(File[] allFiles){
        for(int i = 0; i < allFiles.length; i++)
                if(!allFiles[i].isDirectory())
                    if(allFiles[i].getName().contains(this.objectType))
                        return allFiles[i];
        throw new LoadResourceException("There's no file that contains \"" 
                                       + this.objectType 
                                       + "\" in its name.");
    }
    
    private void createThrowablePrototype(String filename){
        Image image = readImageFromFile(filename);
        getThrowableObjectByType();
        this.prototype.setImage(image);
    }
    
    private void getThrowableObjectByType(){
        switch(this.objectType){
            case "bubble":
                this.prototype = Bubble.createNewBubble();
                break;
            default:
                throw new LoadResourceException("There's no prototype associated "
                                              + "to: \"" + this.objectType + "\"");
        }
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
    
}
