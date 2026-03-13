         package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.EXcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;

public class BaseClass {
	
    public	DataBaseUtility dbLib=new DataBaseUtility();
	
     public	FileUtility fis=new FileUtility();
     public	EXcelUtility exc=new EXcelUtility();
     public	JavaUtility js=new JavaUtility();
     public WebDriverUtility wb=new WebDriverUtility();
	
	public  WebDriver driver =null;
	public static WebDriver sdriver =null;
	
	
	@BeforeSuite
	public void configBS() throws SQLException {
	;	System.out.println("===Connect to DB , Report config====");
		dbLib.getDbConnection();
	}
	@BeforeClass
	public void confiBC() throws IOException {
		System.out.println("===Launch the Browser====");
		String BROWSER = fis.getDataFroamPropertiesFile("browser");
		
		 
	   
	     
	     if(BROWSER.equals("chrome")) {
	    	 driver= new ChromeDriver();
	     } else if(BROWSER.equals("firefox")) {
	    	 driver=new FirefoxDriver();
	     } else if(BROWSER.equals("edge")) {
	    	 driver=new EdgeDriver();
	     } else {
	    	 driver=new ChromeDriver();
	     }
	     sdriver =driver; 
	     UtilityClassObject.setDriver(driver);
	}
	

     @BeforeMethod
     public void configBM() throws IOException {
	System.out.println("=Login=");
	String URL = fis.getDataFroamPropertiesFile("url");
	String USERNAME = fis.getDataFroamPropertiesFile("username");
	String PASSWORD = fis.getDataFroamPropertiesFile("password");
	 LoginPage lp=new LoginPage(driver);
	 
	 lp.loginToapp(URL, USERNAME, PASSWORD);
}
     @AfterMethod
 	public void configAM() {
 		System.out.println("=Logout=");
 		HomePage hp = new HomePage(driver);
 		hp.logout();
 	}

	@AfterClass
	public void confiAC() throws SQLException {
		System.out.println("===Close the Browser===");
		driver.quit();
		
	}
	@AfterSuite
	public void configAS() throws SQLException {
		System.out.println("====close Db , Report backUP===");
		dbLib.closeDbConnection();
	}
	

}
