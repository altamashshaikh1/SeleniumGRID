/**
 *  USED FOR DYNAMIC TEST DATA GENERATION IMPLEMENTATION THROUGH OUT THE FRAMEWORK EXECUTION LIFE CYCLE.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 */

package com.webMethods.io.Integration.TestDataCommonFeatures;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonFailureHandling;

public class WorkFlowsCRUDOperations
{
	/** HELPER METHOD CREATE NEW WORKFLOW **/
	public static String CREATE_NEW_WORKFLOW(String tenantBaseURL, String authtoken, String cookie, String projectUID, String jsonPayload)
	{
		String responseBody = null;

		try 
		{
			URL url = new URL(tenantBaseURL+"/enterprise/v1/flows");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authtoken", authtoken);
			connection.setRequestProperty("Cookie", cookie);
			connection.setRequestProperty("Project_uid", projectUID);
			connection.setRequestProperty("Content-Type", "application/json");  
			connection.setDoOutput(true);

			if (jsonPayload != null && !jsonPayload.isEmpty())
			{
				try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream()))
				{
					outputStream.writeBytes(jsonPayload);
					outputStream.flush();
				}
			}

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) 
				{
					response.append(inputLine);
				}
				in.close();
				responseBody = response.toString();
			} 
			else 
			{
				CommonFailureHandling.captureFailureAndTerminateTest("Workflow create API Request. Response code "+connection.getResponseCode());
			}
			connection.disconnect();
		} 
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Workflow create API Request [POST] : "+tenantBaseURL+"/enterprise/v1/flows. ",exception.getMessage());
		}		

		return responseBody;

	}

	/** HELPER METHOD UPDATE WORKFLOW **/
	public static String UPDATE_WORKFLOW(String tenantBaseURL, String authtoken, String cookie, String workflowUID, String projectUID, String jsonPayload)
	{
		String responseBody = null;

		try
		{
			// Create a URL object with the API URL
			URL url = new URL(tenantBaseURL+"/enterprise/v1/flows/"+workflowUID+"");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Authtoken", authtoken);
			connection.setRequestProperty("Cookie", cookie);
			connection.setRequestProperty("Project_uid", projectUID);
			connection.setRequestProperty("Content-Type", "application/json"); 
			connection.setDoOutput(true);

			if (jsonPayload != null && !jsonPayload.isEmpty()) 
			{
				try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) 
				{
					outputStream.writeBytes(jsonPayload);
					outputStream.flush();
				}
			}

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) 
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) 
				{
					response.append(inputLine);
				}
				in.close();
				responseBody = response.toString();
			} 
			else
			{
				CommonFailureHandling.captureFailureAndTerminateTest("Workflow update API Request. Response code "+connection.getResponseCode());
			}
			connection.disconnect();
		} 
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Workflow update API Request [PUT] : "+tenantBaseURL+"/enterprise/v1/flows/"+workflowUID+" : ",exception.getMessage());
		}	

		return responseBody;

	}

	/** HELPER METHOD GET SINGLE WORKFLOW **/

	/** HELPER METHOD GET ALL WORKFLOWS **/

	/** HELPER METHOD TO DELETE WORKFLOW **/

	/** HELPER METHOD TO GET WORKFLOW UID **/
	public static String getWorkflowUID(String responseBody)
	{
		String workflowUID = null;
		try
		{
			JSONObject myResponse = new JSONObject(responseBody);
			JSONObject outputData = myResponse.getJSONObject("output");
			workflowUID = outputData.getString("uid");
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Fetch workflow uid key value from response body. ",exception.getMessage());
		}

		return workflowUID;

	}
}