/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vosb;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
/**
 *
 * @author Daniel123
 */
public class VOSB {
    public final static String PARENT_DIR = "/FinalRecreation/"; //uesed to store common file dir
    public final static String STARTING_PIC = "VOSB_01.jpg";
    private Map<String, List<String>> story;  //used to store stories
    private File picContent;  //use to store pic index
    
    //pre: picContent should be vaild, or it will throw FileNotFoundException
    //post: the map should be done
    public VOSB() throws FileNotFoundException {
        this.story = new HashMap<String, List<String>>();
        //BufferedInputStream input = new BufferedInputStream(this.getClass().getResourceAsStream(parentDir + "picContent.txt"));
        this.picContent = new File("D:\\Academic\\UW stuff\\ENGL 111 V\\week08\\VOSB\\src\\FinalRecreation\\picContent.txt");
        this.addInMap(new Scanner(this.picContent));
    }
    
    //pre: should enter the label behind VOSB_
    //post: return the next picture the client want
    public List<String> getNextPic(String label) throws IllegalArgumentException {
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
            //used to store the whole line
            String s = sc.nextLine();
            //used to scan through without going worring length
            Scanner scLine = new Scanner(s);
            //used to store the key
            String str = scLine.next();
            //used to store the proceding things 
            List<String> list = new ArrayList<String>();
            while(scLine.hasNext()){
                list.add(scLine.next());
            }
            //put the store plot
            story.put(str, list);
        }
        
        System.out.println(story.toString());
    }
}
