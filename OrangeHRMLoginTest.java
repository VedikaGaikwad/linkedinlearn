/*Assignment3
1. Go to url - https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
2. Type Username and password
3. Click on Login button
4. After login verify that login was successful*/

package scripts;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class OrangeHRMLoginTest {
	
	WebDriver driver;

	@Test	
	public void Test() throws InterruptedException {
		//1.go to url
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		//Thread.sleep(3000);
		
		//2.type usename and password
		// Type the username
		WebElement usernameField = driver.findElement(By.name("username")); 
		usernameField.sendKeys("Admin");
        
		// Type the password
        WebElement passwordField = driver.findElement(By.name("password")); 
        passwordField.sendKeys("admin123");
		
		//3. Click on Login button
		WebElement loginButton=driver.findElement(By.xpath("//button[@type='submit']"));
		loginButton.click();
		
		Thread.sleep(3000);
		
		//4. After login verify that login was successful*/
		WebElement dashboardElement = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]"));
       Assert.assertTrue(dashboardElement.isDisplayed(), "Login was not successful.");

        System.out.println("Login successful!");
		
		
	}
	
	
	
	@BeforeMethod
	public void beforeMethod() {
		
		driver=new ChromeDriver();			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	}
	
	@AfterMethod
	public void aftereMethod() {
		driver.quit();
	}
}
