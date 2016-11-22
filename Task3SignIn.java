package assignment3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task3SignIn extends WebDriverWaitFunctions {
	/*Scenario 3:
		- Click on signing & Account
		- Enter invalid address and invalid password
		- click on sign in button
		- Verify the error message*/
	
	@Test
	public void signin(){
		webDriverFluentWait(By.id("hdr-account")).click();
		
		webDriverFluentWait(By.id("hdr-signin")).click();
		
		webDriverFluentWait(By.id("sign-in-email")).sendKeys("Abc@xys.com");
		webDriverFluentWait(By.id("sign-in-password")).sendKeys("!@#$%^&*");
		webDriverFluentWait(By.xpath("html/body/div[7]/div/div[1]/div/div/form/div[2]/button")).click();
		String actual=webDriverFluentWait(By.xpath("html/body/div[7]/div/div[1]/div/div/p")).getText();
		String expected="We don’t recognize the email and password combination you’ve entered. Please try again, or if you’ve forgotten your password we’ll help reset it";
		Assert.assertEquals(actual,expected);
	}

}
