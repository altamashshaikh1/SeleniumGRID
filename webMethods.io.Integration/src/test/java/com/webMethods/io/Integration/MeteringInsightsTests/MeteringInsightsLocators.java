package com.webMethods.io.Integration.MeteringInsightsTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MeteringInsightsLocators 
{
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

	@FindBy(linkText = "Monitor")
	public static WebElement monitorLinktext;

	@FindBy(xpath = "//span[text()='Insights']")
	public static WebElement InsightsLinktext;

	@FindBy(xpath = "//a[text()='Overview']")
	public static WebElement OverviewLinktext;

	@FindBy(xpath = "//h4[text()='Last 7 Days']")
	public static WebElement Daysdata;

	@FindBy(xpath = "//div[@class = 'insights_dashboard_day_statistics']")
	public static WebElement DataCards;

	@FindBy(xpath = "//h4[contains(text(), 'This month')]")
	public static WebElement Monthsdata;

	@FindBy(xpath = "//div//span[text() = 'Insights for: ']")
	public static WebElement InsightsTimeData;

	@FindBy(xpath = "//div//span[@class= 'time-range']")
	public static WebElement TimeRange;

	@FindBy(xpath = "//div//span[@class= 'anticon anticon-sync']")
	public static WebElement Refreshbutton;

	@FindBy(xpath = "//div//span[@class= 'anticon anticon-sync']")
	public static WebElement Message;

	@FindBy(xpath = "//canvas[@id = 'dashboard_analytics_area']")
	public static WebElement Timelinecanvas;

	@FindBy(xpath = "//canvas[@id = 'analytics_line']")
	public static WebElement Timelinecanvas2;

	@FindBy(xpath = "//canvas[@id = 'dashboard_analytics_pie']")
	public static WebElement Sharecanvas;

	@FindBy(xpath = "//canvas[@id = 'analytics_slice']")
	public static WebElement Sharecanvas2;
	//canvas[@id = 'analytics_slice']

	@FindBy(xpath = "//h4[text() ='Timeline']")
	public static WebElement Timelinetitle;

	@FindBy(xpath = "//h4[text() = 'Share']")
	public static WebElement Sharetitle;

	@FindBy(xpath = "//h4[contains(text(), 'Total Transactions')]")
	public static WebElement TotalTransactions;

	@FindBy(xpath = "//button[text()='1 week']")
	public static WebElement weekOneOption;

	@FindBy(xpath = "//button[text()='2 week']")
	public static WebElement weekTwoOption;

	@FindBy(xpath = "//button[text()='3 week']")
	public static WebElement weekThreeOption;

	@FindBy(xpath = "//button[text()='4 week']")
	public static WebElement weekFourOption;

	@FindBy(xpath = "//a[text()='Analytics']")
	public static WebElement AnalyticsLinktext;

	@FindBy(xpath = "//button[text()='Filters']")
	public static WebElement Filters;

	@FindBy(xpath = "//span[text()='Service type']")
	public static WebElement ServiceType;

	@FindBy(xpath = "//div[text()='Select service type']")
	public static WebElement SelectServiceType;

	@FindBy(xpath = "//div[text()='Workflows']")
	public static WebElement SelectWorkflow;

	@FindBy(xpath = "//span[text()='Project']")
	public static WebElement Project;

	@FindBy(xpath = "//div[text()='Select project']")
	public static WebElement SelectProject;

	@FindBy(xpath = "//div[text()='Default']")
	public static WebElement SelectDefault;

	@FindBy(xpath = "//button[@class='dlt-button dlt-button-primary apply-filter-button']")
	public static WebElement ApplyButton;

	@FindBy(xpath = "//button[@class='dlt-button dlt-button-secondary reset-filter-button']")
	public static WebElement ResetButton;

	@FindBy(xpath = "//button[text()='Projects']")
	public static WebElement Projects;

	@FindBy(xpath = "//div[@class ='pagination-right']")
	public static WebElement Pagination;

	@FindBy(xpath = "//select[@class='dlt-select-input']")
	public static WebElement Paginationnum;

	@FindBy(xpath = "//select//option[@value = '3']")
	public static WebElement selectnum;

	@FindBy(xpath="//div[@class='circle']")
	public static WebElement accnamevalidatorcircle1;

	public static String accnamevalidatorcircle = "//div[@class='circle']";






}