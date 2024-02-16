/**
 *  USED FOR DYNAMIC TEST DATA GENERATION IMPLEMENTATION THROUGH OUT THE FRAMEWORK EXECUTION LIFE CYCLE.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.TestDataCommonFeatures;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonFailureHandling;

public class ProjectCRUDOperations
{
	/** HELPER METHOD CREATE NEW PROJECT **/
	public static String CREATE_NEW_PROJECT(String tenantBaseURL, String authtoken, String cookie, String jsonPayload)
	{
		String newProjectResponse = null;

		URL obj;
		HttpURLConnection httpurlconnection;
		//DataOutputStream dataopstream;

		try
		{
			obj = new URL(tenantBaseURL+"/enterprise/v1/projects");
			httpurlconnection = (HttpURLConnection) obj.openConnection();

			// Setting basic post request
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.setRequestProperty("Content-Type", "application/json");
			httpurlconnection.setRequestProperty("authtoken", authtoken);
			httpurlconnection.setRequestProperty("Cookie", cookie);

			// Send post request
			/*httpurlconnection.setDoOutput(true);
			dataopstream = new DataOutputStream(httpurlconnection.getOutputStream());
			dataopstream.writeBytes(jsonPayload);
			dataopstream.flush();
			dataopstream.close();*/

			// Get the output stream to write the JSON data
			try ( OutputStream os = httpurlconnection.getOutputStream(); OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8") ) 
			{
				osw.write(jsonPayload);
				osw.flush();
			}

			if(httpurlconnection.getResponseCode()==200||httpurlconnection.getResponseCode()==201)
			{
				ExtentTestManager.getTest().pass("Create new project API."+" : "+httpurlconnection.getResponseCode()+" - "+httpurlconnection.getResponseMessage());
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateTest("Create new project API.: "+httpurlconnection.getResponseCode()+" - "+httpurlconnection.getResponseMessage());
			}
		}
		catch(Exception exception)
		{

			CommonExceptionHandling.captureExceptionAndTerminateTest("While resquests which created maually.",exception.getMessage());
		}

		return newProjectResponse;

	}		

	/** HELPER METHOD TO UPDATE PROJECT **/

	/** HELPER METHOD TO GET SINGLE PROJECT **/

	/** HELPER METHOD TO GET ALL PROJECTS LIST **/

	/** HELPER METHOD TO DELETE PROJECT **/

	/** HELPER METHOD TO GET PROJECT UID **/
	public static String getProjectUID(String responseBody)
	{
		String projectUID = null;

		try
		{
			JSONObject myResponse = new JSONObject(responseBody);
			JSONObject outputData = myResponse.getJSONObject("output");
			projectUID = outputData.getString("uid");
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Fetch project uid key value from response body. ",exception.getMessage());
		}

		return projectUID;

	}
}