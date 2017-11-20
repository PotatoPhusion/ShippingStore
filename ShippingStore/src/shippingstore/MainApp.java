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
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.*;

/**
* Main access point
*/
public class MainApp {
    
	private final Dimension MENU_BUTTON_SIZE = new Dimension(100, 35);
	
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
				deletePackage();	// TODO: Implement
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
				try {
					updateUser();	// TODO: Implement
				} catch (NumberFormatException ex) {
		            System.err.println("Input missmatch. Please Try again.");	// TODO: Error Logging and JOptionPane error message
				} catch (BadInputException ex) {
					System.err.println("Bad input. "+ex.getMessage());	// TODO: Error Logging and JOptionPane error message
					System.err.println("Please try again.");
				}
			}
		});
		
		
		deliverPackageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					deliverPackage();	// TODO: Implement
				} catch (InputMismatchException ex) {
		            System.err.println("Input missmatch. Please Try again.");	// TODO: Error Logging and JOptionPane error message
				} catch (BadInputException ex) {
					System.err.println("Bad input. "+ex.getMessage());	// TODO: Error Logging and JOptionPane error message
					System.err.println("Please try again.");
				}
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
    	JPanel cardBox = new JPanel();
    	cardBox.setLayout(new GridLayout(5, 2));
    	JPanel cardCrate = new JPanel();
    	cardCrate.setLayout(new GridLayout(5, 2));
    	JPanel cardDrum = new JPanel();
    	cardDrum.setLayout(new GridLayout(5, 2));
    	
    	class TypeLayoutListener implements ItemListener {
    		public void itemStateChanged(ItemEvent event) {
    			CardLayout cl = (CardLayout)(addPackagePanel.getLayout());
    			cl.show(addPackagePanel, (String)event.getItem());
    			si.repaint();
    		}
    	}
    	
    	JPanel comboBoxPane = new JPanel();
    	comboBoxPane.setLayout(new BoxLayout(comboBoxPane, BoxLayout.Y_AXIS));
        
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
        cardEnvelope.add(new JLabel("Tracking Number: "));
        cardBox.add(new JLabel("Tracking Number: "));
        cardCrate.add(new JLabel("Tracking Number: "));
        cardDrum.add(new JLabel("Tracking Number: "));
        
        JTextField trackingField1 = new JTextField(20);
        JTextField trackingField2 = new JTextField(20);
        JTextField trackingField3 = new JTextField(20);
        JTextField trackingField4 = new JTextField(20);
        
        cardEnvelope.add(trackingField1);
        cardBox.add(trackingField2);
        cardCrate.add(trackingField3);
        cardDrum.add(trackingField4);
        
        //=================================
        // Specification
        //=================================
        cardEnvelope.add(new JLabel("Specification: "));
        cardBox.add(new JLabel("Specification: "));
        cardCrate.add(new JLabel("Specification: "));
        cardDrum.add(new JLabel("Specification: "));
        
        JComboBox<String> specBox1 = new JComboBox<String>(specStrings);
        JComboBox<String> specBox2 = new JComboBox<String>(specStrings);
        JComboBox<String> specBox3 = new JComboBox<String>(specStrings);
        JComboBox<String> specBox4 = new JComboBox<String>(specStrings);
        
        cardEnvelope.add(specBox1);
        cardBox.add(specBox2);
        cardCrate.add(specBox3);
        cardDrum.add(specBox4);
        
        //=================================
        // Mailing Class
        //=================================
        cardEnvelope.add(new JLabel("Mailing Class: "));
        cardBox.add(new JLabel("Mailing Class: "));
        cardCrate.add(new JLabel("Mailing Class: "));
        cardDrum.add(new JLabel("Mailing Class: "));
        
        JComboBox<String> mcBox1 = new JComboBox<String>(mailClassStrings);
        JComboBox<String> mcBox2 = new JComboBox<String>(mailClassStrings);
        JComboBox<String> mcBox3 = new JComboBox<String>(mailClassStrings);
        JComboBox<String> mcBox4 = new JComboBox<String>(mailClassStrings);
        
        cardEnvelope.add(mcBox1);
        cardBox.add(mcBox2);
        cardCrate.add(mcBox3);
        cardDrum.add(mcBox4);
        
        //=================================
        // Envelope Details
        //=================================
        JTextField heightField = new JTextField(6);
        JTextField widthField = new JTextField(6);
        
        cardEnvelope.add(new JLabel("Height: "));
        cardEnvelope.add(heightField);
        cardEnvelope.add(new JLabel("Width: "));
        cardEnvelope.add(widthField);
        
        class EnvelopeListener implements ActionListener {
        	
        	private String ptn;
        	private String specification;
        	private String mailingClass;
        	private int height;
        	private int width;
        	
        	public void actionPerformed(ActionEvent event) {
        		ptn = trackingField1.getText();
        		JOptionPane.showMessageDialog(si, ptn);
        		
        		if (ptn.length() != 5) {
        			JOptionPane.showMessageDialog(si, "Tracking Number must be 5 characters long");	// TODO: Logging
        			return;
        		}
        		else if (ss.packageExists(ptn)) {
        			JOptionPane.showMessageDialog(si, "Tracking Number already in database");	// TODO: Logging
        			return;
        		}
        		
        		try {
        			height = Integer.parseInt(heightField.getText());
        			
        			if (height < 0) {
        				JOptionPane.showMessageDialog(si, "Height must be positive!");	// TODO: Logging
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Height must be an integer!");	// TODO: Logging
        			return;
        		}
        		
        		try {
        			width = Integer.parseInt(widthField.getText());
        			
        			if (width < 0) {
        				JOptionPane.showMessageDialog(si, "Width must be positive!");	// TODO: Logging
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Width must be an integer!");	// TODO: Logging
        			return;
        		}
        		
        		specification = (String)specBox1.getSelectedItem();
        		mailingClass = (String)mcBox1.getSelectedItem();
        		
        		ss.addEnvelope(ptn, specification, mailingClass, height, width);
        		
        		JOptionPane.showMessageDialog(si, "Package successfully added!");	// TODO: Logging
        		printMenu();
        	}
        }
        
        //=================================
        // Box Details
        //=================================
        JTextField dimensionField = new JTextField(6);
        JTextField volumeField = new JTextField(6);
        
        cardBox.add(new JLabel("Largest Dimension: "));
        cardBox.add(dimensionField);
        cardBox.add(new JLabel("Volume: "));
        cardBox.add(volumeField);
        
        class BoxListener implements ActionListener {
        	
        	private String ptn;
        	private String specification;
        	private String mailingClass;
        	private int largestDim;
        	private int volume;
        	
        	public void actionPerformed(ActionEvent event) {
        		ptn = trackingField2.getText();
        		System.out.println(ptn);
        		
        		if (ptn.length() != 5) {
        			JOptionPane.showMessageDialog(si, "Tracking Number must be 5 characters long");	// TODO: Logging
        			return;
        		}
        		else if (ss.packageExists(ptn)) {
        			JOptionPane.showMessageDialog(si, "Tracking Number already in database");	// TODO: Logging
        			return;
        		}
        		
        		try {
        			largestDim = Integer.parseInt(dimensionField.getText());
        			
        			if (largestDim < 0) {
        				JOptionPane.showMessageDialog(si, "Largest Dimension must be positive!");	// TODO: Logging
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Largest Dimension must be an integer!");	// TODO: Logging
        			return;
        		}
        		
        		try {
        			volume = Integer.parseInt(volumeField.getText());
        			
        			if (volume < 0) {
        				JOptionPane.showMessageDialog(si, "Volume must be positive!");	// TODO: Logging
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Volume must be an integer!");	// TODO: Logging
        			return;
        		}
        		
        		specification = (String)specBox1.getSelectedItem();
        		mailingClass = (String)mcBox1.getSelectedItem();
        		
        		ss.addBox(ptn, specification, mailingClass, largestDim, volume);
        		
        		JOptionPane.showMessageDialog(si, "Package successfully added!");	// TODO: Logging
        		printMenu();
        	}
        }
        
        //=================================
        // Crate Details
        //=================================
        JTextField loadField = new JTextField(6);
        JTextField contentField = new JTextField(6);
        
        cardCrate.add(new JLabel("Max Load Weight: "));
        cardCrate.add(loadField);
        cardCrate.add(new JLabel("Content: "));
        cardCrate.add(contentField);
        
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
        			return;
        		}
        		else if (ss.packageExists(ptn)) {
        			JOptionPane.showMessageDialog(si, "Tracking Number already in database");	// TODO: Logging
        			return;
        		}
        		
        		try {
        			loadWeight = Float.parseFloat(loadField.getText());
        			
        			if (loadWeight < 0) {
        				JOptionPane.showMessageDialog(si, "Load Weight must be positive!");	// TODO: Logging
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Load Weight must be a decimal number!");	// TODO: Logging
        			return;
        		}
        		
        		specification = (String)specBox1.getSelectedItem();
        		mailingClass = (String)mcBox1.getSelectedItem();
        		content = contentField.getText();
        		
        		ss.addCrate(ptn, specification, mailingClass, loadWeight, content);
        		
        		JOptionPane.showMessageDialog(si, "Package successfully added!");	// TODO: Logging
        		printMenu();
        	}
        }
        
        //=================================
        // Drum Details
        //=================================        
        JTextField materialField = new JTextField(6);
        JTextField diameterField = new JTextField(6);
        
        cardDrum.add(new JLabel("Drum Material: "));
        cardDrum.add(materialField);
        cardDrum.add(new JLabel("Diameter: "));
        cardDrum.add(diameterField);
        
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
        			return;
        		}
        		else if (ss.packageExists(ptn)) {
        			JOptionPane.showMessageDialog(si, "Tracking Number already in database");	// TODO: Logging
        			return;
        		}
        		
        		try {
        			diameter = Float.parseFloat(volumeField.getText());
        			
        			if (diameter < 0) {
        				JOptionPane.showMessageDialog(si, "Diameter must be positive!");	// TODO: Logging
        				return;
        			}
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(si, "Diameter must be a decimal number");	// TODO: Logging
        			return;
        		}
        		
        		material = materialField.getText();
        		specification = (String)specBox1.getSelectedItem();
        		mailingClass = (String)mcBox1.getSelectedItem();
        		
        		ss.addDrum(ptn, specification, mailingClass, material, diameter);
        		
        		JOptionPane.showMessageDialog(si, "Package successfully added!");	// TODO: Logging
        		printMenu();
        	}
        }
        
        //=================================
        // Frame Setup
        //=================================
        cardEnvelope.add(addButton1);
    	cardEnvelope.add(cancelButton1);
    	cardBox.add(addButton2);
    	cardBox.add(cancelButton2);
    	cardCrate.add(addButton3);
    	cardCrate.add(cancelButton3);
    	cardDrum.add(addButton4);
    	cardDrum.add(cancelButton4);
        
        EnvelopeListener envListen = new EnvelopeListener();
        BoxListener boxListen = new BoxListener();
        CrateListener crateListen = new CrateListener();
        DrumListener drumListen = new DrumListener();
        
        addButton1.addActionListener(envListen);
        addButton2.addActionListener(boxListen);
        addButton3.addActionListener(crateListen);
        addButton4.addActionListener(drumListen);
        
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
     * This method allows the user to enter a new package to the list
     * database.
     * @throws shippingstore.BadInputException bad input
     */
    /*
    public void addNewPackage() throws BadInputException {
    	
    	int packageType = 1;	// TODO: don't forget to delet this
    	
    	JPanel addPackagePanel = new JPanel(new CardLayout());
    	
    	JPanel cardEnvelope = new JPanel();
    	JPanel cardBox = new JPanel();
    	JPanel cardCrate = new JPanel();
    	JPanel cardDrum = new JPanel();
    	
    	class TypeLayoutListener implements ItemListener {
    		public void itemStateChanged(ItemEvent event) {
    			CardLayout cl = (CardLayout)(addPackagePanel.getLayout());
    			cl.show(addPackagePanel, (String)event.getItem());
    		}
    	}
    	
    	JPanel comboBoxPane = new JPanel();
    	
    	String[] typeStrings = { "Envelope", "Box", "Crate", "Drum" };
        
        JComboBox<String> typeList = new JComboBox<String>(typeStrings);
        typeList.setSelectedIndex(0);
        typeList.setEditable(false);
        TypeLayoutListener cardListener = new TypeLayoutListener();
        typeList.addItemListener(cardListener);
        
        
        JLabel trackingNumLabel = new JLabel("Tracking Number: ");
        JTextField trackNum = new JTextField(20);
        
        trackingNumLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        System.out.println("\nEnter tracking number (string): ");
        String ptn = sc.nextLine();
        if (ptn.length() > 5) {
            throw new BadInputException("Tracking number should not be more that 5 characters long.");
        }

        if (ss.packageExists(ptn)) {
            System.out.println("\nPackage with the same tracking number exists, try again");
            return;
        }
        
        String[] specificationStrings = { "Fragile", "Books", "Catalogs", "Do-not-bend", "N/A" };
        
        JComboBox<String> specificationList = new JComboBox<String>(specificationStrings);
        typeList.setSelectedIndex(0);
        typeList.setEditable(false);
        
        System.out.println("\nEnter Specification: Fragile, Books, Catalogs, Do-not-bend, or N/A");
        String specification = sc.nextLine();
        boolean correct = false;

        correct = specification.equalsIgnoreCase("Fragile") || specification.equalsIgnoreCase("Books") || specification.equalsIgnoreCase("Catalogs");
        correct = correct || specification.equalsIgnoreCase("Do-not-bend") || specification.equalsIgnoreCase("N/A");

        if (!(correct)) {
            throw new BadInputException("Specifications can only be one of the following: Fragile, Books, Catalogs, Do-not-bend, or N/A");
        }

        System.out.println("\nEnter mailing class can be First-Class, Priority, Retail, Ground, or Metro.");
        String mailingClass = sc.nextLine();

        correct = mailingClass.equalsIgnoreCase("First-Class") || mailingClass.equalsIgnoreCase("Priority") || mailingClass.equalsIgnoreCase("Retail");
        correct = correct || mailingClass.equalsIgnoreCase("Ground") || mailingClass.equalsIgnoreCase("Metro");
        if (!(correct)) {
            throw new BadInputException("Specifications can only be one of the following: Fragile, Books, Catalogs, Do-not-bend, or N/A");
        }

        if (packageType == 1) {
            System.out.println("\nEnter height (inch), (int): ");
            int height = 0;
            if (sc.hasNextInt()) {
                height = sc.nextInt();
                sc.nextLine();
                if (height < 0) {
                    throw new BadInputException("Height of Envelope cannot be negative.");
                }
            } else {
                sc.nextLine();
                throw new BadInputException("Height of Envelope is integer.");
            }

            int width = 0;
            System.out.println("\nEnter width (inch), (int): ");
            if (sc.hasNextInt()) {
                width = sc.nextInt();
                sc.nextLine();
                if (width < 0) {
                    throw new BadInputException("Width of Envelope cannot be negative.");
                }
            } else {
                sc.nextLine();
                throw new BadInputException("Width of Envelope is integer.");
            }
            
            ss.addEnvelope(ptn, specification, mailingClass, height, width);

        } else if (packageType == 2) {
            System.out.println("\nEnter largest dimension (inch), (int): ");

            int dimension = 0;
            if (sc.hasNextInt()) {
                dimension = sc.nextInt();
                sc.nextLine();
                if (dimension < 0) {
                    throw new BadInputException("Largest dimension of Box cannot be negative.");
                }
            } else {
                sc.nextLine();
                throw new BadInputException("Dimension should be integer.");
            }

            System.out.println("\nEnter volume (inch^3), (int): ");

            int volume = 0;
            if (sc.hasNextInt()) {
                volume = sc.nextInt();
                sc.nextLine();
                if (volume < 0) {
                    throw new BadInputException("Volume of Box cannot be negative.");
                }
            } else {
                sc.nextLine();
                throw new BadInputException("Volume should be integer.");
            }

            ss.addBox(ptn, specification, mailingClass, dimension, volume);

        } else if (packageType == 3) {
            System.out.println("\nEnter maximum load weight (lb), (float): ");

            float weight = 0.0f;
            if (sc.hasNextFloat()) {
                weight = sc.nextFloat();
                sc.nextLine();
                if (weight < 0.0f) {
                    throw new BadInputException("Maximum load weight of Crate cannot be negative.");
                }
            } else {
                sc.nextLine();
                throw new BadInputException("Max load should be float");
            }

            System.out.println("\nEnter content (string): ");
            String content = sc.nextLine();

            ss.addCrate(ptn, specification, mailingClass, weight, content);
           
        } else if (packageType == 4) {

            System.out.println("\nEnter material (Plastic / Fiber): ");
            String material = sc.nextLine();
            if (!(material.equalsIgnoreCase("Plastic") || material.equalsIgnoreCase("Fiber"))) {
                throw new BadInputException("Material of Drum can only be plastic or fiber.");
            }

            float diameter = 0.0f;
            System.out.println("\nEnter diameter (float): ");
            if (sc.hasNextFloat()) {
                diameter = sc.nextFloat();
                sc.nextLine();
                if (diameter < 0.0f) {
                    throw new BadInputException("Diameter of Drum cannot be negative.");
                }
            } else {
                sc.nextLine();
                throw new BadInputException("Diameter should be float");
            }

            ss.addDrum(ptn, specification, mailingClass, material, diameter);
            
        } else {
            System.out.println("Unknown package type entered. Please try again.");
        }
        
        addPackagePanel.add(cardEnvelope, "Envelope");
        addPackagePanel.add(cardBox, "Box");
        addPackagePanel.add(cardCrate, "Crate");
        addPackagePanel.add(cardDrum, "Drum");
        
        si.getContentPane().removeAll();
		si.add(addPackagePanel);
		si.pack();
		
		si.repaint();
    }
    */
    
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
        sc.nextLine();
        System.out.print("\nEnter tracking number of package to search for (string): ");
        String ptn = sc.nextLine();

        if (ss.packageExists(ptn))
            System.out.println(ss.getPackageFormatted(ptn));
        else
            System.out.println("Package with PTN " + ptn + " not found in the database");
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
        // Add fields for new user
        int userType = 0;
        boolean check = false;

        while (!check) {
            System.out.println("Select user type:\n"
                    + "1. Customer\n"
                    + "2. Employee");

            if (sc.hasNextInt()) {
                userType = sc.nextInt();

                if (userType < 1 || userType > 2) {
                    System.out.println("Wrong integer value: choose 1 or 2");
                } else {
                    check = true;
                }
            } else {
                System.out.println("Please select 1 or 2");
            }
        }

        sc.nextLine();
        System.out.println("\nEnter first name (string): ");
        String firstName = sc.nextLine();

        System.out.println("\nEnter last name (string): ");
        String lastName = sc.nextLine();

        if (userType == 1) {
            System.out.println("\nEnter phone number (string): ");
            String phoneNumber = sc.nextLine();

            System.out.println("\nEnter address (string): ");
            String address = sc.nextLine();

            ss.addCustomer(firstName, lastName, phoneNumber, address);

        } else if (userType == 2) {

            check = false;
            float monthlySalary = 0.0f;

            while (!check) {

                System.out.println("\nEnter monthly salary (float): ");

                if (sc.hasNextFloat()) {
                    monthlySalary = sc.nextFloat();
                    if (monthlySalary < 0.0f) {
                        System.out.println("Monthly salary cannot be negative.");
                    } else {
                        check = true;
                    }
                    sc.nextLine();

                } else {
                    System.out.println("Please enter monthly salary as a non-zero float value.");
                    sc.nextLine();
                }
            }

            int ssn = 0;
            check = false;
            while (!check) {

                System.out.println("\nEnter SSN (9-digital int): ");
                if (sc.hasNextInt()) {
                    ssn = sc.nextInt();
                    if (String.valueOf(ssn).length() != 9) {
                        System.out.println("\nThat is not a nine digit number");
                    } else if (ssn < 10000000 || ssn > 999999999) {
                        System.out.println("\nGive a correct 9 digit integer");
                    } else {
                        check = true;
                    }
                    sc.nextLine();
                } else {
                    System.out.println("\nNot a number!");
                    sc.nextLine();
                } //end if
            }// end while

            check = false;
            int bankAccNumber = 0;
            while (!check) {
                System.out.println("\nEnter bank account number (int): ");
                if (sc.hasNextInt()) {
                    bankAccNumber = sc.nextInt();
                    if (bankAccNumber < 0) {
                        System.out.println("\nBank account cannot be negative");
                    } else {
                        check = true;
                    }
                    sc.nextLine();
                } else {
                    System.out.println("Invalid bank Account format, please try again");
                    sc.nextLine();
                }

            }//end while

            ss.addEmployee(firstName, lastName, ssn, monthlySalary, bankAccNumber);
        } else {
            System.out.println("Unknown user type. Please try again.");
        }

    }
    
    /**
     * This method can be used to update a user's information, given their user
     * ID.
     *
     * @throws shippingstore.BadInputException
     */
    public void updateUser() throws BadInputException {
        boolean check = false;
        System.out.print("\nEnter user ID: ");
        int userID = sc.nextInt();

        if (!ss.userExists(userID)) {
            System.out.println("User not found.");
            return;
        }

        sc.nextLine();
        System.out.print("\nEnter first name (string): ");
        String firstName = sc.nextLine();

        System.out.print("\nEnter last name (string): ");
        String lastName = sc.nextLine();

        if (ss.isCustomer(userID)) {
            System.out.print("\nEnter phone number (string): ");
            String phoneNumber = sc.nextLine();
            System.out.print("\nEnter address (string): ");
            String address = sc.nextLine();
            
            ss.updateCustomer(userID, firstName, lastName, phoneNumber, address);

        } else { //User is an employee

            float monthlySalary = 0.0f;
            check = false;
            while (!check) {

                System.out.println("\nEnter monthly salary (float): ");

                if (sc.hasNextFloat()) {
                    monthlySalary = sc.nextFloat();
                    if (monthlySalary < 0.0f) {
                        new BadInputException("Monthly salary cannot be negative.");
                    } else {
                        check = true;
                    }
                    sc.nextLine();
                } else {
                    System.out.println("Please enter monthly salary as a non-zero float value.");
                    sc.nextLine();
                }
            }

            int ssn = 0;
            check = false;
            while (!check) {

                System.out.println("\nEnter SSN (9-digital int): ");
                if (sc.hasNextInt()) {
                    ssn = sc.nextInt();
                    if (String.valueOf(ssn).length() != 9) {
                        new BadInputException("\nThat is not a nine digit number");
                    } else if (ssn < 10000000 || ssn > 999999999) {
                        new BadInputException("\nGive a correct 9 digit integer");

                    } else {
                        check = true;
                    }
                } //end if
                sc.nextLine();

            }// end while

            int bankAccNumber = 0;
            check = false;
            while (!check) {
                System.out.println("\nEnter bank account number (int): ");

                if (sc.hasNextInt()) {
                    bankAccNumber = sc.nextInt();
                    if (bankAccNumber < 0) {
                        new BadInputException("Bank account cannot be negative");
                    } else {
                        check = true;
                    }
                    sc.nextLine();
                } else {
                    System.out.println("Invalid bank Account format, please try again");
                    sc.nextLine();
                }
            } //end while

            ss.updateEmployee(userID, firstName, lastName, ssn, monthlySalary, bankAccNumber);
        }
    }
    
    /**
     * This method is used to complete a package shipping/delivery transaction.
     *
     * @throws shippingstore.BadInputException
     */
    public void deliverPackage() throws BadInputException {

        Date currentDate = new Date(System.currentTimeMillis());

        sc.nextLine();
        System.out.println("\nEnter customer ID (int): ");
        int customerId = sc.nextInt();
        //Check that the customer exists in database
        boolean customerExists = ss.userExists(customerId);

        if (!customerExists) {
            System.out.println("\nThe customer ID you have entered does not exist in the database.\n"
                    + "Please add the customer to the database first and then try again.");
            return;
        }

        System.out.println("\nEnter employee ID (int): ");

        int employeeId = 0;
        if (sc.hasNextInt()) {
            employeeId = sc.nextInt();
        }
        //Check that the employee exists in database
        boolean employeeExists = ss.userExists(employeeId);

        if (!employeeExists) {
            System.out.println("\nThe employee ID you have entered does not exist in the database.\n"
                    + "Please add the employee to the database first and then try again.");
            return;
        }

        sc.nextLine();
        System.out.println("\nEnter tracking number (string): ");
        String ptn = sc.nextLine();

        //Check that the package exists in database
        if (!ss.packageExists(ptn)) {
            System.out.println("\nThe package with the tracking number you are trying to deliver "
                    + "does not exist in the database. Aborting transaction.");
            return;
        }

        System.out.println("\nEnter price (float): ");
        float price = sc.nextFloat();
        if (price < 0.0f) {
            throw new BadInputException("Price cannot be negative.");
        }

        ss.addShppingTransaction(customerId, employeeId, ptn, currentDate, currentDate, price);
        ss.deletePackage(ptn);

        System.out.println("\nTransaction Completed!");
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
    	
    	//si.setSize(400, 400);
    	
    	si.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//si.pack();
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
