package loginPage;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import gluecode.ScriptDay1WithoutFramework_test;

public class LoginValidation {

	@BeforeMethod
	public void preconditionMethod() throws IOException {

//		1..  launching    chromeBrowser 
		Assert.assertTrue(ScriptDay1WithoutFramework_test.launchBrowser(ScriptDay1WithoutFramework_test.readConfig("browser")));

//		2     entering application URL
		Assert.assertTrue(
				ScriptDay1WithoutFramework_test.invokeURL(ScriptDay1WithoutFramework_test.readConfig("applicationURL")));

//		3..  entering valid username and valid password and click on submit button

		

	}

	@Test
	public void loginOperationValidation() throws IOException, InterruptedException {

		Assert.assertTrue(ScriptDay1WithoutFramework_test.loginActions(ScriptDay1WithoutFramework_test.readConfig("username"),
				ScriptDay1WithoutFramework_test.readConfig("password")));
// validate welcome message on homepage
		String actualMessage = ScriptDay1WithoutFramework_test.readLocators("home_welcome_gettext_xpath").getText();
		Assert.assertEquals(actualMessage,ScriptDay1WithoutFramework_test.readConfig("expectedMessage"));
		
		System.out.println("test passed");
		
	}
	
	
	@Test(priority=1)
	
	public void champ() {
		System.out.println("test method2");
	}
	
	//post condition
	@AfterMethod
	public void postconditionMethod() {
		ScriptDay1WithoutFramework_test.closeBrowser();
	}

	}

