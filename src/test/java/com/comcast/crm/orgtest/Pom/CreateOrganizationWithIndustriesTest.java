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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.EXcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepository.utility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;
import com.comcast.crm.objectrepository.utility.OrganizationInfoTest;
import com.comcast.crm.objectrepository.utility.OrganizationsPage;

public class CreateOrganizationWithIndustriesTest {

	public static void main(String[] args) throws IOException {

		FileUtility fis=new FileUtility();
		EXcelUtility exc=new EXcelUtility();
		JavaUtility js=new JavaUtility();
		// read data from property file
		String BROWSER = fis.getDataFroamPropertiesFile("browser");
		String URL =fis.getDataFroamPropertiesFile("url");
		String USERNAME= fis.getDataFroamPropertiesFile("username");
		String PASSWORD = fis.getDataFroamPropertiesFile("password");
		
		
		
		// read data from excel
	     String orgName  = exc.getDataFroamExcel("org",4,2)+js.getRandomNumber();
	     String induestry = exc.getDataFroamExcel("org",4,3);
	     String type= exc.getDataFroamExcel("org", 4, 4);
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
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	     
	     LoginPage lp=new LoginPage(driver);
	     lp.loginToapp(USERNAME,PASSWORD);
	     
	    
	     
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
	    
	    
		hp.logout();
		// step 5 logout
	 driver.quit();
		
		
		
		
		

	}

}
