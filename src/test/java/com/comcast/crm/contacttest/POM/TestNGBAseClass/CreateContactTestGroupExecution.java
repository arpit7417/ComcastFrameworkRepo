package com.comcast.crm.contacttest.POM.TestNGBAseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.basetest.BaseClassForGroup;
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

public class CreateContactTestGroupExecution extends BaseClassForGroup{
	
	   @Test(groups= {"smokeTest"})
		public void createContactTest() throws EncryptedDocumentException, IOException {
		
	   // read data from Excel
		
		String lastname=exc.getDataFroamExcel("contact", 1, 2)+js.getRandomNumber();
	     
	    
	    
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
	     ContactInfoTest  cit=new ContactInfoTest(driver);
	     String verifylastname = cit.getLastNameVerify().getText();
	 //   String verifylastname= driver.findElement(By.id("mouseArea_Last Name")).getText();
	    if(verifylastname.contains(lastname)) {
	    	System.out.println(lastname + " is created===pass");
	    }else {
	    	System.out.println(lastname + " is not created ===fail");
	    }
	     
	    
		
		// step 5 logout
	 
	}
	   
	   @Test(groups="regressionTest")
	   public void createContactWithOrganization() throws EncryptedDocumentException, IOException {
		   
		  
		  // read data from Excel
			
		     String orgName =exc.getDataFroamExcel("contact",7,2)+js.getRandomNumber();
		     String contactLastName=exc.getDataFroamExcel("contact",7,3);
		     
		     
		     System.out.println("Contact Last Name from Excel: " + contactLastName);

		    
		     // step 1 login to app 
		     
		    // step 2 navigate to organization  module
		     HomePage hp=new HomePage(driver);
		     hp.getOrgLink().click();
		      
		   OrganizationsPage op=new OrganizationsPage(driver);
		   op.getCreateNEwOrgBtn().click();;
		    
		     CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
		    
		   
		    String billipadd = "noida34"+js.getRandomNumber();
		     String shipadd = "noida52"+ js.getRandomNumber();
		     cnp.createOrg(orgName, billipadd, shipadd);
		    
		     // verify HEader expected Result
		     OrganizationInfoTest oip=new OrganizationInfoTest(driver);
		     String headrinfo     =oip.getHeaderMag().getText();
		   
		    if(headrinfo.contains(orgName)) {
		    	System.out.println(orgName + " is created===pass");
		    }else {
		    	System.out.println(orgName + " is not created ===fail");
		    }
		     // verify HEader orgName into Expected Result
		    String actOrgName= oip.getOrgNameText().getText();
		
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

		 cnp2.enterLastName(contactLastName);   // 1️⃣ enter lastname
		 cnp2.clickOrgLookup();                // 2️⃣ open popup

		 wb.switchToTabOnURL(driver, "Accounts&action");  // 3️⃣ switch to child
	   
	     
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
	    String actorgName= driver.findElement(By.id("mouseArea_Organization Name")).getText();
	    if( actorgName.contains(orgName)) {
	    	System.out.println(orgName + " is created===pass");
	    }else {
	    	System.out.println(orgName + " is not created ===fail");
	    }
	    
			
			// step 5 logout
		  
			
	   }
	   
	   @Test(groups="regressionTest")
	   public void createContactWithSupportDateTest() throws EncryptedDocumentException, IOException {
		  

	
	
   String  lastname= exc.getDataFroamExcel("contact",4,2)+js.getRandomNumber();
    
    
   
    
    // step 1 login to app 
   HomePage hp=new HomePage(driver);
   hp.getContactLink().click();
  
   ContactPage cp=new ContactPage(driver);
   cp.getCreateNewConatctBtn().click();
   
   
   // step 3 enter all details & create new organization
   CreateNewContactPage cnp=new CreateNewContactPage(driver);
   cnp.enterLastName(lastname);
  
   
    // step 3 enter all details & create new organization
    Date dateobj=new Date();
		
			
		SimpleDateFormat sim=new SimpleDateFormat("yyy-MM-dd");
		String startdate=sim.format(dateobj);
		System.out.println("Actual Date"+    startdate);
		
		
		
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String endDate =sim.format(cal.getTime());
		
    
    
    driver.findElement(By.name("lastname")).sendKeys(lastname);
    
    
    driver.findElement(By.name("support_start_date")).clear();
    driver.findElement(By.name("support_start_date")).sendKeys(startdate);
    
    driver.findElement(By.name("support_end_date")).clear();
    driver.findElement(By.name("support_end_date")).sendKeys(endDate);
    
    
    
 
    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
    
    // verify HEader expected Result
    
   String verifylastname= driver.findElement(By.id("mouseArea_Last Name")).getText();
   if(verifylastname.contains(lastname)) {
   	System.out.println(lastname + " is created===pass");
   }else {
   	System.out.println(lastname + " is not created ===fail");
   }
    
   String actstartdate= driver.findElement(By.id("dtlview_Support Start Date")).getText();
   if(actstartdate.contains(startdate)) {
   	System.out.println(startdate + " is created===pass");
   }else {
   	System.out.println(startdate + " is not created ===fail");
   }
   
   String actendtdate= driver.findElement(By.id("dtlview_Support End Date")).getText();
   if(actendtdate.contains(endDate)) {
   	System.out.println(endDate + " is created===pass");
   }else {
   	System.out.println(endDate + " is not created ===fail");
   }
    
	
	// step 5 logout

	   }

}
