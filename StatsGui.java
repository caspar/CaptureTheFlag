import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
 
public class StatsGui implements ActionListener {	
    private FlagGame fg;
    private static String[] names;
    private static Hashtable<Color, String> colorDict;
    private static Hashtable<String, String> countryColor;
    private static Hashtable<String, Integer> colorNums;
    private static Hashtable<String, String> countryContinent;
    private static Hashtable<String, Integer> continentNums;

    private boolean isRacist = true; // guilty until proven innocent
    private static ArrayList<String> correct = new ArrayList<String>();
    private static ArrayList<String> incorrect = new ArrayList<String>();
    private double ameurocaPercentage;
    private double oceafricasiaPercentage;

    private JFrame frame;
    private JButton quit;

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == quit) {
	    fg.createMenuGui();
	    frame.dispose();
	}
    }

    public StatsGui(FlagGame fg) {
	this.fg = fg;
	correct = fg.getCorrect();
	incorrect = fg.getIncorrect();

	System.out.println("Loading...");
	getNames();
	assembleColorDict();
	assignColors();
	getColors();
	assignContinents();
	getContinents();

	frame = new JFrame("Flag Game Statistics");
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.getContentPane().setLayout(new StatLayout());

	JLabel finished = new JLabel("Statistics!");
	JLabel scoreLabel = new JLabel("Score: " + correct.size());
	JLabel bestLabel = new JLabel("Longest run: " + fg.getLongestStreak());
	JLabel percentageLabel = new JLabel("Overall percentage: " + (double) correct.size() / incorrect.size());
	JLabel correctLabel = new JLabel("<html> Correct: " + arrayListString(correct) + "</html>");
	JLabel incorrectLabel = new JLabel("<html> Incorrect: " + arrayListString(incorrect) + "</html>");
	JLabel isRacist = new JLabel("Racist: " + (getFavoriteContinent() == "Europe" || getFavoriteContinent() == "Americas"));
	JLabel favoriteColor = new JLabel("Favorite Color: " + getFavoriteColor());
	JLabel favoriteContinent = new JLabel("Favorite Continent: " + getFavoriteContinent());

	quit = new JButton("Back");
	quit.addActionListener(this);

	finished.setForeground(Color.white);
	scoreLabel.setForeground(Color.white);
	correctLabel.setForeground(Color.white);
	incorrectLabel.setForeground(Color.white);
	bestLabel.setForeground(Color.white);
	percentageLabel.setForeground(Color.white);
	isRacist.setForeground(Color.white);
	favoriteColor.setForeground(Color.white);
	favoriteContinent.setForeground(Color.white);

	frame.getContentPane().add(finished);
	frame.getContentPane().add(scoreLabel);
	frame.getContentPane().add(correctLabel);
	frame.getContentPane().add(incorrectLabel);
	frame.getContentPane().add(bestLabel);
	frame.getContentPane().add(percentageLabel);
	frame.getContentPane().add(isRacist);
	frame.getContentPane().add(favoriteColor);
	frame.getContentPane().add(favoriteContinent);
	frame.getContentPane().add(quit);

	frame.setSize(1280, 800);
	frame.setBackground(new Color(30,30,30));
	frame.setMinimumSize(new Dimension(800, 600));
	frame.setVisible(true);
    }

    private void assignContinents() { // Spencer with some help from Stack Overflow 
	countryContinent = new Hashtable<String, String>();

        File folder = new File("Continents/");
        File[] continents = folder.listFiles();
        String[] contNames = new String[continents.length-1]; // to account for README.md
        int counter = 0;
        
        for (int i = 0; i < continents.length-1; i++, counter++) {
	    if (continents[i].getName().endsWith(".md"))
		counter++;
	    contNames[i] = continents[counter].toString(); // {America.txt, Asia.txt, etc.}
        }
        
        for (int i = 0; i < contNames.length; i++) {
	    try {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(contNames[i].toString())));
		String str;

		while ((str = in.readLine()) != null)
		    countryContinent.put(str, contNames[i].replace("Continents/","").replace(".txt","").replace("_"," "));
	    } catch(Exception e) {System.out.println(e);}
        }
    }

    public String getFavoriteColor() {
	String index = "black";
	for (String s : colorNums.keySet()) {
	    if (colorNums.get(s) > colorNums.get(index))
		index = s;
	}

	return index;
    }

    public String getFavoriteContinent() {
	String index = "Africa";
	for (String s : continentNums.keySet()) {
	    if (continentNums.get(s) > continentNums.get(index))
		index = s;
	}

	return index;
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
	countryColor = new Hashtable<String, String>(names.length);

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
		
		countryColor.put(names[i].replace("Images/","").replace(".png","").replace("_"," "), key);
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

    private static void getColors() {
	colorNums = new Hashtable<String, Integer>(11);
        for (String s : colorDict.values())
	  colorNums.put(s, 0);;

	for (String s : correct) {
	    String key = countryColor.get(s);
	    colorNums.put(key, colorNums.get(key) + 1);
	}

	for (String s : incorrect) {
	    String key = countryColor.get(s);
	    colorNums.put(key, colorNums.get(key) - 1);
	}
    }

    private static void getContinents() {
	continentNums = new Hashtable<String, Integer>(5);
	continentNums.put("Africa", 0);
	continentNums.put("Americas", 0);
	continentNums.put("Asia", 0);
	continentNums.put("Europe", 0);
	continentNums.put("Oceania", 0);


	for (String s : correct) {
	    String key = countryContinent.get(s);
	    continentNums.put(key, continentNums.get(key) + 1);
	}

	for (String s : incorrect) {
	    String key = countryContinent.get(s);
	    continentNums.put(key, continentNums.get(key) - 1);
	}
    }

    public String arrayListString(ArrayList<String> L) {
	String ans = "";
	
	if (L.size() > 0) {
	    for (String s : L)
		ans += s + ", ";
	    
	    return ans.substring(0, ans.length()-2);
	}
	else
	    return ans;
    }
}
