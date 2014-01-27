import java.awt.*;

public class EndLayout implements LayoutManager {
    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension minimumLayoutSize(Container parent) {
	return new Dimension(400, 200);
    }

    public Dimension preferredLayoutSize(Container parent) {
	return new Dimension(1280,800);
    }

    public void layoutContainer(Container parent) {
	int nComps = parent.getComponentCount();
	if (nComps >= 4) {
	    int h = parent.getHeight();
	    int w = parent.getWidth();
	    Component c = parent.getComponent(0);
	    c.setBounds(w/2 - 210, h/8 - 20, 500, 100);
	    c.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));

	    for (int i=1; i<4; i++) {
		c = parent.getComponent(i);
		c.setBounds(w/3 - 250, h*(i+1)/6 - 25, 1000, 100);
		c.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
	    }
	    if (nComps == 5) {
		c = parent.getComponent(4);
		c.setBounds(w - 160, h - 35, 150, 25);
	    }
	}
    }
}
