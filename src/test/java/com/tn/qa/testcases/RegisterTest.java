package com.tn.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.tn.qa.pages.AccountSuccessPage;
import com.tn.qa.pages.HomePage;
import com.tn.qa.pages.RegisterPage;
import com.tn.qa.testbase.TestBase;
import com.tn.utils.Utilities;

public class RegisterTest extends TestBase{
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	
	public RegisterTest() throws Exception {
		super();
	}
	
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccountLink();
        homepage.selectRegisterOption();	
	}
	
	@Test(priority = 1)
	public void registerAccountWithMandatoryFields() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstNameInFirstNameTextBoxField(dataProp.getProperty("firstName"));
        registerpage.enterLastNameInLastNameTextBoxField(dataProp.getProperty("lastName"));
		registerpage.enterEmailInEmailTextBoxField(Utilities.emailDateTimeStamp());
		registerpage.enterTelephoneInTelephoneTextBoxField(dataProp.getProperty("mobileNumber"));
		registerpage.enterPasswordInPasswordTextBoxField(prop.getProperty("validPassword"));
		registerpage.enterPasswordInConfirmPasswordTextBoxField(prop.getProperty("validPassword"));
		registerpage.checkOnPrivacyPolicyCheckbox();
	    registerpage.clickOnContinueButton();
		AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
	    String actualMessage = accountsuccesspage.retrieveAccountSuccessMessage();
		String expectedMessage = dataProp.getProperty("accountSuccessMessage");
		softassert.assertTrue(actualMessage.equals(expectedMessage));
		softassert.assertAll();
	}
	
	@Test(priority = 2)
	public void registerAccountWithAllFields() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstNameInFirstNameTextBoxField(dataProp.getProperty("firstName"));
        registerpage.enterLastNameInLastNameTextBoxField(dataProp.getProperty("lastName"));
		registerpage.enterEmailInEmailTextBoxField(Utilities.emailDateTimeStamp());
		registerpage.enterTelephoneInTelephoneTextBoxField(dataProp.getProperty("mobileNumber"));
		registerpage.enterPasswordInPasswordTextBoxField(prop.getProperty("validPassword"));
		registerpage.enterPasswordInConfirmPasswordTextBoxField(prop.getProperty("validPassword"));
		registerpage.selectYesForNewsLetterRadioButton();
		registerpage.checkOnPrivacyPolicyCheckbox();
	    registerpage.clickOnContinueButton();
	    AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
	    String actualMessage = accountsuccesspage.retrieveAccountSuccessMessage();
		String expectedMessage = dataProp.getProperty("accountSuccessMessage");
		softassert.assertTrue(actualMessage.equals(expectedMessage));
		softassert.assertAll();
	}
	
	@Test(priority = 3)
	public void registerAccountWithExistingEmail() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstNameInFirstNameTextBoxField(dataProp.getProperty("firstName"));
        registerpage.enterLastNameInLastNameTextBoxField(dataProp.getProperty("lastName"));
        registerpage.enterEmailInEmailTextBoxField(prop.getProperty("validEmail"));
        registerpage.enterTelephoneInTelephoneTextBoxField(dataProp.getProperty("mobileNumber"));
		registerpage.enterPasswordInPasswordTextBoxField(prop.getProperty("validPassword"));
		registerpage.enterPasswordInConfirmPasswordTextBoxField(prop.getProperty("validPassword"));
		registerpage.selectYesForNewsLetterRadioButton();
		registerpage.checkOnPrivacyPolicyCheckbox();
	    registerpage.clickOnContinueButton();
	    String actualMessage = registerpage.retrieveDuplicateEmailWarningMessage();
	    String expectedMessage = dataProp.getProperty("duplicateEmailWarningMessage");
		softassert.assertTrue(actualMessage.equals(expectedMessage));
		softassert.assertAll();
	
	}
	
	@Test(priority = 4)
	public void registerAccountWithoutFillingAnyFields() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.clickOnContinueButton();
		
		String actualPrivacyPolicyWarningMessage = registerpage.retrievePrivacyPolicyWarningMessage();
		String expectePrivacyPolicyWarningMessage = dataProp.getProperty("privacyPolicyWarningMessage");
		softassert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectePrivacyPolicyWarningMessage));
		
		String actualFirstNameWarningMessage = registerpage.retrieveFirstNameWarningMessage();
		String expectedFirstNameWarningMessage = dataProp.getProperty("firstNameWarningMessage");
		softassert.assertEquals(actualFirstNameWarningMessage, expectedFirstNameWarningMessage);
		
		String actualLastNameWarningMessage = registerpage.retrieveLastNameWarningMessage();
		String expectedLastNameWarningMessage = dataProp.getProperty("lastNameWarningMessage");
		softassert.assertEquals(actualLastNameWarningMessage, expectedLastNameWarningMessage);
		
		String actualEmailWarningMessage = registerpage.retrieveEmailWarningMessage();
		String expectedEmailWarningMessage = dataProp.getProperty("emailWarningMessage");
		softassert.assertEquals(actualEmailWarningMessage, expectedEmailWarningMessage);
		
		String actualTelephoneWarningMessage = registerpage.retrieveTelephoneWarningMessage();
		String expectedTelephoneWarningMessage = dataProp.getProperty("telephoneWarningMessage");
		softassert.assertEquals(actualTelephoneWarningMessage, expectedTelephoneWarningMessage);
		
		String actualPasswordWarningMessage = registerpage.retrievePasswordWarningMessage();
		String expectedPasswordWarningMessage = dataProp.getProperty("passwordWarningMessage");
		softassert.assertEquals(actualPasswordWarningMessage, expectedPasswordWarningMessage);
		
		softassert.assertAll();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
