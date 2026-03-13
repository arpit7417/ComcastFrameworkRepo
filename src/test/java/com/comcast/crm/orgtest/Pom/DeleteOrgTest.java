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
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.EXcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.utility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;
import com.comcast.crm.objectrepository.utility.OrganizationInfoTest;
import com.comcast.crm.objectrepository.utility.OrganizationsPage;

public class DeleteOrgTest {

	public static void main(String[] args) throws IOException {
		
		FileUtility fis=new FileUtility();
		EXcelUtility exc=new EXcelUtility();
		JavaUtility js=new JavaUtility();
		WebDriverUtility wlip=new WebDriverUtility();
		// read data from property file
		String BROWSER = fis.getDataFroamPropertiesFile("browser");
		String URL =fis.getDataFroamPropertiesFile("url");
		String USERNAME= fis.getDataFroamPropertiesFile("username");
		String PASSWORD = fis.getDataFroamPropertiesFile("password");
		
		
		// gernerate RAndom Number
		
		js.getRandomNumber();
		
		// read data from Excel
		
		String orgName=exc.getDataFroamExcel("org",9,2)+ js.getRandomNumber();
	     
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
	     
	   /*  lp.getUsernameEdt().sendKeys("admin");
	     lp.getUserpasswordEdt().sendKeys("admin");
	     lp.getLoginBtn().click();
	    */
	   
	    lp.loginToapp("admin", "admin");
	     // step 2 navigate to organization  module
           HomePage hp=new HomePage(driver);
           hp.getOrgLink().click();
           hp.navigateToCampaginPage();
	     
           // click on create organization button
           OrganizationsPage cnp=new OrganizationsPage(driver);
           
         
	       cnp.getCreateNEwOrgBtn().click();
	     
	  
	     
	     // enter all the details & crfeate new Organization
	     CreateNewOrganizationPage cnop= new CreateNewOrganizationPage(driver);
	     String billipadd = "noida34"+js.getRandomNumber();
  	     String shipadd = "noida52"+ js.getRandomNumber();
	     cnop.createOrg(orgName);
	     
	     // verify HEader expected Result
	     OrganizationInfoTest oip=new OrganizationInfoTest(driver);
	    String headerinfo= oip.getHeaderMag().getText(); 
	    
	    if(headerinfo.contains(orgName)) {
	    	System.out.println(orgName + " is created===pass");
	    }else {
	    	System.out.println(orgName + " is not created ===fail");
	    }
	     // verify HEader orgName into Expected Result
	 String actOrgName  = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	 if(actOrgName.contains(orgName)) {
	    	System.out.println(actOrgName + " is created===pass");
	    }else {
	    	System.out.println(actOrgName + " is not created ===fail");
	    }
	     
	    // go back to organization page
	    hp.getOrgLink().click();
	 // search for organization
	    cnp.getSearchEdt().sendKeys(orgName);
	    
	    wlip.select(cnp.getSearchDD(), "Organization Name");
	    cnp.getSearchBtn().click();
	 // in Dynamic webtable select &delete org
	 
		
		// step 5 logout
	 hp.logout(); 
	 driver.quit();
		
		
		
		
		

	}

}
