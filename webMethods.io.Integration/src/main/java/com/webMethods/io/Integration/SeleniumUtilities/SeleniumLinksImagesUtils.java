/**
 *  This class consists of links and images verification implementation.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.SeleniumUtilities;

import java.util.Base64; 
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;

public class SeleniumLinksImagesUtils 
{
	public static void verifyLinkWithBasicAuth(String url,String userName,String password,String description) 
	{
		int statusCode;

		try
		{
			if(!url.equalsIgnoreCase("javascript:void(0)")&&!url.equalsIgnoreCase("javascript:void(0);")&&!url.equalsIgnoreCase("mailto:support-flow@built.io")&&!url.equalsIgnoreCase("#/"))
			{
				URL link = new URL(url);
				HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();
				String encoded = Base64.getEncoder().encodeToString((userName+":"+password).getBytes(StandardCharsets.UTF_8));
				httpConn.setConnectTimeout(300000);
				httpConn.setRequestProperty("Authorization", "Basic "+encoded);
				httpConn.connect();

				statusCode = httpConn.getResponseCode();

				if(statusCode==200||statusCode==301)
				{
					ExtentTestManager.getTest().log(Status.PASS, "<span style='font-weight:bold;'>"+url+"</span>"+" is working");
				}
				else
				{
					ExtentTestManager.getTest().log(Status.FAIL,description+"<br>Response code is not 200 for "+url);					
				}
			}
		} 
		catch(Exception exception)
		{
			ExtentTestManager.getTest().log(Status.FAIL,description+"<br>Exception Caught!!! for verifyLinkWithBasicAuth() method from CommonActions class. "+exception.getLocalizedMessage());
		}
	}

	public static void verifyLinkWithOutBasicAuth(String url,String description) 
	{
		int statusCode;

		try
		{
			if(!url.equalsIgnoreCase("javascript:void(0)")&&!url.equalsIgnoreCase("javascript:void(0);")&&!url.equalsIgnoreCase("mailto:support-flow@built.io")&&!url.equalsIgnoreCase("#/"))
			{
				URL link = new URL(url);
				HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();
				httpConn.setConnectTimeout(300000);
				httpConn.connect();

				statusCode = httpConn.getResponseCode();

				if(statusCode==200||statusCode==301)
				{
					ExtentTestManager.getTest().log(Status.PASS, "<span style='font-weight:bold;'>"+url+"</span>"+" is working");
				}
				else
				{
					ExtentTestManager.getTest().log(Status.FAIL,description+"<br>Response code is not 200 for "+url);
				}
			}
		} 
		catch(Exception exception)
		{			
			ExtentTestManager.getTest().log(Status.FAIL,description+"<br>Exception Caught!!! for verifyLinkWithOutBasicAuth() method from CommonActions class. "+exception.getLocalizedMessage());
		}
	}

	public static void verifyLinks(WebDriver tempDriver, String description) 
	{
		int statusCode;

		List<WebElement> links = tempDriver.findElements(By.tagName("a"));

		for(int i=0;i<links.size();i++)
		{
			String url = links.get(i).getAttribute("href"); 

			try
			{
				if(!url.equalsIgnoreCase("javascript:void(0)")&&!url.equalsIgnoreCase("javascript:void(0);")&&!url.equalsIgnoreCase("mailto:support-flow@built.io")&&!url.equalsIgnoreCase("#/"))
				{
					URL link = new URL(url);
					HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();
					httpConn.setConnectTimeout(300000);
					httpConn.connect();
					statusCode = httpConn.getResponseCode();

					if(statusCode!=200)
					{
						ExtentTestManager.getTest().log(Status.FAIL,description+"<br>Response code is not 200 for "+url);
					}
					else
					{
						ExtentTestManager.getTest().log(Status.PASS, "<span style='font-weight:bold;'>"+url+"</span>"+" is working");
					}
				}
				else
				{
					ExtentTestManager.getTest().log(Status.FAIL,description+"<br>Invalid URL");
				}
			}
			catch(Exception exception)
			{
				ExtentTestManager.getTest().log(Status.FAIL,description+"<br>Exception Caught!!! for verifyLinks() method from CommonActions class. "+exception.getLocalizedMessage());
			}
		}
	}

	public static void verifyImages(WebDriver tempDriver, String description) 
	{
		int statusCode = 0;
		URL link = null;
		HttpURLConnection httpConn = null;

		List<WebElement> img = tempDriver.findElements(By.tagName("img"));

		for(int i=0;i<img.size();i++)
		{
			String src = img.get(i).getAttribute("src"); 

			if(!src.equalsIgnoreCase("javascript:void(0)")&&!src.equalsIgnoreCase("javascript:void(0);")&&!src.equalsIgnoreCase("mailto:support-flow@built.io")&&!src.equalsIgnoreCase("#/")&&!src.startsWith("http"))
			{
				try
				{
					link = new URL(src);
					httpConn = (HttpURLConnection)link.openConnection();
					httpConn.setConnectTimeout(300000);
					httpConn.connect();
					statusCode = httpConn.getResponseCode();
				}
				catch(IOException ioexception)
				{
					ExtentTestManager.getTest().log(Status.FAIL,description+"<br>Exception Caught!!! for verifyImages() method from CommonActions class. "+ioexception.getLocalizedMessage());
				}

				if(statusCode!=200)
				{
					ExtentTestManager.getTest().log(Status.FAIL,description+"<br>Response code is not 200 for "+link);
				}
				else
				{
					ExtentTestManager.getTest().log(Status.PASS, "<span style='font-weight:bold;'>Image with src "+src+"</span>"+" is working");
				}
			}
		}
	}
}