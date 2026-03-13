package com.comcast.crm.orgtest.Pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

import com.comcast.crm.generic.fileutility.EXcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.utility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;
import com.comcast.crm.objectrepository.utility.OrganizationInfoTest;
import com.comcast.crm.objectrepository.utility.OrganizationsPage;

public class CreateOrganizationWithPhoneNumber {

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
		  
	     String orgName =  exc.getDataFroamExcel("org", 7, 2)+js.getRandomNumber();
	     String phoneNumber =exc.getDataFroamExcel("org", 7, 3);
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
	     
	     driver.get(URL);
	     LoginPage lp=new LoginPage(driver);
	     lp.loginToapp(USERNAME, PASSWORD);
	     HomePage hp=new HomePage(driver);
	     hp.getOrgLink().click();
	     wb.waitForPageToLoad(driver);
	    
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
		
	  hp.logout();
		// step 5 logout
	 driver.quit();
		
		
		
		
		

	}

}
