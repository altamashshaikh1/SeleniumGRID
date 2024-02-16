package com.webMethods.io.Integration.TestDataCommonFeatures;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import org.json.JSONObject;

import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonFailureHandling;

public class LoginUserOperations 
{
	/** HELPER METHOD TO LOGIN USER **/
	public static String loginUser(String tenantBaseURL, String userName, String userPassword)
	{
		String responseBody = null;
		URL urlObject;
		HttpURLConnection connection;
		int responseCode; 
		String encoded = Base64.getEncoder().encodeToString((userName+":"+userPassword).getBytes(StandardCharsets.UTF_8));  

		try
		{
			urlObject = new URL(tenantBaseURL+"/enterprise/v1/user/token");
			connection = (HttpURLConnection) urlObject.openConnection();
			connection.setRequestProperty("Authorization", "Basic "+encoded);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent","Mozilla/5.0");
			responseCode = connection.getResponseCode();

			if (responseCode >= 200 && responseCode < 300)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
				{
					response.append(inputLine);
				}				
				responseBody=response.toString();
				in.close();
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateTest("Login user.");
			}
		}
		catch(Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Login user", exception.getMessage());
		}

		return responseBody;

	}

	/** HELPER METHOD TO FETCH AUTHTOKEN FROM RESPONSE BODY **/
	public static String getAuthtoken(String responseBody)
	{
		String authtoken = null;

		try
		{
			JSONObject myResponse = new JSONObject(responseBody);
			JSONObject outputData = myResponse.getJSONObject("output");
			authtoken = outputData.getString("authtoken");
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Get authtoken from response body.",exception.getMessage());
		}

		return authtoken;

	}

	/** HELPER METHOD TO FETCH COOKIES FROM RESPONSE BODY **/
	public static String getCookies(String responseBody)
	{
		String cookie = null;

		try
		{
			JSONObject myResponse = new JSONObject(responseBody);
			JSONObject outputData = myResponse.getJSONObject("output");
			cookie = outputData.getString("cookie");
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Get cookies from response body.",exception.getMessage());
		}

		return cookie;

	}

	/** HELPER METHOD TO FETCH X-CSRF-TOKEN FROM RESPONSE BODY **/
	public static String getCSRF(String responseBody)
	{
		String csrf = null;

		try
		{
			JSONObject myResponse = new JSONObject(responseBody);
			JSONObject outputData = myResponse.getJSONObject("output");
			csrf = outputData.getString("csrf");
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Get X-CSRF from response body.",exception.getMessage());
		}

		return csrf;

	}
}