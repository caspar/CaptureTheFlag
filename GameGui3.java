import java.io.*;            // __
import java.util.Properties; //   |
//import java.util.Timer;      //   |
import java.util.Scanner;    //   |-- Had to do this due to Class ambiguity problem.
import java.util.Hashtable;  //   |
import java.util.Date;       //   |
import java.util.Random;     // __|
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.concurrent.TimeUnit;

public class GameGui implements ActionListener {
    private JFrame frame;
    private Container content;
    private JLabel flag;
    private JLabel scoreLabel;
    private JButton[] c;
    private JButton quit;
    private Random r;
    private String[] names;
    private Dimension flagD;
    public String currentFlag;
    public int score = 0;
    public int x = 0; //Determines which button is correct

    
    private JLabel labelImage(String path) {
 	BufferedImage image;
	try {
	    image = ImageIO.read(new File(path));
	    flagD = new Dimension(image.getWidth(), image.getHeight());
	    return new JLabel(new ImageIcon(image));
	}
	catch (IOException e) {
	    System.out.println("ERROR");
	    return null;
	}
    }
    
    public void actionPerformed(ActionEvent e) { //Caspar
	if (e.getSource() == quit){
	    frame.getContentPane().getComponent(0).setBounds(0,0,0,0);
	    frame.getContentPane().removeAll();
	    init();
	}
	else {
	    int clicked = 0;
	    if (e.getSource() == c[1])
		clicked = 1;
	    if (e.getSource() == c[2])
		clicked = 2;
	    if (e.getSource() == c[3])
		clicked = 3;
	    c[x].setBackground(Color.GREEN);
	    c[x].setOpaque(true);
	    c[x].setBorderPainted(false);
	    if (clicked == x){
		updateBasicStats(true);
		System.out.println("Correct!");
	    }
	    else {
	    	updateBasicStats(false);
	    	updateSpecificStats(false, )
		System.out.println("Incorrect. The correct answer was " + currentFlag);
		c[clicked].setBackground(Color.RED);
		c[clicked].setOpaque(true);
		c[clicked].setBorderPainted(false);
	    }
	    delayedReset();
      	}
    }
    
    
  
    
    public GameGui() {
	frame = new JFrame("Flag Game");
		
	init();
    }
    
    private void init() {
	r = new Random();
	content = new Container();
	getNames();
	assignButtons();
	flag.setSize(flagD);

	frame.setContentPane(content);
	frame.getContentPane().setLayout(new GameLayout());
	frame.getContentPane().add(flag);

        for (int i=0; i<4; i++) {
	    c[i].addActionListener(this);
	    frame.getContentPane().add(c[i]);
	}

	//quit = new JButton("Quit");
	//quit.addActionListener(this);
	//frame.getContentPane().add(quit);
	
	scoreLabel = new JLabel("Score: " + score);
	scoreLabel.setForeground(Color.white);
	frame.getContentPane().add(scoreLabel);
	
	runLabel = new JLabel("Current run: " + currentStreak);
        runLabel.setForeground(Color.white);
        frame.getContentPane().add(runLabel);
        
        bestLabel = new JLabel("Best run: " + longestStreak);
        bestLabel.setForeground(Color.white);
        frame.getContentPane().add(bestLabel);

	frame.setSize(1280, 800);
	frame.setBackground(new Color(30,30,30));
	frame.setMinimumSize(new Dimension(800, 600));
	frame.setVisible(true);
	frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);




    }

    private void getNames() { //Caspar
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
    
    // the following gets the continent of the current country
    
    private void getContinent() { // Spencer
        
        File folder = new File("ContinentsAndOceania/");
        File[] continents = folder.listFiles();
        contNames = new String[continents.length-1]; // to account for README.md
        int counter = 0;
        
        for (int i = 0; i < continents.length; i++; counter++) {
        	if (continents[i].getName().endsWith(".md")
        		counter++;
        	contNames[i] = continents[counter]; // {America.txt, Asia.txt, etc.}
        }
        
        for (int i = 0; i < contNames.length; i++) {
        	BufferedReader in = new BufferedReader(new FileReader("ContinentsAndOceania/" + contNames[i].toString());
        	String str;
        	
        	List<string> list = new ArrayList<String>();

	        while((str = in.readLine()) != null){
	            list.add(str);
        	}

	        String[] stringArr = list.toArray(new String[0]);
	 }

    private void assignButtons() { //Spencer's Code
	String[] choices = new String[4];
	boolean valid;
	c = new JButton[4];

	x = r.nextInt(4); // the index of the button to be given the correct answer

        for (int i = 0; i < 4; i++) {
	    valid = false;

	    while (!valid) {
		choices[i] = names[r.nextInt(names.length)]; //assigns random country to the buttons
		valid = true;
		for (int j=0; j < i; j++) { //Makes sure no duplicate choices
		    if (choices[i] == choices[j])
			valid = false;
		}
	    }

	    c[i] = new JButton(readName(choices[i].substring(7)));
	    if (choices[i].length() > 35) //Accounts for really long country names
		c[i].setFont(new Font("Serif", Font.PLAIN, 10));
	    else if (choices[i].length() > 30)
		c[i].setFont(new Font("Serif", Font.PLAIN, 12));
	    else if (choices[i].length() > 15)
		c[i].setFont(new Font("Serif", Font.PLAIN, 15)); //This one is not serified for some reason..
	}

	flag = labelImage(choices[x]);
	currentFlag = readName(choices[x]); //not redundant, this one's a String. 	
    }
    
    private String readName (String in) { //Caspar
	return in.replace("Images/","").replace(".png","").replace("_"," ");
    }

    public static void pause(int seconds){
	Date start = new Date();
	Date end = new Date();
	while(end.getTime() - start.getTime() < seconds * 1000){
	    end = new Date();
	}
    }
    
    public void holdUp(){ //Caspar
	try{
	    Thread.sleep(200);
	    //TimeUnit.SECONDS.sleep(2);
	}
	catch (Exception e){
	    System.out.println(e);
	}
    }
    
    public void delayedReset() {
	new Thread() {
	    public void run() {
		try{
		    TimeUnit.MILLISECONDS.sleep(500); //Will need a try/catch
		    SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
				frame.getContentPane().getComponent(0).setBounds(0,0,0,0);
				frame.getContentPane().removeAll();
				init();
			    }
			});
		}
		catch(Exception except){
		}
	    }
	}.start();
    }
    
}
