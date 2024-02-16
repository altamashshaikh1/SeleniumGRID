/**
 * Implementation for Auto PV reporting at the end of suite based on test failure count.
 * If test failure count is 0 then auto pv report is invoked via below given method with all required data as payload.
 * It used HttpURLConnection in build java library to make HTTP:POST request.
 * 
 * @author altsh
 * @version 1.0
 * @since 21st August 2023 
 **/

package com.webMethods.io.Integration.AutomationUtilitiesMethods;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.webMethods.io.Integration.Base64EncodingUtils.EncodeDecodeData;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonFailureHandling;
import com.webMethods.io.Integration.Logger4jUtils.LoggerUtil;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;

public class AutoPVReporting 
{
	/** HELPER METHOD TO RETURN PRODUCT NAME FROM CYCLE ID FOR CLOUD CYCLE TEST SUITE **/
	public static String returnProductName(String cloudCycleName)
	{
		String[] parts = cloudCycleName.split("-");		
		return parts[0];
	}

	/** HELPER METHOD TO RETURN PLATFORM NAME FROM CYCLE ID FOR CLOUD CYCLE TEST SUITE **/
	public static String returnPlatformName(String cloudCycleName)
	{
		String[] parts = cloudCycleName.split("-");		
		return parts[1];
	}

	/** HELPER METHOD TO RETURN TEAM NAME FOR CLOUD CYCLE TEST SUITE **/
	public static String returnReportingTeamName()
	{
		return "wmioInt";
	}

	/** HELPER METHOD TO GENERATE PAYLOAD FOR CLOUD CYCLE TEST SUITE **/
	public static String generatePayloadAutoReportPVRequest(String productName, String platformName, String cloudCycleName, int totalTestsCount, int failureTestsCount, String buildVersion)
	{	
		return "{\"Product\":\""+productName+"\",\"Platform\":\""+platformName+"\",\"Cycle\":\""+cloudCycleName+"\",\"Team\":\"wmioInt\",\"Status\":\"SUCCESS\",\"Total_TC\":\""+totalTestsCount+"\",\"Fail\":\""+failureTestsCount+"\",\"Blockers\":\"NA\",\"Remarks\":\"NA\",\"Version\":\""+buildVersion+"\",\"Beta_TC\":\"NA\",\"FixedBlockers\":\"NA\",\"ImagePromotion\":\"Yes\",\"Email\":\"altamash.shaikh@softwareag.com\"}";
	}

	/** HELPER METHOD TO MAKE AUTO PV REPORT REQUEST FOR CLOUD CYCLE TEST SUITE **/
	public static void HTTP_POST_PV_REPORT(String productName, String platformName, String cloudCycleName, int totalTestsCount, int failureTestsCount, String buildVersion)
	{
		URL pvreporturl;
		HttpURLConnection httpurlconnection;
		DataOutputStream dataopstream;

		try
		{
			String autoReportURL = EncodeDecodeData.decodeFromBase64(PropertiesData.readConfigData("autoReportPVAPIURL"));
			pvreporturl = new URL(autoReportURL);
			httpurlconnection = (HttpURLConnection) pvreporturl.openConnection();
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpurlconnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			httpurlconnection.setRequestProperty("Content-Type","application/json");
			httpurlconnection.setDoOutput(true);
			dataopstream = new DataOutputStream(httpurlconnection.getOutputStream());
			dataopstream.writeBytes(generatePayloadAutoReportPVRequest(productName, platformName, cloudCycleName, totalTestsCount, failureTestsCount, buildVersion));
			dataopstream.flush();
			dataopstream.close();

			if( httpurlconnection.getResponseCode()==200 || httpurlconnection.getResponseCode()==201 || httpurlconnection.getResponseCode()==202 )
			{
				System.out.println("Auto PV Reported for "+cloudCycleName);
				SlackAPI.postMessageInSlack("Auto PV Reported for "+cloudCycleName);
				LoggerUtil.INFO("Auto PV Reported for "+cloudCycleName);
			}
			else
			{
				CommonFailureHandling.captureFailure("AutoReport PV URL not working : "+autoReportURL+" : "+httpurlconnection.getResponseCode());
			}
		}
		catch(Exception exception)
		{
			CommonExceptionHandling.captureException("Auto Reporting PV for cloud cycle suite. ",exception.getMessage());
		}
	}
}