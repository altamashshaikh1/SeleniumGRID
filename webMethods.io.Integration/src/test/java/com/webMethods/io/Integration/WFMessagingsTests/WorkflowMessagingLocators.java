package com.webMethods.io.Integration.WFMessagingsTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkflowMessagingLocators 
{
	public static String login_block_1 = "//div[@id='kc-content']";

	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block;

	@FindBy(id = "username")
	public static WebElement usernameInput;

	@FindBy(id = "password")
	public static WebElement passwordInput;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement createNewProjectButton;

	@FindBy(xpath = "//div[@class='loading-indicator-body']")
	public static WebElement loadingindicator;

	@FindBy(id = "new-project")
	public static WebElement projectname;

	@FindBy(xpath ="//button[contains(text(),'Create')]")
	public static WebElement createbutton;

	@FindBy(linkText ="Projects")
	public static WebElement ProjectsPage;

	@FindBy(linkText ="Monitor")
	public static WebElement MonitorPage;

	@FindBy(xpath ="//span[@class='filter-label']")
	public static WebElement Filterdropdown;

	@FindBy(xpath ="//span[text()='Select projects ']")
	public static WebElement projectdropdown;

	@FindBy(xpath ="//span[text()='Select Workflows ']")
	public static WebElement workflowdropdown;

	@FindBy(xpath ="//span[text()='Select Flow services ']")
	public static WebElement flowDropdown;

	@FindBy(xpath ="//span[text()='Select execution status ']")
	public static WebElement executiondropdown;

	@FindBy(xpath ="//input[@class='input-search-textbox']")
	public static WebElement wfsearchbox;

	@FindBy(xpath ="//span[text()='No data found']")
	public static WebElement FilterSpace;

	@FindBy(xpath ="//label[@class='label-checkbox truncate']")
	public static WebElement selectmonitoringvalue;

	@FindBy(xpath ="//*[@name='contextID']")
	public static WebElement contextID;

	@FindBy(xpath ="//button[text()='Apply']")
	public static WebElement applybutton;

	@FindBy(linkText = "Consumer Workflow for Topic")
	public static WebElement topicconsumerexecution;

	@FindBy(linkText = "Queue_PublisherWorkflow")
	public static WebElement queuePublisherexecution;

	@FindBy(linkText = "//span[@class='activity-log-value activity-status success']")
	public static WebElement successfulworkflowexecution;

	@FindBy(linkText = "Consumer Workflow for Queue")
	public static WebElement queueconsumerexecution;

	@FindBy(linkText ="Workflow execution")
	public static WebElement Workflowexecutionpage;

	@FindBy(linkText ="Flow service execution")
	public static WebElement FlowExecutionpage;

	@FindBy(xpath ="//span[@title='MaaS_E2ETest']")
	public static WebElement projectName;

	@FindBy(xpath ="//span[@title='MaaS_NewSubscriberState1']")
	public static WebElement projectName_SubState;

	@FindBy(xpath ="//li/a//span[contains(text(),'Connectors')]")
	public static WebElement connectorsTab;

	@FindBy(linkText ="Events")
	public static WebElement EventsTab;
	
	@FindBy(xpath ="//span[@data-testid='toggle-button-Messaging']")
	public static WebElement MessagingToggleButton;
	
	@FindBy(xpath ="//button[normalize-space()='Messaging destinations']")
	public static WebElement MessagingDestinationsButton;
	
	@FindBy(xpath ="//h3[contains(text(),'Events')]")
	public static WebElement EventsHeader;

	@FindBy(xpath ="//button[contains(text(),'Add queue')]")
	public static WebElement newQueuebutton;

	@FindBy(xpath ="//button[contains(text(),'Save')]")
	public static WebElement savebutton;

	@FindBy(xpath ="//button[contains(text(),'Save and exit')]")
	public static WebElement saveandexitbutton;

	@FindBy(xpath ="//button[@data-eventmap='confirm-modal-exit-without-save']")
	public static WebElement exitwithoutsaving;

	@FindBy(xpath ="//a[text()='Queues']")
	public static WebElement queuestab;

	@FindBy(xpath ="//a[text()='Topics']")
	public static WebElement topicstab;

	@FindBy(xpath ="//div[@aid='published_value']")
	public static WebElement publishedvalue;

	@FindBy(xpath ="//div[@aid='pending_value']")
	public static WebElement pendingValue;

	public static String QueueError ="//div[text()='Could not update the subscriber. Another subscriber, Subscriber_QueueWorkflow, is already subscribed to the queue QueueForWorkflow. There can be only one subscriber for a queue.']";

	public static String errorrule = "//div[text()='Could not save the subscriber. A Default Invocation or a Routing Rule must be specified.']";

	public static String defaultrulename = "//span[text()=': Rule name is not valid.']";

	public static String declarationpage = "//div[@class='modal-main-container dlt-sys-msg modal modal-fixed-footer flow-modal flow-details medium-small with-head delete-project-modal ']//h1[text()='Confirm Default invocation ?']";

	@FindBy(xpath ="//div[@aid='consumed_value']")
	public static WebElement consumedvalue;

	@FindBy(name = "queueName")
	public static WebElement queuetext;

	@FindBy(name = "//span//textarea[@placeholder='Description']")
	public static WebElement subdescription;

	@FindBy(xpath="//div[@class='active'][text()='Queues']")
	public static WebElement queuelist;

	@FindBy(xpath="//li//a[contains(text(),'Topics')]")
	public static WebElement topicsTab;

	@FindBy(xpath ="//button[contains(text(),'Add topic')]")
	public static WebElement newTopicbutton;

	@FindBy(name = "topicName")
	public static WebElement topictext;

	@FindBy(xpath="//div[@class='active'][contains(text(),'Topic')]")
	public static WebElement topicslist;

	@FindBy(linkText ="Subscribers")
	public static WebElement subscribersTab;

	@FindBy(xpath="//button[contains(text(),'Add subscriber')]")
	public static WebElement newSubscriberbutton;

	@FindBy(xpath = "//button[contains(text(),'Add subscriber')]")
	public static WebElement newsubelement; 

	@FindBy(name = "subscriberName")
	public static WebElement subscribertext;

	@FindBy(xpath="//*[@aid='destinationtype']")
	public static WebElement destinationtypedropdown;

	@FindBy(xpath="//span[text()='Destination Type']")
	public static WebElement destinationtypedropdownworkflow;

	@FindBy(xpath="//div[text()='Queue']")
	public static WebElement destinationtypevaluequeue;

	@FindBy(xpath="//div[text()='Topic']")
	public static WebElement destinationtypevaluetopic;

	@FindBy(xpath="//div[@class='flex-basis-430 destination-name']")
	public static WebElement selectdestinationdropdown;

	@FindBy(xpath="//*[@aid='invocationType']")
	public static WebElement selectinvocationdropdown;

	public static String blocker = "//div[@class='ng-tns-c60-18 ng-star-inserted ui-dialog-mask ui-widget-overlay ui-dialog-visible ui-dialog-mask-scrollblocker']";

	@FindBy(xpath="//div[contains(text(),'QueueForWorkflow')]")
	public static WebElement selectqueueDestinationName;

	@FindBy(xpath="//div[contains(text(),'TopicForWorkflow')]")
	public static WebElement selectTopicDestinationName;

	@FindBy(xpath="//*[@aid='invocationType']")
	public static WebElement selectinvocationType;

	@FindBy(xpath="//div[text()='Workflow']")
	public static WebElement invocationsdropdown;

	@FindBy(xpath= "//div[@aid='miniLoader']")
	public static WebElement miniloader;

	@FindBy(xpath="//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath="//h1[text()='New Workflow']")
	public static WebElement newWF;

	@FindBy(xpath="//a[@aid='hideDropdown']")
	public static WebElement moreoptions;

	@FindBy(xpath="//li[@aid='Delete']")
	public static WebElement deleteaction;

	@FindBy(xpath="//button[contains(text(),'Cancel')]")
	public static WebElement Cancelbutton;

	@FindBy(xpath="//*[@title='Add new Workflow']")
	public static WebElement addnewWFbutton;

	@FindBy(xpath="//input[@name='workflowName']")
	public static WebElement Workflownameinsubscriber;	

	@FindBy(xpath="//div[@class='modal-footer action-panel']//button[contains(text(),'Save')]")
	public static WebElement saveconsumerflowbutton;

	@FindBy(name = "workflowName")
	public static WebElement workflowname;

	@FindBy(linkText = "Workflows")
	public static WebElement workflowTab;

	@FindBy(xpath = "//a[text()=' Create New Workflow']")
	public static WebElement createNewWorkflowButton;

	@FindBy(xpath="//input[@class='search-box-input']")
	public static WebElement searchboxInput;

	@FindBy(xpath="//span[@class=' delite-icon dlt-icon-search searchbox-search-icon']")
	public static WebElement searchicon;

	@FindBy(xpath="//span[text()='Consumer Workflow for Queue']")
	public static WebElement qconsumerWF;	


	@FindBy(xpath="//span[text()='Copy of Topic_PublisherWorkflow']")
	public static WebElement clonedpublisherworkflow;

	@FindBy(xpath="//span[text()='Copy of Consumer Workflow for Topic']")
	public static WebElement clonedconsumerworkflow;

	@FindBy(xpath="//span[text()='Consumer Workflow for Topic']")
	public static WebElement TopicconsumerWF;

	@FindBy(xpath="//span[text()='Messaging_Filter']")
	public static WebElement RoutingruleWF;

	@FindBy(xpath="//span[@class='edit-flow']")
	public static WebElement editWorkflow;

	@FindBy(xpath= "//span[@title='ActiveCampaign']")
	public static WebElement activitiesPanelString;

	@FindBy(xpath= "//a[@title='Add Workflow']")
	public static WebElement newWorkflow; 

	public static String searchBox = "//input[@class='search-box-input']";

	@FindBy(id = "focusOn")
	public static WebElement actionSearchTextBox;

	public static String actionSearchedString = "//span[@title='Logger']";

	@FindBy(xpath = "//span[@title='Logger']")
	public static WebElement actionSearched;

	@FindBy(id="a0")
	public static WebElement loggerActionId;

	public static String firstActionConnectorString = "//*[name()='svg']//*[@aid='a0-Connector']";

	@FindBy(xpath="//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnector;

	@FindBy(id="stop")
	public static WebElement stopAction;

	@FindBy(xpath="//span[contains(text(),'Save')]")
	public static WebElement saveworkflowbutton;

	@FindBy(xpath="//*[@title='Settings'][@data-eventmap='metadata-activitySettingsIcon- (Logger)']")
	public static WebElement OpenloggerAction;

	@FindBy(xpath="//button[contains(text(),'Next')]")
	public static WebElement nextButton;

	@FindBy(xpath="//textarea[contains(text(),'')]")
	public static WebElement loggertextfield;

	@FindBy(xpath="//button[contains(text(),'Done')]")
	public static WebElement doneButton;

	@FindBy(xpath="//span[@class='messaging icon-48 trigger-icon']")
	public static WebElement messagingtriggericon;

	@FindBy(xpath="//i[@data-eventmap='metadata-triggerEditIcon-Messaging Trigger for Messaging_Filter']")
	public static WebElement Edittrigger_btn;

	@FindBy(xpath="	//button[text()='Add Filters']")
	public static WebElement AddFilter_btn;

	@FindBy(xpath="	//span[text()='Filter 2']")
	public static WebElement Secondfilterlabel;

	@FindBy(xpath="	//div[@aid='Name']//input[@class='inputElement textbox-edit'][@value='']")
	public static WebElement Filtername;

	@FindBy(xpath="	//div[@aid='Filter Expression']//input[@class='inputElement textbox-edit'][@value='']")
	public static WebElement FilterExpression;

	public static String Triggerlabel = "//span[text()='Select Trigger']";


	@FindBy(xpath="//button[@class='btn btn-primary btn-sm']")
	public static WebElement workflowdoneButton;

	@FindBy(xpath="//div[@class='loader-back-drop']")
	public static WebElement canvasloader;

	@FindBy(xpath="//*[@data-eventmap='leave-canvas']")
	public static WebElement exitworkflow;

	@FindBy(xpath="//a[text()=' Create New Workflow']")
	public static WebElement createnewwfbtn;

	@FindBy(xpath="//i[@class='delite-icon dlt-icon-plus']")
	public static WebElement wfplusicon;

	@FindBy(xpath="//div[contains(text(),'Please select')]")
	public static WebElement selectdestination;

	@FindBy(xpath="//div[text()='Please Select']")
	public static WebElement defaultaccountdropdown;	

	@FindBy(xpath="//div[contains(text(),'DefaultAccount')]")
	public static WebElement defaultaccount;

	@FindBy(xpath="//span[@class='select-delite-caret dlt-icon-caret-down ']")
	public static WebElement dropdown;

	@FindBy(xpath="//*[@title='Settings'][@data-eventmap='metadata-activitySettingsIcon- (Messaging)']")
	public static WebElement openmessagingconnector;

	public static String connectorSearchedString = "//span[@title='Messaging']";

	@FindBy(xpath = "//span[@title='Messaging']")
	public static WebElement connectorSearched;

	@FindBy(xpath = "//Span[contains(text(),'string')]")
	public static WebElement StringLabel;

	@FindBy(xpath = "//input[@placeholder='Search']")
	public static WebElement searchplaceholder;

	@FindBy(xpath = "//*[@data-eventmap='canvas-flow-edit-modal']")
	public static WebElement editWFicon;

	@FindBy(xpath = "//input[@data-eventmap='edit-flow-name-text']")
	public static WebElement workflowfName;

	@FindBy(xpath = "//i[@class=' dlt-icon-more-menu']")
	public static WebElement moreactionsicon;

	@FindBy(xpath="//a[@title='Clone Workflow']")
	public static WebElement workflowclonebutton;

	@FindBy(xpath="//i[@data-eventmap='leave-canvas']")//i[@class='flow-icons material-icons delite-icon dlt-icon-chevron-left icons8']
	public static WebElement workflowbackbutton;

	@FindBy(xpath="//span[@class='dlt-icon-close']")
	public static WebElement closeexecution;

	@FindBy(xpath="//i[@class='run-play-icon']")
	public static WebElement runworkflowbutton;

	@FindBy(xpath="//a[@title='QueueForWorkflow']")
	public static WebElement WorkflowQueuemetrics;

	@FindBy(xpath="//a[@title='TopicForWorkflow']")
	public static WebElement WorkflowTopicmetrics;

	@FindBy(xpath="//button[@class='btn btn-primary btn-sm']")
	public static WebElement clonebutton;

	@FindBy(xpath="//input[@class='search-input-inner']")
	public static WebElement clonesearch;

	@FindBy(xpath="//div[@title='Clone_MaaS']")
	public static WebElement selectProject;

	@FindBy(xpath="//*[text()='Subscriber_TopicWorkflow']")
	public static WebElement topicsubscriberwf;

	@FindBy(xpath="//*[text()='Subscriber_QueueWorkflow']")
	public static WebElement queuesubscriberwf;

	@FindBy(xpath="//*[text()='Clear default invocation']")
	public static WebElement clearinvocation;

	@FindBy(xpath="//span[@title='Clone_MaaS']")
	public static WebElement clonedprojectName;

	@FindBy(xpath="//div[@class='activity-box activity-inner-wrapper start-activity ']")
	public static WebElement workflowstartaction;

	@FindBy(xpath="//i[@class='dlt-icon-delete flow-icons']")
	public static WebElement deletetrigger;

	@FindBy(xpath="//button[contains(text(),'Yes')]")
	public static WebElement confirmtriggerdelete;

	//span[@class='select-delite-caret dlt-icon-caret-down ']

	@FindBy(xpath="//input[@aid='project_selection']")
	public static WebElement clonedropdown;

	@FindBy(xpath="//span[@class='warning configuration-error']")
	public static WebElement Warningicon;

	@FindBy(xpath="//span[@class='label small']")
	public static WebElement workflowstatus;

	@FindBy(xpath="//i[@class='arrow right']")
	public static WebElement mappingarrowicon;

	@FindBy(xpath="//input[@class='inputElement textbox-edit']")
	public static WebElement stringinput;


	@FindBy(xpath="//div[@aid='Destination']//input[@class='inputElement textbox-edit']")
	public static WebElement triggerdestinationinput;

	@FindBy(xpath="//a[@class='new-destination-add-link']")
	public static WebElement newdestinationwf;

	@FindBy(xpath="//input[@name='destinationName']")
	public static WebElement destinationinput;

	@FindBy(xpath="//input[@class='inputElement textbox-edit']")
	public static WebElement triggerdestination;

	@FindBy(xpath="//input[@aid='Subscriber name']")
	public static WebElement subscribertitle;

	@FindBy(xpath="//button[contains(text(),'Skip')]")
	public static WebElement skipbutton;

	@FindBy(xpath="//div[@data-eventmap='metadata-OpenLookUpDropdown-Destination']")
	public static WebElement destinationdropdown;


	@FindBy(xpath="//span[@title='Cloned_TopicWF']")
	public static WebElement topiclookupvalue;


	public static String topiclookupstr = "//span[@title='Cloned_TopicWF']";

	@FindBy(linkText ="Integrations")
	public static WebElement IntegrationsTab;

	@FindBy(xpath="//div[contains(text(),'Copy_Queue_ConsumerFlow')]")
	public static WebElement clonedconsumerflow;

	@FindBy(xpath="//button[@class='btn btn-primary save_flow_btn right primary-btn']")
	public static WebElement saveworkflowbutton2;

	@FindBy(xpath="//span[text()='add']")
	public static WebElement addnewsubscriber_button;

	@FindBy(xpath="//span[@class='cross-icon dlt-icon-close']")
	public static WebElement closeicon;



	public static String subscriberStateEnableButton = "//button[contains(text(),'Enable')]";
	public static String subscriberStateDisableButton = "//button[contains(text(),'Disable')]";
	public static String subscriberStateSuspendButton = "//button[contains(text(),'Suspend')]";
	public static String subscriberStateCheckDisabled = "//div[contains(text(),'DISABLED')]";
	public static String subscriberStateCheckEnabled = "//div[contains(text(),'ENABLED')]";
	public static String changeStateDropdown = "//a[@class='dropdown-button  secondary-button']";
	public static String checkSuccessExecutionFlow = "//div[@class='successful-executions'][contains(text(),'1')]";
	public static String messagingConnectorVisible = "//span[@class='item-name'][contains(text(),'Messaging')]";
	public static String queueFlowCreation = "//div[@title='Queue_Flow']";
	public static String queueWorkflowCreation = "//div[@title='QueueForWorkflow']";
	public static String topicFlowCreation = "//div[@title='Topic_Flow']";
	public static String topicWorkflowCreation = "//div[@title='TopicForWorkflow']";
	public static String addSubscriberButton = "//button[contains(text(),'Add Subscriber')]";
	public static String saveSubscriberButton = "//button[contains(text(),'Save')]";
	public static String subscriberCreatedSubscriberforQueue_Flow = "//div[@title='SubscriberforQueue_Flow']";
	public static String subscriberCreatedSubscriberforTopic_Flow = "//div[@title='SubscriberforTopic_Flow']";
	public static String subscriberCreatedSubscriberforQueue_WorkFlow = "//div[@title='Subscriber_QueueWorkflow']";
	public static String subscriberCreatedSubscriberforTopic_WorkFlow = "//div[@title='Subscriber_TopicWorkflow']";
	public static String disableButtonSelected = "//button[@class='btn-toggle btn-selected'][contains(text(),'Disable')]";
	public static String enableButtonSelected = "//button[@class='btn-toggle btn-selected'][contains(text(),'Enabled')]";
	public static String dropdownValueDisable = "//li/a[contains(text(),'Disable selected')]";
	public static String checkTopicTab = "//li//a[contains(text(),'Topics')]";
	public static String newTopicButtonVisible = "//button[contains(text(),'Add Topic')]";
	public static String messagingTabVisible = "//a//span[contains(text(),'Messaging')]";
	public static String workflowPageLoaded = "//a[@title='Add Workflow']";
	public static String projectPageLoaded = "//i[@class='delite-icon dlt-icon-plus']";
	public static String flowPageLoaded = "//a[@title='Add FlowService']";
	public static String queueMessage = "//div[contains(text(),'Queue created successfully')]";
	public static String topicMessage = "//div[contains(text(),'Topic created successfully')]";
	public static String projectMessage = "//div[contains(text(),'Project created successfully')]";
	public static String flowCreationMessage = "//p[@aria-label='FlowService created']";
	public static String workflowExecutionMessage = "//div[contains(text(),'Workflow testing completed.')]";
	public static String workflowCreationMessage = "//div[contains(text(),'Workflow saved')]";
	public static String flowCanvasLoaded = "//h2[contains(text(),'Start creating the FlowService')]";
	public static String flowStepLoaded = "//input[@placeholder='Type or browse to create the first step']";
	public static String treeMappingCloseIcon = "//a[@title='Close pipeline view']";
	public static String checkMessagingConn = "//i[@class='com.softwareag.messaging_v1 connector-icon']";
	public static String checkOpenMapping = "//span[@class='float-right step-action-icon ut-edit-pipeline-icon ut-pipeline ng-star-inserted']";
	public static String checkMappingOpen = "//h6[contains(text(), 'Add Pipeline Input')]";
	public static String waitFlowExecution = "//div[contains(text(),'Details')]";
	public static String qconsumerWFVisible = "//span[text()='Consumer Workflow for Queue']";
	public static String qpublisherWFVisible = "//span[text()='Queue_PublisherWorkflow']";
	public static String topicconsumerWFVisible = "//span[text()='Consumer Workflow for Topic']";


	//SubsriberState Locaters:

	@FindBy(xpath = "//a[@title='SubscriberforQueue_Flow']")
	public static WebElement subscriberforQueueFlow;

	@FindBy(xpath = "//button[contains(text(),'Enable')]")
	public static WebElement subscriberStateEnableButtonClick;

	@FindBy(xpath = "//button[contains(text(),'Disable')]")
	public static WebElement subscriberStateDisableButtonClick;

	@FindBy(xpath = "//button[contains(text(),'Suspend')]")
	public static WebElement subscriberStateSuspendButtonClick;

	@FindBy(xpath = "//button[contains(text(),'Confirm')]")
	public static WebElement confirmStateChange;

	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	public static WebElement confirmStateChangeOk;

	@FindBy(xpath = "//div[@class='new-radio-button']//label[@class='new-checkbox-label']")
	public static WebElement selectAllSubscribers;

	@FindBy(xpath = "//ul[@id='subscriberStateDropdown']")
	public static WebElement clickchangeStateDropdown;


	@FindBy(xpath="//span[@class='drop-filed-icon dlt-icon-dropdown']")
	public static WebElement clickchangeStateDropdown1;

	@FindBy(xpath = "//li/a[contains(text(),'Disable selected')]")
	public static WebElement disableAllSelectedSubscribers;

	@FindBy(xpath="//span[contains(text(), 'ALL')]")
	public static WebElement dropdownALL;

	@FindBy(xpath="//div[@id= 'APPS']")
	public static WebElement dropdownAPPS;

	@FindBy(xpath="//i[@class='com.softwareag.messaging_v1 connector-icon']")
	public static WebElement dropdownAPPSSelect;

	@FindBy(xpath ="//a[contains(text(),'FlowServices')]")
	public static WebElement newFlowServices;

	@FindBy(xpath="//a[@class='ut-icon-action-bar_run']")
	public static WebElement executeFlowButton;

	@FindBy(linkText = "Monitor")
	public static WebElement monitorLinktext;

	@FindBy(xpath="//a[contains(text(), 'FlowService execution')]")
	public static WebElement flowServiceExecution;

	@FindBy(xpath="//a[contains(text(), 'Workflow execution')]")
	public static WebElement workflowExecution;

	@FindBy(xpath ="//span[@class='filter-label']")
	public static WebElement filters;

	@FindBy(xpath="//input[@class='input-search-textbox']")
	public static WebElement inputProjectFilter;

	@FindBy(xpath = "//div[@class='checkbox-list-dropdown']//label[@class='label-checkbox truncate']")
	public static WebElement selectProjectFilter;

	@FindBy(xpath="//button[@data-testid='apply_filter']")
	public static WebElement applyFilterButton;

	@FindBy(xpath="//span[contains(text(),'Select projects ')]")
	public static WebElement projectPlaceholder;


	@FindBy(xpath="//label[@for='Subscriber_QueueWorkflow']")
	public static WebElement subscriberWFQueue;

	@FindBy(xpath="//button[@class='test-button btn play-icon run_flow ']")
	public static WebElement runWorkflow;

	@FindBy(xpath="//a[@name='MaaS_NewSubscriberState1-more-icon']//i[@class='dlt-icon-more-menu']")
	public static WebElement projectOptions;

	@FindBy(xpath="//span[@title='Delete']")
	public static WebElement delProject;

	@FindBy(xpath="//button[@class='btn btn-danger btn-sm delete-btn-prmy secondary-btn']")
	public static WebElement confirmDelete;

	@FindBy(xpath="//span[text()='Queue_PublisherWorkflow']")
	public static WebElement QueuePublisherWF;

	public static String integrationsTab ="//span[contains(text(),'Integrations')]";

	//i[@name='']

	//Error Handling Locators  -

	@FindBy(xpath="//i[@title='Subscriber settings']")
	public static WebElement EH_settingicon;

	@FindBy(xpath="//span[@aid='fatalErrorHandling']")
	public static WebElement fatalerrorhandler;

	@FindBy(xpath="//input[@id='retryAttempts']") 
	public static WebElement retryAttempts;

	@FindBy(xpath="//input[@id='retryInterval']")
	public static WebElement retryInterval;

	@FindBy(xpath="//button[text()='Ok']")
	public static WebElement Okbutton;

	@FindBy(xpath="//div[text()='Throw exception']")
	public static WebElement retryfailuredropdown;

	@FindBy(xpath="//div[text()='Suspend and retry later']")
	public static WebElement suspendandretrylater;

	@FindBy(xpath="//span[text()='Error handling']")
	public static WebElement EHSTab;

	@FindBy(xpath="//i[@name='SubscriberforQueue_Flow_edit']")
	public static WebElement Subscriberedit;

	@FindBy(xpath="//button[text()='Edit']")
	public static WebElement editSubscriberButton;

	@FindBy(xpath="//i[@name='Subscriber_TopicWorkflow_edit']")
	public static WebElement WorkflowSubscriberedit;

	@FindBy(xpath="//button[@id='addRoutingRuleBtn']")
	public static WebElement Addroutingrule;

	@FindBy(xpath="//i[@title='Delete']")
	public static WebElement DeleteRule;

	@FindBy(xpath="//input[@id='routingRuleName']")
	public static WebElement RoutingRulename;

	@FindBy(xpath="//div[@class='invocation-type rule-invocation-type']//div[text()='Workflow']")
	public static WebElement R_Ruleworkflowdropdown;

	@FindBy(xpath="//div[@class='invocation-type rule-invocation-type']//div[text()='FlowService']")
	public static WebElement R_RuleFlowdropdown;

	@FindBy(xpath="//*[@aid='invocationType']//div[text()='Workflow']")
	public static WebElement WFRuleInvocation;

	@FindBy(xpath="//textarea[@id='routingRuleFilter']")
	public static WebElement Routingruletext;

	@FindBy(xpath="//button[text()='Yes']")
	public static WebElement yesEditbutton;

	@FindBy(xpath="//i[@name='Filter_WF_edit']")
	public static WebElement EditWFRule;

	public static String ErrorConsFlow ="//a[text()='ErrorHandlerCons']";

	@FindBy(xpath="//button[text()='Add']")
	public static WebElement Addrulebttn;

	@FindBy(xpath="//a[@title='SubscriberforQueue_Flow']")
	public static WebElement SubscriberforQueue_Flowlink;

	@FindBy(xpath="//button[@class='btn-toggle btn-selected']")
	public static WebElement Suspended;

	@FindBy(xpath="//button[@class='btn-toggle'][text()='Enable']")
	public static WebElement EnableSubscriber;

	@FindBy(xpath="//a[text()='ErrorHandlerCons']")
	public static WebElement errorConsFlow;

	@FindBy(xpath="//span[contains(text(),'throwExceptionForRetry')]")
	public static WebElement errorexceptionservice;

	@FindBy(xpath="//span[contains(text(),'Queue_Publisher')]")
	public static WebElement queuepublisherflowlabel;

	@FindBy(xpath="//textarea[@placeholder='Enter value here']")
	public static WebElement Setvalue;

	@FindBy(xpath="//span[text()='Exit ']")
	public static WebElement exitFunction;

	@FindBy(xpath="//span[@id='step-desc-1']")
	public static WebElement firstStep;

	@FindBy(xpath="//i[@title='Show Dropdown']")
	public static WebElement Exitdropdown;

	@FindBy(xpath="//span[text()='FAILURE']")
	public static WebElement Failurestatus;

	@FindBy(xpath="//input[@title='Enter failure message']")
	public static WebElement failuremessage;
	@FindBy(xpath="//input[@placeholder='Type to choose action']")
	public static WebElement ExitwithFail;

	@FindBy(xpath="//span[@ptooltip='Click for more options']")
	public static WebElement Moreoptions;

	public static String deletebutton = "//a[@title='Delete']";

	@FindBy(xpath="//a[@title='Delete']")
	public static WebElement Deleteoption;

	@FindBy(xpath="//button[text()=' Ok ']")
	public static WebElement Okdeletebttn;

	@FindBy(xpath="//div[@class='activity-log-value']")
	public static WebElement Transactioncount_Value;

	@FindBy(xpath="//a[text()='ErrorHandlerCons']")
	public static WebElement Flowexecutionlink;

	public static String successmesage = "//div[text()='Successfully enabled the subscriber.']";

	public static String pendingValue0 = "//div[@aid='pending_value'][text()='0']";

	@FindBy(xpath="//div[text()='NOT CONFIGURED']")
	public static WebElement unConfiguredSubscriber;

}




