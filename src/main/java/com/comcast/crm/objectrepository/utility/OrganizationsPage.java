package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createNEwOrgBtn;

	public WebElement getCreateNEwOrgBtn() {
		return createNEwOrgBtn;
	}
	
	@FindBy(name="search_text")
	private WebElement  searchEdt;
	
	@FindBy(name="search_field")
	private WebElement  searchDD;
	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	public WebElement getSearchBtn() {
		return searchBtn;
	}

}
