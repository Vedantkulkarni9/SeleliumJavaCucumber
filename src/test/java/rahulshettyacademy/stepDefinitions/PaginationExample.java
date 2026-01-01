package rahulshettyacademy.stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PaginationExample {
    public static void main(String[] args) {
        // Set the path to your WebDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            driver.get("https://example.com/products"); // Replace with your URL

            boolean hasNextPage;
            do {
                // Extract product names on the current page
                List<WebElement> productNames = driver.findElements(By.cssSelector(".product-name")); // Replace with the correct selector
                for (WebElement productName : productNames) {
                    System.out.println(productName.getText());
                }

                // Check for the "Next" button
                hasNextPage = driver.findElements(By.cssSelector(".next-button")).size() > 0; // Replace with the correct selector
                if (hasNextPage) {
                    WebElement nextButton = driver.findElement(By.cssSelector(".next-button")); // Replace with the correct selector
                    nextButton.click();
                    // Wait for the new page to load (optional)
                    Thread.sleep(2000);
                }
            } while (hasNextPage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
