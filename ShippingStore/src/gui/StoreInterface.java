package gui;

import javax.swing.*;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class StoreInterface extends JFrame{
	
	public static StoreInterface si;
	
	public StoreInterface() {
		super("Shipping Store");
		displayMainMenu();
	}
	
	public StoreInterface(String title) {
		super(title);
		displayMainMenu();
	}
	
	private void displayMainMenu() {
		this.setLayout(new FlowLayout());
		
		MainMenu mainMenu = new MainMenu();
		
		this.getContentPane().removeAll();
		this.add(mainMenu);
		
		this.repaint();
	}
	
}
