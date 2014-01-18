import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;

<<<<<<< HEAD
public class Gui  extends JFrame implements ActionListener {
=======
public class Gui extends JFrame {
>>>>>>> 42e3a9a24681131214b6aff91c6de53f14fed217
 
    Game Game = new Game();
    Random Rand = new Random();

    //private ArrayList<String> flagList = new ArrayList<String>(); 

    private String imagePath = "Images/";

    private Container pane;
    private JLabel flagLabel;
    private JLabel countryNameLabel;
    private JPanel flagpanel;

<<<<<<< HEAD
    ///// For newButtons() /////
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    
    public Gui() { //CASPAR
=======
    private String[] abcd = {"A. ","B. ","C. ","D. "};
    private String b1 = abcd[0];
    private String b2 = abcd[1];
    private String b3 = abcd[2];
    private String b4 = abcd[3];
    private String[] choices = {b1, b2, b3, b4};
    JButton button1 = new JButton(b1);
    JButton button2 = new JButton(b2);
    JButton button3 = new JButton(b3);
    JButton button4 = new JButton(b4);
    private JButton[] answers = {button1, button2, button3, button4};
   
    public Gui() {
>>>>>>> 42e3a9a24681131214b6aff91c6de53f14fed217
	setTitle("Flag Game");
	setSize(700, 400); //should be fullscreen (1280, 800)
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	pane = this.getContentPane();

	readDir();
	longestCountry();
       	//pane.add(newImage());
	newImage();
	this.update(this.getGraphics());
    }

    public void actionPerformed(ActionEvent e){ //CASPAR
	

    }
    
    public JLabel newImage(){  //CASPAR
	//newImage() should randomly select a flag, and call newButtons()
	String newFlag = (Game.flagList).get(Rand.nextInt(223));
	
       	flagLabel = new JLabel(createImageIcon(imagePath + newFlag, "Germany"));
	countryNameLabel = new JLabel(getName(newFlag));
	pane.add(countryNameLabel);
	pane.add(flagLabel);
	this.update(this.getGraphics());
	return flagLabel;
    }
       
    
    private void buttonAnswers() {  // creates the buttons
    
        String[] abcd = {"A","B","C","D"};
        answers = new JButton[4];
        
        // {"" "" "" ""}
        
        for (int i = 0; i < 4; i++) {
            answers[i] = new JButton(abcd[i]); // {A B C D}
            answers[i].addActionListener(this);
        }
    }
    
    public void assignAnswersToButtons(String[] answers) {
        
        int r = new Random (4); // the index of the button to be given the correct answer

	for (int i = 0; i < 4; i++) {
            buttonAnswers[i].setText(answers[i] + getName(Game.flagList).get(Rand.nextInt(233))); // assigns random country
        }    

        JButton correctAnswer = answers[r]; // reassigns the correct answer to the random button
        
        
        frame.setSize(1280, 800);
        frame.setBackground(new Color(30,30,30));
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setVisible(true);
        
    }


    
    /* private void newButtons(String correctName){ //SPENCER
	//must create 4 unique buttons below flag, one of which is correct.
	//the correct answer should not be in the same place every time. 
	//must be large enough to accomodate large country names (36 characters) --> so 40's good
	//should call getName() in each button init.
	//Like so:
	
	frame = new JFrame("Flag Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new MenuLayout());
	
	String button1 = getName(Game.flagList).get(Rand.nextInt(233)));
	String button2 = getName(Game.flagList).get(Rand.nextInt(233)));
	String button3 = getName(Game.flagList).get(Rand.nextInt(233)));
	String button4 = getName(Game.flagList).get(Rand.nextInt(233)))
	JButton[] buttons = new JButton(4);  // array of buttons
	buttons[0] = new JButton(""); // the following adds four blank buttons into it
	buttons[1] = new JButton("");
	buttons[2] = new JButton("");
	buttons[3] = new JButton("");
	Random r = new Random(4); 
	buttons[r] = 
	//String rand = r.getName(Game.flagList).get(Rand.nextInt(233));
	//buttons[rand] = buttons[rand].setText("" + "Whatever the path is.");
	// This didn't do the trick at all lol
	
	button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        frame.getContentPane().add(button1);
        frame.getContentPane().add(button2);
        frame.getContentPane().add(button3);
        frame.getContentPane().add(button4);


	
    }
    */
    
    
    public int longestCountry(){ //CASPAR
	int longest = 0;
	for (int i = 0; i < 223; i ++){
	    if (((Game.flagList).get(i)).length() > longest)
		longest = ((Game.flagList).get(i)).length();
	}
	return longest;
    }

    public String getName(String input){ //CASPAR
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
    }
<<<<<<< HEAD
       
=======
    
    public void assignAnswersToButtons(String[] answers) {
        
        Random rand = new Random();
	int r = rand.nextInt(4); // the index of the button to be given the correct answer

        for (int i = 0; i < 4; i++) {
            choices [i] += getName((Game.flagList).get(Rand.nextInt(233))); // assigns random country to the buttons
            answers[i] = choices[i];
        }

        choices[r] = abcd[r] + "path of answer"; // reassigns the correct answer to the random button
        answers[r] = choices[r];
    }

    
    
>>>>>>> 42e3a9a24681131214b6aff91c6de53f14fed217
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
