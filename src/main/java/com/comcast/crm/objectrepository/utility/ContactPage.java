package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	WebDriver driver;
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(xpath ="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createNewConatctBtn;
	
	
	public WebElement getCreateNewConatctBtn() {
		return createNewConatctBtn;
	}
	
	
	

}
