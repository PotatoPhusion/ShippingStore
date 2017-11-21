package shippingstore;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.*;
import javax.swing.*;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import sun.util.logging.resources.logging;

/**
* Main access point
*/
public class MainApp {
    
	private final Dimension MENU_BUTTON_SIZE = new Dimension(100, 35);
	
	private final static  Logger logger = Logger.getLogger(MainApp.class.getName());
	
    ShippingStore ss;
    private static JFrame si;
    private final Scanner sc; // Used to read from System's standard input
    
    /**
     * Constructor
     */
    public MainApp() {
        ss = ShippingStore.readDatabase();
        this.sc = new Scanner(System.in);
    }
    
    /**
    * This method servers as the main interface between the program and the user.
    * The method interacts with the user by printing out a set of options, and
    * asking the user to select one.
    */
   public void runSoftware() {

	   SwingUtilities.invokeLater(new Runnable() {
		   public void run() {
			   printMenu();
		   }
	   });
   }

   /**
    * Auxiliary method that prints out the operations menu.
    */
    private void printMenu() {
    	JPanel mainMenu = new JPanel();
	   	mainMenu.setLayout(new GridLayout(5, 2));
	   	
	   	//============================================
		// Instantiate Buttons and set preferred size
		//============================================
		JButton showPackagesButton = new JButton("Show All Packages");
		showPackagesButton.setPreferredSize(new Dimension(200, 50));
		
		JButton addPackageButton = new JButton("Add a New Package");
		showPackagesButton.setPreferredSize(new Dimension(200, 50));
		
		JButton deletePackageButton = new JButton("Remove a Package");
		showPackagesButton.setPreferredSize(new Dimension(200, 50));
		
		JButton searchPackageButton = new JButton("Search for a Package");
		showPackagesButton.setPreferredSize(new Dimension(200, 50));
		
		JButton showUsersButton = new JButton("Show All Users");
		showPackagesButton.setPreferredSize(new Dimension(200, 50));
		
		JButton addUserButton = new JButton("Add a New User");
		showPackagesButton.setPreferredSize(new Dimension(200, 50));
		
		JButton updateUserButton = new JButton("Update a User's Info");
		showPackagesButton.setPreferredSize(new Dimension(200, 50));
		
		JButton deliverPackageButton = new JButton("Deliver a Package");
		showPackagesButton.setPreferredSize(new Dimension(200, 50));
		
		JButton showTransactionsButton = new JButton("Show All Transactions");
		showPackagesButton.setPreferredSize(new Dimension(200, 50));
		
		JButton exitButton = new JButton("Save and Quit");
		showPackagesButton.setPreferredSize(new Dimension(200, 50));
		
		
		//============================================
		// Adding ActionListeners to buttons
		//============================================
		showPackagesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						showAllPackages();
					}
				});
			}
		});
		
		
		addPackageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					addNewPackage();	// TODO: Implement
				} catch (NumberFormatException ex) {
		            System.err.println("Input missmatch. Please Try again.");	// TODO: Error Logging and JOptionPane error message
				} catch (BadInputException ex) {
					System.err.println("Bad input. "+ex.getMessage());	// TODO: Error Logging and JOptionPane error message
					System.err.println("Please try again.");
				}
			}
		});
		
		
		deletePackageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				deletePackage();
			}
		});
		
		
		searchPackageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchPackage();	// TODO: Implement
			}
		});
		
		
		showUsersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				showAllUsers();
			}
		});
		
		
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addNewUser();	// TODO: Implement
			}
		});
		
		
		updateUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateUser();
			}
		});
		
		
		deliverPackageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				deliverPackage();
			}
		});
		
		
		showTransactionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				showAllTransactions();
			}
		});
		
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ss.writeDatabase();
				
				si.dispose();
			}
		});
		
		
		//============================================
		// Adding buttons to MainMenu
		//============================================
		mainMenu.add(showPackagesButton);
		mainMenu.add(addPackageButton);
		mainMenu.add(deletePackageButton);
		mainMenu.add(searchPackageButton);
		mainMenu.add(showUsersButton);
		mainMenu.add(addUserButton);
		mainMenu.add(updateUserButton);
		mainMenu.add(deliverPackageButton);
		mainMenu.add(showTransactionsButton);
		mainMenu.add(exitButton);
		
		si.getContentPane().removeAll();
		si.add(mainMenu);
		si.pack();
		
		si.repaint();
	}
    

    /**
     * This method allows the user to enter a new package to the list
     * database.
     * @throws shippingstore.BadInputException bad input
     */
    public void addNewPackage() throws BadInputException, NumberFormatException {
    	
    	String[] typeStrings = { "Envelope", "Box", "Crate", "Drum" };
    	String[] specStrings = { "Fragile", "Books", "Catalogs", "Do-not-bend", "N/A" };
    	String[] mailClassStrings = { "First-Class", "Priority", "Retail", "Ground", "Metro" };
    	
        //=================================
        // Begin building UI
        //=================================
    	JPanel wholePanel = new JPanel();
    	wholePanel.setLayout(new BoxLayout(wholePanel, BoxLayout.Y_AXIS));
    	JPanel addPackagePanel = new JPanel(new CardLayout());
    	
    	JPanel cardEnvelope = new JPanel();											// Contains all stuff to do with envelopes
    	JPanel envelopeInputPanel = new JPanel();									// Contains all of the input fields
    	JPanel envelopeButtonPanel = new JPanel(new FlowLayout());					// Contains the Add and Cancel Buttons
    	envelopeInputPanel.setLayout(new GridLayout(5, 2));							// Fields go in 5 row 2 column grid [Label: Field]
    	cardEnvelope.setLayout(new BoxLayout(cardEnvelope, BoxLayout.Y_AXIS));		// Internal panels stacked on top of each other
    	cardEnvelope.setName("Envelope");
    	
    	JPanel cardBox = new JPanel();
    	JPanel boxInputPanel = new JPanel();
    	JPanel boxButtonPanel = new JPanel(new FlowLayout());
    	boxInputPanel.setLayout(new GridLayout(5, 2));
    	cardBox.setLayout(new BoxLayout(cardBox, BoxLayout.Y_AXIS));
    	cardBox.setName("Box");
    	
    	JPanel cardCrate = new JPanel();
    	JPanel crateInputPanel = new JPanel();
    	JPanel crateButtonPanel = new JPanel();
    	crateInputPanel.setLayout(new GridLayout(5, 2));
    	cardCrate.setLayout(new BoxLayout(cardCrate, BoxLayout.Y_AXIS));
    	cardCrate.setName("Crate");
    	
    	JPanel cardDrum = new JPanel();
    	JPanel drumInputPanel = new JPanel();
    	JPanel drumButtonPanel = new JPanel(new FlowLayout());
    	drumInputPanel.setLayout(new GridLayout(5, 2));
    	cardDrum.setLayout(new BoxLayout(cardDrum, BoxLayout.Y_AXIS));
    	cardDrum.setName("Drum");
    	
    	class TypeLayoutListener implements ItemListener {
    		public void itemStateChanged(ItemEvent event) {
    			CardLayout cl = (CardLayout)(addPackagePanel.getLayout());
    			cl.show(addPackagePanel, (String)event.getItem());
    			//si.repaint();
    		}
    	}
    	
    	JPanel comboBoxPane = new JPanel(new FlowLayout());
        
        JComboBox<String> typeList = new JComboBox<String>(typeStrings);
        typeList.setSelectedIndex(0);
        typeList.setEditable(false);
        TypeLayoutListener cardListener = new TypeLayoutListener();
        typeList.addItemListener(cardListener);
        comboBoxPane.add(new JLabel("Type: "));
        comboBoxPane.add(typeList);
        
        //=================================
        // Add and Cancel Buttons
        //=================================
        JPanel buttonPanel = new JPanel(new FlowLayout());
    	JButton addButton1 = new JButton("Add Package");
    	JButton cancelButton1 = new JButton("Cancel");
    	JButton addButton2 = new JButton("Add Package");
    	JButton cancelButton2 = new JButton("Cancel");
    	JButton addButton3 = new JButton("Add Package");
    	JButton cancelButton3 = new JButton("Cancel");
    	JButton addButton4 = new JButton("Add Package");
    	JButton cancelButton4 = new JButton("Cancel");
    	
    	addButton1.setPreferredSize(new Dimension(150, 30));
    	cancelButton1.setPreferredSize(new Dimension(150, 30));
    	addButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
    	cancelButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	addButton2.setPreferredSize(new Dimension(150, 30));
    	cancelButton3.setPreferredSize(new Dimension(150, 30));
    	addButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
    	cancelButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	addButton3.setPreferredSize(new Dimension(150, 30));
    	cancelButton3.setPreferredSize(new Dimension(150, 30));
    	addButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
    	cancelButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	addButton4.setPreferredSize(new Dimension(150, 30));
    	cancelButton4.setPreferredSize(new Dimension(150, 30));
    	addButton4.setAlignmentX(Component.CENTER_ALIGNMENT);
    	cancelButton4.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	class CancelListener implements ActionListener {
    		public void actionPerformed(ActionEvent event) {
    			printMenu();
    		}
    	}
    	
    	CancelListener cancelListen = new CancelListener();
    	cancelButton1.addActionListener(cancelListen);
    	cancelButton2.addActionListener(cancelListen);
    	cancelButton3.addActionListener(cancelListen);
    	cancelButton4.addActionListener(cancelListen);
        
        // TODO: Literally everything else to do with adding a package.
        
        //=================================
        // Tracking Number
        //=================================
        envelopeInputPanel.add(new JLabel("Tracking Number: "));
        boxInputPanel.add(new JLabel("Tracking Number: "));
        crateInputPanel.add(new JLabel("Tracking Number: "));
        drumInputPanel.add(new JLabel("Tracking Number: "));
        
        JTextField trackingField1 = new JTextField(20);
        JTextField trackingField2 = new JTextField(20);
        JTextField trackingField3 = new JTextField(20);
        JTextField trackingField4 = new JTextField(20);
        
        envelopeInputPanel.add(trackingField1);
        boxInputPanel.add(trackingField2);
        crateInputPanel.add(trackingField3);
        drumInputPanel.add(trackingField4);
        
        //=================================
        // Specification
        //=================================
        envelopeInputPanel.add(new JLabel("Specification: "));
        boxInputPanel.add(new JLabel("Specification: "));
        crateInputPanel.add(new JLabel("Specification: "));
        drumInputPanel.add(new JLabel("Specification: "));
        
        JComboBox<String> specBox1 = new JComboBox<String>(specStrings);
        JComboBox<String> specBox2 = new JComboBox<String>(specStrings);
        JComboBox<String> specBox3 = new JComboBox<String>(specStrings);
        JComboBox<String> specBox4 = new JComboBox<String>(specStrings);
        
        envelopeInputPanel.add(specBox1);
        boxInputPanel.add(specBox2);
        crateInputPanel.add(specBox3);
        drumInputPanel.add(specBox4);
        
        //=================================
        // Mailing Class
        //=================================
        envelopeInputPanel.add(new JLabel("Mailing Class: "));
        boxInputPanel.add(new JLabel("Mailing Class: "));
        crateInputPanel.add(new JLabel("Mailing Class: "));
        drumInputPanel.add(new JLabel("Mailing Class: "));
        
        JComboBox<String> mcBox1 = new JComboBox<String>(mailClassStrings);
        JComboBox<String> mcBox2 = new JComboBox<String>(mailClassStrings);
        JComboBox<String> mcBox3 = new JComboBox<String>(mailClassStrings);
        JComboBox<String> mcBox4 = new JComboBox<String>(mailClassStrings);
        
        envelopeInputPanel.add(mcBox1);
        boxInputPanel.add(mcBox2);
        crateInputPanel.add(mcBox3);
        drumInputPanel.add(mcBox4);
        
        //=================================
        // Envelope Details
        //=================================
        JTextField heightField = new JTextField(6);
        JTextField widthField = new JTextField(6);
        
        envelopeInputPanel.add(new JLabel("Height: "));
        envelopeInputPanel.add(heightField);
        envelopeInputPanel.add(new JLabel("Width: "));
        envelopeInputPanel.add(widthField);
        
        class EnvelopeListener implements ActionListener {
        	
        	private String ptn;
        	private String specification;
        	private String mailingClass;
        	private int height;
        	private int width;
        	
        	public void actionPerformed(ActionEvent event) {
        		ptn = trackingField1.getText();
        		
        		if (ptn.length() != 5) {
        			JOptionPane.showMessageDialog(si, "Tracking Number must be 5 characters long");	// TODO: Logging
        			logger.log(Level.WARNING, "Tracking Number incorrect length.");
        			return;
        		}
        		else if (ss.packageExists(ptn)) {
        			JOptionPane.showMessageDialog(si, "Tracking Number already in database");	// TODO: Logging
        			logger.log(Level.WARNING, "Tracking Number in database already.");
        			return;
        		}
        		
        		try {
        			height = Integer.parseInt(heightField.getText());
        			
        			if (height < 0) {
        				JOptionPane.showMessageDialog(si, "Height must be positive!");	// TODO: Logging
        				logger.log(Level.WARNING, "Height not a positive number.");
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Height must be an integer!");	// TODO: Logging
        			logger.log(Level.WARNING, "Height not an int.");
        			return;
        		}
        		try {
        			width = Integer.parseInt(widthField.getText());
        			
        			if (width < 0) {
        				JOptionPane.showMessageDialog(si, "Width must be positive!");	// TODO: Logging
        				logger.log(Level.WARNING, "Width gotta be positive.");
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Width must be an integer!");	// TODO: Logging
        			logger.log(Level.WARNING, "Width gotta be an int.");
        			return;
        		}
        		
        		specification = (String)specBox1.getSelectedItem();
        		mailingClass = (String)mcBox1.getSelectedItem();
        		
        		ss.addEnvelope(ptn, specification, mailingClass, height, width);
        		
        		JOptionPane.showMessageDialog(si, "Package successfully added!");	// TODO: Logging
        		logger.log(Level.INFO, "Package has been added!.");
        		printMenu();
        	}
        }
        
        //=================================
        // Box Details
        //=================================
        JTextField dimensionField = new JTextField(6);
        JTextField volumeField = new JTextField(6);
        
        boxInputPanel.add(new JLabel("Largest Dimension: "));
        boxInputPanel.add(dimensionField);
        boxInputPanel.add(new JLabel("Volume: "));
        boxInputPanel.add(volumeField);
        
        class BoxListener implements ActionListener {
        	
        	private String ptn;
        	private String specification;
        	private String mailingClass;
        	private int largestDim;
        	private int volume;
        	
        	public void actionPerformed(ActionEvent event) {
        		ptn = trackingField2.getText();
        		
        		if (ptn.length() != 5) {
        			JOptionPane.showMessageDialog(si, "Tracking Number must be 5 characters long");	// TODO: Logging
        			logger.log(Level.WARNING, "Tracking Number incorrect length.");
        			return;
        		}
        		else if (ss.packageExists(ptn)) {
        			JOptionPane.showMessageDialog(si, "Tracking Number already in database");	// TODO: Logging
        			logger.log(Level.WARNING, "Tracking Number in database already.");
        			return;
        		}
        		
        		try {
        			largestDim = Integer.parseInt(dimensionField.getText());
        			
        			if (largestDim < 0) {
        				JOptionPane.showMessageDialog(si, "Largest Dimension must be positive!");	// TODO: Logging
        				logger.log(Level.WARNING, "Dimensions not positive.");
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Largest Dimension must be an integer!");	// TODO: Logging
        			logger.log(Level.WARNING, "Dimension not an integer.");
        			return;
        		}
        		
        		try {
        			volume = Integer.parseInt(volumeField.getText());
        			
        			if (volume < 0) {
        				JOptionPane.showMessageDialog(si, "Volume must be positive!");	// TODO: Logging
        				logger.log(Level.WARNING, "Volume is not positive, must be positive.");
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Volume must be an integer!");	// TODO: Logging
        			logger.log(Level.WARNING, "Volume gotta be an integer brah.");
        			return;
        		}
        		
        		specification = (String)specBox1.getSelectedItem();
        		mailingClass = (String)mcBox1.getSelectedItem();
        		
        		ss.addBox(ptn, specification, mailingClass, largestDim, volume);
        		
        		JOptionPane.showMessageDialog(si, "Package successfully added!");	// TODO: Logging
        		logger.log(Level.INFO, "Package has been added!");
        		printMenu();
        	}
        }
        
        //=================================
        // Crate Details
        //=================================
        JTextField loadField = new JTextField(6);
        JTextField contentField = new JTextField(6);
        
        crateInputPanel.add(new JLabel("Max Load Weight: "));
        crateInputPanel.add(loadField);
        crateInputPanel.add(new JLabel("Content: "));
        crateInputPanel.add(contentField);
        
        class CrateListener implements ActionListener {
        	
        	private String ptn;
        	private String specification;
        	private String mailingClass;
        	private float loadWeight;
        	private String content;
        	
        	public void actionPerformed(ActionEvent event) {
        		ptn = trackingField3.getText();
        		
        		if (ptn.length() != 5) {
        			JOptionPane.showMessageDialog(si, "Tracking Number must be 5 characters long");	// TODO: Logging
        			logger.log(Level.WARNING, "Number is not 5 characters long");
        			return;
        		}
        		else if (ss.packageExists(ptn)) {
        			JOptionPane.showMessageDialog(si, "Tracking Number already in database");	// TODO: Logging
        			logger.log(Level.WARNING,  "Tracking Number already in database.");
        			return;
        		}
        		
        		try {
        			loadWeight = Float.parseFloat(loadField.getText());
        			
        			if (loadWeight < 0) {
        				JOptionPane.showMessageDialog(si, "Load Weight must be positive!");	// TODO: Logging
        				logger.log(Level.WARNING, "Load weight must be positive.");
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Load Weight must be a decimal number!");	// TODO: Logging
        			logger.log(Level.WARNING, "Weight must be a decimal number.");
        			return;
        		}
        		
        		specification = (String)specBox1.getSelectedItem();
        		mailingClass = (String)mcBox1.getSelectedItem();
        		content = contentField.getText();
        		
        		ss.addCrate(ptn, specification, mailingClass, loadWeight, content);
        		
        		JOptionPane.showMessageDialog(si, "Package successfully added!");	// TODO: Logging
        		logger.log(Level.INFO, "Package has been added!");
        		printMenu();
        	}
        }
        
        //=================================
        // Drum Details
        //=================================        
        JTextField materialField = new JTextField(6);
        JTextField diameterField = new JTextField(6);
        
        drumInputPanel.add(new JLabel("Drum Material: "));
        drumInputPanel.add(materialField);
        drumInputPanel.add(new JLabel("Diameter: "));
        drumInputPanel.add(diameterField);
        
        class DrumListener implements ActionListener {
        	
        	private String ptn;
        	private String specification;
        	private String mailingClass;
        	private String material;
        	private float diameter;
        	
        	public void actionPerformed(ActionEvent event) {
        		ptn = trackingField4.getText();
        		
        		if (ptn.length() != 5) {
        			JOptionPane.showMessageDialog(si, "Tracking Number must be 5 characters long");	// TODO: Logging
        			logger.log(Level.WARNING, "Tracking number gotta be 5 characters long.");
        			return;
        		}
        		else if (ss.packageExists(ptn)) {
        			JOptionPane.showMessageDialog(si, "Tracking Number already in database");	// TODO: Logging
        			logger.log(Level.WARNING, "Tracking number already in database.");
        			return;
        		}
        		
        		try {
        			diameter = Float.parseFloat(volumeField.getText());
        			
        			if (diameter < 0) {
        				JOptionPane.showMessageDialog(si, "Diameter must be positive!");	// TODO: Logging
        				logger.log(Level.WARNING, "Diameter must be a positive.");
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Diameter must be a decimal number");	// TODO: Logging
        			logger.log(Level.WARNING, "Diameter ust be a decimal num.");
        			return;
        		}
        		
        		material = materialField.getText();
        		specification = (String)specBox1.getSelectedItem();
        		mailingClass = (String)mcBox1.getSelectedItem();
        		
        		ss.addDrum(ptn, specification, mailingClass, material, diameter);
        		
        		JOptionPane.showMessageDialog(si, "Package successfully added!");	// TODO: Logging
        		logger.log(Level.INFO, "Package added!");
        		printMenu();
        	}
        }
        
        //=================================
        // Frame Setup
        //=================================
        envelopeButtonPanel.add(addButton1);
    	envelopeButtonPanel.add(cancelButton1);
    	boxButtonPanel.add(addButton2);
    	boxButtonPanel.add(cancelButton2);
    	crateButtonPanel.add(addButton3);
    	crateButtonPanel.add(cancelButton3);
    	drumButtonPanel.add(addButton4);
    	drumButtonPanel.add(cancelButton4);
        
        EnvelopeListener envListen = new EnvelopeListener();
        BoxListener boxListen = new BoxListener();
        CrateListener crateListen = new CrateListener();
        DrumListener drumListen = new DrumListener();
        
        addButton1.addActionListener(envListen);
        addButton2.addActionListener(boxListen);
        addButton3.addActionListener(crateListen);
        addButton4.addActionListener(drumListen);
        
        cardEnvelope.add(envelopeInputPanel);
        cardEnvelope.add(envelopeButtonPanel);
        cardBox.add(boxInputPanel);
        cardBox.add(boxButtonPanel);
        cardCrate.add(crateInputPanel);
        cardCrate.add(crateButtonPanel);
        cardDrum.add(boxInputPanel);
        cardDrum.add(boxButtonPanel);
        
        addPackagePanel.add(cardEnvelope, "Envelope");
        addPackagePanel.add(cardBox, "Box");
        addPackagePanel.add(cardCrate, "Crate");
        addPackagePanel.add(cardDrum, "Drum");
        
        si.getContentPane().removeAll();
		wholePanel.add(comboBoxPane, BorderLayout.PAGE_START);
		wholePanel.add(addPackagePanel, BorderLayout.PAGE_END);
		si.add(wholePanel);
		si.pack();
		
		si.repaint();
    }
    
    /**
     * This method displays all the package currently in the inventory, in a
     * formatted manner.
     */
    public void showAllPackages() {
    	JPanel panel = new JPanel();
    	JTextArea packageList = new JTextArea();
    	JButton menuButton = new JButton("Main Menu");
    	
    	packageList.setEditable(false);
    	menuButton.setPreferredSize(MENU_BUTTON_SIZE);
    	menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	
        packageList.setText(ss.getAllPackagesFormatted());
        
        panel.add(packageList);
        panel.add(menuButton);
        
        menuButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		printMenu();
        	}
        });
        
		si.getContentPane().removeAll();
		si.add(panel);
		si.pack();
		
		si.repaint();
    }
    
    /**
     * This method allows the user to delete a package from the inventory
     * database.
     */
    public void deletePackage() {
    	
    	JPanel removePackagePanel = new JPanel();
    	removePackagePanel.setLayout(new BoxLayout(removePackagePanel, BoxLayout.Y_AXIS));
    	
    	JPanel inputPanel = new JPanel(new FlowLayout());
    	JTextField trackingNumberField = new JTextField(20);
    	JLabel label = new JLabel("Enter Tracking Number:");
    	
    	class RemoveListener implements ActionListener {
    		public void actionPerformed(ActionEvent event) {
    			if (ss.deletePackage(trackingNumberField.getText())) 
    				JOptionPane.showMessageDialog(si, "Package deleted.");
    			else 
    				JOptionPane.showMessageDialog(si, "Package with given tracking number not found in the database.");
    			printMenu();
    		}
    	}
    	
    	RemoveListener removeListener = new RemoveListener();
    	
    	trackingNumberField.addActionListener(removeListener);
    	
    	JPanel buttonPanel = new JPanel(new FlowLayout());
    	JButton cancelButton = new JButton("Cancel");
    	JButton removeButton = new JButton("Remove");
    	
    	cancelButton.setPreferredSize(MENU_BUTTON_SIZE);
    	removeButton.setPreferredSize(MENU_BUTTON_SIZE);
    	
    	removeButton.addActionListener(removeListener);
    	
    	cancelButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		printMenu();
        	}
        });
    	
    	inputPanel.add(label);
    	inputPanel.add(trackingNumberField);
    	
    	buttonPanel.add(cancelButton);
    	buttonPanel.add(removeButton);
    	
    	removePackagePanel.add(inputPanel, BorderLayout.NORTH);
    	removePackagePanel.add(buttonPanel, BorderLayout.SOUTH);
    	
    	si.getContentPane().removeAll();
    	si.add(removePackagePanel);
    	si.pack();
    	
    	si.repaint();
    }
    
    /**
     * This method allows the users to search for a package given its tracking number
     * and then it prints details about the package.
     */
    public void searchPackage() {
    	JPanel removePackagePanel = new JPanel();
    	removePackagePanel.setLayout(new BoxLayout(removePackagePanel, BoxLayout.Y_AXIS));
    	
    	JPanel inputPanel = new JPanel(new FlowLayout());
    	JTextField trackingNumberField = new JTextField(20);
    	JLabel label = new JLabel("Enter Tracking Number:");
    	
    	class SearchListener implements ActionListener {
    		public void actionPerformed(ActionEvent event) {
    			if (ss.packageExists(trackingNumberField.getText())) 
    				JOptionPane.showMessageDialog(si, "Package found.");
    			else 
    				JOptionPane.showMessageDialog(si, "Package with given tracking number not found in the database.");
    			printMenu();
    		}
    	}
    	
    	SearchListener searchListener = new SearchListener();
    	
    	trackingNumberField.addActionListener(searchListener);
    	
    	JPanel buttonPanel = new JPanel(new FlowLayout());
    	JButton cancelButton = new JButton("Cancel");
    	JButton searchButton = new JButton("Search");
    	
    	cancelButton.setPreferredSize(MENU_BUTTON_SIZE);
    	searchButton.setPreferredSize(MENU_BUTTON_SIZE);
    	
    	searchButton.addActionListener(searchListener);
    	
    	cancelButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		printMenu();
        	}
        });
    	
    	inputPanel.add(label);
    	inputPanel.add(trackingNumberField);
    	
    	buttonPanel.add(cancelButton);
    	buttonPanel.add(searchButton);
    	
    	removePackagePanel.add(inputPanel, BorderLayout.NORTH);
    	removePackagePanel.add(buttonPanel, BorderLayout.SOUTH);
    	
    	si.getContentPane().removeAll();
    	si.add(removePackagePanel);
    	si.pack();
    	
    	si.repaint();
    }
    
    /**
     * Prints out a list of all users in the database.
     */
    public void showAllUsers() {
    	JPanel panel = new JPanel();
    	JTextArea packageList = new JTextArea();
    	JButton menuButton = new JButton("Main Menu");
    	
    	packageList.setEditable(false);
    	menuButton.setPreferredSize(MENU_BUTTON_SIZE);
    	menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	
        packageList.setText(ss.getAllUsersFormatted());
        
        panel.add(packageList);
        panel.add(menuButton);
        
        menuButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		printMenu();
        	}
        });
        
		si.getContentPane().removeAll();
		si.add(panel);
		si.pack();
		
		si.repaint();
    }
    
    /**
     * This method allows a new user to be added to the database.
     *
     */
    public void addNewUser() {
    	
    	JPanel addUserPanel = new JPanel();
    	
    	JRadioButton employeeButton = new JRadioButton("Employee");
    	employeeButton.setMnemonic(KeyEvent.VK_E);
    	employeeButton.setActionCommand("Employee");
    	
    	JRadioButton customerButton = new JRadioButton("Customer");
    	customerButton.setMnemonic(KeyEvent.VK_C);
    	customerButton.setActionCommand("Customer");
    	
    	ButtonGroup group = new ButtonGroup();
    	group.add(employeeButton);
    	group.add(customerButton);
    	
    	JButton confirmButton = new JButton("Create User");
    	
    	
    	class RadioListener implements ActionListener {
    		public void actionPerformed(ActionEvent event) {
    			if (group.getSelection().getActionCommand().equals("Employee")) {
    				addNewEmployee();
    			}
    			else {
    				addNewCustomer();
    			}
    		}
    	}
    	
    	RadioListener confirm = new RadioListener();
    	confirmButton.addActionListener(confirm);
    	
    	addUserPanel.add(employeeButton);
    	addUserPanel.add(customerButton);
    	addUserPanel.add(confirmButton);
    	
    	//===========================================
    	// Build frame
    	//===========================================
    	si.getContentPane().removeAll();
    	
    	si.add(addUserPanel);
    	si.pack();
    	si.repaint();
    }
    
    private void addNewEmployee() {
    	JPanel mainPane = new JPanel();
    	mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
    	
    	JPanel inputFields = new JPanel();
    	inputFields.setLayout(new GridLayout(0, 2));
    	
    	JPanel buttonPanel = new JPanel();
    	JButton cancelButton = new JButton("Cancel");
    	JButton updateButton = new JButton("Create User");
    	
    	JLabel firstNameLabel = new JLabel("First Name: ");
    	JLabel lastNameLabel = new JLabel("Last Name: ");
    	JLabel ssnLabel = new JLabel("Phone Number: ");
    	JLabel salaryLabel = new JLabel("Address: ");
    	JLabel bankNumberLabel = new JLabel("Bank Account Number: ");
    	
    	JTextField firstNameField = new JTextField(20);
    	JTextField lastNameField = new JTextField(20);
    	JTextField ssnField = new JTextField(20);
    	JTextField salaryField = new JTextField(20);
    	JTextField bankNumberField = new JTextField(20);
    	
    	cancelButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			printMenu();
    		}
    	});
    	
    	class UpdateButtonListener implements ActionListener {
    		private String firstName;
    		private String lastName;
    		private int ssn;
    		private float salary;
    		private int bankNumber;
    		
    		public void actionPerformed(ActionEvent event) {
    			firstName = firstNameField.getText();
    			lastName = lastNameField.getText();
    			
    			try {
    				ssn = Integer.parseInt(ssnField.getText());
    				
    				if (ssn < 100000000 || ssn > 999999999) {
    					JOptionPane.showMessageDialog(si, "Thst is not a 9-digit number!");
    					return;
    				}
    			} catch (NumberFormatException ex) {
    				JOptionPane.showMessageDialog(si, "Please enter the number without dashes");	// TODO: Logging
    				logger.log(Level.SEVERE, "SSN must not have hyphens.");
    				return;
    			}
    			
    			try {
    				salary = Float.parseFloat(ssnField.getText());
    				
    				if (salary <= 0f) {
    					JOptionPane.showMessageDialog(si, "Salary must be greater than 0!");	// TODO: Logging
    					logger.log(Level.SEVERE, "Salary must be greater than 0");
    					return;
    				}
    			} catch (NumberFormatException ex) {
    				JOptionPane.showMessageDialog(si, "Please enter monthly salary as a non-zero decimal number.");	// TODO: Logging
    				logger.log(Level.SEVERE, "Monthly salary must be non zero decimal number.");
    				return;
    			}
    			
    			try {
    				bankNumber = Integer.parseInt(ssnField.getText());
    				
    				if (bankNumber < 0) {
    					JOptionPane.showMessageDialog(si, "Bank account number must be greater than 0!");	// TODO: Logging
    					logger.log(Level.SEVERE, "Bank account must be greather than zero.");
    					return;
    				}
    			} catch (NumberFormatException ex) {
    				JOptionPane.showMessageDialog(si, "Please enter numeric characters only.");	// TODO: Logging
    				logger.log(Level.SEVERE, "Only numerical characters accepted.");
    				return;
    			}
    			
    			ss.addEmployee(firstName, lastName, ssn, salary, bankNumber);
    			JOptionPane.showMessageDialog(si, "New user successfully added!");	// TODO: Log this
    			logger.log(Level.INFO, "New user added!");
    			printMenu();
    		}
    	}
    	
    	UpdateButtonListener updateListen = new UpdateButtonListener();
    	updateButton.addActionListener(updateListen);
    	
    	buttonPanel.add(cancelButton);
    	buttonPanel.add(updateButton);
    	
    	inputFields.add(firstNameLabel);
    	inputFields.add(firstNameField);
    	inputFields.add(lastNameLabel);
    	inputFields.add(lastNameField);
    	inputFields.add(ssnLabel);
    	inputFields.add(ssnField);
    	inputFields.add(salaryLabel);
    	inputFields.add(salaryField);
    	inputFields.add(bankNumberLabel);
    	inputFields.add(bankNumberField);
    	
    	mainPane.add(inputFields);
    	mainPane.add(buttonPanel);
    	
    	si.getContentPane().removeAll();
    	
    	si.add(mainPane);
    	si.pack();
    	si.repaint();
    }
    
   	private void addNewCustomer() {
    	JPanel mainPane = new JPanel();
    	mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
    	
    	JPanel inputFields = new JPanel();
    	inputFields.setLayout(new GridLayout(0, 2));
    	
    	JPanel buttonPanel = new JPanel();
    	JButton cancelButton = new JButton("Cancel");
    	JButton updateButton = new JButton("Create User");
    	
    	JLabel firstNameLabel = new JLabel("First Name: ");
    	JLabel lastNameLabel = new JLabel("Last Name: ");
    	JLabel phoneLabel = new JLabel("Phone Number: ");
    	JLabel addressLabel = new JLabel("Address: ");
    	
    	JTextField firstNameField = new JTextField(20);
    	JTextField lastNameField = new JTextField(20);
    	JTextField phoneField = new JTextField(20);
    	JTextField addressField = new JTextField(20);
    	
    	cancelButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			printMenu();
    		}
    	});
    	
    	class UpdateButtonListener implements ActionListener {
    		private String firstName;
    		private String lastName;
    		private String phoneNumber;
    		private String address;
    		
    		public void actionPerformed(ActionEvent event) {
    			firstName = firstNameField.getText();
    			lastName = lastNameField.getText();
    			phoneNumber = phoneField.getText();
    			address = addressField.getText();
    			
    			ss.addCustomer(firstName, lastName, phoneNumber, address);
    			JOptionPane.showMessageDialog(si, "New user successfully added!");	//TODO: Log this
    			logger.log(Level.INFO, "New user added!");
    			printMenu();
    		}
    	}
    	
    	UpdateButtonListener updateListen = new UpdateButtonListener();
    	updateButton.addActionListener(updateListen);
    	
    	buttonPanel.add(cancelButton);
    	buttonPanel.add(updateButton);
    	
    	inputFields.add(firstNameLabel);
    	inputFields.add(firstNameField);
    	inputFields.add(lastNameLabel);
    	inputFields.add(lastNameField);
    	inputFields.add(phoneLabel);
    	inputFields.add(phoneField);
    	inputFields.add(addressLabel);
    	inputFields.add(addressField);
    	
    	mainPane.add(inputFields);
    	mainPane.add(buttonPanel);
    	
    	si.getContentPane().removeAll();
    	
    	si.add(mainPane);
    	si.pack();
    	si.repaint();
    }
    
    

    
    public void updateUser() {
    	JPanel idPanel = new JPanel();
    	JLabel enterIdLabel = new JLabel("Enter user ID: ");
    	JTextField idField = new JTextField(20);
    	
    	JButton cancelButton = new JButton("Cancel");
    	JButton updateButton = new JButton("Update User");
    	
    	cancelButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			printMenu();
    		}
    	});
    	
    	class IdListener implements ActionListener {
	    	int userId;
	    	
	    	public void actionPerformed(ActionEvent event) {
		    	try {
		    		userId = Integer.parseInt(idField.getText());
		    		
		    		if (!ss.userExists(userId)) {
		    			JOptionPane.showMessageDialog(si, "No user found with ID: " + userId);	// TODO: Logging
		    			logger.log(Level.SEVERE, "No user found");
		    			return;
		    		}
		    		
		    		if (ss.isCustomer(userId)) {
		    			updateCustomer(userId);
		    		}
		    		else {
		    			updateEmployee(userId);
		    		}
		    	} catch (NumberFormatException ex) {
		    		JOptionPane.showMessageDialog(si, "Please enter a proper user ID");	// TODO: Logging
		    		logger.log(Level.WARNING, "A proper user ID is needed.");
		    		return;
		    	}
	    	}
    	}
    	
    	IdListener idListen = new IdListener();
    	updateButton.addActionListener(idListen);
    	
    	idPanel.add(enterIdLabel);
    	idPanel.add(idField);
    	idPanel.add(cancelButton);
    	idPanel.add(updateButton);
    	
    	si.getContentPane().removeAll();
    	
    	si.add(idPanel);
    	si.pack();
    	si.repaint();
    }
    
    public void updateCustomer(int userId) {
    	JPanel mainPane = new JPanel();
    	mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
    	
    	JPanel inputFields = new JPanel();
    	inputFields.setLayout(new GridLayout(0, 2));
    	
    	JPanel buttonPanel = new JPanel();
    	JButton cancelButton = new JButton("Cancel");
    	JButton updateButton = new JButton("Update");
    	
    	JLabel firstNameLabel = new JLabel("First Name: ");
    	JLabel lastNameLabel = new JLabel("Last Name: ");
    	JLabel phoneLabel = new JLabel("Phone Number: ");
    	JLabel addressLabel = new JLabel("Address: ");
    	
    	JTextField firstNameField = new JTextField(20);
    	JTextField lastNameField = new JTextField(20);
    	JTextField phoneField = new JTextField(20);
    	JTextField addressField = new JTextField(20);
    	
    	cancelButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			printMenu();
    		}
    	});
    	
    	class UpdateButtonListener implements ActionListener {
    		private int userID;
    		private String firstName;
    		private String lastName;
    		private String phoneNumber;
    		private String address;
    		
    		UpdateButtonListener(int userId) {
    			this.userID = userId;
    		}
    		
    		public void actionPerformed(ActionEvent event) {
    			firstName = firstNameField.getText();
    			lastName = lastNameField.getText();
    			phoneNumber = phoneField.getText();
    			address = addressField.getText();
    			
    			ss.updateCustomer(userID, firstName, lastName, phoneNumber, address);
    			JOptionPane.showMessageDialog(si, "User information updated.");	// TODO: Log this
    			logger.log(Level.INFO, "User info updated.");
    			printMenu();
    		}
    	}
    	
    	UpdateButtonListener updateListen = new UpdateButtonListener(userId);
    	updateButton.addActionListener(updateListen);
    	
    	buttonPanel.add(cancelButton);
    	buttonPanel.add(updateButton);
    	
    	inputFields.add(firstNameLabel);
    	inputFields.add(firstNameField);
    	inputFields.add(lastNameLabel);
    	inputFields.add(lastNameField);
    	inputFields.add(phoneLabel);
    	inputFields.add(phoneField);
    	inputFields.add(addressLabel);
    	inputFields.add(addressField);
    	
    	mainPane.add(inputFields);
    	mainPane.add(buttonPanel);
    	
    	si.getContentPane().removeAll();
    	
    	si.add(mainPane);
    	si.pack();
    	si.repaint();
    }
    
    public void updateEmployee(int userId) {
    	JPanel mainPane = new JPanel();
    	mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
    	
    	JPanel inputFields = new JPanel();
    	inputFields.setLayout(new GridLayout(0, 2));
    	
    	JPanel buttonPanel = new JPanel();
    	JButton cancelButton = new JButton("Cancel");
    	JButton updateButton = new JButton("Update");
    	
    	JLabel firstNameLabel = new JLabel("First Name: ");
    	JLabel lastNameLabel = new JLabel("Last Name: ");
    	JLabel ssnLabel = new JLabel("Phone Number: ");
    	JLabel salaryLabel = new JLabel("Address: ");
    	JLabel bankNumberLabel = new JLabel("Bank Account Number: ");
    	
    	JTextField firstNameField = new JTextField(20);
    	JTextField lastNameField = new JTextField(20);
    	JTextField ssnField = new JTextField(20);
    	JTextField salaryField = new JTextField(20);
    	JTextField bankNumberField = new JTextField(20);
    	
    	cancelButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			printMenu();
    		}
    	});
    	
    	class UpdateButtonListener implements ActionListener {
    		private int userID;
    		private String firstName;
    		private String lastName;
    		private int ssn;
    		private float salary;
    		private int bankNumber;
    		
    		UpdateButtonListener(int userId) {
    			this.userID = userId;
    		}
    		
    		public void actionPerformed(ActionEvent event) {
    			firstName = firstNameField.getText();
    			lastName = lastNameField.getText();
    			
    			try {
    				ssn = Integer.parseInt(ssnField.getText());
    				
    				if (ssn < 100000000 || ssn > 999999999) {
    					JOptionPane.showMessageDialog(si, "Thst is not a 9-digit number!");
    					return;
    				}
    			} catch (NumberFormatException ex) {
    				JOptionPane.showMessageDialog(si, "Please enter the number without dashes");	// TODO: Logging
    				logger.log(Level.SEVERE, "SSN must not have dashes.");
    				return;
    			}
    			
    			try {
    				salary = Float.parseFloat(ssnField.getText());
    				
    				if (salary <= 0f) {
    					JOptionPane.showMessageDialog(si, "Salary must be greater than 0!");	// TODO: Logging
    					logger.log(Level.SEVERE, "Salary needs to be greater than 0.");
    					return;
    				}
    			} catch (NumberFormatException ex) {
    				JOptionPane.showMessageDialog(si, "Please enter monthly salary as a non-zero decimal number.");	// TODO: Logging
    				logger.log(Level.SEVERE, "Monthly salary must be non zero.");
    				return;
    			}
    			
    			try {
    				bankNumber = Integer.parseInt(ssnField.getText());
    				
    				if (bankNumber < 0) {
    					JOptionPane.showMessageDialog(si, "Bank account number must be greater than 0!");	// TODO: Logging
    					logger.log(Level.SEVERE, "Bank account number must be greater than 0");
    					return;
    				}
    			} catch (NumberFormatException ex) {
    				JOptionPane.showMessageDialog(si, "Please enter numeric characters only.");	// TODO: Logging
    				logger.log(Level.SEVERE, "Only numerical characters accepted.");
    				return;
    			}
    			
    			ss.updateEmployee(userID, firstName, lastName, ssn, salary, bankNumber);
    			JOptionPane.showMessageDialog(si, "User information updated.");
    			logger.log(Level.INFO, "User info updated.");
    			printMenu();
    		}
    	}
    	
    	UpdateButtonListener updateListen = new UpdateButtonListener(userId);
    	updateButton.addActionListener(updateListen);
    	
    	buttonPanel.add(cancelButton);
    	buttonPanel.add(updateButton);
    	
    	inputFields.add(firstNameLabel);
    	inputFields.add(firstNameField);
    	inputFields.add(lastNameLabel);
    	inputFields.add(lastNameField);
    	inputFields.add(ssnLabel);
    	inputFields.add(ssnField);
    	inputFields.add(salaryLabel);
    	inputFields.add(salaryField);
    	inputFields.add(bankNumberLabel);
    	inputFields.add(bankNumberField);
    	
    	mainPane.add(inputFields);
    	mainPane.add(buttonPanel);
    	
    	si.getContentPane().removeAll();
    	
    	si.add(mainPane);
    	si.pack();
    	si.repaint();
    }
    
    /**
     * This method is used to complete a package shipping/delivery transaction.
     *
     */
    public void deliverPackage() {
    	
    	Date currentDate = new Date(System.currentTimeMillis());
    	
    	JPanel mainPane = new JPanel();
    	mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
    	
    	JPanel inputFields = new JPanel();
    	inputFields.setLayout(new GridLayout(0, 2));
    	
    	JPanel buttonPanel = new JPanel();
    	JButton cancelButton = new JButton("Cancel");
    	JButton deliverButton = new JButton("Deliver");
    	
    	JLabel crustyLabel = new JLabel("Customer ID: ");
    	JLabel squidwardLabel = new JLabel("Employee ID: ");
    	JLabel trackingLabel = new JLabel("Tracking Number: ");
    	JLabel priceLabel = new JLabel("Shipping Cost: ");
    	
    	JTextField crustyField = new JTextField(20);
    	JTextField squidwardField = new JTextField(20);
    	JTextField trackingField = new JTextField(20);
    	JTextField priceField = new JTextField(20);
    	
    	cancelButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			printMenu();
    		}
    	});
    	
    	class DeliveryListener implements ActionListener {
    		private int customerID;
    		private int employeeID;
    		private String ptn;
    		private Date currentDate;
    		private Date deliveryDate;
    		private float price;
    		
    		public DeliveryListener(Date date) {
    			this.currentDate = date;
    			this.deliveryDate = date;
    		}
    		
    		public void actionPerformed(ActionEvent event) {
    			try {
    				customerID = Integer.parseInt(crustyField.getText());
    				
    				if (!ss.isCustomer(customerID)) {
    					JOptionPane.showMessageDialog(si, "Invalid Customer ID!");		// TODO: Log me baby
    					logger.log(Level.SEVERE, "Customer ID invalid.");
    					return;
    				}
    			} catch (NumberFormatException ex) {
    				JOptionPane.showMessageDialog(si, "Please enter numeric characters only.");		// TODO: Log it up
    				logger.log(Level.SEVERE, "Numerical characters only.");
    				return;
    			}
    			
    			try {
    				employeeID = Integer.parseInt(squidwardField.getText());
    				
    				if (!ss.isEmployee(employeeID)) {
    					JOptionPane.showMessageDialog(si, "Invalid Employee ID!");		// TODO: Log me baby
    					logger.log(Level.SEVERE, "Employee ID invalid.");
    					return;
    				}
    			} catch (NumberFormatException ex) {
    				JOptionPane.showMessageDialog(si, "Please enter numeric characters only.");		// TODO: Log it up
    				logger.log(Level.SEVERE, "Numerical characters only.");
    				return;
    			}
    			
    			ptn = trackingField.getText();
    			
    			if (ptn.length() != 5) {
    				JOptionPane.showMessageDialog(si, "Invalid tracking number!");		// TODO: New phone, log dis
    				logger.log(Level.SEVERE, "Tracking num invalid.");
    				return;
    			}
    			else if (!ss.packageExists(ptn)) {
    				JOptionPane.showMessageDialog(si, "Package not found!");		// TODO: Log me up, Scotty
    				logger.log(Level.SEVERE, "Package not able to be located.");
    				return;
    			}
    			
    			try {
    				price = Float.parseFloat(crustyField.getText());
    				
    				if (price < 0f) {
    					JOptionPane.showMessageDialog(si, "Price cannot be negaive!");		// TODO: Log me baby
    					logger.log(Level.SEVERE, "Price cannot be a negative number.");
    					return;
    				}
    			} catch (NumberFormatException ex) {
    				JOptionPane.showMessageDialog(si, "Please enter numeric characters only.");		// TODO: Log it up
    				logger.log(Level.SEVERE, "Numerical characters only.");
    				return;
    			}
    			
    			ss.addShippingTransaction(customerID, employeeID, ptn, currentDate, deliveryDate, price);
    			JOptionPane.showMessageDialog(si, "Package delivered!");
    			logger.log(Level.INFO, "Package has been delivered.");
    			printMenu();
    		}
    	}
    	
    	DeliveryListener deliverMe = new DeliveryListener(currentDate);
    	deliverButton.addActionListener(deliverMe);
    	
    	buttonPanel.add(cancelButton);
    	buttonPanel.add(deliverButton);
    	
    	inputFields.add(crustyLabel);
    	inputFields.add(crustyField);
    	inputFields.add(squidwardLabel);
    	inputFields.add(squidwardField);
    	inputFields.add(trackingLabel);
    	inputFields.add(trackingField);
    	inputFields.add(priceLabel);
    	inputFields.add(priceField);
    	
    	mainPane.add(inputFields);
    	mainPane.add(buttonPanel);
    	
    	si.getContentPane().removeAll();
    	
    	si.add(mainPane);
    	si.pack();
    	si.repaint();
    }
    
    
    /**
     * Prints out a list of all recorded transactions.
     */
    public void showAllTransactions() {
    	JPanel panel = new JPanel();
    	JTextArea packageList = new JTextArea();
    	JButton menuButton = new JButton("Main Menu");
    	
    	packageList.setEditable(false);
    	menuButton.setPreferredSize(MENU_BUTTON_SIZE);
    	menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	
    	if (ss.getAllTransactionsText() == "") {
    		packageList.setText("No recent transactions...");
    	}
    	
        
        panel.add(packageList);
        panel.add(menuButton);
        
        menuButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		printMenu();
        	}
        });
        
		si.getContentPane().removeAll();
		si.add(panel);
		si.pack();
		
		si.repaint();
    }

    
    private static void createAndShowUI() {
    	si = new JFrame("Shipping Store");
    	si.setLayout(new FlowLayout());
    	si.setMinimumSize(new Dimension(300, 100));
    	
    	try {
            // Set System Look & Feel
    		UIManager.setLookAndFeel(
    				UIManager.getSystemLookAndFeelClassName());
    	} 
    	catch (UnsupportedLookAndFeelException e) {
    		e.printStackTrace();
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	catch (InstantiationException e) {
    		e.printStackTrace();
    	}
    	catch (IllegalAccessException e) {
    		e.printStackTrace();
    	}
    	
    	
    	si.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		si.pack();
		si.setVisible(true);
    }
    
	
	/**
     * The main method of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			createAndShowUI();
    		}
    	});
    	
        MainApp app = new MainApp();
        app.runSoftware();
    }
    
}
