package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;


public class InvoiceTestFromXmlSuiet extends BaseClass {
	
	@Test
	public void createInvoiceTest() {
		
		System.out.println("execute createInvoiceTest");
		
		String actTitle=driver.getTitle();
		System.out.println(actTitle);
		Assert.assertEquals(actTitle, "Login");
		
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
		
	}
	
	
	@Test
	public void createInvoiceWithContactTest() {
		System.out.println("execute createInvoiceWithConatctTest");
		
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Login");

		System.out.println("Step-1");
		
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
		
		
		
	}

}
