package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

//Stopped at 8:20:58, start of part 8

public class LoginTest extends Base{

	public WebDriver driver;
	
	LoginPage loginPage;
	
	@BeforeMethod
	public void setup() {
		
		loadPropertiesFile();
		driver = initialiseBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
	}
	
	@Test(priority=1, dataProvider="validCredentialSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		AccountPage accountPage = loginPage.login(email, password);		
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(), "Edit your account information option is NOT displayed.");
	}
	
	@DataProvider(name="validCredentialSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.login(Utilities.generateTimeStamp(), dataprop.getProperty("invalidPassword"));
		//String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageTest();
		//String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageTest().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected Warning message is NOT displayed.");		
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		loginPage.login(Utilities.generateTimeStamp(), prop.getProperty("validPassword"));
		//String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageTest();
		//String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageTest().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected Warning message is NOT displayed.");
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		loginPage.login(prop.getProperty("validEmail"), dataprop.getProperty("invalidPassword"));
		//String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageTest();
		//String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");		
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageTest().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected Warning message is NOT displayed.");
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutCredentials() {
		
		loginPage.clickOnLoginButton();		
		//String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageTest();
		//String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");		
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageTest().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected Warning message is NOT displayed.");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
