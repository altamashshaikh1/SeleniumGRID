/**
 * This class contains Slack API to post message on slack channel and upload file to channel
 *
 * @author altsh
 * @version 1.0
 * @since 21st August 2023 
 **/

package com.webMethods.io.Integration.AutomationUtilitiesMethods;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream; 
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import com.webMethods.io.Integration.Constants.FilePathConstants;
import com.webMethods.io.Integration.FileUtilities.FileUtilitiesHelperFunctions; 
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;

public class SlackAPI
{
	public static HttpsURLConnection httpClient;

	public static HttpURLConnection httpConn;

	public static OutputStream outputStream;

	public static FileInputStream inputStream;

	public static DataOutputStream wr;

	public static BufferedReader in;

	public static String boundary;

	public static PrintWriter writer;

	public static int status;

	public static BufferedReader reader;

	public static String line;

	/** HELPER METHOD TO POST MESSAGE IN SLACK CHANNEL **/
	public static void postMessageInSlack(String messageString)
	{
		try
		{
			httpClient = (HttpsURLConnection) new URL(PropertiesData.readConfigData("slackHook")).openConnection();
			httpClient.setRequestMethod("POST");
			httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpClient.setRequestProperty("Content-Type", "application/json");
			String urlParameters = "{\"text\":\"" + messageString + "\"}";
			httpClient.setDoOutput(true);
			wr = new DataOutputStream(httpClient.getOutputStream());	
			wr.writeBytes(urlParameters);
			wr.flush();
			in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();

			while ((line = in.readLine()) != null) 
			{
				response.append(line);
			}
		}
		catch(Exception exception)
		{				
			System.out.println("!!! EXCEPTION !!! : Postmessage on slack channel - "+exception.getMessage());
		}
	}

	/** HELPER METHOD UPLOAD FILE TO SLACK CHANNEL **/
	public static void uploadFileToSlack()
	{
		try
		{
			boundary = "===" + System.currentTimeMillis() + "===";
			URL url = null;
			url = new URL(PropertiesData.readConfigData("slackFileUploadToken"));
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setUseCaches(false);
			httpConn.setDoOutput(true); 
			httpConn.setDoInput(true);
			httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
			outputStream = httpConn.getOutputStream();
			writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
		}
		catch(Exception exception)
		{				
			System.out.println("!!! EXCEPTION !!! : uploadFileToSlack Postmessage on slack channel. "+exception.getMessage());
		}
	}

	/** HELPER METHOD TO UPLOAD FILE WITH FILE PART **/
	public static void addFilePart(String fieldName, File uploadFile) 
	{
		String fileName = uploadFile.getName();

		try
		{
			writer.append("--" + boundary).append(FilePathConstants.LINE_FEED);
			writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"").append(FilePathConstants.LINE_FEED);
			writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(FilePathConstants.LINE_FEED);
			writer.append("Content-Transfer-Encoding: binary").append(FilePathConstants.LINE_FEED);
			writer.append(FilePathConstants.LINE_FEED);
			writer.flush();

			inputStream = new FileInputStream(uploadFile);
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) 
			{
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.flush();
			inputStream.close();
			writer.append(FilePathConstants.LINE_FEED);
			writer.flush();
		}
		catch(Exception exception)
		{				
			System.out.println("!!! EXCEPTION !!! : addFilePart Postmessage on slack channel. "+exception.getMessage());
		}
	}

	/** HELPER METHOD TO LIST FILES REQUIRED TO UPLOAD FILE IN SLACK CHANNEL **/
	public static List<String> finish() 
	{
		List<String> response = null;

		try
		{
			response = new ArrayList<String>();
			writer.append(FilePathConstants.LINE_FEED).flush();
			writer.append("--" + boundary + "--").append(FilePathConstants.LINE_FEED);
			writer.close();
			status = httpConn.getResponseCode();

			if (status == HttpURLConnection.HTTP_OK) 
			{
				reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				while ((line = reader.readLine()) != null)
				{
					response.add(line);
				}

				reader.close();
				httpConn.disconnect();
			} 
			else 
			{				
				System.out.println("!!! FAILURE !!! : List<String> finish Postmessage on slack channel HttpURLConnection HTTP_NOT_OK.");
			}
		}
		catch(Exception exception)
		{				
			System.out.println("!!! EXCEPTION !!! : List<String> finish Postmessage on slack channel. "+exception.getMessage());
		}

		return response;

	}

	/** HELPER METHOD UPLOAD FILE AND SHARE REPORTS TO SLACK CHANNEL **/
	public static void uploadAndShareReportFile(String tempZipFileName)
	{
		if(tempZipFileName.toString().length()!=0)
		{
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Sharing Automation .zip file in slack channel.");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			postMessageInSlack("Refer below *.zip* file for automation reports , logs and failed screenshots.");

			uploadFileToSlack();
			addFilePart("file",FileUtilitiesHelperFunctions.fileAbsolute(""+FilePathConstants.FILE_SEPARATOR+""+tempZipFileName+""));

			@SuppressWarnings("unused")
			List<String> response = finish();
		}
		else
		{				
			System.out.println("!!! FAILURE !!! : uploadAndShareReportFile Postmessage on slack channel.");
		}
	}
}