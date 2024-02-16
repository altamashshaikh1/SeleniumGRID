package com.webMethods.io.Integration.WorkflowsTests.BVT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkflowCRUDTestLocators 
{
	@FindBy(css = ".flow-icons.material-icons.delite-icon.dlt-icon-chevron-left.icons8")
	public static WebElement leaveCanvasButton;

	@FindBy(css = ".delite-icon.dlt-icon-plus")
	public static WebElement addNewWorkflowButton;

	@FindBy(css = "input[placeholder='Search']")
	public static WebElement searchWorkflowTextbox;

	@FindBy(xpath = "//span[@title='ActiveCampaign']")
	public static WebElement activitiesPanelString;

	@FindBy(css = "input[placeholder='Search']")
	public static WebElement inputWorkflowSearchBox;

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

	@FindBy(xpath = "//span[@title='Default']")
	public static WebElement defaultProject;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//a[text()='Projects']")
	public static WebElement projectDashboardLink;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement searchBox;

	@FindBy(xpath = "//span[text()='Default']")
	public static WebElement searchedProjectName;

	@FindBy(xpath = "//span[text()='AltamashUpdateAutomationWorkflow']")
	public static WebElement searchedWorkflow;

	@FindBy(xpath = "//span[@class='inline-block select-dropdown-icon right delite-icon dlt-icon-caret-down']")
	public static WebElement workflowTagsTextBox;

	@FindBy(xpath = "//li[@title='AltamashUpdateAutomationWorkflow']")
	public static WebElement workflowTagName;

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

	@FindBy(xpath = "//span[@title='Logger']")
	public static WebElement actionSearched;

	@FindBy(id="a0")
	public static WebElement loggerActionId;

	@FindBy(xpath="//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnector;

	@FindBy(id="stop")
	public static WebElement stopAction;

	@FindBy(xpath="//button[@class='btn btn-primary save_flow_btn right primary-btn']")
	public static WebElement workflowSave;

	@FindBy(xpath = "//*[text()='Workflow saved.']")
	public static WebElement workflowSaveMessage;

	@FindBy(xpath = "//div[@class='card-content']")
	public static WebElement workflowCardString;

	@FindBy(xpath = "//*[text()='Workflow cloned successfully.']")
	public static WebElement workflowCloneMessage;

	@FindBy(xpath = "//*[text()='Workflow deleted successfully.']")
	public static WebElement workflowDeleteMessage;

	@FindBy(xpath = "//*[text()='Workflow deactivated successfully.']")
	public static WebElement workflowDeactivateMessage;

	@FindBy(xpath = "//*[text()='Workflow activated successfully.']")
	public static WebElement wokrlflowActivateMessage;

	@FindBy(xpath = "//*[text()='Template Export successfully']")
	public static WebElement wokrlflowExportMessage;

	@FindBy(xpath = "//span[@class='cross-icon dlt-icon-close']")
	public static WebElement closeMessage;

	@FindBy(xpath = "//div[@id='notification-container']")
	public static WebElement workflowSuccessMessage;

	@FindBy(xpath = "//button[@class='modal-action btn btn-primary btn-sm']")
	public static WebElement actionNextButton;

	@FindBy(xpath = "//button[text()='Test']")
	public static WebElement actionTestButton;

	@FindBy(xpath = "//div[@class='object-key-val']")
	public static WebElement actionTestData;

	@FindBy(xpath = "//textarea[@class='inputElement textbox-edit']")
	public static WebElement loggerActionTextArea;

	@FindBy(xpath = "//span[@aid='active_switch']")
	public static WebElement workflowSwitch;

	@FindBy(xpath = "//div[@id='notification-container']")
	public static WebElement workflowStatusMessage;

	@FindBy(xpath = "//i[@data-eventmap='canvas-flow-edit-modal']")
	public static WebElement editPencilIcon;

	@FindBy(xpath = "//input[@aid='Workflow Name']")
	public static WebElement workflowRenameTextbox;

	@FindBy(xpath = "//span[text()='AltamashUpdateAutomationWorkflow']")
	public static WebElement renamedWorkflowName;

	@FindBy(xpath = "//span[text()='Untitled Workflow']")
	public static WebElement untitledWorkflow;

	@FindBy(xpath = "//span[@class='switch-handle small']")
	public static WebElement switchIcon;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement workflowRenameDoneButton;

	@FindBy(xpath = "//a[@class='tooltipped edit-flow']")
	public static WebElement workflowEditOption;

	@FindBy(xpath = "//button[@class='test-button btn play-icon run_flow ']")
	public static WebElement workflowPlayButton;

	@FindBy(xpath = "//span[@class='button-collapse ']//i[@data-eventmap='canvas-activty-panel-close']")
	public static WebElement collapseActivityPanel;

	@FindBy(xpath = "//*[text()='Workflow testing started.']")
	public static WebElement workflowExecutionStartMessage;

	@FindBy(xpath = "//*[text()='Workflow testing completed.']")
	public static WebElement workflowExecutionCompletedMessage;

	@FindBy(xpath = "//div[@class='notifications-alert success']")
	public static WebElement workflowExecutionComplete;

	@FindBy(xpath = "//div[@class='logs-content']")
	public static WebElement debuggerPanelDivDom;

	@FindBy(xpath = "//button[@id='debug-panel-icon']")
	public static WebElement debugPanel;

	@FindBy(xpath = "//*[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvas;

	@FindBy(xpath = "//div[@class='flow-console-logs-container']")
	public static WebElement flowInternalContainerPanel;

	@FindBy(xpath = "//a[text()='Workflow Execution']")
	public static WebElement workflowExecutionBackLink;

	@FindBy(xpath = "//div[@class='flow-execution-workflow-container']")
	public static WebElement flowContainerPanel;

	@FindBy(xpath = "//span[text()='success']")
	public static WebElement workflowExecutionStatus;

	@FindBy(xpath = "//a[text()='Export']")
	public static WebElement workflowExecutionExportLogs;

	@FindBy(xpath = "//span[@class='single-workflow-title cursr-pntr']")
	public static WebElement workflowExecutionLogs;

	@FindBy(xpath = "//div[@class='flow-console-logs-container']")
	public static WebElement workflowLogs;

	@FindBy(xpath = "//a[@data-activates='2']")
	public static WebElement createdWorkflowEllipsis;

	@FindBy(xpath = "//span[text()='Clone']")
	public static WebElement workflowExport;

	@FindBy(xpath = "//button[text()='Export']")
	public static WebElement exportButton;

	@FindBy(xpath = "//a[@data-activates='1']")
	public static WebElement createdWorkflowEllipsis1;

	@FindBy(xpath = "//span[text()='Clone']")
	public static WebElement workflowClone;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
	public static WebElement cloneButton;

	@FindBy(xpath = "//a[@data-activates='2']")
	public static WebElement createdWorkflowEllipsis2;

	@FindBy(xpath = "//span[text()='Delete']")
	public static WebElement deleteWorkflow;

	@FindBy(xpath = "//button[@data-eventmap='metadata-confirmDeleteModal-Are you sure you want to delete this Workflow? (Delete)']")
	public static WebElement deleteOption;

	@FindBy(xpath = "//a[@aid='flowCount']")
	public static WebElement workflowCount;

	@FindBy(xpath = "//span[@class='stats']")
	public static WebElement workflowRunCount;  

	@FindBy(xpath = "//div[@class='ql-editor ql-blank']")
	public static WebElement quillEditorTextBox;
}