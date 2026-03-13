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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;

public class ListImpClass implements ITestListener ,ISuiteListener {
	
	 public  static  ExtentReports report;
	public ExtentSparkReporter spark;
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
		System.out.println("Report configuration");
		
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report.html");
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
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		System.out.println("===="+result.getMethod().getMethodName()+"===END===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();

		TakesScreenshot tks = (TakesScreenshot) (BaseClass.sdriver);
		File src = tks.getScreenshotAs(OutputType.FILE);
		 String time = new Date().toString().replace(" ", "_").replace(":","_");

		try {
			File dest = new File("./screenshot/"+testName+"+"+time+"+.png");
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
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
