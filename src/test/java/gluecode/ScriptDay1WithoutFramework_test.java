package gluecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class ScriptDay1WithoutFramework_test {
	public static SoftAssert softAssert = new SoftAssert();
	public static WebDriver driver;
	public static Properties prop_config;
	public static WebElement e;
	public static String loc_value;
	/*
	 * @param browser
	 * 
	 * @Author : chris
	 * 
	 * @Return : boolean
	 * 
	 * @Description :launch browser demands for browser as chrome, firefox, edge
	 * 
	 * @Date : 01/11/2022
	 * 
	 */

	public static boolean launchBrowser(String browser) {
		boolean flag = false;
		try {

			if (browser.equalsIgnoreCase("chrome")) {
				// WebDriverManager.chromedriver().setup();
				WebDriverManager.chromedriver().setup();

				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			System.out.println(browser + " browser launched successfully");
			flag = true;
		} catch (Exception e) {
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
		} catch (Exception e) {
			System.out.println(url + " url not launched due to exception : " + e.toString());
			flag = false;
		}
		return flag;

	}

	public static void closeBrowser() {
		driver.close();
	}

	public static boolean loginActions(String username, String password) {
		boolean flag = false;
		try {
			ScriptDay1WithoutFramework_test.readLocators("login_username_txtbox_id").sendKeys(username);
			
			ScriptDay1WithoutFramework_test.readLocators("login_password_txtbox_name").sendKeys(password);
			
	   
//			driver.findElement(By.name("username")).sendKeys(username);
//			
//			driver.findElement(By.name("password")).sendKeys(password);
			//Thread.sleep(2000);
			ScriptDay1WithoutFramework_test.readLocators("login_signIn_btn_xpath").click();
			//driver.findElement(By.xpath("//button[@type='submit']")).click();
			ScriptDay1WithoutFramework_test.readLocators("login_agreement_btn_xpath").click();
			System.out.println("login successful");
			flag = true;
		} catch (Exception e) {
			System.out.println("login failed due to exception : " + e.toString());
			flag = false;
		}
		return flag;

	}
	// utility method ton click on dashboard panel

	public static WebElement readLocators(String key) throws IOException, InterruptedException {
		WebElement e = null;

		FileInputStream fis1 = new FileInputStream(
				"C:\\Users\\GOD\\eclipse-workspace\\TheLiveProject\\src\\test\\resources\\Repositories\\locator.properties");
		Properties prop = new Properties();

		prop.load(fis1);
		loc_value = prop.getProperty(key);
		if (key.endsWith("_id")) {
			e = driver.findElement(By.id(loc_value));

			elementHighlight(e);

		} else if (key.endsWith("_name")) {

			e = driver.findElement(By.name(loc_value));
			elementHighlight(e);
		} else if (key.endsWith("_xpath")) {

			e = driver.findElement(By.xpath(loc_value));
			elementHighlight(e);
		} else if (key.endsWith("_clsName")) {
			e = driver.findElement(By.className(loc_value));
			elementHighlight(e);
		} else if (key.endsWith("_cssSelector")) {
			e = driver.findElement(By.cssSelector(loc_value));
			elementHighlight(e);
		} else if (key.endsWith("_linkText")) {
			e = driver.findElement(By.linkText(loc_value));
			elementHighlight(e);
		} else if (key.endsWith("_tagname")) {
			e = driver.findElement(By.tagName(loc_value));
			elementHighlight(e);
		} else if (key.endsWith("_partialLinkText")) {
			e = driver.findElement(By.partialLinkText(loc_value));
			elementHighlight(e);
		}

		return e;
	}

	public static String readConfig(String key) throws IOException {
		String config_value = null;

		FileInputStream fis2 = new FileInputStream(
				"C:\\Users\\GOD\\eclipse-workspace\\TheLiveProject\\src\\test\\resources\\Repositories\\config.properties");
		Properties prop = new Properties();

		prop.load(fis2);
		config_value = prop.getProperty(key);

		return config_value;
	}

	public static void elementHighlight(WebElement e2) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background:yellow;border:2px dashed blue');", e2);
		Thread.sleep(1000);
		// js.executeScript(" arguments[0].setAttribute('style', 'background:yellow;
		// border:2px dashed blue'); ", element);

	}

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
	
	public static void readExcelData(String xpathkey,int rowNo,int columnNo) throws IOException {
		File src= new File("C:\\Users\\GOD\\eclipse-workspace\\TheLiveProject\\src\\test\\resources\\TestData\\testdata.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		Properties prop = new Properties();
		XSSFSheet sh = wb.getSheet(prop.getProperty("ExcelTestDataSheetname"));
		System.out.println(sh.getSheetName());
	String abc=	sh.getRow(rowNo).getCell(columnNo).getStringCellValue();
	driver.findElement(By.xpath(prop_config.getProperty(xpathkey))).sendKeys(abc);
	}
	
	
	

	public static void main(String[] args) throws InterruptedException {

		launchBrowser("chrome");

		invokeURL("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		Thread.sleep(2000);
		// https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers

		loginActions("Admin", "admin123"); // ;https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index

		// driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		// driver.findElement(By.name("username")).sendKeys("Admin");
		// driver.findElement(By.name("password")).sendKeys("admin123");
		// driver.findElement(By.xpath("//button[@type='submit']")).click();

		/*
		 * WebElement dashboardElement =
		 * driver.findElement(By.xpath("(//*[text()='Dashboard'])[2]"));
		 * System.out.println(dashboardElement.getText());
		 * 
		 * Assert.assertEquals(dashboardElement.getText(), "Dashboard");
		 * 
		 * //click admin dashboard button
		 * 
		 * driver.findElement(By.xpath("//*[text()='Admin']")).click();
		 * 
		 * WebElement adminHeaderElement =
		 * driver.findElement(By.xpath("//h6[text()='Admin']"));
		 * System.out.println(adminHeaderElement.getText());
		 * 
		 * // Perform multiple assertions softAssert.assertEquals(driver.getTitle(),
		 * "OrangeHRM", "Title mismatch");
		 * softAssert.assertTrue(driver.getCurrentUrl().contains("viewSystemUser"),
		 * "URL doesn't contain 'example'");
		 * softAssert.assertEquals(adminHeaderElement.getText(), "Admin",
		 * "Admin header text mismatch");
		 * 
		 * 
		 * softAssert.assertAll();
		 * 
		 * 
		 * 
		 * driver.findElement(By.
		 * xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys(
		 * "Alice.Duval");
		 * driver.findElement(By.cssSelector("div.oxd-form-actions button:nth-child(2)")
		 * ).click();
		 * 
		 */
	}

}
