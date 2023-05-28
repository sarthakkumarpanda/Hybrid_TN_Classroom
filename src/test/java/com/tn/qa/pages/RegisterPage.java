package com.tn.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	public WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameTextBoxField;
	
	@FindBy(id = "input-lastname")
	private WebElement lastNameTextBoxField;
	
	@FindBy(id = "input-email")
	private WebElement emailTextBoxField;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneTextBoxField;
	
	@FindBy(id = "input-password")
	private WebElement passwordTextBoxField;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordTextBoxField;
	
	@FindBy(name = "agree")
	private WebElement privacyPolicyCheckBox;
	
	@FindBy(css = "input.btn.btn-primary")
	private WebElement continueButton;
	
	@FindBy(xpath = "//input[@name = 'newsletter' and @value = '1']")
	private WebElement newsLetterRadioButton;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement privacyPolicyWarningMessage;
	
	@FindBy(xpath = "//input[@id = 'input-firstname']/following-sibling::div")
	private WebElement firstNameWarningMessage;
	
	@FindBy(xpath = "//input[@id = 'input-lastname']/following-sibling::div")
	private WebElement lastNameWarningMessage;
	
	@FindBy(xpath = "//input[@id = 'input-email']/following-sibling::div")
	private WebElement emailWarningMessage;
	
	@FindBy(xpath = "//input[@id = 'input-telephone']/following-sibling::div")
	private WebElement telephoneWarningMessage;
	
	@FindBy(xpath = "//input[@id = 'input-password']/following-sibling::div")
	private WebElement passwordWarningMessage;
	
	@FindBy(xpath = "//div[contains(@class, 'alert alert-danger alert-dismissible')]")
	private WebElement duplicateEmailWarningMessage;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void enterFirstNameInFirstNameTextBoxField(String firstNameText) {
		firstNameTextBoxField.sendKeys(firstNameText);
	}
	
	public void enterLastNameInLastNameTextBoxField(String lastNameText) {
		lastNameTextBoxField.sendKeys(lastNameText);
	}
	
	public void enterEmailInEmailTextBoxField(String emailText) {
		emailTextBoxField.sendKeys(emailText);
	}
	
	public void enterTelephoneInTelephoneTextBoxField(String telephoneText) {
		telephoneTextBoxField.sendKeys(telephoneText);
	}
	
	public void enterPasswordInPasswordTextBoxField(String passwordText) {
		passwordTextBoxField.sendKeys(passwordText);
	}
	
	public void enterPasswordInConfirmPasswordTextBoxField(String confirmPasswordText) {
		confirmPasswordTextBoxField.sendKeys(confirmPasswordText);
	}
	
	public void checkOnPrivacyPolicyCheckbox() {
		privacyPolicyCheckBox.click();	
	}
	
	public void clickOnContinueButton() {
		continueButton.click();
	}
	
	public void selectYesForNewsLetterRadioButton() {
		newsLetterRadioButton.click();
	}
	
	public String retrievePrivacyPolicyWarningMessage() {
		String ppwarningmessage = privacyPolicyWarningMessage.getText();
		return ppwarningmessage;
	}
	
	public String retrieveFirstNameWarningMessage() {
		String fnwarningmessage = firstNameWarningMessage.getText();
		return fnwarningmessage;
	}
	
	
	public String retrieveLastNameWarningMessage() {
		String lnwarningmessage = lastNameWarningMessage.getText();
		return lnwarningmessage;
	}
	
	public String retrieveEmailWarningMessage() {
		String emWarningmessage = emailWarningMessage.getText();
		return emWarningmessage;
	}
	
	public String retrieveTelephoneWarningMessage() {
		String telephoneWarningmessage = telephoneWarningMessage.getText();
		return telephoneWarningmessage;
	}
	
	public String retrievePasswordWarningMessage() {
		String pwWarningmessage = passwordWarningMessage.getText();
		return pwWarningmessage;
	}
	
	public String retrieveDuplicateEmailWarningMessage() {
		String dEmailWarningMessage = duplicateEmailWarningMessage.getText();
		return dEmailWarningMessage;
	}

}
