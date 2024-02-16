package com.webMethods.io.Integration.EnableDisabledWorkflowTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkflowLogsEnableDisableTestLocators 
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

	@FindBy(xpath = "//a[contains(.,'Log in with environment')]")
	public static WebElement ssoLoginLink;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[@title='HarshadUIAutomation']")
	public static WebElement myUIautomationproject;

	@FindBy(xpath = "//span[@title='ExecutionlogsoptionTestAutomation']")
	public static WebElement executionlogsautomationproject;

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

	@FindBy(xpath = "//span[@title='Trello']")
	public static WebElement action1Searched;

	@FindBy(xpath = "//span[@title='Logger']")
	public static WebElement action2Searched;

	@FindBy(id="a0")
	public static WebElement trelloConnectorActionId;

	@FindBy(id="stop")
	public static WebElement stopAction;

	@FindBy(xpath="//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnector;

	@FindBy(xpath="//div[@class='select2-common__placeholder css-1wa3eu0-placeholder'][contains(.,'Select Action')]")
	public static WebElement selectactionmodal_str;

	@FindBy(xpath = "//div[@class='select2-common__placeholder css-1wa3eu0-placeholder'][contains(.,'Select Action')]")
	public static WebElement selectactionmodal;

	@FindBy(xpath="//input[@tabindex='0']")
	public static WebElement actionsearchfield;

	@FindBy(xpath="//a[@class='add-icon']")
	public static WebElement add_acc;

	@FindBy(xpath="//h1[@class='modal-title'][contains(.,'Add Account')]")
	public static WebElement addAccountModal_str;

	@FindBy(xpath="//input[@aid='Account Name']")
	public static WebElement accountName;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Add')]")
	public static WebElement addAuthButton;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Add')]")
	public static WebElement addButton;

	@FindBy(xpath ="//a[contains(.,'Log in')]")
	public static WebElement trelloLogin;

	@FindBy(xpath ="//h1[contains(.,'Log in to Trello')]")
	public static WebElement loginPage;

	@FindBy(xpath ="//input[@id='user']")
	public static WebElement trelloEmail;

	@FindBy(xpath = "//input[@id='login']")
	public static WebElement trelloLogInButton;

	@FindBy(xpath = "//input[@id='password']")
	public static WebElement trelloPassword;

	@FindBy(xpath = "//span[@class='css-178ag6o'][contains(.,'Log in')]")
	public static WebElement trelloLogInButton2;

	@FindBy(xpath = "//h1[contains(.,'Would you like to give the following application access to your account?')]")
	public static WebElement accessPage;

	@FindBy(xpath = "//input[@id='approveButton']")
	public static WebElement allowAccess;

	@FindBy(xpath = "//input[@id='approveButton']")
	public static WebElement allowButton;

	@FindBy(xpath = "//div[@class='notification-message']")
	public static WebElement notification;

	@FindBy(xpath="//div[@class='notification-message'][contains(.,'Account saved successfully.')]")
	public static WebElement successMessage;

	@FindBy(xpath="//*[contains(text(),'Trello_')]")
	public static WebElement authVerify;

	@FindBy(xpath = "//button[contains(.,'Next')]")
	public static WebElement accselectmodalnextbutton;

	@FindBy(xpath = "//div[@class = 'form-section']")
	public static WebElement actionformsection_str;

	@FindBy(xpath = "//button[contains(.,'Done')]")
	public static WebElement testactionmodaldonebutton;

	@FindBy(xpath="//button[@class='btn btn-primary save_flow_btn right primary-btn']")
	public static WebElement workflowSave;

	@FindBy(xpath = "//*[text()='Workflow saved.']")
	public static WebElement workflowSaveMessage;

	@FindBy(xpath = "//i[@data-eventmap='canvas-flow-edit-modal']")
	public static WebElement editPencilIcon;

	@FindBy(xpath = "//input[@aid='Workflow Name']")
	public static WebElement workflowRenameTextbox;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement workflowRenameDoneButton;

	@FindBy(xpath = "//button[@class='test-button btn play-icon run_flow ']")
	public static WebElement workflowPlayButton;

	@FindBy(xpath = "//*[text()='Workflow testing started.']")
	public static WebElement workflowExecutionStartMessage;

	@FindBy(xpath = "//*[text()='Workflow testing completed.']")
	public static WebElement workflowExecutionCompletedMessage;

	@FindBy(xpath = "//span[text() = 'Trello']")
	public static WebElement Trelloworkflow;

	@FindBy(xpath = "//span[text() = 'Copy of Trello']")
	public static WebElement clonedTrelloworkflow;

	@FindBy(xpath = "//i[@class='flow-icons edit-workflow icons8-edit-workflow']")
	public static WebElement workfloweditoption;

	@FindBy(xpath = "//button[@id='debug-panel-icon']")
	public static WebElement debugPanel;

	@FindBy(xpath = "//div[@class= 'single-workflow-execution']")
	public static WebElement executelogsentry_str;

	@FindBy(xpath = "//span[@class = 'single-workflow-title cursr-pntr']")
	public static WebElement executionlogstime;

	@FindBy(xpath = "//div[@data-testid ='flow_console_log']")
	public static WebElement detailExecutionlogsmodal;

	@FindBy(xpath = "//div[@aid='flowSettingsModal']")
	public static WebElement workflowsettingmodal_str;

	@FindBy(xpath = "//span[text()='Execution settings']")
	public static WebElement worflowexesettingmodal;

	@FindBy(xpath = "//span[text()='Maintain workflow execution logs when executed via webhook or trigger']")
	public static WebElement maintainexelogsoption;

	@FindBy(xpath = "//button[contains(.,'Close')]")
	public static WebElement worflowexesettingmodalclosed;

	@FindBy(xpath = "//div[@aid='Access Key']//input [@type='text']")
	public static WebElement accesskeyinputboxonactionconfigmodal;

	@FindBy(xpath = "//div[@aid = 'miniloader']")
	public static WebElement miniloader;

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

	@FindBy(linkText = "Configurations")
	public static WebElement configtab;

	@FindBy(xpath = "//*[text()='Workflow']")
	public static WebElement workflowOption;

	@FindBy(linkText =  "Connections")
	public static WebElement connectionOption;

	@FindBy(xpath = "//span[@data-testid='toggle-icon']")
	public static WebElement connectionlistloaddropdownarrow;

	@FindBy(xpath = "//span[contains(.,'Trello_')]")
	public static WebElement trelloacc;

	@FindBy(xpath="//span[@class=' delete-icon delite-icon dlt-icon-delete icon-mr mlm']")
	public static WebElement acc_delete_icon;

	@FindBy(xpath="//button[contains(.,'Delete')]")
	public static WebElement acc_delete_btn;

	@FindBy(xpath = "//*[text()='Account removed successfully.']")
	public static WebElement accDeleteMessage;

	@FindBy(xpath = "//input[starts-with(@id,'checkbox-persist_logs')]")
	public static WebElement maintainActitivylogcheckbox;

	@FindBy(xpath = "//label[starts-with(@for,'checkbox-setting_visualization')]")
	public static WebElement visualizationlinecheckbox;

	@FindBy(xpath = "//input[starts-with(@id,'checkbox-setting_visualization')]")
	public static WebElement visualizationlinecheckboxstate;

	@FindBy(xpath = "//button[@data-eventmap = 'flow-settings']")
	public static WebElement workflowsettingsopt;

	@FindBy(xpath = "//div[@aid= 'flowSettingsModal']")
	public static WebElement workflowsettingmodal;

	@FindBy(linkText = "Execution settings")
	public static WebElement workflowExecutionSettings;

	@FindBy(xpath = "//span[text()= 'Maintain workflow execution logs when executed via webhook or trigger']")
	public static WebElement maintain_exe_log_opt_str;

	@FindBy(xpath = "//div[@aid='runTimeLabel']//div//div//div//div")
	public static WebElement workflowruntimeinput;

	@FindBy(xpath = "//div[text()= '3']")
	public static WebElement workflowruntime3;

	@FindBy(xpath = "//div[text()= '12']")
	public static WebElement workflowruntimelist;

	@FindBy(xpath = "//span[text()= 'Show real-time visualization of workflow when executed via webhook or trigger']")
	public static WebElement visualization_line_opt_str;

	@FindBy(xpath = "//button[@data-eventmap = 'settings-modal-close-button']")
	public static WebElement workflowSettingsclosedopt;

	@FindBy(xpath = "//div[text()='Please Select']")
	public static WebElement selectaccountdropdownicon;

	@FindBy(xpath = "//*[contains(text(),'Trello_')]")
	public static WebElement trelloaccfromlist;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm right btn-submit']")
	public static WebElement importButtonToProject;

	@FindBy(xpath = "//*[text()='Workflow imported successfully']")
	public static WebElement workflowimportMessage;

	@FindBy(xpath = "//span[text() = 'Maintain Execution log ON']")
	public static WebElement maintainexelogsONworkflow;

	@FindBy(xpath = "//span[text() = 'Maintain Execution log OFF']")
	public static WebElement maintainexelogsOFFworkflow;

	@FindBy(xpath = "//span[text()='Clone']")
	public static WebElement workflowClone;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement cloneButton;

	@FindBy(xpath = "//*[text()='Workflow cloned successfully.']")
	public static WebElement workflowCloneMessage;

	@FindBy(xpath = "//span[contains(@class,'truncate title-flow-name left ')]")
	public static WebElement workflowslist_str;

	@FindBy(xpath = "//span[contains(@class,'truncate title-flow-name left ')]")
	public static WebElement workflowslist;

	@FindBy(xpath = "//i[@class=' dlt-icon-more-menu']")
	public static WebElement moreMenu;

	@FindBy(xpath = "//a[@class='truncate title-flow-menu'][@title='Delete Workflow']")
	public static WebElement menu;

	@FindBy(xpath = "//h1[contains(.,'Confirm Delete')]")
	public static WebElement confirmDelete;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement addNewProject;

	@FindBy(id = "new-project")
	public static WebElement newProjectModalInputLabel;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement newProjectModalCreateButton;

	@FindBy(xpath = "//*[text()='Project created successfully.']")
	public static WebElement projectCreationSuccessMessage;

	@FindBy(xpath = "//*[text() = 'Project parameters']")
	public static WebElement projectParatext;

	@FindBy(xpath = "//textarea[@class='inputElement textbox-edit']")
	public static WebElement loggerActionTextArea;

	@FindBy(xpath = "//a[text()='Projects']")
	public static WebElement projectDashboardLink;

	@FindBy(xpath = "//span[@title='Execution logs option Test']")
	public static WebElement maintainexeLogTestproject;

	@FindBy(xpath = "//a[@aid='projectCardMenuBtn']")
	public static WebElement projectEllipsis;

	@FindBy(xpath = "//a[@aid='projectpublishBtn']")
	public static WebElement projectPublishProject;

	@FindBy(xpath = "//a[@name='Execution logs option Test-delete']")
	public static WebElement deleteproject;

	@FindBy(xpath = "//div[@class='published-select-table']")
	public static WebElement assetsModal;

	@FindBy(xpath = "//label[@class='new-checkbox-label'][contains(.,'Workflows')]")
	public static WebElement workflowscheckbox;

	@FindBy(xpath = "//button[contains(.,'Next')]")
	public static WebElement nextButton;

	@FindBy(xpath = "//input[@aid='Name']")
	public static WebElement deploymentNameTextBox;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Publish')]")
	public static WebElement ProjectpublishButton;

	@FindBy(xpath = "//*[text()='Project Published Successfully']")
	public static WebElement projectPublishMessage;

	@FindBy(xpath = "//button[@data-testid='project-delete-button']")
	public static WebElement projectDeletecnfbutton;

	@FindBy(xpath = "//*[text()='Project deleted successfully.']")
	public static WebElement projectDeleteSuccessMessage;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Save and continue')]")
	public static WebElement saveandcontinueButton;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Deploy')]")
	public static WebElement projectdeployButton;

	@FindBy(xpath = "//*[text()='Project deployed successfully']")
	public static WebElement projectDeployementMessage;

	@FindBy(xpath = "//*[text()='To view detailed execution logs, go to ']")
	public static WebElement infomsg1;

	@FindBy(xpath = "//*[text()='To view detailed execution logs, go to settings, select the Maintain Execution Logs checkbox, and then rerun the workflow.']")
	public static WebElement infomsgcomplete;

	@FindBy(xpath = "//*[text()=', select the Maintain Execution Logs checkbox, and then rerun the workflow.']")
	public static WebElement infomsg2;

	@FindBy(linkText = "settings")
	public static WebElement workflowsettinglink;

	@FindBy(xpath = "//div[@aid='a0-Get All Boards-disabled']")
	public static WebElement disabledaction;

	@FindBy(linkText = "Messaging")
	public static WebElement messagingtab;

	@FindBy(linkText = "Deployments")
	public static WebElement deploymenytab;

	@FindBy(linkText = "Deploy")
	public static WebElement deployoption;

	@FindBy(xpath = "//a[text() ='Subscribers']")
	public static WebElement subscriberopt;

	@FindBy(xpath = "//button[text()='Add queue']")
	public static WebElement addqueueopt;

	@FindBy(xpath = "//div[text()='New queue']")
	public static WebElement newquewindow;

	@FindBy(xpath = "//input[@name='queueName']")
	public static WebElement queuenameinput;

	@FindBy(xpath = "//button[text()='Save']")
	public static WebElement msgsavebutton;

	@FindBy(xpath = "//div[@class='modal-footer action-panel']//button[contains(text(),'Save')]")
	public static WebElement msgsavebutton1;

	@FindBy(xpath = "//div[@class='action-btns-container']//button[contains(text(),'Cancel')]")
	public static WebElement cancelbutton;

	@FindBy(xpath = "//*[text()='Queue created successfully.']")
	public static WebElement QueueCreatedMsg;

	@FindBy(xpath = "//*[text()='Queue deleted successfully.']")
	public static WebElement QueueDeletedMsg;

	@FindBy(xpath = "//button[text()='Add subscriber']")
	public static WebElement addsubopt;

	@FindBy(xpath = "//input[@id='subscriberName']")
	public static WebElement subnameinput;

	@FindBy(xpath = "//input[@aid='destinationName']")
	public static WebElement destinameinput;

	@FindBy(xpath = "//div[@aid='invocationType']//div//div")
	public static WebElement invocationtype;

	@FindBy(xpath = "//div[text()='Workflow']")
	public static WebElement workflowfromdropdown;

	@FindBy(xpath = "//input[@aid='Workflow name']")
	public static WebElement workflownameinput;

	@FindBy(xpath = "//a[@class='new-destination-add-link']")
	public static WebElement addnewworkflowinsubscriber;

	@FindBy(xpath = "//button[text()='Yes']")
	public static WebElement cancelcnf;

	@FindBy(linkText = "Integrations")
	public static WebElement integrationtab;

	@FindBy(xpath = "//span[text() = 'Workflow from Subscriber']")
	public static WebElement workflowfromSubscriber;

	@FindBy(xpath = "//i[@title='Delete']")
	public static WebElement deletequeueoption;

	@FindBy(xpath = "//div[@aid='stop-Stop']")	
	public static WebElement stopactionindebugger;

	@FindBy(id = "start")	
	public static WebElement startAction;

	@FindBy(xpath = "//li[@data-id='webhook-trigger']")	
	public static WebElement webhookOption;	

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")	
	public static WebElement webhookNextButton;	

	@FindBy(xpath = "//input[@class='input-element-webhook']")	
	public static WebElement webhookURLTextBox;	

	@FindBy(id = "webhook-url")	
	public static WebElement webhookURLTextBox2;

	@FindBy(xpath = "//span[text()='None']")	
	public static WebElement noneOption;

	@FindBy(xpath = "//div[@id='a0']")	
	public static WebElement trelloActionString;

	@FindBy(xpath = "//div[@class='modal-overlay']")	
	public static WebElement webhookattachloder;

	@FindBy(xpath = "//span[@class='import-title']")
	public static WebElement importInputBoxString;

	@FindBy(id = "myRecipiesfileInput")
	public static WebElement uploadFileButton;

	@FindBy(xpath = "//div[@class='connection-content-wrapper']")
	public static WebElement connectorarea_str;

	@FindBy(xpath="//div[@id='connector-delete-modal']")
	public static WebElement acc_delete_cnf_modal;

	@FindBy(xpath="//div[@id='is_private_webhook']//div//div[@aid='on']")
	public static WebElement isprivateswitch;

}