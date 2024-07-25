package seleniumtraining;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Set;

public class MultiWindowTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        
        // Initialize the Chrome driver
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void testMultiWindow() throws InterruptedException {
        // 1. Go to URL
        driver.get("http://www.cookbook.seleniumacademy.com/Config.html");

        // 2. Click on Help button
        WebElement helpButton = driver.findElement(By.id("helpbutton"));
        helpButton.click();
        String mainWindowHandle = driver.getWindowHandle();
        switchToNewWindow(mainWindowHandle);

        // Verify the Help window text
        WebElement helpText = driver.findElement(By.xpath("//h3[text()='Build my Car - Configuration Help']"));
        Assert.assertTrue(helpText.isDisplayed(), "Help text is not displayed.");
        System.out.println("Help text verified.");

        // Switch back to the main window
        driver.switchTo().window(mainWindowHandle);

        // 3. Click "Online Chat Support" button to open Chat window
        WebElement chatButton = driver.findElement(By.id("chatbutton"));
        chatButton.click();
        switchToNewWindow(mainWindowHandle);

        // Verify something on the Chat window
        WebElement chatText = driver.findElement(By.xpath("//h3[text()='Chat Window']"));
        Assert.assertTrue(chatText.isDisplayed(), "Chat window text is not displayed.");
        System.out.println("Chat window text verified.");

        // Switch back to the main window
        driver.switchTo().window(mainWindowHandle);

        // 4. Click "Visit Us" button to open VisitUs window
        WebElement visitUsButton = driver.findElement(By.id("visitbutton"));
        visitUsButton.click();
        switchToNewWindow(mainWindowHandle);

        // Verify something on the VisitUs window
        WebElement visitUsText = driver.findElement(By.xpath("//h3[text()='Visit Us']"));
        Assert.assertTrue(visitUsText.isDisplayed(), "Visit Us text is not displayed.");
        System.out.println("Visit Us text verified.");

        // 5. Close all windows except the main window
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }

        // Switch back to the main window
        driver.switchTo().window(mainWindowHandle);
    }

    private void switchToNewWindow(String mainWindowHandle) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        // Quit the browser
        driver.quit();
    }

    public static void main(String[] args) {
        MultiWindowTest test = new MultiWindowTest();
        test.setUp();
        try {
            test.testMultiWindow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            test.tearDown();
        }
    }
}

