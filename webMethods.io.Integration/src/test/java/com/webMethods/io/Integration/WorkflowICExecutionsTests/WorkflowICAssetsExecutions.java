package com.webMethods.io.Integration.WorkflowICExecutionsTests;

import org.testng.annotations.Test;
import com.webMethods.io.Integration.AutomationUtilitiesMethods.HttpURLConnHelperFuntions;
import com.webMethods.io.Integration.Constants.MavenArgumentConstants;
import com.webMethods.io.Integration.PropertiesUtilities.PropertiesData;  

public class WorkflowICAssetsExecutions
{
	public static String workflowWebhookURL;

	public static String userName;

	public static String userPassword;

	@Test(groups = {"WorkflowExecutionsICAssets"},description="Verify workflow executions having rest/soap connectors and flowservices")
	public void workflowExecutionWithRestSoapConnectors()
	{
		workflowWebhookURL = PropertiesData.readInputData("webhookURL");
		userName = MavenArgumentConstants.SOURCE_TENANT_USERNAME;
		userPassword = MavenArgumentConstants.SOURCE_TENANT_USERPASSWORD;
		HttpURLConnHelperFuntions.executeWorkflowHavingICAssets(workflowWebhookURL, userName, userPassword,"Execute workflow having IC assets (Rest/Soap connectors and flowservices)");
	}
}