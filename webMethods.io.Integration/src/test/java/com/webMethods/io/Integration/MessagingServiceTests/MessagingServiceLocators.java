package com.webMethods.io.Integration.MessagingServiceTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagingServiceLocators 
{
	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block;

	@FindBy(xpath = "//a[contains(.,'Log in with environment')]")
	public static WebElement ssoLoginLink;

	@FindBy(id = "username")
	public static WebElement usernameInput;

	@FindBy(id = "password")
	public static WebElement passwordInput;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement createNewProjectButton;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement addWorkflowIcon;

	@FindBy(xpath = "//a[@title='Add Flow service']")
	public static WebElement addFlowServiceIcon;

	@FindBy(xpath ="//input[@placeholder='Search']")
	public static WebElement projectSearchTextBox2;

	@FindBy(xpath = "//span[@title='Messaging Automation']")
	public static WebElement myUIautomationproject1;

	@FindBy(xpath = "//i[@class= 'dlt-icon-more-menu']")
	public static WebElement elopsis;

	@FindBy(xpath = "//span[text()= 'Publish Project']")
	public static WebElement Publish;

	@FindBy(xpath = "//span[text()='Assets']")
	public static WebElement assetsModalString;

	@FindBy(xpath = "//div[@class='published-select-table']")
	public static WebElement assetsModal;

	@FindBy(xpath = "//button[text()='Next']")
	public static WebElement nextButton;

	@FindBy(xpath = "//span[text()='Confirm dependencies']")
	public static WebElement dependencyModalString;

	@FindBy(xpath = "//input[@aid='Name']")
	public static WebElement deploymentNameTextBox;

	@FindBy(xpath = "//button[text()='Publish']")
	public static WebElement publishButton;

	@FindBy(xpath = "//*[text()='Project Published Successfully']")
	public static WebElement projectPublishMessage;

	@FindBy(id = "username")
	public static WebElement emailTextBox;

	@FindBy(id = "password")
	public static WebElement password;

	@FindBy(xpath = "//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath = "//span[text()='Messaging Automation']")
	public static WebElement projectDeployedName;

	@FindBy(xpath = "//h3[text()='Name the Project']")
	public static WebElement projectDeployedvisible;

	@FindBy(xpath = "//button[text()='Save and continue']")
	public static WebElement Saveandcontnu;

	@FindBy(xpath = "//span[text()='Messaging']")
	public static WebElement Messagingconnector;

	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-caret-down']")
	public static WebElement dropdown;

	@FindBy(xpath = "//div[text()='DefaultAccount']")
	public static WebElement selectaccount;

	@FindBy(xpath = "//*[text()='Skip Assets']")
	public static WebElement skipAssets;

	@FindBy(xpath="//div[@class='loading-indicator-body']")
	public static WebElement loadingindicator;

	@FindBy(xpath = "//h3[text()='Configure triggers']")
	public static WebElement ConfigureTrigger;

	@FindBy(xpath = "//h3[text()='Configure parameters']")
	public static WebElement ConfigureParameter;

	@FindBy(xpath = "//button[text()='Yes']")
	public static WebElement Yes;

	@FindBy(xpath = "//div[@class='circle']")
	public static WebElement loader;

	@FindBy(xpath = "//button[text()='Deploy']")
	public static WebElement Deploy;

	@FindBy(xpath = "//*[text()='Project deployed successfully']")
	public static WebElement projectDeployementMessage;

	@FindBy(xpath="//span[contains(text(),'WorkflowRuleConsumer')]")
	public static WebElement WorkflowRuleConsumer;

	@FindBy(xpath="//span[contains(text(),'defaultQueueWorkflowReceiver')]")
	public static WebElement defaultQueueWorkflowReceiver;

	@FindBy(xpath="//span[contains(@class,'title')][contains(text(),'Subscriber_QueueWorkflow')]/ancestor::div/following-sibling::div/span/span[text()='defaultQueueWorkflowReceiver']")
	public static WebElement defaultQueueWorkflowReceiver1;

	@FindBy(xpath="//span[contains(text(),'MessagingPublisherWorkflow')]")
	public static WebElement MessagingPublisherWorkflow;

	@FindBy(xpath="//span[contains(text(),'defaultTopicWorkflowReceiver')]")
	public static WebElement defaultTopicWorkflowReceiver;

	@FindBy(xpath="//*[contains(text(),'new Consumer Workflow')]")
	public static WebElement newConsumerWorkflow;

	@FindBy(xpath="//span[contains(@class,'title')][contains(text(),'Subscriber_TopicWorkflow')]/ancestor::div/following-sibling::div/span/span[text()='defaultTopicWorkflowReceiver']")
	public static WebElement defaultTopicWorkflowReceiver1;

	@FindBy(xpath="//span[@class='edit-flow']")
	public static WebElement editWorkflow1;

	@FindBy(xpath = "//span[@title='AMQP']")
	public static WebElement activitiesPanel;

	@FindBy(xpath = "//button[@class='test-button btn play-icon run_flow ']")
	public static WebElement workflowPlayButton;

	@FindBy(xpath = "//*[text()='Workflow testing started.']")
	public static WebElement workflowExecutionStartMessage;

	@FindBy(xpath = "//*[text()='Workflow testing completed.']")
	public static WebElement workflowExecutionCompletedMessage;

	@FindBy(xpath = "//*[@data-eventmap='leave-canvas']")
	public static WebElement leaveCanvas;

	@FindBy(xpath = "//span[text()='Messaging']")
	public static WebElement Messaging;

	@FindBy(xpath = "//span[text()='1']")
	public static WebElement run1;

	@FindBy(xpath = "//span[text()='Subscriber_QueueWorkflow']")
	public static WebElement SubQueueWF;

	@FindBy(xpath = "//span[contains(@class,'title')][contains(text(),'Subscriber_TopicWorkflow')]/ancestor::div/following-sibling::div/span/span[text()='Topic_Workflow']")
	public static WebElement TopicWF;

	@FindBy(xpath = "//span[contains(@class,'title')][contains(text(),'Subscriber_QueueWorkflow')]/ancestor::div/following-sibling::div/span/span[text()='Queue_Workflow']")
	public static WebElement QueueWF;

	@FindBy(xpath="//span[contains(text(),'MessagingPublisherFlow')]")
	public static WebElement MessagingPublisherFlow;

	@FindBy(xpath="//span[contains(text(),'RoutingRuleQueueConsumer')]")
	public static WebElement RoutingRuleQueueConsumer;

	@FindBy(xpath="//span[contains(text(),'defaultQueueConsumerFlow')]")
	public static WebElement defaultQueueConsumerFlow;

	@FindBy(xpath="//span[contains(text(),'defaultTopicConsumerFlow')]")
	public static WebElement defaultTopicConsumerFlow;

	@FindBy(xpath = "//a[text()='Flow services']")
	public static WebElement FlowServices;

	@FindBy(xpath = "//a[@class='ut-icon-action-bar_run ng-star-inserted']")
	public static WebElement FlowServicesplaybuttom;

	@FindBy(xpath = "//a[@title='Go back']")
	public static WebElement Leaveflow;

	@FindBy(xpath = "//span[text()=' Run Successful ']")
	public static WebElement Flowservicesussessmessage;

	@FindBy(linkText ="Configurations")
	public static WebElement configurationsTab;

	@FindBy(linkText ="Messaging")
	public static WebElement messagingTab;

	@FindBy(linkText ="Subscribers")
	public static WebElement subscribersTab;

	@FindBy(linkText ="Subscriber_TopicWorkflow")
	public static WebElement Subscriber_TopicWorkflow;

	@FindBy(linkText ="Subscriber_QueueFlow")
	public static WebElement Subscriber_QueueFlow;

	@FindBy(xpath ="//a[text()='Edit']")
	public static WebElement editSubscriberLink;

	@FindBy(xpath="//button[text()='Edit']")
	public static WebElement editSubscriberButton;

	@FindBy(xpath = "//button[text()='Suspend']")
	public static WebElement suspendButton;

	@FindBy(xpath = "//button[text()='Disable']")
	public static WebElement disableButton;

	@FindBy(xpath = "//button[text()='Disabled']")
	public static WebElement disabledStatusButton;

	@FindBy(xpath = "//button[text()='Enabled']")
	public static WebElement enabledStatusButton;

	@FindBy(xpath = "//button[text()='Suspended']")
	public static WebElement suspendedStatusButton;

	@FindBy(xpath = "//button[text()='Enable']")
	public static WebElement enableButton;

	@FindBy(xpath = "//button[text()='Confirm']")
	public static WebElement confirmButton;

	@FindBy(xpath = "//span[text()='Configurations']")
	public static WebElement configurationsLinkTab;

	@FindBy(xpath = "//*[text()='Workflow']")
	public static WebElement workflowOption;

	@FindBy(xpath = "//*[text()='Triggers']")
	public static WebElement TriggersOption;

	@FindBy(xpath="//*[@aid='destinationtype']")
	public static WebElement destinationtypedropdown;

	@FindBy(xpath="//span[text()='Destination Type']")
	public static WebElement destinationtypedropdownworkflow;

	@FindBy(xpath="//div[text()='Queue']")
	public static WebElement destinationtypevaluequeue;

	@FindBy(xpath="//div[text()='Topic']")
	public static WebElement destinationtypevaluetopic;

	@FindBy(xpath="//*[@aid='destinationName']")
	public static WebElement selectdestinationdropdown;

	@FindBy(xpath="//*[@aid='invocationType']")
	public static WebElement selectinvocationdropdown;

	@FindBy(xpath="//div[contains(text(),'Queue_FlowService')]")
	public static WebElement selectqueueDestinationName;

	@FindBy(xpath="//div[contains(text(),'Topic_Workflow')]")
	public static WebElement selectTopicDestinationName;

	@FindBy(xpath="//input[@name='flowServiceName']")
	public static WebElement Flownameinsubscriber;

	@FindBy(xpath="//div[text()='A Flow service with name MessagingPublisherFlow already exists']")
	public static WebElement duplicateFlowError;

	@FindBy(xpath="//div[text()='Could not enable the subscriber. Another subscriber, Subscriber_TopicWorkflow, is already subscribed to the queue Queue_FlowService. There can be only one subscriber for a queue.']")
	public static WebElement QueueError;

	@FindBy(xpath ="//button[contains(text(),'Save')]")
	public static WebElement savebutton;

	@FindBy(xpath="//div[@class='modal-footer action-panel']//button[contains(text(),'Save')]")
	public static WebElement saveconsumerflowbutton;

	@FindBy(xpath="//div[@class='modal-footer action-panel']//button[contains(text(),'Cancel')]")
	public static WebElement cancelConsumerflowbutton;

	@FindBy(xpath="//button[text()='Yes']")
	public static WebElement yesButton;

	@FindBy(xpath ="//div[@id='invocationType']//*[text()='Workflow']")
	public static WebElement workflowInvocationType;

	@FindBy(xpath ="//div[@class='invocation-type rule-invocation-type']//*[text()='Workflow']")
	public static WebElement workflowRuleInvocationType;

	@FindBy(xpath="//input[@name='workflowName']")
	public static WebElement Workflownameinsubscriber;

	@FindBy(xpath ="//div[@id='invocationType']//*[text()='FlowService']")
	public static WebElement flowServiceInvocationType;

	@FindBy(xpath ="//div[@class='invocation-type rule-invocation-type']//*[text()='FlowService']")
	public static WebElement flowServiceRuleInvocationType;

	@FindBy(xpath ="//*[text()='Not set']")
	public static WebElement flowServiceInvocationTypeNOTSET;

	@FindBy(xpath ="//div[@class='new-destination-add-button ']")
	public static WebElement addNewConsumerService;

	@FindBy(xpath ="//*[@class='activity-switch'][@data-eventmap='start-activity']")
	public static WebElement WorkflowStartActivity;

	@FindBy(xpath ="//input[@aid='FSInvocationName']")
	public static WebElement pleaseSelectSpace;

	@FindBy(xpath ="//*[@class='dlt-icon-info']")
	public static WebElement infoIcon;

	@FindBy(xpath ="//*[@name='Rule1_edit']")
	public static WebElement editRule;

	@FindBy(xpath ="//*[@name='routingRuleFilter']")
	public static WebElement editRuleFilter;

	@FindBy(linkText ="Integrations")
	public static WebElement IntegrationsTab;

	@FindBy(linkText = "Workflows")
	public static WebElement workflowTab;

	@FindBy(xpath = "//a[@title='Add Workflow']")
	public static WebElement createNewWorkflowButton;

	@FindBy(xpath ="//*[@class='activity-switch'][@data-eventmap='start-activity']")
	public static WebElement StartActivity;
}