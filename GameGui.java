import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.concurrent.TimeUnit;

public class GameGui implements ActionListener {
    private FlagGame fg;
    private JFrame frame;
    private Container content;
    private JLabel flag;
    private JLabel scoreLabel;
    private JLabel modifierLabel;
    private JButton[] c;
    private JButton quit;
    private Random r;
    private String[] names;
    private Dimension flagD;
    private boolean stop;
    private int score;
    private int type;
    private int modifier;
    private ArrayList<String> correct = new ArrayList<String>();
    private ArrayList<String> incorrect = new ArrayList<String>();
    private int correctChoice = 0; //Determines which button is correct

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

    public void actionPerformed(ActionEvent e) { //All of us
	if (!stop) { //Prevents hitting buttons while going to next flag slide
	    if (e.getSource() == quit) {
		fg.createMenuGui();
		frame.dispose();
	    }
	    else {
		modifier--;
		c[correctChoice].setBackground(Color.GREEN); //Correct answer turns green either way
		c[correctChoice].setOpaque(true);
		c[correctChoice].setBorderPainted(false);

		if (e.getSource() == c[correctChoice]) {
		     updateBasicStats(true);
		     updateSpecificStats(true, getContinent());
                     System.out.println("Correct!");
                     correct.add(c[correctChoice].getText());
                     c[clicked].setBackground(Color.RED);
                     c[clicked].setOpaque(true);
                     c[clicked].setBorderPainted(false);
		     System.out.println("Correct");
//		     score++;
		}
		else {
		    updateBasicStats(false);
                    updateSpecificStats(false, getContinent());
		    JButton clicked = (JButton) e.getSource();
		    System.out.println("Incorrect. The correct answer was " + c[correctChoice].getText());
		    incorrect.add(c[correctChoice].getText());
		    clicked.setBackground(Color.RED);
		    clicked.setOpaque(true);
		    clicked.setBorderPainted(false);
		}

		if (modifier == 0)
		    finish();
		else
		    delayedReset();
	    }
	}
    }
        
    public GameGui(FlagGame fg, int type, int modifier) {
	this.fg = fg;
	this.type = type;
        this.modifier = modifier;
	frame = new JFrame("Flag Game");
	score = 0;
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

	quit = new JButton("Quit");
	quit.addActionListener(this);
	frame.getContentPane().add(quit);
	
	modifierLabel = new JLabel("Flags Left: " + modifier);
	modifierLabel.setForeground(Color.white);
	frame.getContentPane().add(modifierLabel);

	scoreLabel = new JLabel("Score: " + score);
	scoreLabel.setForeground(Color.white);
	frame.getContentPane().add(scoreLabel);
	
	frame.setSize(1280, 800);
	frame.setBackground(new Color(30,30,30));
	frame.setMinimumSize(new Dimension(800, 600));
	frame.setVisible(true);
    }

    public void finish() {
	for (int i=0; i<frame.getContentPane().getComponentCount(); i++)
	    frame.getContentPane().getComponent(i).setBounds(0,0,0,0);

	frame.getContentPane().removeAll();
	frame.getContentPane().setLayout(new EndLayout());

	JLabel finished = new JLabel("Game Finished!");
	scoreLabel = new JLabel("Score: " + score);
	JLabel correctLabel = new JLabel("<html> Correct: " + arrayListString(correct) + "</html>");
	JLabel incorrectLabel = new JLabel("<html> Incorrect: " + arrayListString(incorrect) + "</html>");

	quit = new JButton("Continue");
	quit.addActionListener(this);

	finished.setForeground(Color.white);
	scoreLabel.setForeground(Color.white);
	correctLabel.setForeground(Color.white);
	incorrectLabel.setForeground(Color.white);

	frame.getContentPane().add(finished);
	frame.getContentPane().add(scoreLabel);
	frame.getContentPane().add(correctLabel);
	frame.getContentPane().add(incorrectLabel);
	frame.getContentPane().add(quit);

	fg.addCorrect(correct);
	fg.addIncorrect(incorrect);
    }

    private void getNames() { //Caspar
	File folder = new File("Images/");
	File[] images = folder.listFiles(); 
	names = new String[images.length-2]; //Ignoring title.png and README.md
	int counter = 0; //Accounts for when skipping over unwanted files
	
	for (int i = 0; i < names.length; i++, counter++) 
	    {
		if (!images[counter].getName().endsWith(".png") || images[counter].getName().contains("Title.png"))
		    counter++;

		names[i] = images[counter].toString();
	    }
    }
    
    private void getContinent() { // Spencer with some help from StackOverflow
        
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
    }
         
    private void assignButtons() { //Spencer's Code
	String[] choices = new String[4];
	boolean valid;
	c = new JButton[4];

	correctChoice = r.nextInt(4); // the index of the button to be given the correct answer

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

	flag = labelImage(choices[correctChoice]);	
    }
    
    private String readName (String in) { //Caspar
	return in.replace("Images/","").replace(".png","").replace("_"," ");
    }

    public void delayedReset() {
	stop = true;
	new Thread() {
	    public void run() {
		try {
		    TimeUnit.MILLISECONDS.sleep(500); //Will need a try/catch
		    SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
				frame.getContentPane().getComponent(0).setBounds(0,0,0,0);
				frame.getContentPane().removeAll();
				init();
				stop = false;
			    }
			});
		}
		catch(Exception except) {
		}
	    }
	}.start();
    }

    public String arrayListString(ArrayList<String> L) {
	String ans = "";
	for (String s : L)
	    ans += s + ", ";
	
	return ans.substring(0, ans.length()-2);
    }
}
