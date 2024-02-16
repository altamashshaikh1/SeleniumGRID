package com.webMethods.io.Integration.DynamicAccFieldsTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DynamicAccountSelectAllFieldsTestLocators 
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

	@FindBy(xpath = "//span[@title='RestConnector1']")
	public static WebElement actionSearched;

	@FindBy(xpath = "//span[@title='RestConnector_AWSS3']")
	public static WebElement action1Searched;

	@FindBy(id="a0")
	public static WebElement restActionId;

	@FindBy(id="a0")
	public static WebElement restConnectorActionId;

	@FindBy(id="stop")
	public static WebElement stopAction;

	@FindBy(xpath="//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnector;

	@FindBy(xpath="//a[@title ='Add  Custom Action']")
	public static WebElement custom_oper_add_str;

	@FindBy(xpath="//div[@class='dynamic-conn-text'][contains(.,'Info: Dynamic Connection is enabled for this action. You can now dynamically pass certain authentication and authorization details that will override the existing values of your selected account.')]")
	public static WebElement infoMessage;

	@FindBy(xpath = "//div[@class='select2-common__value-container select2-common__value-container--has-value css-1hwfws3'][contains(.,'automationconnector')]")
	public static WebElement actionListdropdown;

	@FindBy(xpath = "//button[@aid='edit']")
	public static WebElement customOperEditIcon;

	@FindBy(xpath = "//div[@class = 'custom-operation-inner-detail']")
	public static WebElement customOperModal;

	@FindBy(xpath = "//div[@class='pill-switch-convert']")
	public static WebElement dynamicAccOption;

	@FindBy(xpath = "//*[text()='Enable Dynamic Account']")
	public static WebElement dynamicaccText;

	@FindBy(xpath = "//*[text() = 'Off']")
	public static WebElement optionOffText;

	@FindBy(xpath = "//*[text() = 'On']")
	public static WebElement optionOnText;

	@FindBy(xpath = "//span[@aid='convert_to_string_switch']")
	public static WebElement toggleswitch;

	@FindBy(xpath = "//span[text() = '2']")
	public static WebElement secondsteps;

	@FindBy(xpath = "//span[text() = 'Dynamic Account Configuration']")
	public static WebElement secondsteptext;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Next')]")
	public static WebElement nextButton;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Done')]")
	public static WebElement doneButton;

	@FindBy(xpath = "//div[@class = 'select-action-main-content']")
	public static WebElement selectOperationModal;

	@FindBy(xpath = "//input[@name = 'Username']")
	public static WebElement dynamicaccformusernameinputbox;

	@FindBy(xpath = "(//span[text() = 'Name'])[1]")
	public static WebElement firstselectallcheckbox;

	@FindBy(xpath = "(//span[text() = 'Name'])[2]")
	public static WebElement secondselectallcheckbox;

	@FindBy(xpath = "//span[text() = 'Server URL']")
	public static WebElement serverUrl;

	@FindBy(xpath = "//button[contains(.,'Next')]")
	public static WebElement accselectmodalnextbutton;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	public static WebElement accselectmodalcancelbutton;

	@FindBy(xpath = "//button[contains(.,'Previous')]")
	public static WebElement previousbutton;

	@FindBy(xpath = "//button[contains(.,'Done')]")
	public static WebElement testactionmodaldonebutton;

	@FindBy(xpath = "//div[@class='form-section']")
	public static WebElement actionformsection;

	@FindBy(xpath = "//span[text() = '$connection']")
	public static WebElement $connobjtext;

	@FindBy(xpath = "//span[text() = 'Connection']")
	public static WebElement connobjtext;

	@FindBy(xpath = "//span[text() = 'Server URL']")
	public static WebElement serverUrltext;

	@FindBy(xpath = "//span[text() = 'Credentials']")
	public static WebElement credobjText;

	@FindBy(xpath = "//span[text() = 'Password']")
	public static WebElement passwordtext;

	@FindBy(xpath="//button[@class='btn btn-primary save_flow_btn right primary-btn']")
	public static WebElement workflowSave;

	@FindBy(xpath = "//*[text()='Workflow saved.']")
	public static WebElement workflowSaveMessage;

	@FindBy(xpath = "//span[text()='Connection']")
	public static WebElement connectionsection;

	@FindBy(xpath = "//span[text()='Credentials']")
	public static WebElement credentialsection;

	@FindBy(xpath = "//span[text() = 'Connection Retry Count']")
	public static WebElement connretrycountfield;

	@FindBy(xpath = "//input[@name='Connection Retry Count']")
	public static WebElement connretrycountinputbox;

	@FindBy(xpath = "//span[text() = 'Username']")
	public static WebElement usernamefield;

	@FindBy(xpath = "//input[@name='Username']")
	public static WebElement usernameinputbox;

	@FindBy(xpath = "//*[text() = 'Retry on Response Failure']")
	public static WebElement retryresponsefailurefield;

	@FindBy(xpath = "//*[text() = 'Trust store Alias']")
	public static WebElement truststorealiasfield;

	@FindBy(xpath = "//*[text() = 'Keystore Alias']")
	public static WebElement keystorealiasfield;

	@FindBy(xpath = "//*[text() = 'Client Key Alias']")
	public static WebElement clientkeyaliasfield;

	@FindBy(xpath = "//*[text() = 'Hostname verifier']")
	public static WebElement hostnamefield;

	@FindBy(xpath = "//*[text() = 'Enable Compression']")
	public static WebElement enablecompressionfield;

	@FindBy(xpath = "//*[text() = 'Enable SNI']")
	public static WebElement enablesnifield;

	@FindBy(xpath = "//*[text() = 'SNI Server Name']")
	public static WebElement sniservernamefield;

	@FindBy(xpath = "//*[text() = 'AWS S3 Signature']")
	public static WebElement awss3signaturefield;

	@FindBy(xpath = "//*[text() = 'Access Key']")
	public static WebElement accesskeyfield;

	@FindBy(xpath = "//input[@name='Access Key']")
	public static WebElement accesskeyinputbox;

	@FindBy(xpath = "//div[@aid='Access Key']//input [@type='text']")
	public static WebElement accesskeyinputboxonactionconfigmodal;

	@FindBy(xpath = "//div[@aid='Secret Key']//input [@type='password']")
	public static WebElement secretkeyinputboxonactionconfigmodal;

	@FindBy(xpath = "//*[text() = 'Secret Key']")
	public static WebElement secretkeyfield;

	@FindBy(xpath = "//*[text() = 'Region']")
	public static WebElement regionfield;

	@FindBy(xpath = "(//input[contains(@type,'text')])[4]")
	public static WebElement nametextfield;

	@FindBy(xpath = "(//input[contains(@type,'text')])[6]")
	public static WebElement connretrycounttextfield;

	@FindBy(xpath = "(//input[contains(@type,'text')])[7]")
	public static WebElement usernametextfield;

	@FindBy(xpath = "//input[@type='password']")
	public static WebElement passwordfield;

	@FindBy(xpath = "//button[contains(.,'Test')]")
	public static WebElement testButton;

	@FindBy(xpath = "//div[@class='object-key-val']")
	public static WebElement testactionoutputscreen;

	@FindBy(xpath = "//*[text() = 'Random 1 created']")
	public static WebElement testactionresult;

	@FindBy(xpath = "//div[@aid = 'miniloader']")
	public static WebElement miniloader;

	@FindBy(xpath = "//div[contains(@class,'modal-content custom-operation ')]")
	public static WebElement miniloder1_str;

	@FindBy(xpath = "//*[text() = 'Input']")
	public static WebElement testscreenInputtext;

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