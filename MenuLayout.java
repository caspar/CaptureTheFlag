import java.awt.*;

public class MenuLayout implements LayoutManager {
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
	    c.setBounds(w/2 - 200, h/8 - 50, 400, 100);
	    
	    for (int i=1; i<4; i++) {
		c = parent.getComponent(i);
		c.setBounds(w/3 - 50, h*(i+3)/9 - 25, 100, 50);
	    }

	    if (nComps >= 5) {
		c = parent.getComponent(4);
		c.setBounds(w*2/5, h*8/18 - 25, 300, 30);
	    }
	}
    }
}
