package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	

	WebDriver driver;
	public CreateNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy( name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveinfoBtn;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement OrgNamePlus;
	
	public WebElement getOrgNamePlus() {
		return OrgNamePlus;
	}

	public WebElement getLastName() {
		return lastNameEdt;
	}

	public WebElement getSaveinfoBtn() {
		return saveinfoBtn;
	}
	
	public void enterLastName(String lastname) {
	    lastNameEdt.sendKeys(lastname);
	}

	public void clickOrgLookup() {
	    OrgNamePlus.click();
	}

	public void clickSave() {
	    saveinfoBtn.click();
	}
	

}
