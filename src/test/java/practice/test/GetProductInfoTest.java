package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfoTest {
	
	@Test(dataProvider="getData")
	public void getProductInfoTest(String brandNAme, String productName) {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://www.amazon.in/");
		
		// search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandNAme,Keys.ENTER);
		 
		// captur product info
		////span[contains(text(),'iPhone 16 Pro Max 1 TB')]/../../../../div[3]/div[1]//a/span/span[2]
		String x="//span[contains(text(),'"+productName+"')]/../../../../div[3]/div[1]//a/span/span[2]";
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println("price IS"+price);
		
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] objArr=new Object[3][2];
		objArr[0][0]= "iphone";
		objArr[0][1]="iPhone 17 Pro Max 1 TB: 17.42 cm (6.9″) Display with Promotion, A19 Pro Chip, Best Battery Life in Any iPhone Ever, Pro Fusion Camera System, Center Stage Front Camera";
		
		objArr[1][0]= "iphone";
		objArr[1][1]="iPhone 16 Pro Max 1 TB: 5G Mobile Phone ";
		
		objArr[2][0]= "iphone";
		objArr[2][1]="iPhone Air 512 GB: Thinnest iPhone  ";
		
		return objArr;
	}

}
