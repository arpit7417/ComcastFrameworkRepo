package com.comcast.crm.orgtest.Pom.TestNGBAseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.EXcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.utility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;
import com.comcast.crm.objectrepository.utility.OrganizationInfoTest;
import com.comcast.crm.objectrepository.utility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass{

	
		
		@Test
		public void createOrganizationTest() throws EncryptedDocumentException, IOException {
		// gernerate RAndom Number
		
		js.getRandomNumber();
		
		// read data from Excel
		
		String orgName=exc.getDataFroamExcel("org", 1,2)+ js.getRandomNumber();
	    
	     
	    
	     // step 2 navigate to organization  module 
           HomePage hp=new HomePage(driver);
           hp.navigateToCampaginPage(); 
           hp.getOrgLink().click();
         
	     
           // click on create organization button
           OrganizationsPage cnp=new OrganizationsPage(driver);
	       cnp.getCreateNEwOrgBtn().click();
	     
	    String billipadd = "noida34"+js.getRandomNumber();
	     String shipadd = "noida52"+ js.getRandomNumber();
	     
	     // enter all the details & crfeate new Organization
	     CreateNewOrganizationPage cnop= new CreateNewOrganizationPage(driver);
	     cnop.createOrg(orgName,billipadd,shipadd);
	     
	     // verify HEader expected Result
	     OrganizationInfoTest oip=new OrganizationInfoTest(driver);
	    String headerinfo= oip.getHeaderMag().getText();
	    
	    if(headerinfo.contains(orgName)) {
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
	     
		}
		
		@Test
		public void createOrganizationWithIndustrriesTest() throws EncryptedDocumentException, IOException {
			
				// read data from excel
		     String orgName  = exc.getDataFroamExcel("org",4,2)+js.getRandomNumber();
		     String induestry = exc.getDataFroamExcel("org",4,3);
		     String type= exc.getDataFroamExcel("org", 4, 4);
		    
		       // step 2 navigate to organization  module
		     HomePage hp=new HomePage(driver);
		     hp.getOrgLink().click();;
		     
		     OrganizationsPage op = new OrganizationsPage(driver);
		     op.getCreateNEwOrgBtn().click();
		    
		     // enter all the details and create new organization
		     CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		     String billipadd = "noida34"+js.getRandomNumber();
		     String shipadd = "noida52"+js.getRandomNumber();
		     
		     cnop.createOrg(orgName, billipadd, shipadd,induestry ,type  );
		     
		     
		    // verify the industries and type info
		     OrganizationInfoTest oip = new OrganizationInfoTest(driver);
		     String actinduestry = oip.getIndInfo().getText();
			
			 if(actinduestry.contains(induestry)) {
			    	System.out.println(induestry + " information is verified===pass");
			    }else {
			    	System.out.println(induestry+ " information is not  verified===fail");
			    	
			    }
			    
		     
		     
			 String actType = oip.getTypeInfo().getText();
		
		 if(actType.contains(type)) {
		    	System.out.println(type + " information is verified===pass");
		    }else {
		    	System.out.println(type + " information is not verified===fail");
		    	
		    }
		    
		}
		
		  
		@Test
		public void createOrganizationWithPhoneNumber() throws EncryptedDocumentException, IOException {

			// read data from Excel
			  
		     String orgName =  exc.getDataFroamExcel("org", 7, 2)+js.getRandomNumber();
		     String phoneNumber =exc.getDataFroamExcel("org", 7, 3);
		    
		     
		     // step 1 login to app
		  
		     HomePage hp=new HomePage(driver);
		     hp.getOrgLink().click();
		     
		    
		     // // step 2 navigate to organization  module
		   OrganizationsPage op=new OrganizationsPage(driver);
		   op.getCreateNEwOrgBtn().click();
		   
		   // step 3 enter all details & create new organization
		   CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		     
		     
		   String billipadd = "noida34"+js.getRandomNumber();
		     String shipadd = "noida52"+ js.getRandomNumber();
		     cnop.createOrg(orgName, billipadd, shipadd, phoneNumber);
		   
		     
		     // verify HEader expected Result
		     
		    String headrinfo= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		    if(headrinfo.contains(orgName)) {
		    	System.out.println(orgName + " is created===pass");
		    }else {
		    	System.out.println(orgName + " is not created ===fail");
		    }
		     // verify HEader orgName into Expected Result
		    
		    OrganizationInfoTest oip=new OrganizationInfoTest(driver);
		       String actOrgName = oip.getOrgNameText().getText();
		
		 if(actOrgName.contains(orgName)) {
		    	System.out.println(actOrgName + " is created===pass");
		    }else {
		    	System.out.println(actOrgName + " is not created ===fail");
		    }
		     
		     // verify the phone Number
		    String actphonenu = oip.getPhoneNoInfo().getText();
		 
		  if(actphonenu.contains(phoneNumber)) {
			  System.out.println(actphonenu + " information is created");
		  } else {
			  System.out.println(actphonenu + " information is not created");
		  }
			
		
			
			
			
			
			
			
		}
			
			
			
		
	

		
		
		
		
		

	

}
