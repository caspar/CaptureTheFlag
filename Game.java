import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//For Gui implementation:
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {
    //This class is gonna deal mostly with analytics


    ///ARRAY LISTS////////
    public ArrayList<String> flagList = new ArrayList<String>(); 
    public ArrayList<String> usedFlags = new ArrayList<String>(); //maybe we don't need this? (maybe repeats are ok)
    public ArrayList<String> correct = new ArrayList<String>(); //for analytic purposes
    //we need a way to associate each flag with its predominant color, national language, etc. (also for analytics)
    //maybe multi-dimentional arrayLists!!!
    ///////////////////////

    //public String[] flagList = String[244];

    public Game(){
	//setup method
    }

    // public void longestCountry(){
    // 	int longest;
    // 	for (int i = 0; i < flagList.length; i ++){
    // 	    if ((flagList.get(i)).length() > longest)
    // 		longest = flagList.get(i);
    // 	}
    // 	System.out.println(longest);

    // }

 

}
