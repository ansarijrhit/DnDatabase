package UserInterface;

import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public abstract class Tabs extends JTabbedPane {

	protected abstract void createTabs();

	public void reInitilize() {
		this.removeAll();
		createTabs();
		this.revalidate();
		this.repaint();
	}
}
