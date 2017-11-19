package gui;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	
	JButton showPackagesButton;
	JButton addPackageButton;
	JButton deletePackageButton;
	JButton searchPackageButton;
	JButton showUsersButton;
	JButton addUserButton;
	JButton updateUserButton;
	JButton deliverPackageButton;
	JButton showTransactionsButton;
	JButton exitButton;
	
	
	public MainMenu() {
		this.setLayout(new GridLayout(5, 2));
		
		showPackagesButton = new JButton("Show All Packages");
		addPackageButton = new JButton("Add a New Package");
		deletePackageButton = new JButton("Remove a Package");
		searchPackageButton = new JButton("Search for a Package");
		showUsersButton = new JButton("Show All Users");
		addUserButton = new JButton("Add a New User");
		updateUserButton = new JButton("Update a User's Info");
		deliverPackageButton = new JButton("Deliver a Package");
		showTransactionsButton = new JButton("Show All Transactions");
		exitButton = new JButton("Exit Program");
		
		ShowAllListener showAllListener = new ShowAllListener();
		showPackagesButton.addActionListener(showAllListener);
		
		this.add(showPackagesButton);
		this.add(addPackageButton);
		this.add(deletePackageButton);
		this.add(searchPackageButton);
		this.add(showUsersButton);
		this.add(addUserButton);
		this.add(updateUserButton);
		this.add(deliverPackageButton);
		this.add(showTransactionsButton);
		this.add(exitButton);
	}
	
	private class ShowAllListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			showPackagesButton.setText("I changed.");
		}
		
	}
}
