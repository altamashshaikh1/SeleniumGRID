/**
 * This class includes Log4j v2.x implementation used in helper methods in overall framework.
 * 
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.Logger4jUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil 
{
	private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

	/** HELPER METHOD TO LOG INFO DATA **/
	public static final void INFO(String message)
	{
		logger.info(message);
	}

	/** HELPER METHOD TO LOG WARN DATA **/
	public static final void WARN(String message) 
	{
		logger.warn(message);
	}

	/** HELPER METHOD TO LOG ERROR DATA **/
	public static final void ERROR(String message) 
	{
		logger.error(message);
	}

	/** HELPER METHOD TO LOG ERROR DATA **/
	public static final void ERROR(String message, Throwable throwable) 
	{
		logger.error(message, throwable);
	}

	/** HELPER METHOD TO LOG DEBUG DATA **/
	public static void DEBUG(String message)
	{
		logger.debug(message);
	}
}