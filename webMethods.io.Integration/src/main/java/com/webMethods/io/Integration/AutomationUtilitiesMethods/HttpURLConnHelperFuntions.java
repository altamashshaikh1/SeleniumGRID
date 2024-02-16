/**
 * This class contains helper functions for making HTTP requests and handling responses in the Selenium WebDriver grid framework.
 * It provides methods to send GET and POST requests, set request headers, handle cookies, and extract response data.
 * These functions can be used to interact with web services and APIs within the framework.
 *
 * Note: This class uses the HttpURLConnection class available in Java for making HTTP requests. Make sure that the necessary
 * Java libraries are available on the classpath.
 *
 * @author altsh
 * @version 1.0
 * @since 21st August 2023 
 **/

package com.webMethods.io.Integration.AutomationUtilitiesMethods;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import org.json.JSONObject;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.ExtentManager.ExtentTestManager;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonFailureHandling;
import java.io.InputStreamReader;
import java.util.Base64;
import org.json.JSONArray;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream; 
import java.io.InputStream; 

public class HttpURLConnHelperFuntions
{
	/** HELPER METHOD TO GET CURRENT UI BUILD VERSION **/
	public static String getUIBuildVersion(String urlString)
	{
		URL urlObject;
		HttpURLConnection connection;
		int responseCode;
		String buildVersion = null;

		try
		{
			urlObject = new URL(urlString);
			connection = (HttpURLConnection) urlObject.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent","Mozilla/5.0");
			responseCode = connection.getResponseCode();

			if(responseCode == HttpURLConnection.HTTP_OK)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null)
				{
					response.append(inputLine);
				}
				in.close();

				JSONObject jsonObject = new JSONObject(response.toString());
				buildVersion = jsonObject.get("version").toString();

			}
			else  
			{
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check: Fetch current UI BuildVersion. Unable to get current build version. [GET] : "+urlString+" = "+responseCode+"");
			}
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("Pre-Check: Fetch current UI BuildVersion. While fetching current UI BuildVersion. ", exception.getMessage());
		}

		return buildVersion;

	}

	/** HELPER METHOD TO VERIFY TENANT STATUS **/
	public static void verifyTenantHealthStatus(String applicationURL) 
	{
		try 
		{
			URL url = new URL(applicationURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			if (connection.getResponseCode() == 200)
			{
				//LoggerUtil.INFO(MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+" is up and accessible. Automation suite execution will begin now.");
				//System.out.println("  Pre-Check: Verifying Environment availability. "+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+" is up and accessible. Automation suite execution will begin now.");
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check: Verifying Environment availability. "+MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+" seems to be down ["+connection.getResponseCode()+"]. Automation suite execution will terminate.");
			}
		} 
		catch (Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("Pre-Check: Verifying Environment availability.",MavenArgumentConstants.ENVIRONMENT_MAVEN_ARGUMENT+" seems to be down. Automation suite execution will terminate. "+exception.getMessage());
		}
	}

	/** HELPER METHOD TO VERIFY TENANT CREDENTIALS **/
	public static void verifyUserCredentialsWithTenant(String applicationURL, String userName, String userPassword) 
	{
		String basicAuth = userName + ":" + userPassword;
		byte[] authBytes = basicAuth.getBytes(StandardCharsets.UTF_8);
		String encodedAuth = Base64.getEncoder().encodeToString(authBytes);

		try 
		{
			URL url = new URL(applicationURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(10000);

			if (connection.getResponseCode() == 200)
			{
				//System.out.println("Pre-Check: Verify credentails. Valid credentials entered.");
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateSuite("Pre-Check: Invalid username "+userName+" or password "+userPassword+" ["+connection.getResponseCode()+"] : "+applicationURL+". Automation suite will be terminated.");
			}
		} 
		catch (Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("Pre-Check: Verify credentails. Verify tenant "+applicationURL+" credentails. Automation suite will be terminated. ",exception.getMessage());
		}
	}

	/** HELPER METHOD EXECUTE WORKFLOW WEBHOOK URL **/
	public static void executeWebhookURL(String URL,String description)
	{
		try
		{
			URL obj = new URL(URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setDoOutput(true);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) 
			{
				response.append(inputLine);
			}
			in.close();

			int responseCode = con.getResponseCode();

			if(responseCode==200)
			{
				ExtentTestManager.getTest().info(description);
			}
			else
			{			
				CommonFailureHandling.captureFailureAndTerminateTest(description);
			}
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description, exception.getMessage());
		}
	} 

	/** HELPER METHOD EXECUTE WORKFLOW WEBHOOK URL **/
	public static void executeWorkflowHavingICAssets(String webHookURL,String userEmail,String userPassword,String description)
	{
		String encodedCredentials = credentialsBase64EncodingString(userEmail, userPassword);

		try
		{
			HttpURLConnection httpClient = (HttpURLConnection) new URL(webHookURL).openConnection();
			httpClient.setRequestMethod("GET");
			httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpClient.setRequestProperty("Authorization", "Basic "+encodedCredentials);

			int responseCode = httpClient.getResponseCode();

			if(responseCode==200)
			{
				ExtentTestManager.getTest().info("<pre><b>Webhook URL</b> : "+webHookURL+" \n<b>Method</b> : GET\n<b>Response: </b>"+httpClient.getResponseMessage()+"</pre>");
			}
			else
			{
				CommonFailureHandling.captureFailureAndTerminateTest(description);
			}
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description, exception.getMessage());
		}
	}

	/** HELPER METHOD ENCODE BASE 64 STRING **/
	public static String credentialsBase64EncodingString(String userName, String userPassword)
	{
		String encodedString = null;

		try
		{
			Base64.Encoder encoder = Base64.getEncoder();
			String normalString = ""+userName+":"+userPassword+"";
			encodedString = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Encode base64 String.", exception.getMessage());
		}

		return encodedString;

	}

	/** HELPER METHOD DECODE BASE 64 STRING **/
	public static String decodeData(String dataToDecode)
	{
		byte[] actualByte;
		String actualString = null;

		try
		{
			actualByte = Base64.getDecoder().decode(dataToDecode);
			actualString = new String(actualByte);
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Decode base64 String.", exception.getMessage());
		}

		return actualString;

	}

	/** HELPER METHOD HTTP GET METHOD REQUEST **/
	public static String HTTP_GET(String urlString, String userName, String userPassword, String description) 
	{
		URL urlObject;
		HttpURLConnection connection;
		int responseCode;
		String responseBody = null;
		String encoded = Base64.getEncoder().encodeToString((userName+":"+userPassword).getBytes(StandardCharsets.UTF_8));  

		try
		{
			urlObject = new URL(urlString);
			connection = (HttpURLConnection) urlObject.openConnection();
			connection.setRequestProperty("Authorization", "Basic "+encoded);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent","Mozilla/5.0");
			responseCode = connection.getResponseCode();

			if(responseCode == HttpURLConnection.HTTP_OK)
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
				CommonFailureHandling.captureFailureAndTerminateTest(description);
			}
		}
		catch (Exception exception) 
		{			
			CommonExceptionHandling.captureExceptionAndTerminateTest(description, exception.getMessage());
		}

		return responseBody;

	}

	/** HELPER METHOD HTTP GET METHOD REQUEST **/
	public static String HTTP_GET(String urlString, String authToken, String cookieString,String csrfToken,String description)
	{
		URL urlObject;
		HttpURLConnection connection;
		int responseCode;
		String responseBody = null;

		try
		{
			urlObject = new URL(urlString);
			connection = (HttpURLConnection) urlObject.openConnection();
			connection.setRequestProperty("authtoken",authToken);
			connection.setRequestProperty("Cookie",cookieString);
			connection.setRequestProperty("X-CSRF-TOKEN",csrfToken);
			connection.setRequestProperty("Accept","*/*");			
			connection.setRequestMethod("GET");
			responseCode = connection.getResponseCode();

			if(responseCode == HttpURLConnection.HTTP_OK)
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
				CommonFailureHandling.captureFailureAndTerminateTest(description);
			}
		}
		catch (Exception exception) 
		{		
			CommonExceptionHandling.captureExceptionAndTerminateTest(description, exception.getMessage());
		}

		return responseBody;

	}

	/** HELPER METHOD HTTP GET METHOD REQUEST **/
	public static String HTTP_GET(String urlString, String authToken, String cookieString,String csrfToken,String projectUID,String description)
	{
		URL urlObject;
		HttpURLConnection connection;
		int responseCode;
		String responseBody = null;

		try
		{
			urlObject = new URL(urlString);
			connection = (HttpURLConnection) urlObject.openConnection();
			connection.setRequestProperty("authtoken",authToken);
			connection.setRequestProperty("Cookie",cookieString);
			connection.setRequestProperty("X-CSRF-TOKEN",csrfToken);
			connection.setRequestProperty("project_uid",projectUID);
			connection.setRequestProperty("Accept","*/*");			
			connection.setRequestMethod("GET");
			responseCode = connection.getResponseCode();

			if(responseCode == HttpURLConnection.HTTP_OK)
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
				CommonFailureHandling.captureFailureAndTerminateTest(description);
			}
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest(description, exception.getMessage());
		}

		return responseBody;

	}

	/** HELPER METHOD HTTP POST METHOD REQUEST **/
	public static void HTTP_POST_REQUEST(String url,String postJsonData,String description)
	{
		URL obj;
		HttpURLConnection httpurlconnection;
		DataOutputStream dataopstream;

		try
		{
			obj = new URL(url);
			httpurlconnection = (HttpURLConnection) obj.openConnection();

			// Setting basic post request
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpurlconnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			httpurlconnection.setRequestProperty("Content-Type","application/json");

			// Send post request
			httpurlconnection.setDoOutput(true);
			dataopstream = new DataOutputStream(httpurlconnection.getOutputStream());
			dataopstream.writeBytes(postJsonData);
			dataopstream.flush();
			dataopstream.close();

			if(httpurlconnection.getResponseCode()==200||httpurlconnection.getResponseCode()==201)
			{
				ExtentTestManager.getTest().info(description+" : "+httpurlconnection.getResponseCode()+" - "+httpurlconnection.getResponseMessage());
			}
			else
			{				
				CommonFailureHandling.captureFailureAndTerminateTest(description);
			}
		}
		catch(Exception exception)
		{			
			CommonExceptionHandling.captureExceptionAndTerminateTest(description, exception.getMessage());
		}
	}

	/** HELPER METHOD GET DATA FROM RESPONSE **/
	public static String getJsonKeyValue(String responseBody, String keyName)
	{
		String keyValue = null;

		try
		{
			JSONObject myResponse = new JSONObject(responseBody);
			JSONObject outputData = myResponse.getJSONObject("output");
			keyValue = outputData.getString(keyName);
		}
		catch (Exception exception) 
		{				
			CommonExceptionHandling.captureExceptionAndTerminateTest("Retrive JSON Key value.",exception.getMessage());
		}

		return keyValue;

	}

	/** HELPER METHOD GET DATA FROM RESPONSE **/
	public static boolean getJsonKeyBooleanValue(String responseBody,String firstKeyObject,String secondKeyObject,String keyName)
	{
		boolean keyValue = false;

		try
		{
			JSONObject myResponse = new JSONObject(responseBody);
			JSONObject outputDataOne = myResponse.getJSONObject(firstKeyObject);
			JSONObject outputDataTwo = outputDataOne.getJSONObject(secondKeyObject);
			keyValue = outputDataTwo.getBoolean(keyName);
		}
		catch (Exception exception) 
		{				
			CommonExceptionHandling.captureExceptionAndTerminateTest("Retrive JSON boolean Key value. ",exception.getMessage());
		}

		return keyValue;

	}

	/** HELPER METHOD GET DATA FROM RESPONSE **/
	public static boolean getJsonKeyBooleanValue1(String responseBody,String firstKeyObject,String secondKeyObject,String thirdKeyObject,String keyName)
	{
		boolean keyValue = false;

		try
		{
			JSONObject myResponse = new JSONObject(responseBody);
			JSONObject outputDataOne = myResponse.getJSONObject(firstKeyObject);
			JSONObject outputDataTwo = outputDataOne.getJSONObject(secondKeyObject);
			JSONObject outputDataThree = outputDataTwo.getJSONObject(thirdKeyObject);
			keyValue = outputDataThree.getBoolean(keyName);
		}
		catch (Exception exception) 
		{				
			CommonExceptionHandling.captureExceptionAndTerminateTest("Retrive JSON boolean Key value. ",exception.getMessage());
		}

		return keyValue;

	}

	/** HELPER METHOD HTTP GET METHOD REQUEST PUBLIC API **/
	public static String HTTP_PUBLIC_API_GET_REQUEST(String urlString,String userEmail,String userPassword)
	{
		String responseBodyString = "";

		try
		{
			String credentials = userEmail + ":" + userPassword;
			String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

			//Create the URL and HttpURLConnection object
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization",encodedCredentials);

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) 
			{
				// Request successful
				InputStream inputStream = connection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				String line;
				StringBuilder response = new StringBuilder();

				while ((line = reader.readLine()) != null)
				{
					response.append(line);
				}

				reader.close();
				responseBodyString = response.toString();
			} 
			else
			{				
				CommonFailureHandling.captureFailureAndTerminateTest("Response code : "+responseCode+". Response message from server : "+connection.getResponseMessage().toString());	
			}

			connection.disconnect();

		}
		catch(Exception exception)
		{				
			CommonExceptionHandling.captureExceptionAndTerminateTest("Make HTTP GET request for "+urlString+"",exception.getMessage());
		}

		return responseBodyString;

	}

	/** HELPER METHOD HTTP POST METHOD REQUEST PUBLIC API **/
	public static String HTTP_PUBLIC_API_POST_REQUEST(String urlString,String userEmail,String userPassword,String postJsonData)
	{		
		String responseBodyString = "";

		try 
		{
			String credentials = userEmail + ":" + userPassword;
			String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

			//Create the URL and HttpURLConnection object
			URL url = new URL(urlString);
			HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.setDoInput(true);
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setRequestProperty("Content-Type", "application/json");
			httpurlconnection.setRequestProperty("Authorization", encodedCredentials);

			//Write the request body data
			OutputStream outputStream = httpurlconnection.getOutputStream();
			outputStream.write(postJsonData.getBytes(StandardCharsets.UTF_8));
			outputStream.flush();
			outputStream.close();

			//Get the response code
			int responseCode = httpurlconnection.getResponseCode();

			if(responseCode == 201 || responseCode == 200)
			{
				//Read the response body
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()));
				String line;
				StringBuilder responseBody = new StringBuilder();

				while ((line = reader.readLine()) != null) 
				{
					responseBody.append(line);
				}

				reader.close();
				responseBodyString = responseBody.toString();
			}
			else
			{				
				CommonFailureHandling.captureFailureAndTerminateTest("Response code : "+responseCode+". Response message from server : "+httpurlconnection.getResponseMessage().toString());	
			}

			httpurlconnection.disconnect();
		} 
		catch(Exception exception)
		{				
			CommonExceptionHandling.captureExceptionAndTerminateTest("Make HTTP Post request for "+urlString+"",exception.getMessage());
		}

		return responseBodyString;

	}

	/** HELPER METHOD HTTP POST MULTIPART METHOD REQUEST PUBLIC API **/
	public static String HTTP_POST_MULTIPART_PUBLIC_API(String urlString,String userEmail,String userPassword,String filePath,String fileFieldName)
	{
		String responseBodyString = "";

		try 
		{
			String credentials = userEmail + ":" + userPassword;
			String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

			File file = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(file);

			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=---------------------------boundary");
			connection.setRequestProperty("Authorization", encodedCredentials);

			OutputStream outputStream = connection.getOutputStream();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);

			writer.append("-----------------------------boundary\r\n");
			writer.append("Content-Disposition: form-data; name=\"" + fileFieldName + "\"; filename=\"" + file.getName() + "\"\r\n");
			writer.append("Content-Type: " + HttpURLConnection.guessContentTypeFromName(file.getName()) + "\r\n");
			writer.append("\r\n");
			writer.flush();

			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = fileInputStream.read(buffer)) != -1)
			{
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.flush();

			writer.append("\r\n");
			writer.append("-----------------------------boundary--\r\n");
			writer.close();

			int responseCode = connection.getResponseCode();

			if (responseCode == 201 || responseCode == 200)
			{
				// Request successful
				InputStream inputStream = connection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				String line;
				StringBuilder response = new StringBuilder();

				while ((line = reader.readLine()) != null)
				{
					response.append(line);
				}

				reader.close();
				responseBodyString = response.toString();
			} 
			else
			{				
				CommonFailureHandling.captureFailureAndTerminateTest("Response code : "+responseCode+". Response message from server : "+connection.getResponseMessage().toString());	
			}

			fileInputStream.close();
			connection.disconnect();

		} 
		catch(Exception exception)
		{				
			CommonExceptionHandling.captureExceptionAndTerminateTest("Make HTTP Post Multipart request for "+urlString,exception.getMessage());
		}

		return responseBodyString;

	}

	/** HELPER METHOD HTTP DELETE METHOD REQUEST PUBLIC API **/
	public static String HTTP_DELETE_PUBLIC_API(String urlString,String userEmail,String userPassword,String postJsonData)
	{
		String responseBodyString = "";

		try 
		{
			String credentials = userEmail + ":" + userPassword;
			String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

			//Create the URL and HttpURLConnection object
			URL url = new URL(urlString);
			HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setRequestMethod("DELETE");
			httpurlconnection.setDoInput(true);
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setRequestProperty("Content-Type", "application/json");
			httpurlconnection.setRequestProperty("Authorization", encodedCredentials);

			//Write the request body data
			OutputStream outputStream = httpurlconnection.getOutputStream();
			outputStream.write(postJsonData.getBytes(StandardCharsets.UTF_8));
			outputStream.flush();
			outputStream.close();

			//Get the response code
			int responseCode = httpurlconnection.getResponseCode();

			if(responseCode == 201 || responseCode == 200)
			{
				//Read the response body
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()));
				String line;
				StringBuilder responseBody = new StringBuilder();

				while ((line = reader.readLine()) != null) 
				{
					responseBody.append(line);
				}

				reader.close();
				responseBodyString = responseBody.toString();
			}
			else
			{				
				CommonFailureHandling.captureFailureAndTerminateTest("Response code : "+responseCode+". Response message from server : "+httpurlconnection.getResponseMessage().toString());	
			}

			httpurlconnection.disconnect();
		} 
		catch(Exception exception)
		{				
			CommonExceptionHandling.captureExceptionAndTerminateTest("Make HTTP Delete request for "+urlString+"",exception.getMessage());
		}

		return responseBodyString;

	}

	/** HELPER METHOD EXTRACT JSON KEY VALUE FROM RESPONSE BODY **/
	public static String getJsonKey(String responseBody, String keyName)
	{
		String keyValue = null;

		try
		{
			JSONObject jsonobject = new JSONObject(new String(responseBody));
			JSONObject uid = jsonobject.getJSONObject("output");
			keyValue = uid.getString(keyName);
		}
		catch (Exception exception) 
		{				
			CommonExceptionHandling.captureExceptionAndTerminateTest("Get JSON key from response body. ",exception.getMessage());
		}

		return keyValue;

	}

	/** HELPER METHOD EXTRACT JSON KEY VALUE FROM RESPONSE BODY **/
	public static String getJsonArrayKeyValue(String responseString, String keyName)
	{
		String keyValue = null;

		try
		{
			JSONObject jsonobject = new JSONObject(new String(responseString));
			JSONObject outputUid = jsonobject.getJSONObject("output");
			JSONArray jsonObjects = outputUid.getJSONArray("objects");
			keyValue = jsonObjects.getJSONObject(0).getString("uid");		
		}
		catch (Exception exception) 
		{				
			CommonExceptionHandling.captureExceptionAndTerminateTest("Get JSON array key from response body. ",exception.getMessage());
		}

		return keyValue;

	}
}