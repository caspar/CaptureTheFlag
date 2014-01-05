import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Caspar Lant

public class FlagGame {
  
    //we need some way to store things like highscores, statistics, playernames, saved games, etc. 
    //any ideas? - C
    
    // text files should do the trick. Players should input a username and password if they want to have their
    // highscores, stats, etc. saved, otherwise they will just be relocated to a temporary file that will be
    // deleted every time they quit. Sound good? -SW 

    public static void main(String[] args){
	Gui G = new Gui();
	G.setVisible(true);

    }
}
