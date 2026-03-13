package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDateTest {
	
	public static void main(String[] args) throws IOException {
		// create an object of Fileutility
				FileUtility fis=new FileUtility();
				EXcelUtility exc= new EXcelUtility();
				JavaUtility js=new JavaUtility();
				
				String BROWSER =fis.getDataFroamPropertiesFile("browser");
				String URL =fis.getDataFroamPropertiesFile("url");
				String USERNAME= fis.getDataFroamPropertiesFile("username");
				String PASSWORD = fis.getDataFroamPropertiesFile("password");
				
		
	
		
		
	    String  lastname= exc.getDataFroamExcel("contact",4,2)+js.getRandomNumber();
	     
	     
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
	     
	     // step 2 navigate to contacts  module
	     driver.findElement(By.linkText("Contacts")).click();
	     driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	     
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
	 driver.quit();
	}

}
