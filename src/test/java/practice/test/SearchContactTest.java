package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepository.utility.LoginPage;



/**
 *  test class for contact module
 * @author Deepak
 * 
 * 
 */
public class SearchContactTest  extends BaseClass{
	
	/**
	 * Scenario : login() ===> navigateContact===> createContact()== verify 
	 */
	
	@Test
	public void searchconatctTest() {
		
		LoginPage lp= new LoginPage(driver);
		lp.loginToapp("url", "username", "password");
		
	}
	

}
