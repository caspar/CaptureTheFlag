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
    private int score = 0;
    private int type;
    private int modifier;
    private Timer timer;
    private ArrayList<String> correct = new ArrayList<String>();
    private ArrayList<String> incorrect = new ArrayList<String>();
    private int correctChoice = 0; //Determines which button is correct
    private int currentStreak;
    private int longestStreak;

    private JLabel labelImage(String path) {
 	BufferedImage image;
	try {
	    image = ImageIO.read(new File(path));
	    flagD = new Dimension(image.getWidth(), image.getHeight());
	    return new JLabel(new ImageIcon(image));
	}
	catch (IOException e) {
	    System.out.println("Oak: " + "\"This isn't the time to use that!\"");
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
		if (type == 0)
		    modifier--;
		c[correctChoice].setBackground(Color.GREEN); //Correct answer turns green either way
		c[correctChoice].setOpaque(true);
		c[correctChoice].setBorderPainted(false);

		if (e.getSource() == c[correctChoice]) {
                     System.out.println("Correct!");
                     correct.add(c[correctChoice].getText());
		     currentStreak++;
		}
		else {
		    JButton clicked = (JButton) e.getSource();
		    System.out.println("Incorrect. The correct answer was " + c[correctChoice].getText());
		    incorrect.add(c[correctChoice].getText());
		    clicked.setBackground(Color.RED);
		    clicked.setOpaque(true);
		    clicked.setBorderPainted(false);
		    if (currentStreak > longestStreak)
			longestStreak = currentStreak;
		    currentStreak = 0;
		}

		if (modifier == 0 && type == 0) {
		    if (currentStreak > longestStreak)
			longestStreak = currentStreak;
		    finish();
		}
		else
		    delayedReset();
	    }
	}
    }
    
    public GameGui(FlagGame fg, int type, int mod) {
	this.fg = fg;
	this.type = type;
        this.modifier = mod;

	if (type == 1) {
	    ActionListener timeListener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			if (modifier == 0) {
			    timer.stop();
			    if (currentStreak > longestStreak)
				longestStreak = currentStreak;
			    finish();
			}
			else {
			    modifier--;
			    frame.getContentPane().remove(6);
			    modifierLabel = new JLabel("Time Left: " + modifier);
			    modifierLabel.setForeground(Color.white);
			    frame.getContentPane().add(modifierLabel, 6);
			    frame.setVisible(true);
			}
		    }
		};
	    timer = new Timer(1000, timeListener);
	    timer.start();
	}
	longestStreak = 0;
	currentStreak = 0;
	frame = new JFrame("Flag Game");
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
	
	if (type == 0)
	    modifierLabel = new JLabel("Flags Left: " + modifier);
	else
	    modifierLabel = new JLabel("Time Left: " + modifier);
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
	JLabel scoreLabel = new JLabel("Score: " + score);
	JLabel bestLabel = new JLabel("Longest run: " + longestStreak);
	JLabel percentageLabel = new JLabel("Overall percentage: " + (double) correct.size() / incorrect.size());
	JLabel correctLabel = new JLabel("<html> Correct: " + arrayListString(correct) + "</html>");
	JLabel incorrectLabel = new JLabel("<html> Incorrect: " + arrayListString(incorrect) + "</html>");

	quit = new JButton("Continue");
	quit.addActionListener(this);

	finished.setForeground(Color.white);
	scoreLabel.setForeground(Color.white);
	correctLabel.setForeground(Color.white);
	incorrectLabel.setForeground(Color.white);
	bestLabel.setForeground(Color.white);
	percentageLabel.setForeground(Color.white);

	frame.getContentPane().add(finished);
	frame.getContentPane().add(scoreLabel);
	frame.getContentPane().add(correctLabel);
	frame.getContentPane().add(incorrectLabel);
	frame.getContentPane().add(bestLabel);
	frame.getContentPane().add(percentageLabel);
	frame.getContentPane().add(quit);
	frame.setSize(1280, 800);
	frame.setBackground(new Color(30,30,30));
	frame.setMinimumSize(new Dimension(800, 600));
	frame.setVisible(true);

	fg.addCorrect(correct);
	fg.addIncorrect(incorrect);
	fg.setLongestStreak(longestStreak);
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
	
	if (L.size() > 0) {
	    for (String s : L)
		ans += s + ", ";
	    
	    return ans.substring(0, ans.length()-2);
	}
	else
	    return ans;
    }
}
