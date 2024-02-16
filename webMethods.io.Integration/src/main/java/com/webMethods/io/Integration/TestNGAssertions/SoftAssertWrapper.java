/**
 * Implementation for SoftAssertion wrapper to support test execution continuation after soft assertion fail and dependsOnMethods TestNG attribute.
 * 
 * @author altsh
 * @version 1.0
 * @since 21st August 2023 
 **/

package com.webMethods.io.Integration.TestNGAssertions;

import org.assertj.core.api.SoftAssertions;
import org.testng.ITestResult;

public class SoftAssertWrapper
{
	private SoftAssertions softAssertions = new SoftAssertions();

	private boolean continueOnFailure = false;

	/** HELPER METHOD TO INITIALIZE SOFTASSERTION WRAPPER **/
	public static SoftAssertWrapper initializingSoftAssertionWrapper(SoftAssertWrapper softassertwrapper)
	{
		return softassertwrapper = new SoftAssertWrapper();
	}

	/** HELPER METHOD TO COLLECT ALL ASSERT ALL **/
	public void assertAll() 
	{
		softAssertions.assertAll();

		if (!continueOnFailure) 
		{
			softAssertions = new SoftAssertions();
		}
	}

	/** HELPER METHOD TO GET SOFT ASSERTIONS **/
	public SoftAssertions getSoftAssertions() 
	{
		return softAssertions;
	}

	/** HELPER METHOD TO CONTINUE TEST EXECUTIONS ON FAILURES **/
	public void setContinueOnFailure(boolean continueOnFailure)
	{
		this.continueOnFailure = continueOnFailure;
	}

	/** HELPER METHOD TO CONTINUE TEST EXECUTIONS ON FAILURES **/
	public boolean shouldContinueOnFailure() 
	{
		return continueOnFailure;
	}

	/** HELPER METHOD TO GET CHECK AND VERIFY CONTINUE TEST BASED ON CONDITIONS **/
	public static boolean isContinueOnFailureSet(ITestResult result)
	{
		ContinueOnFailure annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(ContinueOnFailure.class);
		return annotation != null;
	}
}