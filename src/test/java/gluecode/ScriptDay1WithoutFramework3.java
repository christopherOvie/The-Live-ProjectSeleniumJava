package gluecode;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class ScriptDay1WithoutFramework3 {
	public static SoftAssert softAssert = new SoftAssert();
	public static WebDriver driver;
	/*
	 * @param browser 
	 * @Author : chris
	 * @Return : boolean
	 * @Description :launch browser demands for browser as chrome, firefox, edge
	 * @Date : 01/11/2022

	 */

	
	public static boolean launchBrowser(String browser) {
		boolean flag = false;
		try {

			if(browser.equalsIgnoreCase("chrome")) {
				//WebDriverManager.chromedriver().setup();
				WebDriverManager.chromedriver().setup();

				driver = new ChromeDriver();
			}else if(browser.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			System.out.println(browser + " browser launched successfully");
			flag = true;
		}catch(Exception e) {
			System.out.println(browser + " browser not launched due to exception : " + e.toString());
			flag = false;
		}

		return flag;
	}
	
	
	
	/**
	 * 
	 * @param url
	 * @return
	 */

	public static boolean invokeURL(String url) {
		boolean flag = false;
		try {
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			System.out.println(url + " url launched successfully");
			flag = true;
		}catch(Exception e) {
			System.out.println(url + " url not launched due to exception : " + e.toString());
			flag = false;
		}
		return flag;

	}
	
	
	public static void closeBrowser() {
		driver.close();
	}
	
	
	
	public  static boolean loginActions(String username,String password) {
		boolean flag = false;
		try {
			driver.findElement(By.name("username")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			System.out.println("login successful");
			flag = true;
		}catch(Exception e) {
			System.out.println("login failed due to exception : " + e.toString());
			flag = false;
		}
		return flag;
		
	}	
	//utility method ton click on dashboard panel
	
	
	
	public static void clickButton(String buttonXPath) {
        try {
            

            // Find the button using XPath
            WebElement button = driver.findElement(By.xpath(buttonXPath));

            // Click the button
            button.click();

            System.out.println("Button clicked successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
	
	
	
	
	
	
	public static void main(String[] args) throws InterruptedException {

		launchBrowser("chrome");
		
		invokeURL("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		Thread.sleep(2000);
		//https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers
		
		loginActions("Admin", "admin123"); //;https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index
		

		//driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
	//driver.findElement(By.name("username")).sendKeys("Admin");
	//driver.findElement(By.name("password")).sendKeys("admin123");
  // driver.findElement(By.xpath("//button[@type='submit']")).click();
   
	/*WebElement dashboardElement = driver.findElement(By.xpath("(//*[text()='Dashboard'])[2]"));
  System.out.println(dashboardElement.getText());

		Assert.assertEquals(dashboardElement.getText(), "Dashboard");

		//click admin dashboard button

		driver.findElement(By.xpath("//*[text()='Admin']")).click();

		WebElement adminHeaderElement = driver.findElement(By.xpath("//h6[text()='Admin']"));
		System.out.println(adminHeaderElement.getText());

		// Perform multiple assertions
		softAssert.assertEquals(driver.getTitle(), "OrangeHRM", "Title mismatch");
		softAssert.assertTrue(driver.getCurrentUrl().contains("viewSystemUser"), "URL doesn't contain 'example'");
		softAssert.assertEquals(adminHeaderElement.getText(), "Admin", "Admin header text mismatch");
	

		softAssert.assertAll();


		
		driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys("Alice.Duval");
		driver.findElement(By.cssSelector("div.oxd-form-actions button:nth-child(2)")).click();

*/
	}

	}

