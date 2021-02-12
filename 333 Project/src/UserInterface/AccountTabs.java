package UserInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Connection.UserService;

public class AccountTabs extends Tabs {

	UIMain ui;
	UserService us;
	
	public AccountTabs(UIMain ui, UserService us) {
		this.ui = ui;
		this.us = us;
	}
	@Override
	protected void createTabs() {
		JPanel changePass = new JPanel(new GridBagLayout());
		initilizeChangePassView(changePass, new GridBagConstraints());
		changePass.setVisible(true);
		this.addTab("Change Account Password", changePass);
		
		JPanel deleteAccount = new JPanel(new GridBagLayout());
		initilizeDeleteAccountView(deleteAccount, new GridBagConstraints());
		deleteAccount.setVisible(true);
		this.addTab("Delete", deleteAccount);
	}
	private void initilizeDeleteAccountView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();

		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Do you wish to delete your account?"), c);
		c.gridx = 1;
		c.gridy = 0;
		JButton deleteButt = new JButton("Delete My Account");
		deleteButt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to delete your account and all information tied to it?";
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Deletion", optionType);
				if (result == JOptionPane.OK_OPTION) {
					boolean success = ui.getBackEnd().getDeleteFunctions().deletePlayer(ui.getPlayerUsername());
					if (success) {
						result = JOptionPane.showConfirmDialog(panel, "Press OKAY to exit", "Account was Deleted", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
						if (result == JOptionPane.OK_OPTION) {
							System.exit(0);
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error: Unable to delete account.");
					}
				}
				initilizeDeleteAccountView(panel, new GridBagConstraints());
			}
		});
		panel.add(deleteButt, c);	
	}
	
	private void initilizeChangePassView(JPanel panel, GridBagConstraints c) {
		panel.removeAll();
		panel.repaint();

		c.insets = new Insets(10, 5, 0, 5);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Enter New Password: "), c);
		c.gridx = 1;
		JTextField newPass = new JTextField(30);
		panel.add(newPass, c);
		c.gridx = 2;
		JButton updateButt = new JButton("Update Password");
		updateButt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pass = newPass.getText();
				if (pass.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "Please fill out new password field.");
					return;
				}
				int optionType = JOptionPane.OK_CANCEL_OPTION;
				String confirmString = "Are you sure you want to change your password?";
				int result = JOptionPane.showConfirmDialog(panel, confirmString, "Confirm Deletion", optionType);
				if (result == JOptionPane.OK_OPTION) {
					boolean success = us.updatePassword(ui.getPlayerUsername(), pass);
					if (success) {
						JOptionPane.showMessageDialog(panel, "Account password updated");
					} else {
						JOptionPane.showMessageDialog(panel, "Error: Unable to update password updated");
					}
				}
				initilizeChangePassView(panel, new GridBagConstraints());
			}
		});
		panel.add(updateButt, c);	
	}

}
