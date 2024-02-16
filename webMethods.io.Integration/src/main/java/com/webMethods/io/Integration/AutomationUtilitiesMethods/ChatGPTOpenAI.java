/**
 * This Java class contains a comprehensive helper method that employs ChatGPT, an AI-powered language model, to greatly enhance our UI automation test suite.
 * The primary purpose of this method is to intelligently gather and analyze exceptions and failure information that may occur during the execution of our UI automation tests.
 * By harnessing the capabilities of ChatGPT, we have elevated our error-handling and diagnostic capabilities. 
 * This method allows us to seamlessly capture critical information when unexpected issues arise, such as exceptions, errors, or anomalies in our test scenarios.
 * 
 * @author altsh
 * @version 1.0
 * @since 21st August 2023 
 **/

package com.webMethods.io.Integration.AutomationUtilitiesMethods;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import com.webMethods.io.Integration.Base64EncodingUtils.EncodeDecodeData;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;

public class ChatGPTOpenAI 
{
	/** HELPER METHOD TO SANITIZE ERROR MESSAGE AND RETURN STRING REQUIRED AS INPUT TEXT TO CHAT API REQUESTS **/
	public static String sanitizeErrorMessage(String exceptionMessage)
	{
		String sanitizedMessage;

		sanitizedMessage = exceptionMessage.replaceAll("[^a-zA-Z0-9\\s]", "");
		sanitizedMessage = sanitizedMessage.replaceAll("\\s+", " ").trim();
		sanitizedMessage = sanitizedMessage.substring(0, Math.min(exceptionMessage.length(), 100));

		try 
		{
			sanitizedMessage = URLEncoder.encode(exceptionMessage, "UTF-8");
		}
		catch (UnsupportedEncodingException unsupportedEncodingException)
		{
			return "!!! EXCEPTION !!! : Exception occured "+unsupportedEncodingException.getMessage();
		}

		return sanitizedMessage;

	}

	/** HELPER METHOD MAKE POST REQUEST TO CHAT GPT API **/
	public static String HTTP_POST_CGPT(String inputData)
	{
		String url;
		URL obj;
		HttpURLConnection httpurlconnection;
		DataOutputStream dataopstream;
		BufferedReader bufferedReader;
		StringBuffer response;
		JSONObject parentJsonObject;
		JSONArray jsonArray;
		JSONObject messageJSONObject = null;

		try
		{
			url = PropertiesData.readConfigData("OPEN_AI_URL");
			obj = new URL(url);
			httpurlconnection = (HttpURLConnection) obj.openConnection();

			/** Setting basic post request **/
			httpurlconnection.setRequestProperty("Authorization","Bearer "+" "+EncodeDecodeData.decodeFromBase64(PropertiesData.readConfigData("OPEN_AI_KEY")));
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpurlconnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			httpurlconnection.setRequestProperty("Content-Type","application/json");

			/** Payload **/
			String sanitizedInputData = sanitizeErrorMessage(inputData);
			String postJsonData = "{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\""+sanitizedInputData+"\"}]}";

			/** Send post request **/
			httpurlconnection.setDoOutput(true);
			dataopstream = new DataOutputStream(httpurlconnection.getOutputStream());
			dataopstream.writeBytes(postJsonData);
			dataopstream.flush();
			dataopstream.close();

			if(httpurlconnection.getResponseCode()==200||httpurlconnection.getResponseCode()==201)
			{
				/** Print response body **/
				bufferedReader = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()));
				String output;
				response = new StringBuffer();

				while ((output = bufferedReader.readLine())!=null) 
				{
					response.append(output);
				}

				bufferedReader.close();
				parentJsonObject = new JSONObject(response.toString());
				jsonArray= parentJsonObject.getJSONArray("choices");

				for(int index = 0; index < jsonArray.length(); index++)
				{
					messageJSONObject = jsonArray.getJSONObject(index).getJSONObject("message");
				}
			}
			else
			{
				return "!!! FAILURE !!! : "+httpurlconnection.getResponseMessage()+" ["+httpurlconnection.getResponseCode()+"] : While making requests to OpenAI ChatGPT to get failure information.";
			}
		}
		catch(Exception exception)
		{
			return "!!! EXCEPTION !!! : Exception was encountered while making requests to OpenAI ChatGPT to get failure information. "+exception.getMessage();
		}

		return messageJSONObject.get("content").toString();

	}
}