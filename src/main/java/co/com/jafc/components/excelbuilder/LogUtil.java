package co.com.jafc.components.excelbuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class LogUttil
 * @description
 * @date 14/04/2014
 */
public class LogUtil {
	
	private static Logger logger;
	
	static {
		logger = Logger.getLogger("SGP");
		logger.setLevel(Level.ALL);

	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014
	 * @description 
	 * @param className
	 * @param message
	 * @param level
	 * @param throwable
	 */
	public static void log(String className, String message, Level level, Throwable throwable) {
		logger.logp(level, className, null, message, throwable);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014
	 * @description 
	 * @return Logger : log4j.Logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}
