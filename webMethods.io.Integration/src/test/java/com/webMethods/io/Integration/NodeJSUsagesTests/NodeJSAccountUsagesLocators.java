package com.webMethods.io.Integration.NodeJSUsagesTests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NodeJSAccountUsagesLocators
{
	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block;

	@FindBy(id = "username")
	public static WebElement usernameInput;

	@FindBy(id = "password")
	public static WebElement passwordInput;

	@FindBy(id = "username")
	public static WebElement emailTextBox;

	@FindBy(id = "password")
	public static WebElement password;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement createNewProjectButton;

	@FindBy(xpath = "//input[@placeholder='Search']")
	public static WebElement searchProjectTextBox;

	@FindBy(xpath = "//span[@title='BACKLOG_TEST_DATA']")
	public static WebElement backlogTestDataProjectName;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addNewWorkflowButton;

	@FindBy(linkText = "Connectors")
	public static WebElement connectorsLinkText;

	@FindBy(xpath = "//div[@class='single-connector ']")
	public static List<WebElement> allConnectorsIcon;

	@FindBy(xpath = "//span[@class='inner-view-configured'][text()='1 Account configured']")
	public static WebElement accountConfiguredTextConnectorsPage;

	@FindBy(xpath = "//span[@title='PubNub_1   '][text()='PubNub_1']")
	public static WebElement pubNubAccountNameConfigured;

	@FindBy(xpath = "//span[@class='workflow-inner-detail-title'][text()='WORKFLOW_NODEJS_BACKLOG_TESTDATA']")
	public static WebElement workflowNameUsingPubnubAccount;

	@FindBy(xpath = "//span[@class='workflow-inner-operation-name'][text()='Get Message History']")
	public static WebElement operationNameUsingPubnubAccount;

	@FindBy(xpath = "//span[@class='workflow-inner-detail-title'][text()='FLOWSERVICES_NODEJS_BACKLOG_TESTDATA']")
	public static WebElement flowServiceNameUsingPubnubAccount;

	@FindBy(xpath = "//span[@class='workflow-inner-operation-name'][text()='Publish Message']")
	public static WebElement operationflowServiceNamePubnubAccount;

	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-chevron-down icon-chevron-down']")
	public static WebElement connectorsPageDownArrowService;   

	@FindBy(xpath = "//span[@class='icon-chevron dlt-icon-chevron-double-right']")
	public static WebElement hideAvailableConnectorPanelButton;

	@FindBy(xpath = "//span[@title='Delete']")
	public static WebElement deleteIconPubnubAccount;

	@FindBy(id = "connector-delete-modal")
	public static WebElement usagesModal;

	@FindBy(xpath = "//td[text()='WORKFLOW_NODEJS_BACKLOG_TESTDATA']")
	public static WebElement workflowUsedPubnub;

	@FindBy(xpath = "//td[text()='FLOWSERVICES_NODEJS_BACKLOG_TESTDATA']")
	public static WebElement flowservicesUsedPubnub;

	@FindBy(xpath = "//button[text()='Cancel']")
	public static WebElement cancelbuttonUsagesModal;

	@FindBy(linkText = "Configurations")
	public static WebElement configurationsLink;

	@FindBy(xpath = "//span[@class='no-table-data']")
	public static WebElement nocertificatesMessage;

	@FindBy(xpath = "//span[text()='Workflow']")
	public static WebElement workflowOptionsConfigPage;

	@FindBy(linkText = "Connections")
	public static WebElement connectionsLink;

	@FindBy(xpath = "//span[text()='1 Accounts configured']")
	public static WebElement accountsConfiguredText;

	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-chevron-down icon-chevron-down']")
	public static WebElement downArrowButtonConfig;

	@FindBy(xpath = "//div[@id='Get Message History']//span[@class='delite-icon dlt-icon-caret-down']")
	public static WebElement select2DropdownButton;

	@FindBy(xpath = "//button[@aid='PubNub_1-dropdown-deleteBtn']")
	public static WebElement pubNubConnectorDeleteIcon;

	@FindBy(xpath = "//span[@class='icon-chevron dlt-icon-chevron-double-left']")
	public static WebElement expandAvailableConnectorPanel;

	@FindBy(xpath = "//div[@class='panel-open']//input[@class='search-box-input']")
	public static WebElement connectorPanelSearchBox;

	@FindBy(xpath = "//span[@class='predefined-search-icon delite-icon dlt-icon-search']")
	public static WebElement searchIcon;

	@FindBy(xpath = "//div[@title='PubNub']")
	public static WebElement searchedConnectorService;

	@FindBy(xpath = "//div[@data-testid='add-account-modal']")
	public static WebElement addNewConnectionModal;

	@FindBy(xpath = "//input[@aid='Account Name']")
	public static WebElement connectionTextBoxName;

	@FindBy(xpath = "//*[text()='Account with name PubNub_1 already exists.']")
	public static WebElement pubnubNameExistsMessage;

	@FindBy(xpath = "//button[text()='Cancel']")
	public static WebElement closeAccountNameModal;

	@FindBy(xpath = "//button[@class='modal-action modal-close btn btn-link btn-sm']")
	public static WebElement closeEditAccountModal;

	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-plus']")
	public static WebElement addConnectionButton;

	@FindBy(xpath = "//div[@data-testid='activity-add-authorization']")
	public static WebElement addNewConnectionModalConfigPage;

	@FindBy(linkText = "Projects")
	public static WebElement projectsLink;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//a[@name='BACKLOG_TEST_DATA-publish']")
	public static WebElement projectPublishProject;

	@FindBy(xpath = "//div[@class='published-select-table']")
	public static WebElement assetsModal;

	@FindBy(xpath = "//button[text()='Next']")
	public static WebElement nextButton;

	@FindBy(xpath = "//button[@class='btn btn-danger btn-sm btn-primary']")
	public static WebElement saveAndContinueButton;

	@FindBy(xpath = "//button[text()='Publish']")
	public static WebElement publishButton;

	@FindBy(xpath = "//a[@name='BACKLOG_TEST_DATA-more-icon']")
	public static WebElement projectEllipsis;

	@FindBy(xpath = "//span[text()='Assets']")
	public static WebElement assetsModalString;

	@FindBy(xpath = "//span[text()='Assets Name']")
	public static WebElement dependenciesModalString;

	@FindBy(xpath = "//input[@aid='Name']")
	public static WebElement deploymentNameTextBox;

	@FindBy(xpath = "//*[text()='Project Published Successfully']")
	public static WebElement projectPublishMessage;

	@FindBy(xpath = "//span[text()='BACKLOG_TEST_DATA']")
	public static WebElement projectDeployedName;

	@FindBy(xpath = "//div[@id='Connect to PubNub']")
	public static WebElement connectoToPubnubDropDown;

	@FindBy(xpath = "//input[@id='react-select-7-input']']")
	public static WebElement select2DropDownPleaseSelectOption;

	@FindBy(xpath = "//span[@title='Publish Message']//span[@class='highlight']")
	public static WebElement addAccountScreenDeployment;

	@FindBy(xpath = "//a[@class='add-icon']")
	public static WebElement addNewConnectionIcon;

	@FindBy(xpath = "//div[@data-testid='activity-add-authorization']")
	public static WebElement addNewConnectionDeploymentModal;

	@FindBy(xpath = "//input[@aid='Publish Key']")
	public static WebElement publishKeyTextBox;

	@FindBy(xpath = "//input[contains(@aid,'Subscribe Key')]")
	public static WebElement subscriberKeyTextBox;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement addNewButtonConnection;

	@FindBy(xpath = "//*[text()='Account saved successfully.']")
	public static WebElement accountSavedMessage;

	@FindBy(xpath = "//button[text()='Deploy']")
	public static WebElement deployButton;

	@FindBy(xpath = "//*[text()='Project deployed successfully']")
	public static WebElement deployMessage;
}