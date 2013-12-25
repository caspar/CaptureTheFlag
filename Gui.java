import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Caspar Lant

public class Gui extends JFrame {
 
    Game Game = new Game();
    
    ImageIcon flag = new ImageIcon("Images/Cuba.png", "blahblahblah");
    

    private Container pane;
    private JLabel label;
    
    public Gui() {
	setTitle("Flag Game");
	setSize(1280, 800); //should be fullscreen
	setLocation(100,100);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	//pane = this.getContentPane();

	label = new JLabel(flag);
    }


}