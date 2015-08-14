import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;


public class Gui extends JFrame {



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
