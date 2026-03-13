package com.comcast.crm.listenerutility;

 

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass2 implements ITestListener ,ISuiteListener {
	
	 public  static  ExtentReports report;
	public ExtentSparkReporter spark;
	public static ExtentTest test;   // if we done static we can used in parallel execution
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
		System.out.println("Report configuration");
		
		 String time = new Date().toString().replace(" ", "_").replace(":","_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suiet Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		// add environment information & create test
		
		 report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME_100");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	//	ITestListener.super.onFinish(context);
		System.out.println("Report backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestStart(result);
		System.out.println("===="+result.getMethod().getMethodName()+"===START===");
		test=report.createTest(result.getMethod().getMethodName());
		
	    UtilityClassObject.setTest(test); 
		test.log(Status.INFO, result.getMethod().getMethodName()+" =====> STARTED <======");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		System.out.println("===="+result.getMethod().getMethodName()+"===END===");
		test.log(Status.PASS, result.getMethod().getMethodName()+" =====> COMPLETED <======");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();

		TakesScreenshot tks = (TakesScreenshot) (BaseClass.sdriver);
		String filePath = tks.getScreenshotAs(OutputType.BASE64);
		 String time = new Date().toString().replace(" ", "_").replace(":","_");

		test.addScreenCaptureFromBase64String(filePath,testName+" "+time  );
		
		test.log(Status.FAIL, result.getMethod().getMethodName()+" =====> FAILED <======");
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		
		test.log(Status.SKIP, result.getMethod().getMethodName()+" =====> SKIPPED <======");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	
	
	

}
