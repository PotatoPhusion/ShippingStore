package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import shippingstore.MainApp;

public class PackageList extends JPanel {
	
	public PackageList() {
		JLabel packageList = new JLabel(MainApp.ss.getAllPackagesFormatted());
		
		this.add(packageList);
	}
}
