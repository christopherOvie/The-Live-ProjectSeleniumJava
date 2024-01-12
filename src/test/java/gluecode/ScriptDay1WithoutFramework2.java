package gluecode;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class ScriptDay1WithoutFramework2 {

	static SoftAssert softAssert = new SoftAssert();
	
	public static void main(String[] args) throws InterruptedException {
		
//		
//		ChromeOptions options= new ChromeOptions();
//    	options.addArguments("--disable-notifications");
       // WebDriverManager.chromedriver().setup();
       //RemoteWebDriver driver = new ChromeDriver();
	
		
       WebDriver driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
	
	
		
		driver.findElement(By.name("username")).sendKeys("Admin");
		
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
        WebElement dashboardElement = driver.findElement(By.xpath("(//*[text()='Dashboard'])[2]"));
        System.out.println(dashboardElement.getText());
        
		Assert.assertEquals(dashboardElement.getText(), "Dashboard");
		
		//click admin dashboard button
		
		driver.findElement(By.xpath("//*[text()='Admin']")).click();
		
		WebElement adminHeaderElement = driver.findElement(By.xpath("//h6[text()='Admin']"));
       System.out.println(adminHeaderElement.getText());
//		
//        try {
//        	softAssert.assertEquals(adminHeaderElement.getText(), "admin");
//		
//		 System.out.println("Validate admin page opened successfully");
//        } catch (Exception e) {
//	        
//        	System.out.println("admin page is not open due to " + e.fillInStackTrace());
//        }
//       // driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys("Admin");
//        softAssert.assertAll();
      
//String actualText= driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).getText();
//String expectedText="Admin";
//System.out.println(actualText);

//try {
    // Perform multiple assertions
    softAssert.assertEquals(driver.getTitle(), "OrangeHRM", "Title mismatch");
    softAssert.assertTrue(driver.getCurrentUrl().contains("viewSystemUser"), "URL doesn't contain 'example'");
    softAssert.assertEquals(adminHeaderElement.getText(), "Admin", "Admin header text mismatch");
    // Add more assertions as needed

//} catch (Exception e) {
    // Catch any exceptions thrown during assertions
   // System.out.println("Caught exception: " + e.getMessage());

//} finally {
    // Perform any cleanup or actions that need to be done regardless of the assertion results
    //driver.quit();

    // Assert all the soft assertions
    softAssert.assertAll();
  

//}
driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys("Alice.Duval");
driver.findElement(By.cssSelector("div.oxd-form-actions button:nth-child(2)")).click();

		
		

	}

}
