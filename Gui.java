import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Caspar Lant

public class Gui extends JFrame {
 
    Game Game = new Game();
    
    private String imagePath = "Images/";

    private Container pane;
    private JLabel label;
    private JPanel flagpanel;

    public ArrayList<String> flagList = new ArrayList<String>();
    
    public Gui() {
	setTitle("Flag Game");
	setSize(1280, 800); //should be fullscreen
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	pane = this.getContentPane();

	readDir();
	
	label = new JLabel(createImageIcon(imagePath + "Germany.png", "Germany"));

	pane.add(label);

	this.update(this.getGraphics());
    }

    public void newImage(){
	

    }
    
    public void readDir(){
	String path = "./Images/"; 
	
	String files;
	File folder = new File(path);
	File[] listOfFiles = folder.listFiles(); 
	
	for (int i = 0; i < listOfFiles.length; i++) 
	    {
		
		if (listOfFiles[i].isFile()) 
		    {
			files = listOfFiles[i].getName();
			if (files.endsWith(".png"))
			    {
				flagList.add(files);
			    }
		    }
	    }
	System.out.println(flagList);

    }
    
   
    
    
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    
    
}