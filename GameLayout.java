import java.awt.*;

public class GameLayout implements LayoutManager {
    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
	comp.setBounds(0,0,0,0);
    }

    public Dimension minimumLayoutSize(Container parent) {
	return new Dimension(400, 200);
    }

    public Dimension preferredLayoutSize(Container parent) {
	return new Dimension(1280,800);
    }

    public void layoutContainer(Container parent) {
	int nComps = parent.getComponentCount();
	if (nComps > 7) {
	    int h = parent.getHeight();
	    int w = parent.getWidth();
	    Component c = parent.getComponent(0);
	    c.setLocation(w/2 - c.getWidth()/2, h/4 - c.getHeight()/2);

	    c = parent.getComponent(1);
	    c.setBounds(w*2/5 - 75, h*5/9 - 25, 150, 50);
	    c = parent.getComponent(2);
	    c.setBounds(w*2/5 - 75, h*2/3 - 25, 150, 50);
	    c = parent.getComponent(3);
	    c.setBounds(w*3/5 - 75, h*5/9 - 25, 150, 50);
	    c = parent.getComponent(4);
	    c.setBounds(w*3/5 - 75, h*2/3 - 25, 150, 50);
	    c = parent.getComponent(5);
	    c.setBounds(10, 10, 75, 25);
	    c = parent.getComponent(6);
	    c.setBounds(w-225, 10, 125, 25);
	    c = parent.getComponent(7);
	    c.setBounds(w-85, 10, 75, 25);
	}
    }
}
