package com.webMethods.io.Integration.Listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class RetryListener implements ITestListener
{
	private int maxRetryCount = 2;
	private int retryCount = 0;

	@Override
	public void onTestFailure(ITestResult result) 
	{
		if (retryCount < maxRetryCount) 
		{
			System.out.println("Retrying test " + result.getName() + " with status " + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
			retryCount++;
			result.setStatus(ITestResult.FAILURE);
		} 
		else 
		{
			retryCount = 0;
			result.setStatus(ITestResult.FAILURE);
		}
	}

	private String getResultStatusName(int status) 
	{
		switch (status) 
		{
		case ITestResult.SUCCESS:
			return "SUCCESS";
		case ITestResult.FAILURE:
			return "FAILURE";
		case ITestResult.SKIP:
			return "SKIP";
		default:
			return "UNKNOWN";
		}
	}

	// Implement other methods of the ITestListener interface if needed
	// For example: onTestStart, onTestSuccess, onTestSkipped, etc.
}