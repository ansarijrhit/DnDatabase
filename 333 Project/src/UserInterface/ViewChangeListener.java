package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewChangeListener implements ActionListener {
	private ArrayList<JPanel> views;
	private JPanel changeTo;
	private JFrame frame;
	
	public ViewChangeListener(ArrayList<JPanel> panels, JPanel panel, JFrame frame) {
		this.views = panels;
		this.changeTo = panel;
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for (JPanel panel : this.views) {
			panel.setVisible(false);
		}
		changeTo.setVisible(true);
		this.frame.revalidate();
	}

}
