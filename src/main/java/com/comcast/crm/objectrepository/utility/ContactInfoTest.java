package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoTest {
	
	WebDriver driver;
	public ContactInfoTest(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(id="mouseArea_Last Name")
	private WebElement lastNameVerify;
	
	public WebElement getLastNameVerify() {
		return lastNameVerify;
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerVerify;
	public WebElement getHeaderVerify() {
		return headerVerify;
	}
}
