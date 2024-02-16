package com.webMethods.io.Integration.Configuration.BVT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfigurationLocators 
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

	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block_1;

	@FindBy(xpath = "//span[text()='ConnectorsConfigurationsTestData']")
	public static WebElement connectorsConfigurationsProject;

	@FindBy(xpath = "//span[text()='Workflow1']")
	public static WebElement workflow1;

	@FindBy(xpath = "//span[text()='Configurations']")
	public static WebElement configurationsLinkTab;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[text()='You have not added any certificates in this project.']")
	public static WebElement noCert;

	@FindBy(xpath = "//*[text()='Workflow']")
	public static WebElement workflowOption;

	@FindBy(linkText =  "Connections")
	public static WebElement connectionOption;

	@FindBy(xpath = "//span[text()='1 Accounts configured']")
	public static WebElement configurationAccount;

	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-chevron-down icon-chevron-down']")
	public static WebElement connectionDropdown;

	@FindBy(xpath = "//span[text()='Get Form Details']")
	public static WebElement jotformActionName;

	@FindBy(xpath = "//a[@class='add-icon']")
	public static WebElement addNewConnectionIcon;

	@FindBy(xpath = "//input[@aid='API Key']")
	public static WebElement jotFormKeyTextBox;

	@FindBy(xpath = "//div[@class='circle']")
	public static WebElement accnamevalidatorcircle;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement addNewConnectionDoneButton;

	@FindBy(xpath = "//div[text()='Jotform_2']")
	public static WebElement addedJotFormConnection;

	@FindBy(xpath = "//div[@aid='miniLoader']")
	public static WebElement miniLoader;

	@FindBy(xpath = "//div[text()='Jotform_2']")
	public static WebElement addedConnectionDropDown;

	@FindBy(xpath = "//div[text()='JotForm_1']")
	public static WebElement addedConnectionDropDown1;

	@FindBy(xpath = "//button[@aid='Jotform_2-dropdown-editBtn']")
	public static WebElement addedConnectionEditButton;

	@FindBy(xpath = "//button[@class=' modal-action modal-close btn btn-link btn-sm']")
	public static WebElement closeEditModal;

	@FindBy(xpath = "//span[text()='JotForm_1']")
	public static WebElement addedJotfotm2ConnectionName;

	@FindBy(xpath = "//*[text()='Config data updated successfully']")
	public static WebElement updateConfigDataMessage;

	@FindBy(xpath = "//div[text()='JotForm_1']")
	public static WebElement addedJotForm1Connection;

	@FindBy(xpath = "//button[@aid='Jotform_2-dropdown-deleteBtn']")
	public static WebElement connectionDeleteIcon;

	@FindBy(xpath = "//button[text()=' Delete ']")
	public static WebElement connectionDeleteButton;

	@FindBy(xpath = "//button[@aid='JotForm_1-dropdown-deleteBtn']")
	public static WebElement connectionDeleteIcon2;

	@FindBy(xpath = "//*[text()='Workflow1']")
	public static WebElement usedWorkflowName;

	@FindBy(xpath = "//button[text()='Cancel']")
	public static WebElement closeUsageModal;

	@FindBy(linkText = "Webhooks")
	public static WebElement webhooksOption;

	@FindBy(xpath = "//i[@class='delite-icon dlt-icon-copy dp48 ']")
	public static WebElement webhookCopyIcon;

	@FindBy(xpath = "//div[@class='modal-overlay']")
	public static WebElement overlayModal;

	@FindBy(xpath = "//div[@class='modal-main-overlay ']")
	public static WebElement overlayModalProjectParam;

	@FindBy(xpath =  "//span[@class='title-copy']")
	public static WebElement webhookURLTextArea;

	@FindBy(xpath = "//i[@class='delite-icon dlt-icon-more-menu']")
	public static WebElement webhookOptions;

	@FindBy(xpath = "//span[text()='Refresh webhook url']")
	public static WebElement webhookRefreshOption;

	@FindBy(xpath = "//*[text()='Updated successfully']")
	public static WebElement webhookUpdateMessage;

	@FindBy(xpath = "//span[text()='Webhook Authentication']")
	public static WebElement webhookAuthenticationOption;

	@FindBy(id = "webhook-settings-modal")
	public static WebElement webhookSettingsModal;

	@FindBy(xpath = "//button[text()='Cancel']")
	public static WebElement webhookSettingsModalDoneButton;

	@FindBy(xpath = "//span[text()='View Webhook Payload']")
	public static WebElement webhookViewWebhookPayload;

	@FindBy(xpath = "//button[@class='modal-action waves-effect btn btn-link btn-sm']")
	public static WebElement webhookSettingsModalDoneButton2;

	@FindBy(linkText = "Triggers")
	public static WebElement triggersOption;

	@FindBy(xpath = "//span[@title='Clock trigger #1']")
	public static WebElement addedTrigger;

	@FindBy(xpath = "//span[@title='Edit']")
	public static WebElement triggerPencilIcon;

	@FindBy(xpath = "//input[@value='Clock trigger #1']")
	public static WebElement addedTriggerNameStringModal;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement triggerEditDoneButton;

	@FindBy(xpath = "//span[@title='Delete']")
	public static WebElement triggerDeleteIcon;

	@FindBy(xpath = "//button[@class='modal-action modal-close btn btn-link btn-sm']")
	public static WebElement closeUsageModalButton;

	@FindBy(linkText = "Parameter")
	public static WebElement parameterOption;

	@FindBy(xpath = "//*[text()='Parameter1']")
	public static WebElement addedParam;

	@FindBy(xpath = "//*[text()='Description']")
	public static WebElement paramsDescription;

	@FindBy(xpath = "//a[@data-activates='param-0']")
	public static WebElement firstParamsEllipsis;

	@FindBy(xpath = "//i[@class='dd-icons dlt-icon-delete icon-mr icon-pad']")
	public static WebElement paramsDeleteOption;

	@FindBy(xpath = "//button[@class='modal-action modal-close btn btn-default btn-sm']")
	public static WebElement closeParamsUsageModal;

	@FindBy(xpath = "//a[text()='New Parameter']")
	public static WebElement addNewParameterOption;

	@FindBy(xpath = "//input[@aid='Name']")
	public static WebElement paramsInputBox;

	@FindBy(xpath = "//*[text()='Create']")
	public static WebElement createParamsButton;

	@FindBy(xpath = "//button[text()='Update ']")
	public static WebElement updateParamsButton;

	@FindBy(xpath = "//*[text()='Parameter2']")
	public static WebElement addedParam2;

	@FindBy(xpath = "//span[text()='Edit Parameter']")
	public static WebElement paramsEditOption;

	@FindBy(xpath = "//a[@data-activates='param-1']")
	public static WebElement secondParamsEllipsis;

	@FindBy(xpath = "//button[@class='btn secondary-btn delete-btn-prmy btn-sm']")
	public static WebElement paramsDeleteButton;
}