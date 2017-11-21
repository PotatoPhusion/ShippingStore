package shippingstore;

import java.io.IOException;
import java.util.logging.*;

/**
 * 
 * @author Rafael Reza
 *
 * Was supposed to write all the logging statements in the MainApp
 * to a designated output file but has not been playing nice :/
 * 
 */

public class LoggerClass {
	private final static  Logger logger = Logger.getLogger(LoggerClass.class.getName());
	private static FileHandler fh = null;
	
	public static void init() {
		try {
			fh = new FileHandler("LoggingInfo.log", false);
			}
		catch (SecurityException | IOException e) {
				e.printStackTrace();
			}
		fh.setLevel(Level.CONFIG);
		fh.setFormatter(new SimpleFormatter());
		logger.addHandler(fh);
	}	

	public static void main (String[] args) {
		
	LoggerClass.init();
	
	logger.setLevel(Level.FINER);
	logger.log(Level.INFO, "Program startup.");
	logger.log(Level.INFO, "Program end.");
	//MainApp.addNewPackage();
	//MainApp.EnvelopeListener();
	//MainApp.CrateListener();
	//MainApp.BoxListener();
	//MainApp.DrumListener();
	}
}
