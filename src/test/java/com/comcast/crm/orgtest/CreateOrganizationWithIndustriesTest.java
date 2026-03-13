package com.comcast.crm.orgtest;

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
	     
	     driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	     driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	     driver.findElement(By.id("submitButton")).click();
	     
	     // step 2 navigate to organization  module
	     driver.findElement(By.linkText("Organizations")).click();
	     driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	     // enter all the details and create new organization
	     driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
	     
	    WebElement wbsele=  driver.findElement(By.name("industry"));
	     Select sel= new Select(wbsele);
	     sel.selectByVisibleText(induestry);
	    WebElement tysele= driver.findElement(By.name("accounttype"));
	     Select sel1= new Select(tysele);
	     sel1.selectByVisibleText(type);
	     
	     
	     
	    String billipadd = "noida34"+js.getRandomNumber();
	     String shipadd = "noida52"+js.getRandomNumber();
	     driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys(billipadd);
	     driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(shipadd);
	     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	     
	    
	     
	 
	 // verify the industries and type info
	     
		 String actinduestry  = driver.findElement(By.name("industry")).getText();
		 if(actinduestry.contains(induestry)) {
		    	System.out.println(induestry + " information is verified===pass");
		    }else {
		    	System.out.println(induestry+ " information is not  verified===fail");
		    	
		    }
		    
	     
	     
	     
	 String actType  = driver.findElement(By.name("accounttype")).getText();
	 if(actType.contains(type)) {
	    	System.out.println(type + " information is verified===pass");
	    }else {
	    	System.out.println(type + " information is not verified===fail");
	    	
	    }
	    
	    
		
		// step 5 logout
	 driver.quit();
		
		
		
		
		

	}

}
