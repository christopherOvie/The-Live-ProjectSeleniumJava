package gluecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExcelReader {

	public static void main(String[] args) throws IOException {
		
//		FileInputStream fis = new FileInputStream("C:\\Users\\GOD\\Desktop\\exceldata.xlsx");
//		
//		XSSFWorkbook workbook = new XSSFWorkbook(fis);
//		
		
File src = new File("C:\\Users\\GOD\\Desktop\\exceldata.xlsx");
		
		//load file
		FileInputStream fis = new FileInputStream(src);
		
		//load workbook
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		//load worksheet
		XSSFSheet sh = wb.getSheet("Sheet1");
		//print loaded sheet name
		
		System.out.println(sh.getSheetName());
		
		//print test4name from excel sheet
			System.out.println(sh.getRow(2).getCell(1).getStringCellValue());
		   System.out.println(sh.getPhysicalNumberOfRows());
		   
		   System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

			//Initiating your chromedriver
			WebDriver driver=new ChromeDriver();
			
			//open browser with desried URL
			driver.get("https://www.facebook.com/");
			
			driver.findElement(By.cssSelector("button[title='Allow all cookies']")).click();
			String abc = sh.getRow(2).getCell(1).getStringCellValue();
			driver.findElement(By.id("email")).sendKeys(abc);

	}

}
