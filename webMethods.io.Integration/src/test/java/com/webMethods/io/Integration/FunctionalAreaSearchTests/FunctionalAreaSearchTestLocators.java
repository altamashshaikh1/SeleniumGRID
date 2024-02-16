package com.webMethods.io.Integration.FunctionalAreaSearchTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FunctionalAreaSearchTestLocators 
{
	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block;

	@FindBy(id = "username")
	public static WebElement emailTextBox;

	@FindBy(id = "password")
	public static WebElement password;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement createNewProjectButton;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[@title='HarshadUIAutomation']")
	public static WebElement myUIautomationproject;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement createNewWorkflow;

	@FindBy(xpath = "//a[text()=' Create New Workflow']")
	public static WebElement createNewWorkflowButton;

	@FindBy(xpath = "//span[@title='ActiveCampaign']")
	public static WebElement activitiesPanel;

	@FindBy(id = "canvasContainer")
	public static WebElement canvasAssets;

	@FindBy(id = "focusOn")
	public static WebElement actionSearchTextBox;

	@FindBy(xpath = "//span[@title='Coupa']")
	public static WebElement actionSearched;

	@FindBy(id="a0")
	public static WebElement CoupaActionId;

	@FindBy(xpath="//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnector;

	@FindBy(id="stop")
	public static WebElement stopAction;

	@FindBy(xpath = "//div[text()='Select Version']")
	public static WebElement sel_version;

	@FindBy(xpath = "//div[text()='29']")
	public static WebElement coupa_v29;

	@FindBy(xpath = "//a[@title ='Add  Custom Action']")
	public static WebElement customOperAddOption;

	@FindBy(xpath = "//div[@class = 'custom-operation-inner-detail']")
	public static WebElement customOperModal;

	@FindBy(xpath = "//div[text()= 'Please Select']")
	public static WebElement accSelDropdown;

	@FindBy(xpath = "//div[text()='Coupa_1']")
	public static WebElement coupaAccSelect;

	@FindBy(xpath = "//input[@aid = 'Name']")
	public static WebElement custOperName;

	@FindBy(xpath = "//div[text()='Select Value']")
	public static WebElement functionAeraDropdown;

	@FindBy(xpath = "//input[@aid = 'Select-functional-area']")
	public static WebElement functionAreaSearchbox;

	@FindBy(xpath = "//div[text()='No data found']")
	public static WebElement noResultMsg;

	@FindBy(xpath = "//div[text() = 'Reference Data']")
	public static WebElement refData;

	@FindBy(xpath = "//div[text() = 'Transaction Data']")
	public static WebElement transData;

	@FindBy(xpath = "//div[text() = 'Shared Resources']")
	public static WebElement sharedResource;

	@FindBy(xpath = "//div[text() = 'Reference Data']")
	public static WebElement selValue;

	@FindBy(xpath = "//div[text() = 'Transaction Data']")
	public static WebElement selValue1;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Next')]")
	public static WebElement nextButton;

	@FindBy(xpath = "//div[@class = 'select-action-main-content']")
	public static WebElement selectOperationModal;

	@FindBy(xpath = "//span[@class='radio-btn-label-text'][contains(.,'Add Approver For Expense Report')]")
	public static WebElement strFirstRecinList1;

	@FindBy(xpath = "(//span[@class='radio-btn-label-text'][contains(.,'Create')])[1]")
	public static WebElement firstRecordinList;

	@FindBy(xpath = "(//span[contains(@class,'radio-btn-label-text')])[1]")
	public static WebElement firstRecordinList1;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Previous')]")
	public static WebElement previousButton;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Cancel')]")
	public static WebElement cancelButton;

	@FindBy(xpath = "//div[@id='activity-settings']")
	public static WebElement activitySettingModal;

	@FindBy(xpath = "//button[@data-eventmap='activity-settings-modal-cancel']")
	public static WebElement actSettingCancelButton;

	@FindBy(xpath="//button[@class='btn btn-primary save_flow_btn right primary-btn']")
	public static WebElement workflowSave;

	@FindBy(xpath = "//*[text()='Workflow saved.']")
	public static WebElement workflowSaveMessage;

	@FindBy(xpath = "//*[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvas;

	@FindBy(xpath = "//a[@data-activates='1']")
	public static WebElement createdWorkflowEllipsis1;

	@FindBy(xpath = "//span[text()='Delete']")
	public static WebElement deleteWorkflow;

	@FindBy(xpath = "//button[@data-eventmap='metadata-confirmDeleteModal-Are you sure you want to delete this Workflow? (Delete)']")
	public static WebElement deleteOption;

	@FindBy(xpath = "//*[text()='Workflow deleted successfully.']")
	public static WebElement workflowDeleteMessage;
}