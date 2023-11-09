package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base{
	
	public WebDriver driver;
	
	SearchPage searchPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setup() {
		loadPropertiesFile();
		driver = initialiseBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		homePage = new HomePage(driver);
	}

	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		searchPage = homePage.searchProduct(dataprop.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfProduct(), "There are no products that matches the search criteria.");
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		searchPage = homePage.searchProduct(dataprop.getProperty("invalidProduct"));
		Assert.assertEquals("abcd", dataprop.getProperty("noProductTextInSearchResult"), "A product is displayed.");
		//searchPage.retrieveNoProductText()
	}
	
	@Test(priority=3,dependsOnMethods={"verifySearchWithInvalidProduct","verifySearchWithValidProduct"})
	public void verifySearchWithoutAnyProduct() {
		searchPage = homePage.clickOnSearch();
		Assert.assertEquals(searchPage.retrieveNoProductText(), dataprop.getProperty("noProductTextInSearchResult"), "A product is displayed.");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
