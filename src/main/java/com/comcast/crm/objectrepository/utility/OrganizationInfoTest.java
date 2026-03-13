package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoTest {
	
	WebDriver driver;
	public OrganizationInfoTest(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement  headerMag;
	
	public WebElement getHeaderMag() {
		return headerMag;
	}

	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgNameInfo;

	public WebElement getOrgNameText() {
	    return orgNameInfo;
	}
    
	@FindBy(name="industry")
	private WebElement  indInfo;
	
	@FindBy(name="accounttype")
	private WebElement typeInfo;
	public WebElement getIndInfo() {
		return indInfo;
	}

	public WebElement getTypeInfo() {
		return typeInfo;
	}
	
	@FindBy(id="mouseArea_Phone")
	private WebElement phoneNoInfo;
	
	public WebElement getPhoneNoInfo() {
		return phoneNoInfo;
	}
	
}
