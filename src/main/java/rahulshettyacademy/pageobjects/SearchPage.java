
package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rahulshettyacademy.resources.SeleniumLibs;

public class SearchPage {

    WebDriver driver;
    SeleniumLibs seleniumlib;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.seleniumlib = new SeleniumLibs(driver);
    }

    // Element locators
    private By searchBox = By.xpath("//div[@class='py-2 border-bottom ml-3']//input[@placeholder='search']");

    // Actions
    public void typeInSearchBox(String text) {
        //seleniumlib.waitForElementVisible(searchBox, 10);
   // 	By searchBox = By.xpath("//div[@class='py-2 border-bottom ml-3']//input[@placeholder='search']"); // change if needed
    	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    //	driver.findElement(searchBox).sendKeys(text);
        seleniumlib.type(searchBox, text);
        System.out.println("✅ Typed '" + text + "' successfully in the search box");
    }
}