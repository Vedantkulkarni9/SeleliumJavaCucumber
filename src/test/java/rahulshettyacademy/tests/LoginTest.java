package rahulshettyacademy.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/"); // <-- change URL
    }
/**
    @Test(dataProvider = "loginData", dataProviderClass = ExcelDataProvider.class)
    public void loginTest(String username, String password) {

        // Locate username field
        WebElement usernameField = driver.findElement(By.id("userEmail"));
        usernameField.clear();
        usernameField.sendKeys(username);

        // Locate password field
        WebElement passwordField = driver.findElement(By.id("userPassword"));
        passwordField.clear();
        passwordField.sendKeys(password);

        // Click Login button
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();

        // Add assertion if required
        // Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        
      //label[normalize-space()='Option1'] 
    }
    **/
    public void selectCheckbox(WebDriver driver, String labelText) {
        driver.findElement(
            By.xpath("//label[contains(normalize-space(),'" + labelText + "')]/input")
        ).click();
    }
    @Test
    public void testCheckboxSelection() throws InterruptedException {
        
  //      WebDriver driver = new ChromeDriver();
 //       driver.get("https://example.com");  // your test page

        selectCheckbox(driver, "Option3");

        Thread.sleep(2000);
        driver.quit();
    }

    

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
