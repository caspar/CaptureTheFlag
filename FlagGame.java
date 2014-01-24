import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.concurrent.TimeUnit;


public class FlagGame {
    private static String[] names;
    private static Hashtable<Color, String> colorDict;
    private static Hashtable<String, String> countryColors;
  
    //we need some way to store things like highscores, statistics, playernames, saved games, etc. 
    //any ideas? - C
    
    // text files should do the trick. Players should input a username and password if they want to have their
    // highscores, stats, etc. saved, otherwise they will just be relocated to a temporary file that will be
    // deleted every time they quit. Sound good? -SW 
    //	Yes. Or we could just have a drop-down member with past players (probably no easier to write)

    public static void main(String[] args){
	System.out.println("Loading...");
	getNames();
	assembleColorDict();
	assignColors();
	GameGui GG = new GameGui();
    }

    private static void getNames() { //Mainly Caspar's Code
	File folder = new File("Images/");
	File[] images = folder.listFiles(); 
	names = new String[images.length-2]; //Ignoring title.png and README.md
	int counter = 0; //Accounts for when skipping over unwanted files
	
	for (int i = 0; i < names.length; i++, counter++) 
	    {
		if (!images[i].getName().endsWith(".png") || images[i].getName() == "Title.png")
		    counter++;

		names[i] = images[counter].toString();
	    };
    }

    private static void assignColors() {
	countryColors = new Hashtable<String, String>(names.length);

	BufferedImage image;
	Color tempColor;
	int[] intColors;
	int[][] rgb;
	String key;

	Hashtable<String, Integer> colors = new Hashtable<String, Integer>(10);

	for (int i=0; i<names.length; i++) {
	    try {
		for (String s : colorDict.values())
		    colors.put(s, 0);
		//System.out.println(colors);
	    
		//System.out.println(names[i]);
		image = ImageIO.read(new File(names[i]));
		intColors = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		rgb = new int[intColors.length][3];
		for (int j=0; j<intColors.length; j++) {
		    tempColor = new Color(intColors[j]);
		    rgb[j][0] = tempColor.getRed();
		    rgb[j][1] = tempColor.getGreen();
		    rgb[j][2] = tempColor.getBlue();
		    //System.out.println(Arrays.toString(rgb[j]));
		    key = findColor(rgb[j]);
		    //System.out.println(key);
		    colors.put(key, colors.get(key)+1);
		}
	        
		key = "black";
		for (String s : colors.keySet()) {
		    if (colors.get(s) > colors.get(key))
			key = s;
		}
		//System.out.println(colors);
		//System.out.println(key);
		
		countryColors.put(names[i], key);
	    }
	    catch (IOException e) {
		System.out.println("ERROR");
	    }
	}	    
    }

    private static String findColor(int[] rgb) {
	int[] rgbFinal = {0, 0, 0};
	int max = Math.max(rgb[0], Math.max(rgb[1], rgb[2]));

	if (max > 50) { //Creates black
	    for (int i=0; i<rgb.length; i++) {
		if (rgb[i] == max || rgb[i] > max - 20) //"Error" of 20
		    rgbFinal[i] = 255;
		else if(rgb[i] > max - 20 && rgb[i] < max + 20)
		    rgbFinal[i] = 128;
		else
		    rgbFinal[i] = 0;
	    }
	}

	if (rgb[0] == rgb[1] && rgb[0] == rgb[2] && max > 50 && max < 200) { //Creates grey
	    rgb[0] = 128;
	    rgb[1] = 128;
	    rgb[2] = 128;
	}
	//System.out.println(new Color(rgbFinal[0], rgbFinal[1], rgbFinal[2]));
	return colorDict.get(new Color(rgbFinal[0], rgbFinal[1], rgbFinal[2]));
    }

    private static void assembleColorDict() {
	colorDict = new Hashtable<Color, String>(21); //Colors with no 255 but some 128's are impossible to get from findColor
	colorDict.put(new Color(0,0,0), "black");
	colorDict.put(new Color(255,0,0), "red");
	colorDict.put(new Color(0,255,0), "green");
	colorDict.put(new Color(0,0,255), "blue");
	colorDict.put(new Color(255,255,0), "yellow");
	colorDict.put(new Color(255,0,255), "pink");
	colorDict.put(new Color(0,255,255), "cyan");
	colorDict.put(new Color(255,128,0), "orange");
	colorDict.put(new Color(255,0,128), "pink");
	colorDict.put(new Color(128,255,0), "green");
	colorDict.put(new Color(0,255,128), "green");
	colorDict.put(new Color(128,0,255), "purple");
	colorDict.put(new Color(0,128,255), "blue");
	colorDict.put(new Color(255,128,128), "pink"); //Not really sure...
	colorDict.put(new Color(128,255,128), "green");
	colorDict.put(new Color(128,128,255), "purple");
	colorDict.put(new Color(255,255,128), "yellow");
	colorDict.put(new Color(255,128,255), "pink");
	colorDict.put(new Color(128,255,255), "cyan");
	colorDict.put(new Color(255,255,255), "white");
	colorDict.put(new Color(128,128,128), "grey");
    }
}
