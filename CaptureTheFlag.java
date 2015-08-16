import java.io.*;
import java.util.*;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

/**
 * @author Caspar Lant
 * TODO center flag image
 * TODO learn about buttons
 */
public class CaptureTheFlag extends JFrame{

    public static final String PATH = "img/flags/";
    public final GameLayout layout = new GameLayout();

    public static void main(String[] args){
        CaptureTheFlag game = new CaptureTheFlag(args[0]);
    }

    /**
     * CaptureTheFlag Constructor
     * @date   2015-08-15
     * @param  flagname   Flag's Name
     */
    public CaptureTheFlag(String flagname){
        File   folder = new File(PATH);
        File[] images = folder.listFiles();
        DisplayFlag(PATH + flagname);
        //GameGui game = new GameGui();
    }

    private void DisplayFlag(String flag){
        ImageImplement panel = new ImageImplement(new ImageIcon(flag+".png").getImage());
        add(panel); setVisible(true); setSize(470, 335); setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
