import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Caspar Lant

public class Gui extends JFrame {
 
    Game Game = new Game();
    Random Rand = new Random();
    
    private String imagePath = "Images/";

    private Container pane;
    private JLabel label;
    private JPanel flagpanel;

   
    public Gui() {
	setTitle("Flag Game");
	setSize(1280, 800); //should be fullscreen (1280, 800)
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	pane = this.getContentPane();

	readDir();
	//	pane.add(newImage());
	newImage();
	this.update(this.getGraphics());

    }

    public JLabel newImage(){
	String newFlag = (Game.flagList).get(Rand.nextInt(223));
	//System.out.println(newFlag);
	//getName(newFlag);
	
       	label = new JLabel(createImageIcon(imagePath + newFlag, "Germany"));
	pane.add(label);
	this.update(this.getGraphics());

	return label;
    }
    
    public String getName(String input){
	String output = "";
	for (int i = 0; i < input.length() - 4; i ++){
	    if ((input.charAt(i)) == '_')
		output += " ";
	    else
		output += input.charAt(i);
	}
	System.out.println(output);
	return output;
    }
    
    public void readDir(){ //Cite source (not mine)
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
				Game.flagList.add(files);
			    }
		    }
	    }
	//System.out.println(Game.flagList);

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