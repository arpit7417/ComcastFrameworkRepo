package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility{
	
	/**
	 * 
	 * 
	 * @author  Deepak
	 * Contains Login Page elements & business Lib like Login()
	 */
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	// Rule 1  create a seprate java class of each page
	// Rule 2 Object creation identify all the element by @findBy anotation
	// 
	
	@FindBy(name="user_name")
	 private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement userpasswordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	
	public WebElement getUserpasswordEdt() {
		return userpasswordEdt;
	}

	

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	
	
	// r5ule 3 object initilization   in tesr scrict pageFactory.inti
	// rule 4 object Encapsulation  provide private and access getters methos
	// rule 5 provide actions 
	 
	/**
	 * 
	 * login to application based on  username ,password
	 * @param username
	 * @param password
	 */
	
	public void loginToapp(String username , String password) {
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		userpasswordEdt.sendKeys(password);
		loginBtn.click();
	}
	
	/**
	 *  login to application based on username,password,url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToapp(String url,String username , String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		userpasswordEdt.sendKeys(password);
		loginBtn.click();
	}
	
	
	

}
