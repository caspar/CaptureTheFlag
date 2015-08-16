import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

class ImageImplement extends JPanel {

    private Image img;
    public ImageImplement(Image img) {
        GameLayout layout = new GameLayout();
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null)); setPreferredSize(size);
        setMinimumSize(size); setMaximumSize(size); setSize(size); setLayout(layout);
        Image zimbabwe = new ImageIcon("img/flags/Zimbabwe.png").getImage();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
