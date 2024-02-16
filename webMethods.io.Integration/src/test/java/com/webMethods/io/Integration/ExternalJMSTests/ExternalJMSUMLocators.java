package com.webMethods.io.Integration.ExternalJMSTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExternalJMSUMLocators
{
	public static String login_block_1 = "//div[@id='kc-content']";

	@FindBy(linkText = "Log in with environment devrealm1 User.")
	public static WebElement ssoLoginLink;

	@FindBy(xpath = "//div[@class='loading-indicator-body']")
	public static WebElement loadingindicator;

	@FindBy(xpath = "//div[@id='kc-content']")
	public static WebElement login_block;

	@FindBy(id = "username")
	public static WebElement usernameInput;

	@FindBy(id = "password")
	public static WebElement passwordInput;

	@FindBy(id = "kc-login")
	public static WebElement loginButton;

	@FindBy(linkText ="Messaging")
	public static WebElement messagingTab;
	
	@FindBy(linkText ="Events")
	public static WebElement EventsTab;
	
	@FindBy(xpath ="//span[@data-testid='toggle-button-Messaging']")
	public static WebElement MessagingToggleButton;
	
	@FindBy(xpath ="//button[normalize-space()='Messaging destinations']")
	public static WebElement MessagingDestinationsButton;
	
	@FindBy(xpath ="//h3[contains(text(),'Events')]")
	public static WebElement EventsHeader;

	@FindBy(linkText ="Connectors")
	public static WebElement connectorsTab;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement createNewProjectButton;

	@FindBy(linkText ="REST")
	public static WebElement RESTconnectorsTab;


	@FindBy(linkText ="Predefined")
	public static WebElement PredefinedconnectorsTab;

	@FindBy(linkText ="Subscribers")
	public static WebElement subscribersTab;

	@FindBy(xpath="//input[@class='search-box-input']")
	public static WebElement projectSearchTextBox;

	@FindBy(xpath="//div[@aid='miniLoader']")
	public static WebElement miniloader; 

	@FindBy(xpath= "//button[contains(text(),'Add subscriber')]")
	public static WebElement newsubelement;

	@FindBy(name = "subscriberName")
	public static WebElement subscribertext;

	@FindBy(xpath="//button[contains(text(),'Add subscriber')]")
	public static WebElement newSubscriberbutton;

	@FindBy(xpath ="//button[contains(text(),'Save')]")
	public static WebElement savebutton;

	@FindBy(xpath = "//a[@title='Add new Flow service']")
	public static WebElement addflowbutton;

	@FindBy(xpath="//a[@title='Add new Flow service']")
	public static WebElement addnewflowbutton;

	@FindBy(xpath="//input[@name='flowServiceName']")
	public static WebElement Flownameinsubscriber;

	@FindBy(xpath="//div[@class='modal-footer action-panel']//button[contains(text(),'Save')]")
	public static WebElement saveconsumerflowbutton;

	@FindBy(xpath="//span[@class='cross-icon dlt-icon-close']")
	public static WebElement closeicon;

	@FindBy(xpath ="//span[text()='JMS_Automation']")
	public static WebElement JMSprojectName;


	@FindBy(xpath="//*[@aid='connectorType']")
	public static WebElement connectortypedropdown;

	@FindBy(xpath="//div[text()='JMS']")
	public static WebElement connectortypeJMS;


	// JMS Account Locators

	@FindBy(xpath="//button[text()='Yes']")
	public static WebElement yesButton;

	@FindBy(xpath="//a[@title='Add new account']")
	public static WebElement addJMSaccount;

	@FindBy(xpath="//textarea[@id='description']")
	public static WebElement accountdescription;

	@FindBy(xpath="//button[text()='Next']")
	public static WebElement nextButton;

	@FindBy(xpath="//input[@id='jmsAccountName'][@aid='Name']")
	public static WebElement AccountnameInput;

	public static String nextbuttonelement = "//div//button[text()='Next']";

	@FindBy(xpath="//input[@id='providerURL']")
	public static WebElement providerURL;

	@FindBy(xpath="//input[@id='securityPrincipal']")
	public static WebElement accusername;

	@FindBy(xpath="//input[@id='securityCredentials']")
	public static WebElement accpassword;

	@FindBy(xpath="//input[@id='clientID']")
	public static WebElement accclientID;

	@FindBy(xpath="//input[@id='jndi_connectionFactoryLookupName']")
	public static WebElement connfactorylookupname;

	@FindBy(xpath="//div[@aid='off']")
	public static WebElement toggleOffButton;

	@FindBy(xpath="//div[@aid='on']")
	public static WebElement toggleOnButton;

	@FindBy(xpath="//input[@id='maxRetryCount']")
	public static WebElement maxRetryCount;

	@FindBy(xpath="//input[@id='retryInterval']")
	public static WebElement retryInterval;

	@FindBy(xpath="//input[@id='retryInterval'][@value='0']")
	public static WebElement retryIntervalDisabled;

	@FindBy(xpath ="//button[@class='primary-btn mr-10'][contains(text(),'Save')]")
	public static WebElement savejmsaccountbutton;

	@FindBy(xpath ="//div[@aid='Authorize JMS']//div[text()='Please Select']")
	public static WebElement accountSelection;

	@FindBy(xpath ="//div[text()='JMS_1']")
	public static WebElement configacc;

	@FindBy(xpath ="//input[@id='destinationName']")
	public static WebElement jmsDestinationname;

	@FindBy(xpath ="//*[@aid='accountName']//div[@class='select2-common__value-container css-1hwfws3']")
	public static WebElement JMSaccountdropdown;

	@FindBy(xpath="//*[@aid='destinationtype']")
	public static WebElement destinationtypedropdown;

	@FindBy(xpath="//div[text()='Topic']")
	public static WebElement destinationtypevaluetopic;

	@FindBy(xpath = "//div[text()='JMS_1']")
	public static WebElement JMS_1Account;
	
	@FindBy(xpath = "//div[text()='JMS_UM_Provider']")
	public static WebElement JMS_UM_ProviderAccount;

	//JMS Connector Workflow Locators 

	@FindBy(xpath = "//div[text()='Please Select']")
	public static WebElement Connect_toJMS;

	@FindBy(xpath = "//span[@title='JMS']")
	public static WebElement connectorJMS;

	@FindBy(xpath = "//span[@title='destinationName']")
	public static WebElement JMSdestinationNamelabel;

	//#Connectors page Locators

	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-chevron-down icon-chevron-down']")
	public static WebElement expand_connector;

	@FindBy(xpath = "//span[@class='delite-icon dlt-icon-chevron-up icon-chevron-down']")
	public static WebElement Collapse_connector;

	@FindBy(xpath = "//span[@title=' Enable']")
	public static WebElement EnableAccount;

	@FindBy(xpath = "//span[@title=' Disable']")
	public static WebElement  DisableAccount;

	@FindBy(xpath = "//span[@title='Edit']")
	public static WebElement EditAccount;

	@FindBy(xpath = "//span[@title='Delete']")
	public static WebElement DeleteAccount;

	@FindBy(xpath = "//span[@title='JMS']")
	public static WebElement JMS_Title;

	@FindBy(xpath = "//span[@title='JMS']")
	public static WebElement JMSConnector;

	@FindBy(xpath = "//button[text()='New account']")
	public static WebElement jmsNewAccount;

	@FindBy(xpath = "//span[@data-key='isSSL']")
	public static WebElement enableSSLbutton;

	@FindBy(xpath = "//*[@aid='keyStoreAlias']//div[@class='select2-common__value-container css-1hwfws3']")
	public static WebElement Keystore_aliasdropdown;

	@FindBy(xpath = "//div[text()='SSLKey_JMS']")
	public static WebElement jmskey;

	@FindBy(xpath = "//*[@aid='keyAlias']//div[@class='select2-common__value-container css-1hwfws3']")
	public static WebElement keyalias;

	@FindBy(xpath = "//td[text()='JMSTopicSubscriber']")
	public static WebElement Jmssubscriber;

	@FindBy(xpath="//button[contains(text(),'Cancel')]")
	public static WebElement Cancelbutton;

	@FindBy(xpath = "//div[text()='devrealm2.cc.aw-us.webmethodscloud-dev.com']")
	public static WebElement keyalias_dropdown;

	@FindBy(xpath = "//span[text()='JMS_1']")
	public static WebElement jms_1Acc;

	@FindBy(xpath = "//div[@class='circle']")
	public static WebElement minicircle; 

	@FindBy(xpath="//input[@class='search-box-input']")
	public static WebElement searchboxInput;

	@FindBy(xpath = "//div[text()='webMethods Cloud Container UM']")
	public static WebElement cloudConatinerUMText;

	@FindBy(xpath = "//div[text()='Universal Messaging']")
	public static WebElement universalMessagingText;

	@FindBy(xpath = "//input[@placeholder='{{host}}:{{port}}']")
	public static WebElement UMProviderURL;

	@FindBy(xpath = "//span[text()='No']")
	public static WebElement clusteredUMDefaultValue;

	@FindBy(xpath = "//div[@title='Inactive']//input[@name='jndi_enableFollowTheMaster']")
	public static WebElement followTheMasterDefaultValue;

	@FindBy(xpath = "//span[@data-key='clusteredUM']")
	public static WebElement clusteredUMtoggleButton;

	@FindBy(xpath = "//button[text()='Add failover node']")
	public static WebElement addFailoverNodeButton; 

	@FindBy(xpath = "//input[@placeholder='{{host}}:{{port}}'][@value='']")
	public static WebElement failoverURLTextArea;

	@FindBy(xpath = "//div[text()='nhps://']")
	public static WebElement nhpsprotocol;

	@FindBy(xpath = "//div[text()='nsps://']")
	public static WebElement nspsprotocol;

	@FindBy(xpath = "//div[text()='nhp://']")
	public static WebElement nhp_protocol;

	@FindBy(xpath = "//div[text()='nsp://']")
	public static WebElement nsp_protocol;

	@FindBy(xpath = "//span[@class='addon-span left-addon'][text()='nhps://']")
	public static WebElement nhpsfailover;

	@FindBy(xpath = "//span[@class='addon-span left-addon'][text()='nsps://']")
	public static WebElement nspsfailover;

	@FindBy(xpath = "//span[@class='addon-span left-addon'][text()='nhp://']")
	public static WebElement nhpfailover;

	@FindBy(xpath = "//span[@class='addon-span left-addon'][text()='nsp://']")
	public static WebElement nspfailover;

	@FindBy(xpath = "//button[text()='Add realm node']")
	public static WebElement addRealmNodeButton;

	@FindBy(xpath = "//span[text()='JMS_UM_Provider']")
	public static WebElement jms_UMAcc;

	@FindBy(xpath = "//i[@class='dlt-icon-delete action-icon']")
	public static WebElement deleteProvider;

	@FindBy(xpath = "//span[@data-testid='label-jndi_enableFollowTheMaster']")
	public static WebElement enabledFollowTheMaster;

	@FindBy(xpath = "//span[@data-key='jndi_enableFollowTheMaster']")
	public static WebElement followTheMasterToggleButton;

	@FindBy(xpath = "//div[text()='Retry sequentially']")
	public static WebElement retrySequentially;

	@FindBy(xpath = "//div[text()='Retry randomly']")
	public static WebElement retryRandomly;

	@FindBy(xpath = "//input[@aid='Minimum session pool size']")
	public static WebElement minimumSessionPool;

	@FindBy(xpath = "//input[@aid='Maximum session pool size']") 
	public static WebElement maximumSessionPool;

	@FindBy(xpath = "//input[@aid='Idle timeout (in seconds)']")
	public static WebElement idleTimeout;

	@FindBy(xpath = "//span[@class='error-validaion-text']")
	public static WebElement errorValidator;

	@FindBy(xpath="//i[@class=' dlt-icon-delete action-icon']")
	public static WebElement deleteicon;

	@FindBy(xpath="//button[contains(text(),'Delete')]")
	public static WebElement deletebutton;

	@FindBy(linkText ="Integrations")
	public static WebElement IntegrationsTab;

	@FindBy(linkText = "Workflows")
	public static WebElement workflowTab;

	@FindBy(xpath = "//a[text()=' Create New Workflow']")
	public static WebElement createNewWorkflowButton;

	@FindBy(xpath="//i[@class='delite-icon dlt-icon-plus']")
	public static WebElement wfplusicon;

	@FindBy(xpath="//div[@class='loader-back-drop']")
	public static WebElement canvasloader;

	@FindBy(xpath="//*[@data-eventmap='leave-canvas']")
	public static WebElement exitworkflow;

	@FindBy(xpath="//a[text()=' Create New Workflow']")
	public static WebElement createnewwfbtn;

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

	@FindBy(xpath ="//label[@class='label-checkbox truncate']")
	public static WebElement selectmonitoringvalue;

	@FindBy(xpath ="//*[@name='contextID']")
	public static WebElement contextID;

	@FindBy(xpath ="//button[text()='Apply']")
	public static WebElement applybutton;

	@FindBy(linkText ="Flow service execution")
	public static WebElement FlowExecutionpage;

	@FindBy(xpath="//div[@class='activity-log-value']")
	public static WebElement Transactioncount_Value;

	@FindBy(xpath="//a[text()='JMSUMConsumer_Queue']")
	public static WebElement Flowexecutionlink;

	@FindBy(xpath="//a[@aid='hideDropdown']")
	public static WebElement moreoptions;

	@FindBy(xpath="//li[@aid='Delete']")
	public static WebElement deleteaction;

	@FindBy(xpath= "//span[@title='ActiveCampaign']")
	public static WebElement activitiesPanelString;

	public static String newWorkflow = "//a[@title='Add Workflow']";

	public static String searchBox = "//input[@class='search-box-input']";

	@FindBy(id = "focusOn")
	public static WebElement actionSearchTextBox;

	public static String actionSearchedString = "//span[@title='Logger']";

	@FindBy(xpath = "//span[@title='Logger']")
	public static WebElement actionSearched;

	@FindBy(id="a0")
	public static WebElement loggerActionId;

	@FindBy(xpath = "//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnectorString;

	@FindBy(xpath="//*[name()='svg']//*[@aid='a0-Connector']")
	public static WebElement firstActionConnector;


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

	@FindBy(xpath="//button[contains(text(),'Done')]")
	public static WebElement doneButton;

	@FindBy(xpath="//button[@class='btn btn-primary btn-sm']")
	public static WebElement workflowdoneButton;

	@FindBy(xpath="//span[contains(text(),'Save')]")
	public static WebElement saveworkflowbutton;

	@FindBy(xpath="//i[@class='run-play-icon']")
	public static WebElement runworkflowbutton;

	@FindBy(xpath="//i[@data-eventmap='leave-canvas']")//i[@class='flow-icons material-icons delite-icon dlt-icon-chevron-left icons8']
	public static WebElement workflowbackbutton;

	@FindBy(id="stop")
	public static WebElement stopAction;






}
