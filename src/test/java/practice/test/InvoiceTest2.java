package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;


public class InvoiceTest2 extends BaseClass {
	
	@Test(retryAnalyzer=com.comcast.crm.listenerutility.RetryListenerImp.class)
	public void avtivatedSim() {
		
		System.out.println("execute createInvoiceTest");
		
		String actTitle=driver.getTitle();
		System.out.println(actTitle);
		Assert.assertEquals(actTitle, "Login");
		
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
		
	}
	
	

}
