package com.tn.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tn.qa.pages.AccountPage;
import com.tn.qa.pages.HomePage;
import com.tn.qa.pages.LoginPage;
import com.tn.qa.testData.ExcelData;
import com.tn.qa.testbase.TestBase;
import com.tn.utils.Utilities;

public class LoginTest extends TestBase {

	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();

	public LoginTest() throws Exception {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccountLink();
		homepage.selectLoginOption();
	}

	@Test(priority = 1, dataProvider = "TN", dataProviderClass = ExcelData.class, enabled = false)
	public void verifyLoginWithValidCredentials(String username, String password) {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailinEmailTextBoxField(username);
		loginpage.enterPasswordinPasswordTextBoxField(password);
		loginpage.clickOnLoginButton();
        AccountPage accountpage = new AccountPage(driver);
        softassert.assertTrue(accountpage.verifyLoginLinkIsDisplayed());
		softassert.assertAll();
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailinEmailTextBoxField(Utilities.emailDateTimeStamp());
		loginpage.enterPasswordinPasswordTextBoxField(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveNoMatchPasswordWarningMessage() ;
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNomatchWarning");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();
	}

	@Test(priority = 3)
	public void verifyLoginWithValidUsernameInvalidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailinEmailTextBoxField(prop.getProperty("validEmail"));
		loginpage.enterPasswordinPasswordTextBoxField(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveNoMatchPasswordWarningMessage() ;
        String expectedWarningMessage = dataProp.getProperty("emailPasswordNomatchWarning");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();
	}

	@Test(priority = 4)
	public void verifyLoginWithInvalidUsernameValidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailinEmailTextBoxField(Utilities.emailDateTimeStamp());
		loginpage.enterPasswordinPasswordTextBoxField(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveNoMatchPasswordWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNomatchWarning");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();
	}

	@Test(priority = 5)
	public void verifyLoginWithNoCredentials() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveNoMatchPasswordWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNomatchWarning");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
