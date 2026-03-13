package com.comcast.crm.contacttest.POM;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.utility.ContactInfoTest;
import com.comcast.crm.objectrepository.utility.ContactPage;
import com.comcast.crm.objectrepository.utility.CreateNewContactPage;
import com.comcast.crm.objectrepository.utility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;
import com.comcast.crm.objectrepository.utility.OrganizationInfoTest;
import com.comcast.crm.objectrepository.utility.OrganizationsPage;

public class CreateContactWithOrganization {
	public static void main(String[] args) throws IOException {
		FileUtility fis=new FileUtility();
		EXcelUtility exc=new EXcelUtility();
		JavaUtility js=new JavaUtility();
		
		WebDriverUtility wb=new WebDriverUtility();
		
		// read data from property file
		String BROWSER = fis.getDataFroamPropertiesFile("browser");
		String URL =fis.getDataFroamPropertiesFile("url");
		String USERNAME= fis.getDataFroamPropertiesFile("username");
		String PASSWORD = fis.getDataFroamPropertiesFile("password");
		
		
		
		
	
		
		// read data from Excel
		
	     String orgName =exc.getDataFroamExcel("contact",7,2)+js.getRandomNumber();
	     String contactLastName=exc.getDataFroamExcel("contact",7,3);
	     
	     
	     System.out.println("Contact Last Name from Excel: " + contactLastName);

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
	     lp.loginToapp(URL, USERNAME, PASSWORD);
	    
	     
	    
	     
	     // step 2 navigate to organization  module
	     HomePage hp=new HomePage(driver);
	     hp.getOrgLink().click();
	     OrganizationsPage op=new OrganizationsPage(driver);
	     op.getCreateNEwOrgBtn().click();
	     
	     CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
	     String billipadd = "noida34"+js.getRandomNumber();
	     String shipadd = "noida52"+ js.getRandomNumber();
	     cnp.createOrg(orgName, billipadd, shipadd);
	    
	     // verify HEader expected Result
	     OrganizationInfoTest oip=new OrganizationInfoTest(driver);
	     String headrinfo= oip.getHeaderMag().getText();
	    
	    if(headrinfo.contains(orgName)) {
	    	System.out.println(orgName + " is created===pass");
	    }else {
	    	System.out.println(orgName + " is not created ===fail");
	    }
	     // verify HEader orgName into Expected Result
	    String actOrgName  = oip.getOrgNameText().getText();
	
	 if(actOrgName.contains(orgName)) {
	    	System.out.println(actOrgName + " is created===pass");
	    }else {
	    	System.out.println(actOrgName + " is not created ===fail");
	    }
	 
	 // step 5 Navigate to contact module
	     
	 hp.getContactLink().click();
    
     
     // step6 click on create o
	 ContactPage cp=new ContactPage(driver);
	 cp.getCreateNewConatctBtn().click();
    
     
     // step 7 enter all details & create new organization
	 CreateNewContactPage cnp2=new CreateNewContactPage(driver);
	 cnp2.enterLastName(contactLastName);
	 cnp2.clickOrgLookup();
     
     
     // switch to child window
     wb.switchToTabOnURL(driver, "Accounts&action");
   
     
     driver.findElement(By.id("search_txt")).sendKeys(orgName);
     driver.findElement(By.name("search")).click();
     
     //  //a[text()='Facebook_1411']  not used becoz i
    // here it will create dynamic at run time    this xpath will also created at a run time dynamically
     driver.findElement(By.xpath(" //a[text()='"+orgName+"']")).click();
     
     // switch to parent window
     wb.switchToTabOnURL(driver, "Contacts&action");
  
     cnp2.clickSave();
   
     
     // verify HEader expected Result
     ContactInfoTest cip=new ContactInfoTest(driver);
     String headerinfo=cip.getHeaderVerify().getText();
   
    if( headerinfo.contains(contactLastName)) {
    	System.out.println(contactLastName + " is created===pass");
    }else {
    	System.out.println(contactLastName + " is not created ===fail");
    }
    String actorgName= cip.getLastNameVerify().getText();
   
    if( actorgName.contains(orgName)) {
    	System.out.println(orgName + " is created===pass");
    }else {
    	System.out.println(orgName + " is not created ===fail");
    }
    
		
		// step 5 logout
	 driver.quit();
		
	}

}
