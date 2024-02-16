package com.webMethods.io.Integration.PartnerCertsTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnerCertificateLocators 
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

	@FindBy(xpath = "//span[text()='AS2TestData']")
	public static WebElement partnerCertificateProjectName;

	@FindBy(xpath = "//div[@aid='miniLoader']")
	public static WebElement miniLoaderString;

	@FindBy(linkText = "Configurations")
	public static WebElement configurationsLink;

	@FindBy(xpath = "//span[text()='AutomationTestData']")
	public static WebElement addedPartnerCertString;

	@FindBy(xpath = "//a[text()='New certificate']")
	public static WebElement newCertificateButton;

	@FindBy(xpath = "//a[text()='Partner certificate']")
	public static WebElement newPartnerOption;

	@FindBy(xpath = "//input[@aid='Name']")
	public static WebElement partnerNameInputbox;

	@FindBy(xpath = "//div[@class='circle']")
	public static WebElement accnamevalidatorcircle;

	@FindBy(xpath = "//span[@class='upload-file-name']")
	public static WebElement uploadedCertFileName;

	@FindBy(id = "myRecipiesfileInput")
	public static WebElement uploadRecipesButton;

	@FindBy(xpath = "//textarea[@aid='Description']")
	public static WebElement descriptionField;	

	@FindBy(xpath = "//button[@class='btn btn-primary btn-sm btn-save']")
	public static WebElement doneButton;

	@FindBy(xpath = "//div[text()='Partner certificate 'Partner1' added successfully.']")
	public static WebElement partnerCreationSuccessMessage;

	@FindBy(xpath = "//span[text()='Partner1']")
	public static WebElement createdPartnerCerts;

	@FindBy(xpath = "//div[@class='modal-main-overlay ']")
	public static WebElement overlayModalString;

	@FindBy(xpath = "//a[@data-activates='certificate-Partner1-1']")
	public static WebElement createPartnerEllipsis;

	@FindBy(xpath = "//button[text()='Browse']")
	public static WebElement browseButtonString;

	@FindBy(linkText = "Edit partner")
	public static WebElement editPartnerOption;

	@FindBy(linkText = "Delete")
	public static WebElement deletePartnerOption;

	@FindBy(xpath = "//button[text()='Delete']")
	public static WebElement deletePartnerButton;

	@FindBy(linkText = "Projects")
	public static WebElement projectTabLink;

	@FindBy(xpath = "//div[@class='modal-overlay']")
	public static WebElement overlayModalStringProject;

	@FindBy(xpath = "//a[@title='Add Project']")
	public static WebElement addNewProjectString;

	@FindBy(xpath = "//a[@name='AS2TestData-more-icon']")
	public static WebElement projectEllipsisIcon;

	@FindBy(linkText = "Delete")
	public static WebElement deleteProjectOption;

	@FindBy(xpath = "//button[text()='Delete']")
	public static WebElement deleteButton;

	@FindBy(xpath = "//div[@class='notification-message']")
	public static WebElement projectCertUsages;
}