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
	
	StoreInterface si;
	
	public MainMenu(StoreInterface si) {
		this.si = si;
		
		this.setLayout(new GridLayout(5, 2));
		
		//=========================================
		// Instantiate Buttons
		//=========================================
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
		
		
		//=========================================
		// Adding ActionListeners to buttons
		//=========================================
		ShowPackagesListener showPackagesListener = new ShowPackagesListener();
		showPackagesButton.addActionListener(showPackagesListener);
		
		AddPackageListener addPackageListener = new AddPackageListener();
		addPackageButton.addActionListener(addPackageListener);
		
		DeletePackageListener deletePackageListener = new DeletePackageListener();
		deletePackageButton.addActionListener(deletePackageListener);
		
		SearchPackageListener searchPackageListener = new SearchPackageListener();
		searchPackageButton.addActionListener(searchPackageListener);
		
		ShowUsersListener showUsersListener = new ShowUsersListener();
		showUsersButton.addActionListener(showUsersListener);
		
		AddUserListener addUserListener = new AddUserListener();
		addUserButton.addActionListener(addUserListener);
		
		UpdateUserListener updateUserListener = new UpdateUserListener();
		updateUserButton.addActionListener(updateUserListener);
		
		DeliverPackageListener deliverPackageListener = new DeliverPackageListener();
		deliverPackageButton.addActionListener(deliverPackageListener);
		
		ShowTransactionsListener showTransactionsListener = new ShowTransactionsListener();
		showTransactionsButton.addActionListener(showTransactionsListener);
		
		ExitListener exitListener = new ExitListener();
		exitButton.addActionListener(exitListener);
		
		
		//=========================================
		// Adding buttons to MainMenu
		//=========================================
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
	
	private class ShowPackagesListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			showPackagesButton.setText("I changed.");
		}
		
	}
	private class AddPackageListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			addPackageButton.setText("I changed.");
		}
		
	}
	private class DeletePackageListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			deletePackageButton.setText("I changed.");
		}
		
	}
	private class SearchPackageListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			searchPackageButton.setText("I changed.");
		}
		
	}
	private class ShowUsersListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			showUsersButton.setText("I changed.");
		}
		
	}
	private class AddUserListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			addUserButton.setText("I changed.");
		}
		
	}
	private class UpdateUserListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			updateUserButton.setText("I changed.");
		}
		
	}
	private class DeliverPackageListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			deliverPackageButton.setText("I changed.");
		}
		
	}
	private class ShowTransactionsListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			showTransactionsButton.setText("I changed.");
		}
		
	}
	private class ExitListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			exitButton.setText("I changed.");
		}
		
	}
}
