package com.tn.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tn.qa.pages.HomePage;
import com.tn.qa.pages.SearchPage;
import com.tn.qa.testbase.TestBase;

public class SearchProductTest extends TestBase{
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	
	public SearchProductTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));	
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.enterProductNameInSearchBox(dataProp.getProperty("validProduct"));
		homepage.clickOnSearchButton();
		SearchPage searchpage = new SearchPage(driver);
		softassert.assertTrue(searchpage.displayStatusValidProduct());
		softassert.assertAll();
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.enterProductNameInSearchBox(dataProp.getProperty("invalidProduct"));
		homepage.clickOnSearchButton();
		SearchPage searchpage = new SearchPage(driver);
		softassert.assertTrue(searchpage.displayStatusInvalidProduct());
		softassert.assertAll();
	}
	
	@Test(priority = 3, dependsOnMethods = "verifySearchWithInvalidProduct")
	public void verifySearchWithoutProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.clickOnSearchButton();
		SearchPage searchpage = new SearchPage(driver);
		softassert.assertTrue(searchpage.displayStatusInvalidProduct());
		softassert.assertAll();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
