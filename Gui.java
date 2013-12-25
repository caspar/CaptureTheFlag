import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Caspar Lant

public class Gui extends JFrame {
 
    Game Ga = new Game();
    
    private Container pane;
    private JButton exitButton,b;
    private JLabel l;
    private JTextField text;
    private JPanel canvas;
    private Container buttons;
    private JCheckBox box;
    
    public void hello() {
	System.out.println("HEllo");
    }

    
    public Gui() {
	setTitle("Flag Game");
	setSize(1280, 800); //should be fullscreen
	setLocation(100,100);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane = this.getContentPane();

    }


}