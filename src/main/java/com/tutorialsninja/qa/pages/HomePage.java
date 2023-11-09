package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	// Objects
	@FindBy(xpath="//span[normalize-space()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	private WebElement loginOption;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	private WebElement registerOption;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchItemField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage navigateToLoginPage() {		
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterItemSearch(String searchItemText) {
		searchItemField.sendKeys(searchItemText);
	}
	
	public SearchPage clickOnSearch() {
		searchButton.click();
		return new SearchPage(driver);
	}
	
	public SearchPage searchProduct(String searchItemText) {
		searchItemField.sendKeys(searchItemText);
		searchButton.click();
		return new SearchPage(driver);
	}
}
