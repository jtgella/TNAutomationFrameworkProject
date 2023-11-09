package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;


public class RegisterTest extends Base{
	
	public WebDriver driver;
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	@BeforeMethod
	public void setup() {
		
		loadPropertiesFile();
		driver = initialiseBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		accountSuccessPage = registerPage.registerWithMandatoryFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"), Utilities.generateTimeStamp(), dataprop.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		//String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataprop.getProperty("accountSuccessfullyCreatedHeading"), "Registration failed.");
		accountSuccessPage.clickOnContinueButton();
	}

	@Test(priority=2)
	public void verifyRegisteringAnAccountForAllFields() {
		
		accountSuccessPage = registerPage.registerWithAllFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"), Utilities.generateTimeStamp(), dataprop.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		//String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataprop.getProperty("accountSuccessfullyCreatedHeading"), "Registration failed.");
		accountSuccessPage.clickOnContinueButton();
	}
	
	@Test(priority=3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress() {
		
		registerPage.registerWithAllFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"), prop.getProperty("validEmail"), dataprop.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		//String actualWarningMessage =  registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataprop.getProperty("duplicateEmailWarning")), "Warning message regarding duplicate email is NOT displayed.");
	}

	@Test(priority=4)
	public void verifyRegisteringAnAccountWithoutDetails() {
		
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataprop.getProperty("privacyPolicyWarning"), dataprop.getProperty("firstNameWarning"), dataprop.getProperty("lastNameWarning"), dataprop.getProperty("emailWarning"), dataprop.getProperty("telephoneWarning"), dataprop.getProperty("passwordWarning")), "Warning message(s) are not displayed.");
			
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
