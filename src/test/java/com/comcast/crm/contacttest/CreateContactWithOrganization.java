package com.comcast.crm.contacttest;

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
	     
	     driver.get(URL);
	    // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	     wb.waitForPageToLoad(driver);
	     
	     driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	     driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	     driver.findElement(By.id("submitButton")).click();
	     
	     // step 2 navigate to organization  module
	     driver.findElement(By.linkText("Organizations")).click();
	     driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	     
	     driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
	     
	    String billipadd = "noida34"+js.getRandomNumber();
	     String shipadd = "noida52"+ js.getRandomNumber();
	     driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys(billipadd);
	     driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(shipadd);
	     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	     
	     // verify HEader expected Result
	     
	    String headrinfo= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    if(headrinfo.contains(orgName)) {
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
	 
	 // step 5 Navigate to contact module
	     
	 
     driver.findElement(By.linkText("Contacts")).click();
     
     // step6 click on create o
     driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
     
     // step 7 enter all details & create new organization
     driver.findElement(By.name("lastname")).sendKeys(contactLastName);
     driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
     
     // switch to child window
     wb.switchToTabOnURL(driver, "Accounts&action");
   /*  Set<String> set=driver.getWindowHandles();
     Iterator<String> it=set.iterator();
     
     while(it.hasNext()) {
    	 String windowID=it.next();
    	 driver.switchTo().window(windowID);
    	 
    	String acturl= driver.getCurrentUrl();
    	if(acturl.contains("Accounts&action")) {
    		break;
    	}
     } */
     
     driver.findElement(By.id("search_txt")).sendKeys(orgName);
     driver.findElement(By.name("search")).click();
     
     //  //a[text()='Facebook_1411']  not used becoz i
    // here it will create dynamic at run time    this xpath will also created at a run time dynamically
     driver.findElement(By.xpath(" //a[text()='"+orgName+"']")).click();
     
     // switch to parent window
     wb.switchToTabOnURL(driver, "Contacts&action");
   /*  Set<String> set1=driver.getWindowHandles();
     Iterator<String> it1=set.iterator();
     
     while(it1.hasNext()) {
    	 String windowID=it1.next();
    	 driver.switchTo().window(windowID);
    	 
    	String acturl= driver.getCurrentUrl();
    	if(acturl.contains("Contacts&action")) {
    		break;
    	}
     } */  
  
     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
     
     // verify HEader expected Result
     
    String headerinfo= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
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
	 driver.quit();
		
	}

}
