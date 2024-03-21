package FlipkartCaseStudy.FlipkartCaseStudy;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


/*
 * Unit test for simple Application.
 */

public class AppTest{
	WebDriver driver;
	
	@Test
	public void openBrowser() {
		//Setting the Chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");	
		
		//Creating an object for the chromedriver
		driver = new ChromeDriver();
		
		//maximizing the window
		driver.manage().window().maximize();
		
		//visiting the goindigo.in website
		driver.get("https://www.flipkart.com/");
		
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium book");
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._1AtVbE")));

        // Selecting the first book that appears
        WebElement firstBook = driver.findElement(By.cssSelector("img[alt='Selenium WebDriver 3 Practical Guide']"));
        firstBook.click();
        
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        String currentWindowHandle = driver.getWindowHandle();

	    // Get all window handles
	    Set<String> windowHandles = driver.getWindowHandles();
	
	    // Iterate over all window handles
	    for (String handle : windowHandles) {
	        if (!handle.equals(currentWindowHandle)) {
	            // Switch to the new window/tab
             driver.switchTo().window(handle);
             break;
	        }
	    }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebElement formElement = driver.findElement(By.xpath("//form[@class='N79-rD']"));

        
        WebElement checkPincodeButton = formElement.findElement(By.id("pincodeInputId"));
        checkPincodeButton.sendKeys("410401");
        
        WebElement checkButton = driver.findElement(By.xpath("//span[@class='_2P_LDn']"));
        checkButton.click();
        

        
        WebElement addToCart = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']"));
        addToCart.click();

        WebElement placeOrder = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2ObVJD _3AWRsL']"));
        placeOrder.click();

        WebElement emailEntry = driver.findElement(By.cssSelector("input[type='text']"));
        emailEntry.sendKeys("omkarkhandebharad.sit.it@gmail.com");
                		
        WebElement continueBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        continueBtn.click();
        
        WebElement mobnumberEntry = driver.findElement(By.cssSelector("input[class='_2IX_2- _17N0em']"));
        mobnumberEntry.sendKeys("1234567890");
        continueBtn.click();
        
        File screenshotFile = ((ChromeDriver)driver).getScreenshotAs(OutputType.FILE);

        // Saving the screenshot to a file
        try {
            FileUtils.copyFile(screenshotFile, new File("screenshot.png"));
            System.out.println("Screenshot saved successfully.");
        } catch (Exception e) {
            System.out.println("Failed to save the screenshot: " + e.getMessage());
        }
	}
}