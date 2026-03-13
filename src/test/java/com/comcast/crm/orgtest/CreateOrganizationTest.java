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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.EXcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationTest {

	public static void main(String[] args) throws IOException {
		
		FileUtility fis=new FileUtility();
		EXcelUtility exc=new EXcelUtility();
		JavaUtility js=new JavaUtility();
		// read data from property file
		String BROWSER = fis.getDataFroamPropertiesFile("browser");
		String URL =fis.getDataFroamPropertiesFile("url");
		String USERNAME= fis.getDataFroamPropertiesFile("username");
		String PASSWORD = fis.getDataFroamPropertiesFile("password");
		
		
		// gernerate RAndom Number
		
		js.getRandomNumber();
		
		// read data from Excel
		
		String orgName=exc.getDataFroamExcel("org", 1,2)+ js.getRandomNumber();
	     
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
	     
	     
		
		// step 5 logout
	 driver.quit();
		
		
		
		
		

	}

}
