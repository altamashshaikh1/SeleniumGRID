package com.webMethods.io.Integration.ProjectParamsPublishDeployTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectParamsPublishDeployLocators 
{
	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block;

	@FindBy(xpath = "//a[contains(.,'Log in with environment')]")
	public static WebElement ssoLoginLink;

	@FindBy(id = "username")
	public static WebElement emailTextBox;

	@FindBy(id = "password")
	public static WebElement password;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement addNewButtonString;

	@FindBy(xpath = "//span[text()='Workflow1']")
	public static WebElement workflow1string;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//a[@name='ProjectParameterPublishDeploy-more-icon']")
	public static WebElement projectEllipsis;

	@FindBy(xpath = "//a[@name='ProjectParameterPublishDeploy-publish']")
	public static WebElement projectPublishProjectString;

	@FindBy(xpath = "//span[text()='ProjectParameterPublishDeploy']")
	public static WebElement projectPublishProject_str; 

	@FindBy(xpath = "//span[@title='ProjectParameterPublishDeploy']")
	public static WebElement prjparamTestproject;

	@FindBy(xpath = "//a[@name='ProjectParameterPublishDeploy-publish']")
	public static WebElement projectPublishProject;

	@FindBy(xpath = "//span[text()='Assets']")
	public static WebElement assetsModalString;

	@FindBy(xpath = "//div[@class='published-select-table']")
	public static WebElement assetsModal;

	@FindBy(xpath = "//button[text()='Next']")
	public static WebElement nextButton;

	@FindBy(xpath = "//label[@class='new-checkbox-label'][contains(.,'Workflows')]")
	public static WebElement workflowscheckbox;

	@FindBy(xpath = "//span[text()='Assets Name']")
	public static WebElement dependenciesModalString;

	@FindBy(xpath = "//span[text()='Assets Name']")
	public static WebElement dependenciesModal;

	@FindBy(xpath = "//span[text()='Publish settings']")
	public static WebElement publishSettingsModalString;

	@FindBy(xpath = "//input[@aid='Name']")
	public static WebElement deploymentNameTextBox;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Publish')]")
	public static WebElement ProjectpublishButton;

	@FindBy(xpath = "//*[text()='Project Published Successfully']")
	public static WebElement projectPubishMessageString;

	@FindBy(xpath = "//*[text()='Project Published Successfully']")
	public static WebElement projectPublishMessage;

	@FindBy(xpath = "//span[text()='ProjectParameterPublishDeploy']")
	public static WebElement projectparamTestbeforeProjectcreate_str;

	@FindBy(xpath = "//span[@title='ProjectParameterPublishDeploy']")
	public static WebElement prjparamsproject;

	@FindBy(xpath = "//div[@class='deploy-right-header right-project-name']")
	public static WebElement deployprojectnamemodal;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Save and continue')]")
	public static WebElement saveandcontinueButton;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Deploy')]")
	public static WebElement projectdeployButton;

	@FindBy(xpath = "//h3[text()='Skip Assets']")
	public static WebElement skipassetsString; 

	@FindBy(xpath = "//*[text()='Configure accounts']")
	public static WebElement configureAccountString;  

	@FindBy(xpath = "//*[text()='Configure triggers']")
	public static WebElement configureTriggerString;  

	@FindBy(xpath = "//*[text()='Configure parameters']")
	public static WebElement configureParameterString;

	@FindBy(xpath = "//button[contains(.,'Next')]")
	public static WebElement accselectmodalnextbutton;

	@FindBy(xpath = "//*[text()='Project deployed successfully']")
	public static WebElement projectDeployementMessageString; 

	@FindBy(xpath = "//*[text()='Project deployed successfully']")
	public static WebElement projectDeployementMessage;

	@FindBy(xpath = "//div[@class='deploy-parameters-inner-content']")
	public static WebElement paramsinnermodal;

	@FindBy(xpath = "//span[@class='single-parameters-title'][contains(.,'Test1')]")
	public static WebElement test1params_str;

	@FindBy(xpath = "//span[@class='single-parameters-title'][contains(.,'Test1')]")
	public static WebElement test1params;

	@FindBy(xpath = "//span[@class='single-parameters-title'][contains(.,'Test2')]")
	public static WebElement test2params;

	@FindBy(xpath = "//span[@class='single-parameters-title'][contains(.,'Test3')]")
	public static WebElement test3params;

	@FindBy(xpath = "//span[@class='single-parameters-title'][contains(.,'Test22')]")
	public static WebElement test2updatedparams;

	@FindBy(xpath = "//span[@aid='password-value-Test1'][contains(.,'1')]")
	public static WebElement test1paramsvalue_str;

	@FindBy(xpath = "//span[@aid='password-value-Test1'][contains(.,'1')]")
	public static WebElement test1paramssourcevalue;

	@FindBy(xpath = "//span[@aid='password-value-Test1'][contains(.,'11')]")
	public static WebElement test1paramsdestinatationvalue;

	@FindBy(xpath = "//span[@aid='password-value-Test2'][contains(.,'2')]")
	public static WebElement test2paramsvalue;

	@FindBy(xpath = "//span[@aid='password-value-Test22'][contains(.,'2')]")
	public static WebElement test22paramsvalue;

	@FindBy(xpath = "//span[@aid='password-value-Test22'][contains(.,'2')]")
	public static WebElement test22paramsvalue_str;

	@FindBy(xpath = "//a[@aid='edit-Test1']//i")
	public static WebElement test1parameditpencilicon;

	@FindBy(xpath = "//span[@class='key'][contains(.,'Test1')]")
	public static WebElement configpagetest1param;

	@FindBy(xpath = "//span[@class='key'][contains(.,'Test2')]")
	public static WebElement configpagetest2param;

	@FindBy(xpath = "//span[@class='key'][contains(.,'Test3')]")
	public static WebElement configpagetest3param;

	@FindBy(xpath = "//span[@class='key'][contains(.,'Test22')]")
	public static WebElement configpagetest2paramupdatedkey;

	@FindBy(xpath = "//span[text() = 'ProjectParameterWorkflow']")
	public static WebElement projectparamworkflow;

	@FindBy(xpath =  "//*[text()='Configurations']")
	public static WebElement configurationsLink;

	@FindBy(xpath =  "//*[text()='Workflow']")
	public static WebElement configurationWorkflow;

	@FindBy(linkText = "Parameter")
	public static WebElement parameterConfigurationLink;

	@FindBy(xpath = "//div[@class='parameter-wrapper']")
	public static WebElement configPageParamsModal;

	@FindBy(xpath = "//span[@class='value'][contains(.,'11')]")
	public static WebElement test1paramsupdatedvalue;

	@FindBy(xpath = "//span[@class='value'][contains(.,'1')]")
	public static WebElement test1paramsoldvalue;

	@FindBy(xpath = "//span[@class='value'][contains(.,'2')]")
	public static WebElement test2paramsconfigpagevalue;

	@FindBy(xpath = "//a[@data-activates='param-0']")
	public static WebElement firstParamsEllipsis;

	@FindBy(xpath = "//span[text()='Edit Parameter']")
	public static WebElement paramsEditOption;

	@FindBy(xpath = "//span[text()='Edit Parameter']")
	public static WebElement paramsEditOptionString;

	@FindBy(xpath = "//input[@aid='Name'][@value='Test2']")
	public static WebElement test2ParamKeyField_key;

	@FindBy(xpath = "//input[@aid='Name'][@value='Test2']")
	public static WebElement test2ParamKeyFieldwithval;

	@FindBy(xpath = "//input[@aid='Name']")
	public static WebElement kyenameinput;

	@FindBy(xpath = "//span[text()='Set as Password Field']")
	public static WebElement test2Paramispwdcheckbox;

	@FindBy(xpath = "//button[text()='Update ']")
	public static WebElement updateParamsButton;

	@FindBy(xpath = "//*[text()='Project params updated successfully.']")
	public static WebElement projectParamUpdateMessageString;

	@FindBy(xpath = "//a[text()='Projects']")
	public static WebElement projectDashboardLink;

	@FindBy(xpath = "//h1[text()='Edit Parameter']")
	public static WebElement namefield;

	@FindBy(xpath = "//a[text()='New Parameter']")
	public static WebElement addNewParameterOption;

	@FindBy(xpath = "//*[text()='Create']")
	public static WebElement createParamsButton;

	@FindBy(xpath = "//*[text()='Test3']")
	public static WebElement addedParamString3;

	@FindBy(xpath = "//*[text()='Test3']")
	public static WebElement addedParam3;

	@FindBy(xpath = "//*[text()='Project params saved successfully.']")
	public static WebElement projectParamCreateMessageString;

	@FindBy(xpath = "//span[@class='eye-icon dlt-icon-password-show']")
	public static WebElement showPwdIcon;

	@FindBy(linkText = "Deployments")
	public static WebElement deploymenytab;

	@FindBy(linkText = "Deploy")
	public static WebElement deployoption;

	@FindBy(xpath = "//div[@aid='previous']//div//span[@class='arrow-right dlt-icon-chevron-down']")
	public static WebElement prevparamconfigopenIcon;

	@FindBy(xpath = "//span[@aid='eyes-icon-Test22']")
	public static WebElement test2pwdicon;

	@FindBy(xpath = "//span[@aid='eyes-icon-Test22'][@class='eye-icon dlt-icon-password-hide']")
	public static WebElement test2pwdicondisable;

	@FindBy(xpath = "//span[@aid='password-value-Test22']")
	public static WebElement test2pwdformvalue;

	@FindBy(xpath = "//a[@aid='edit-Test3']")
	public static WebElement test3editicon;

	@FindBy(xpath = "//label[@aid='checkbox-Test1']")
	public static WebElement test1checkboxicon;

	@FindBy(xpath = "//a[@aid='edit-Test1'][@class='disabled not-password dropdown-button action-menu']")
	public static WebElement test1editicondisabled;

	@FindBy(linkText = "Projects")
	public static WebElement projectsLink;
}