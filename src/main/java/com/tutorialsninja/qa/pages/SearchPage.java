package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;

	@FindBy(xpath="//div[@class='caption']/descendant::h4")
	private WebElement validProduct;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement noProduct;

	public SearchPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean displayStatusOfProduct() {
		boolean displayStatus = validProduct.isDisplayed();
		return displayStatus;
	}
	
	public String retrieveNoProductText() {
		String noProductWarningText = noProduct.getText();
		return noProductWarningText;
	}
}
