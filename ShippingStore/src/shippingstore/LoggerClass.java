package shippingstore;

import java.io.IOException;
import java.util.logging.*;

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
	logger.log(Level.INFO, "message balls fireass");
	logger.log(Level.SEVERE, "message big");
	ShippingStore.test();
	
	
	}
}
