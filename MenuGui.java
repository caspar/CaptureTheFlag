import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;

public class MenuGui implements ActionListener {
    private JFrame frame;
    private JLabel background;
    private JLabel title;
    private JButton play;
    private JButton stats;
    private JButton quit;
    private JButton arcade;
    private JButton timed;
    private JButton group;
    private boolean played;

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
		group = new JButton("Group");

		arcade.addActionListener(this);
		timed.addActionListener(this);
		group.addActionListener(this);
	    
		JPanel box = new JPanel(new FlowLayout());
		box.add(arcade);
		box.add(timed);
		box.add(group);
		frame.getContentPane().add(box);

		frame.setVisible(true);
	    }
	}
	else if (e.getSource() == quit)
	    System.exit(0);
	else if (e.getSource() == stats) {
	}
	else if (e.getSource() == arcade) {
	}
	else if (e.getSource() == timed) {
	}
	else if (e.getSource() == group) {
	}
    }

    public MenuGui() {
	played = false;

	frame = new JFrame("Flag Game");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	frame.setVisiblamae(true);
    }
}
