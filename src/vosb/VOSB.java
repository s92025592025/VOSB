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
    public final static String PARENT_DIR = "FinalRecreation/"; //uesed to store common file dir
    public final static String STARTING_PIC = "VOSB_01.jpg";
    public static final String HTML_HEAD = "<html><p>";
    public static final String HTML_END = "</html></p>";
    private Map<String, List<String>> story;  //used to store stories
    private Map<String, String> discription; //used to store the displayed discription
    
    //pre: picContent should be vaild, or it will throw FileNotFoundException
    //post: the map should be done
    public VOSB() throws FileNotFoundException {
        //starting to put in all the scene route
        this.story = new HashMap<String, List<String>>();
        //this.picAddInMap(new Scanner(this.getClass().getResourceAsStream(PARENT_DIR + "/PicContent.txt" )));
        this.picAddInMap(new Scanner(new File("PicContent.txt" )));
        //starting to put in the discription of each picture
        this.discription = new HashMap<String, String>();
        this.disAddInMap(new Scanner(new File("discriptionContent.txt")));
    }
    
    //pre: should enter the while file name(included property)
    //post: return the next picture the client want
    public List<String> getNextPic(String label) throws IllegalArgumentException {
        //check whether there is a next pic
        if(this.storyEnd(label)){
            throw new IllegalArgumentException();
        }
        return this.story.get(label);
    }
    
    //pre: pre: should enter the while file name(included property)
    //post: return a String contains the discription of the picture
    public String getDiscrip(String label){
        return this.HTML_HEAD + this.discription.get(label) + this.HTML_END;
    }
    
    //pre: enter the current and vaild scene
    //post: tell you whether is next
    public boolean storyEnd(String scene) {
        return !this.story.containsKey(scene);
    }
    
    //put the content onto the map
    private void picAddInMap(Scanner sc) {
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
        
       // System.out.println(story.toString());
    }
    
    //put in the discreption of each pic
    private void disAddInMap(Scanner sc) {
        while(sc.hasNextLine()){
            String s[] = sc.nextLine().split("::=");
            this.discription.put(s[0].trim(), s[1].trim());
        }
        
        //System.out.println(this.discription);
    }
}
