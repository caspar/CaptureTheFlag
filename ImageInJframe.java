import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Caspar Lant
 */
@Deprecated
class ImageImplement extends JPanel {

    private Image img;
    public ImageImplement(Image img) {
        GameLayout layout = new GameLayout();
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null)); setPreferredSize(size);
        setMinimumSize(size); setMaximumSize(size); setSize(size); setLayout(layout);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}

public class ImageInJframe extends JFrame {

    @Deprecated
    public ImageInJframe(String flag){
        start(flag);
    }

    public void start(String img) {
        ImageImplement panel = new ImageImplement(new ImageIcon(    img+".png").getImage());
        add(panel); setVisible(true); setSize(1280,800); setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
