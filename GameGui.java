import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;

public class GameGui implements ActionListener {
    private JFrame frame;
    private JLabel flag;
    private JButton[] c;
    private JButton quit;
    private Random r;
    private String[] names;
    private Dimension flagD;

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

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == c[0]) {
	}
	else if (e.getSource() == c[1]) {
	}
	else if (e.getSource() == c[2]) {
	}
	else if (e.getSource() == c[3]) {
	}
	else if (e.getSource() == quit) {
	    frame.getContentPane().removeAll();
	    init();
	}
    }

    public GameGui() {
	frame = new JFrame("Flag Game");
	init();
    }

    private void init() {
	r = new Random();
	getNames();
	assignButtons();
	flag.setSize(flagD);

	frame.getContentPane().setLayout(new GameLayout());
	frame.getContentPane().add(flag);

        for (int i=0; i<4; i++) {
	    c[i].addActionListener(this);
	    frame.getContentPane().add(c[i]);
	}

	quit = new JButton("Quit");
	quit.addActionListener(this);
	frame.getContentPane().add(quit);

	frame.setSize(1280, 800);
	frame.setBackground(new Color(30,30,30));
	frame.setMinimumSize(new Dimension(800, 600));
	frame.setVisible(true);
    }

    private void getNames() { //Partially Caspar's	
	File folder = new File("Images/");
	File[] images = folder.listFiles(); 
	names = new String[images.length];
	
	for (int i = 0; i < images.length; i++) 
	    {
		if (images[i].getName().endsWith(".png"))
		    names[i] = images[i].toString();
	    }
    }

    private void assignButtons() { //Partially Spencer's
	String[] choices = new String[4];
	boolean valid;
	c = new JButton[4];

	int x = r.nextInt(4); // the index of the button to be given the correct answer

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

	    c[i] = new JButton(choices[i].substring(7).replace(".png","").replace("_"," "));
	    if (choices[i].length() > 35) //Accounts for really long country names
		c[i].setFont(new Font("Serif", Font.PLAIN, 10));
	    else if (choices[i].length() > 30)
		c[i].setFont(new Font("Serif", Font.PLAIN, 12));
	    else if (choices[i].length() > 15)
		c[i].setFont(new Font("Serif", Font.PLAIN, 15));
			     
        }

	flag = labelImage(choices[x]);
    }
}
