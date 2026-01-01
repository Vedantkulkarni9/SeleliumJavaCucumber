package rahulshettyacademy.pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchAppWithManager {
    public static void main(String[] args) {

        // ✅ Automatically downloads the right ChromeDriver
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://rahulshettyacademy.com/client");
        System.out.println("✅ Page title: " + driver.getTitle());

        driver.quit();
    }
}