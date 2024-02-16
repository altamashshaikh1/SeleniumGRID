/**
 *  This class consists all selenium wait constants required to store and retrieve in explicit waits..
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.Constants;

import org.openqa.selenium.Dimension;

public class TestDataConstants 
{
	public static final int WIDTH = 1920;

	public static final int HEIGHT = 1080;

	public static final String ALPHA_NUMERIC_STRING = "abcdefgHijklmnoPqrstuvwxyz0123456789";

	public static int totalSuccessTests = 0;

	public static int totalFailureTests = 0;

	public static int totalSkipTests = 0;
	
	public static int totalTestCount = 0;

	public static final Dimension DIMENSIONS = new Dimension(TestDataConstants.WIDTH,TestDataConstants.HEIGHT);
}