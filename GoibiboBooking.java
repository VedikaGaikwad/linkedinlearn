package seleniumtraining;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GoibiboBooking {

    public static void main(String[] args) throws InterruptedException {
       
        // Initialize the Chrome driver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = null;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Maximize the browser window
        driver.manage().window().maximize();

        // 1. Go to URL
        driver.get("https://www.goibibo.com/");

        // Wait until the page loads completely
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("root")));

        // Close any pop-up if present
        try {
            WebElement closePopup = driver.findElement(By.xpath("//div[@class='sc-iCfMLu dnGGsK']/span"));
            closePopup.click();
        } catch (Exception e) {
            System.out.println("No pop-up to close.");
        }

        // 2. Enter departure and destination cities
        WebElement fromCity = driver.findElement(By.id("gosuggest_inputSrc"));
        fromCity.clear();
        fromCity.sendKeys("Pune");

        // Wait for the dropdown to appear and select the first option
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='react-autosuggest-1']/li")));
        List<WebElement> fromCityOptions = driver.findElements(By.xpath("//ul[@id='react-autosuggest-1']/li"));
        fromCityOptions.get(0).click();

        WebElement toCity = driver.findElement(By.id("gosuggest_inputDest"));
        toCity.clear();
        toCity.sendKeys("Delhi");

        // Wait for the dropdown to appear and select the first option
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='react-autosuggest-1']/li")));
        List<WebElement> toCityOptions = driver.findElements(By.xpath("//ul[@id='react-autosuggest-1']/li"));
        toCityOptions.get(0).click();

        // 3. Select departure and return dates
        WebElement departureDate = driver.findElement(By.id("departureCalendar"));
        departureDate.click();
        
        // Select 25th June
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@aria-label,'Tue Jun 25 2024')]")));
        WebElement depDate = driver.findElement(By.xpath("//div[contains(@aria-label,'Tue Jun 25 2024')]"));
        depDate.click();

        WebElement returnDate = driver.findElement(By.id("returnCalendar"));
        returnDate.click();
        
        // Select 29th June
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@aria-label,'Sat Jun 29 2024')]")));
        WebElement retDate = driver.findElement(By.xpath("//div[contains(@aria-label,'Sat Jun 29 2024')]"));
        retDate.click();

        // 4. Select number of adults and travel class
        WebElement travellerClass = driver.findElement(By.id("pax_link_common"));
        travellerClass.click();

        WebElement addAdult = driver.findElement(By.id("adultPaxPlus"));
        addAdult.click(); // Increment to 2 adults

        WebElement travelClass = driver.findElement(By.xpath("//select[@id='gi_class']/option[text()='Business']"));
        travelClass.click();

        WebElement applyBtn = driver.findElement(By.id("pax_close"));
        applyBtn.click();

        // 5. Search for flights
        WebElement searchBtn = driver.findElement(By.id("gi_search_btn"));
        searchBtn.click();

        // Wait until search results load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='fltHpyResults']")));

        // Print a message to indicate the end of the process
        System.out.println("Flight search completed.");

        // Close the browser
        driver.quit();
    }
}

