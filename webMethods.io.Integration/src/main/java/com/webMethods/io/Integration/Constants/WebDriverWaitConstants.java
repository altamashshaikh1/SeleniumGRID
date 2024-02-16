/**
 *  This class consists all constants for explicit waits
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.Constants;

public class WebDriverWaitConstants 
{
	/**
	 ** PageLoad Timeout: The pageLoadTimeout is used to define how long the WebDriver instance should wait for a page to load completely. 
	 ** This is essential for ensuring that the initial page load completes within a reasonable time.
	 **
	 ** PRIRORITY : 1
	 ** RECOMMENDED TIMEOUT VALUE : AROUND 30 sec TO 40 sec
	 **/
	public static final long PAGE_LOAD_TIMEOUT_IN_SECONDS = 40; 
	public static final long DOM_RENDER_TIMEOUT_IN_SECONDS = 30;

	/**
	 ** Script Load Timeout: The setScriptTimeout specifies the amount of time WebDriver should wait for asynchronous scripts to load. 
	 ** This can be important for applications heavily relying on AJAX or dynamic content.
	 **
	 ** PRIRORITY : 2
	 ** RECOMMENDED TIMEOUT VALUE : AROUND 15 sec TO 20 sec
	 **/
	public static final long SCRIPT_TIMEOUT_IN_SECONDS = 15;


	/**
	 ** Implicit Waits: Implicit waits instruct WebDriver to wait a certain amount of time before throwing an exception when trying to locate an element. 
	 ** Implicit waits are applied globally to all elements.
	 **
	 ** PRIRORITY : 3
	 ** RECOMMENDED TIMEOUT VALUE : AROUND 5 sec TO 10 sec 
	 **/
	public static final long IMPLICIT_WAIT_TIME_IN_SECONDS = 10;

	/**
	 ** Explicit Waits for Elements (Based on Expected Conditions): Explicit waits using ExpectedConditions are highly targeted and should be used for specific elements
	 ** where you know a certain condition will eventually be satisfied (e.g., element visibility, presence, etc.).
	 **
	 ** PRIRORITY : 4
	 ** RECOMMENDED TIMEOUT VALUE : AROUND 10 sec TO 20 sec 
	 **/   
	public static final long WAIT_FOR_ELEMENT_TIMEOUT_IN_SECONDS = 160;
	public static final long WAIT_FOR_ELEMENT_NOTVISIBLE_TIMEOUT_IN_SECONDS = 160;

	/**
	 ** Fluent Waits: Fluent waits provide more flexibility by allowing you to set the polling interval. 
	 ** Use this when you need to wait for an element that might take a bit longer to appear but doesn't need the precision of explicit waits.
	 **
	 ** PRIRORITY : 5
	 **	RECOMMENDED TIMEOUT VALUE : AROUND [5 sec TO 10 sec] OR [10 sec TO 20 sec]. POLLING INTERVAL CAN BE SET AROUND 500 milliseconds. 
	 **/
	public static final long FLUENT_WAIT_TIME_IN_SECONDS = 20;
	public static final long POLLING_TIME_IN_SECONDS = 500;
}