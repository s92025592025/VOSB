/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vosb;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *
 * @author Daniel123
 */
public class VOSB {
    public final static String parentDir = "/FinalRecreation/"; //uesed to store common file dir
    private Map<String, String[]> story;  //used to store stories
    private File picContent;  //use to store pic index
    
    //pre: picContent should be vaild, or it will throw FileNotFoundException
    //post: the map should be done
    public VOSB() throws FileNotFoundException {
        this.story = new HashMap<String, String[]>();
        this.picContent = new File("picContent.txt");
        this.addInMap(new Scanner(picContent));
    }
    
    //pre: should enter the label behind VOSB_
    //post: return the next picture the client want
    public String[] getNextPic(String label) throws IllegalArgumentException {
        //check whether there is a next pic
        if(!this.storyEnd(label)){
            throw new IllegalArgumentException();
        }
        return this.story.get(label);
    }
    
    //pre: enter the current and vaild scene
    //post: tell you whether is next
    public boolean storyEnd(String scene) {
        return this.story.containsKey(scene);
    }
    
    //put the content onto the map
    private void addInMap(Scanner sc) {
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            story.put(s.substring(0, 11), s.substring(12).split("\\s+"));
        }
    }
}
