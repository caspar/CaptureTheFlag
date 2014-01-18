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
    
    public int score = 0;

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

    // We could have the different gameplaymodes down here::::

    public void  timedGame(String mode, int time){
	while (time > 0){
	    time --;
	}
    }

 

}
