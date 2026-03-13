package com.comcast.crm.contacttest.POM;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.EXcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepository.utility.ContactInfoTest;
import com.comcast.crm.objectrepository.utility.ContactPage;
import com.comcast.crm.objectrepository.utility.CreateNewContactPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;

public class CreateContactTest {
	
	public static void main(String[] args) throws IOException {
		
		// create an object of Fileutility
		FileUtility fis=new FileUtility();
		EXcelUtility exc= new EXcelUtility();
		JavaUtility js=new JavaUtility();
		
		String BROWSER =fis.getDataFroamPropertiesFile("browser");
		String URL =fis.getDataFroamPropertiesFile("url");
		String USERNAME= fis.getDataFroamPropertiesFile("username");
		String PASSWORD = fis.getDataFroamPropertiesFile("password");
		
		
		
		
		// read data from Excel
		
		String lastname=exc.getDataFroamExcel("contact", 1, 2)+js.getRandomNumber();
	     
	     WebDriver driver =null;
	     
	     if(BROWSER.equals("chrome")) {
	    	 driver= new ChromeDriver();
	     } else if(BROWSER.equals("firefox")) {
	    	 driver=new FirefoxDriver();
	     } else if(BROWSER.equals("edge")) {
	    	 driver=new EdgeDriver();
	     } else {
	    	 driver=new ChromeDriver();
	     }
	     
	     // step 1 login to app 
	    
	     
	     LoginPage lp=new LoginPage(driver);
	     lp.loginToapp(URL,USERNAME, PASSWORD);
	    
	     
	     // step 2 navigate to contacts  module
	     HomePage hp=new HomePage(driver);
	     hp.getContactLink().click();
	     
	    ContactPage cp=new ContactPage(driver);
	    cp.getCreateNewConatctBtn().click();
	    
	     
	     // step 3 enter all details & create new organization
	    CreateNewContactPage cnp=new CreateNewContactPage(driver);
	    cnp.enterLastName(lastname);
	    cnp.clickSave();
	    
	     
	     // verify HEader expected Result
	      ContactInfoTest cit=new ContactInfoTest(driver);
	      String verifylastname= cit.getLastNameVerify().getText();
	   
	    if(verifylastname.contains(lastname)) {
	    	System.out.println(lastname + " is created===pass");
	    }else {
	    	System.out.println(lastname + " is not created ===fail");
	    }
	     
	    
		
		// step 5 logout
	 driver.quit();
	}

}
