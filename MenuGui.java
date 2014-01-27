import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;

public class MenuGui implements ActionListener {
    private FlagGame fg;
    private JFrame frame;
    private JLabel background;
    private JLabel title;
    private JLabel desc;
    private JButton play;
    private JButton stats;
    private JButton quit;
    private JButton arcade;
    private JButton timed;
    private JTextField mod;
    private boolean played;
    private int next;

    private JLabel labelImage(String path) {
 	BufferedImage image;
	try {
	    image = ImageIO.read(new File(path));
	    return new JLabel(new ImageIcon(image));
	}
	catch (IOException e) {
	    System.out.println("ERROR");
	    return null;
	}
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == play) {
	    if (!played) {
		played = true;
		arcade = new JButton("Arcade");
		timed = new JButton("Timed");
		desc = new JLabel("     # Flags/Seconds");
		desc.setForeground(Color.white);
		desc.setSize(100, 10);
		mod = new JTextField(1);

		arcade.addActionListener(this);
		timed.addActionListener(this);
	    
		JPanel box = new JPanel(new GridLayout(1, 4));
		box.setSize(300, 20);
		box.add(arcade);
		box.add(timed);
		box.add(desc);
		box.add(mod);
		frame.getContentPane().add(box);

		frame.setVisible(true);
	    }
	}
	else if (e.getSource() == quit)
	    System.exit(0);
	else if (e.getSource() == stats) {
	    fg.createStatsGui();
	    frame.setVisible(false);
	}
	else if (e.getSource() == arcade && mod.getText() != "") {
	    if (Integer.parseInt(mod.getText()) >= 5) {
		fg.createGameGui(0, Integer.parseInt(mod.getText()));
		frame.setVisible(false); 
	    }
	}
	else if (e.getSource() == timed && mod.getText() != "") {
	    if (Integer.parseInt(mod.getText()) >= 5) {
		fg.createGameGui(1, Integer.parseInt(mod.getText()));
		frame.setVisible(false);
	    }
	}
    }

    public MenuGui(FlagGame fg) {
	this.fg = fg;
	played = false;
	next = -1;

	frame = new JFrame("Flag Game");
	//frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	frame.getContentPane().setLayout(new MenuLayout());
	
	title = labelImage("Images/Title.png"); //From cooltext.com...
	frame.getContentPane().add(title);

	play = new JButton("Play");
	stats = new JButton("Statistics");
	quit = new JButton("Quit");

	play.addActionListener(this);
	stats.addActionListener(this);
	quit.addActionListener(this);

	frame.getContentPane().add(play);
	frame.getContentPane().add(stats);
	frame.getContentPane().add(quit);

	frame.setSize(1280, 800);
	frame.setBackground(new Color(30,30,30));
	frame.setMinimumSize(new Dimension(800, 600));
	frame.setVisible(true);
    }
}
