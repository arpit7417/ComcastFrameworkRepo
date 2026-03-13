package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	
	
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	@FindBy(name ="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(name="bill_street")
	private WebElement billingAddressEdt;

	@FindBy(name="ship_street")
	private WebElement shippingAddressEdt;

     @FindBy(name="accounttype")
     private WebElement typeDD;
     
     @FindBy(id="phone")
     private WebElement phoneEdt;
     
	public WebElement getPhoneEdt() {
		return phoneEdt;
	}

	public WebElement getTypeDD() {
		return typeDD;
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getBillingAddressEdt() {
		return billingAddressEdt;
	}

	public WebElement getShippingAddressEdt() {
		return shippingAddressEdt;
	}
	
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName,String billAdd,String ShipAdd) {
		orgNameEdt.sendKeys(orgName);
		
		
        
		billingAddressEdt.sendKeys(billAdd);
		shippingAddressEdt.sendKeys(ShipAdd);
		saveBtn.click();
	}
	public void createOrg(String orgName,String billAdd,String ShipAdd,String industry,String type) {
		orgNameEdt.sendKeys(orgName);
		Select sel=new Select(industryDD);
		sel.selectByVisibleText(industry);
		
		Select sel1 = new Select(typeDD);
	    sel1.selectByVisibleText(type);
		
        
		billingAddressEdt.sendKeys(billAdd);
		shippingAddressEdt.sendKeys(ShipAdd);
		saveBtn.click();
	}

	public void createOrg(String orgName,String billAdd,String ShipAdd,String phoneno) {
		orgNameEdt.sendKeys(orgName);
		
		
        phoneEdt.sendKeys(phoneno);
		billingAddressEdt.sendKeys(billAdd);
		shippingAddressEdt.sendKeys(ShipAdd);
		saveBtn.click();
	}

}
