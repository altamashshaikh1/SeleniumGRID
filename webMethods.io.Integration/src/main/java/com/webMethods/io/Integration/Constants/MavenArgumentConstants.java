/**
 *  This class consists all constants required to store and retrieve Maven Arguments.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.Constants;

import com.webMethods.io.Integration.Base64EncodingUtils.EncodeDecodeData;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;

public class MavenArgumentConstants 
{	
	/** LOCAL MAVEN ARGUMENT VARIABLE **/
	public static final String LOCAL_MAVEN_ARGUMENT = System.getProperty("LocalTest");

	/** ENVIRONMENT MAVEN ARGUMENT VARIABLE **/
	public static final String ENVIRONMENT_MAVEN_ARGUMENT = System.getProperty("Environment");

	/** SOURCE TENANT MAVEN ARGUMENT VARIABLES **/
	public static final String SOURCE_TENANT_URL= System.getProperty("SrcURL");
	public static final String SOURCE_TENANT_USERNAME =  System.getProperty("SrcUserName");
	public static final String SOURCE_TENANT_USERPASSWORD = getSourceTenantPassword();
	public static final String SOURCE_TENANT_NAME = getSourceTenantDomainName();

	/** DESTINATION TENANT MAVEN ARGUMENT VARIABLES **/
	public static final String DESTINATION_TENANT_URL = System.getProperty("DestURL");
	public static final String DESTINATION_TENANT_USERNAME = System.getProperty("DestUserName");
	public static final String DESTINATION_TENANT_USERPASSWORD = getDestinationTenantPassword();
	public static final String DESTINATION_TENANT_NAME = getDestinationTenantDomainName();

	/** BROWSER MAVEN ARGUMENT VARIABLE **/
	public static final String BROWSER_MAVEN_ARGUMENT = System.getProperty("Browser");

	/** CLOUD CYCLE NAME **/
	public static final String CLOUD_CYCLE_NAME_MAVEN_ARGUMENT = System.getProperty("CloudCycleName");

	/** SUITE XML FILE MAVEN ARGUMENT VARIABLE **/
	public static final String SUITE_XML_FILE_MAVEN_ARGUMENT = System.getProperty("suiteXmlFile");

	/** GET SOURCE TENANT BASE 64 DECODED PASSWORD VALUE **/
	public static String getSourceTenantPassword()
	{		
		String decodedString = null;

		try
		{
			decodedString = EncodeDecodeData.decodeFromBase64(System.getProperty("SrcUserPassword"));
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("While decoding base64 string.", exception.getMessage());
		}

		return decodedString;

	}

	/** GET DESTINATION TENANT BASE 64 DECODED PASSWORD VALUE **/
	public static String getDestinationTenantPassword()
	{
		String decodedString = null;

		try
		{
			decodedString = EncodeDecodeData.decodeFromBase64(System.getProperty("DestUserPassword"));
		}
		catch (Exception exception) 
		{
			CommonExceptionHandling.captureExceptionAndTerminateSuite("While decoding base64 string.", exception.getMessage());
		}

		return decodedString;
	}

	/** HELPER METHOD TO GET SOURCE TENANT/DOMAIN NAME FROM URL **/
	public static String getSourceTenantDomainName()
	{
		String tenantName = null;
		String trimmedString = null;

		switch(ENVIRONMENT_MAVEN_ARGUMENT)
		{
		case "PreDev":
			trimmedString = SOURCE_TENANT_URL.replace("https://", "");
			if(trimmedString.contains(".stag-int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".stag-int-aws-us.webmethods.io", "");				
			}
			else
			{
				//Azure not available
			}
			break;

		case "Developement" : 
			trimmedString = SOURCE_TENANT_URL.replace("https://", "");
			if(trimmedString.contains("dev-int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".dev-int-aws-us.webmethods.io", "");
			}
			else
			{
				//Azure not available
			}
			break;

		case "Integration" :   
			trimmedString = SOURCE_TENANT_URL.replace("https://", "");
			if(trimmedString.contains(".int-aw-us1.webmethods-int.io"))
			{
				tenantName = trimmedString.replace(".int-aw-us1.webmethods-int.io", "");
			}
			else
			{
				//Azure not available
			}
			break;

		case "Stag" : 
			trimmedString = SOURCE_TENANT_URL.replace("https://", "");
			if(trimmedString.contains(".stag-int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".stag-int-aws-us.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-us.webmethods-stage.io"))
			{
				tenantName = trimmedString.replace(".int-az-us.webmethods-stage.io", "");
			}
			break;

		case "Spro" :
			trimmedString = SOURCE_TENANT_URL.replace("https://", "");
			if(trimmedString.contains(".spro-int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".spro-int-aws-us.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-us.webmethods-spro.io"))
			{
				tenantName = trimmedString.replace(".int-az-us.webmethods-spro.io", "");
			}
			break;

		case "PreProd" : 
			trimmedString = SOURCE_TENANT_URL.replace("https://", "");
			if(trimmedString.contains(".preprod-int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".preprod-int-aws-us.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-us.webmethods-preprod.io"))
			{
				tenantName = trimmedString.replace(".int-az-us.webmethods-preprod.io", "");
			}
			break;

		case "Production" : 
			trimmedString = SOURCE_TENANT_URL.replace("https://", "");

			if(trimmedString.contains(".int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-aws-us.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-az-us.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-aws-de.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-aws-de.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-eu.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-az-eu.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-aw-au.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-aw-au.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-au.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-az-au.webmethods.io", "");
			}
			break;
		}

		return tenantName;

	}

	/** HELPER METHOD TO GET DESTINATION TENANT/DOMAIN NAME FROM URL **/
	public static String getDestinationTenantDomainName()
	{
		String tenantName = null;
		String trimmedString = null;

		switch(ENVIRONMENT_MAVEN_ARGUMENT)
		{
		case "PreDev":
			trimmedString = DESTINATION_TENANT_URL.replace("https://", "");
			if(trimmedString.contains(".stag-int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".stag-int-aws-us.webmethods.io", "");
			}
			else
			{
				//Azure not available
			}
			break;

		case "Developement" : 
			trimmedString = DESTINATION_TENANT_URL.replace("https://", "");
			if(trimmedString.contains("dev-int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".dev-int-aws-us.webmethods.io", "");
			}
			else
			{
				//Azure not available
			}
			break;

		case "Integration" :   
			trimmedString = DESTINATION_TENANT_URL.replace("https://", "");
			if(trimmedString.contains(".int-aw-us1.webmethods-int.io"))
			{
				tenantName = trimmedString.replace(".int-aw-us1.webmethods-int.io", "");
			}
			else
			{
				//Azure not available
			}
			break;

		case "Stag" : 
			trimmedString = DESTINATION_TENANT_URL.replace("https://", "");
			if(trimmedString.contains(".stag-int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".stag-int-aws-us.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-us.webmethods-stage.io"))
			{
				tenantName = trimmedString.replace(".int-az-us.webmethods-stage.io", "");
			}
			break;

		case "Spro" :
			trimmedString = DESTINATION_TENANT_URL.replace("https://", "");
			if(trimmedString.contains(".spro-int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".spro-int-aws-us.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-us.webmethods-spro.io"))
			{
				tenantName = trimmedString.replace(".int-az-us.webmethods-spro.io", "");
			}
			break;

		case "PreProd" : 
			trimmedString = DESTINATION_TENANT_URL.replace("https://", "");
			if(trimmedString.contains(".preprod-int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".preprod-int-aws-us.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-us.webmethods-preprod.io"))
			{
				tenantName = trimmedString.replace(".int-az-us.webmethods-preprod.io", "");
			}
			break;

		case "Production" : 
			trimmedString = DESTINATION_TENANT_URL.replace("https://", "");

			if(trimmedString.contains(".int-aws-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-aws-us.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-us.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-az-us.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-aws-de.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-aws-de.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-eu.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-az-eu.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-aw-au.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-aw-au.webmethods.io", "");
			}
			else if(trimmedString.contains(".int-az-au.webmethods.io"))
			{
				tenantName = trimmedString.replace(".int-az-au.webmethods.io", "");
			}
			break;
		}

		return tenantName;

	}
}