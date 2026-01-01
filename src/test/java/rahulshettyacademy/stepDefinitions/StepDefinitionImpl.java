package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.SearchPage;
import rahulshettyacademy.resources.SeleniumLibs;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;
    public SeleniumLibs seleniumlib;
    public SearchPage searchPage;
    WebDriverWait wait;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
        seleniumlib = new SeleniumLibs(driver);
        searchPage = new SearchPage(driver); // ✅ Use class-level variable, not local one

        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
        productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) {
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String expectedMessage) {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(expectedMessage));
        driver.close();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) {
        Assert.assertEquals(strArg1, landingPage.getErrorMessage());
        driver.close();
    }

    @Then("^Select checkbox (.+) (.+)$")
    public void select_checkbox(String checkbox, String selected) {
        boolean isSelected = Boolean.parseBoolean(selected);
        seleniumlib.selectCheckboxByName(checkbox, isSelected);
        System.out.println("Checkbox '" + checkbox + "' selection state set to: " + isSelected);
    }

    public void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Then("^User types in search (.+)$")
    public void user_types_in_search(String searchText) {
        if (seleniumlib == null)
            seleniumlib = new SeleniumLibs(driver);
        if (searchPage == null)
            searchPage = new SearchPage(driver);

        searchPage.typeInSearchBox(searchText);
        System.out.println("✅ Typed '" + searchText + "' successfully in the search box");
    }
}

    
	
	
	
	
	

