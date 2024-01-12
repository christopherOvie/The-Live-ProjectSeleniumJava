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

public class WebTableAutomation {

	public static int colNum=4;  //static methods can only access static variables
	public static void main(String[] args) throws InterruptedException, IOException {
	//open driver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
		//enter the dat while execution
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println(" do you want to work with BSE/NSE table");
				   String bseOrNse=    br.readLine();
				   
			//open app	   
		driver.get("https://money.rediff.com/gainers/bsc/daily/groupa");		//Thread.sleep(3000);
		driver.findElement(By.xpath("//p[contains(text(),'Manage options')]")).click();
		//Thread.sleep(3000);
		driver.findElement(By.xpath("(//p[contains(text(),'Accept all')])[1]")).click();

		
		//BSE  ---need not click-->bse -->4
		//BSE  --> THEN only click on NSE button .hence NSE table will generate
		//column is 
		if(bseOrNse.equalsIgnoreCase("BSE")) {
		 colNum=4;
		}
		else if(bseOrNse.equalsIgnoreCase("NSE")) {
			 colNum=3;	
			//driver.findElement(By.xpath("//span[contains(text(),'NSE')]")).click();	
			driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/div[1]/div[1]/div[2]/div[1]/span[3]/a")).click();
		}
		else {
			System.out.println("here is a warning that "+ bseOrNse+" value is not working for BSE table");
			
			System.out.println(colNum);
			
			 List<WebElement>numberOfRows=	driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr"));
			   System.out.println(numberOfRows.size());
			   
			   List<WebElement>numberOfColumn =  driver.findElements(By.cssSelector("table[class='dataTable'] thead  tr th"));
			   System.out.println(numberOfColumn.size());	
			
			   for(int i =1;i<numberOfRows.size();i++) {
			   String value = driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr["+i+"]/td["+colNum+"]")).getText();
				  System.out.println(value);	
		}
		
		//get total number OF Column
	}

	}
	}

