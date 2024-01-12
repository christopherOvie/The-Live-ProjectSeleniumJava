package gluecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTableAutomation2 {

	public static int colNum=4;  //static methods can only access static variables
	public static void main(String[] args) throws InterruptedException, IOException {
	//open driver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
				   
			//open app	   
		driver.get("https://money.rediff.com/gainers/bsc/daily/groupa");
		//Thread.sleep(3000);
		driver.findElement(By.xpath("//p[contains(text(),'Manage options')]")).click();
		//Thread.sleep(5000);
		driver.findElement(By.xpath("(//p[contains(text(),'Accept all')])[1]")).click();
		
//Thread.sleep(5000);
	//	driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/div[1]/div[1]/div[2]/div[1]/span[1]/a")).click();	
		//driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/div[1]/div[1]/div[2]/div[1]/span[3]/a")).click();	
	
		//get total number OF Column   //*[@id="leftcontainer"]/div[1]/div[1]/div[2]/div[1]/span[3]/a
		//[class='dataTable'] tbody tr:nth-child(1) td:nth-child(4)  css currentprice
		////table[@class='dataTable']/thead/tr/th  column xpath
	   List<WebElement>numberOfRows=	driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr"));
	   System.out.println(numberOfRows.size());
	   List<WebElement>numberOfColumn =  driver.findElements(By.cssSelector("table[class='dataTable'] thead  tr th"));
	   System.out.println(numberOfColumn.size());
	   
	  String value = driver.findElement(By.cssSelector("[class='dataTable'] tbody tr:nth-child(1) td:nth-child(4)")).getText();
	  System.out.println(value);
	  
	  
	  System.out.println("xxxxxxxx");
	List<WebElement>  specificCol=  driver.findElements(By.cssSelector("[class='dataTable'] tbody tr td:nth-child(4)"));
	
	System.out.println(specificCol.size());
	 System.out.println("yyyyyyy");
	for(int i =0;i<specificCol.size();i++) {
	String price=	specificCol.get(i).getText();
	System.out.println(price);
	}
	}

}
