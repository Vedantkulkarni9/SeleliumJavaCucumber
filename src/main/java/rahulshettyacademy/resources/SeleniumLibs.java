package rahulshettyacademy.resources;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumLibs {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;

    // -------------------------
    // Constructor
    // -------------------------
    public SeleniumLibs(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        if (driver instanceof JavascriptExecutor) {
            this.js = (JavascriptExecutor) driver;
        }
    }

    // Defensive re-init if needed
    private void ensureHelpers() {
        if (this.wait == null) {
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        if (this.actions == null) {
            this.actions = new Actions(driver);
        }
        if (this.js == null && driver instanceof JavascriptExecutor) {
            this.js = (JavascriptExecutor) driver;
        }
    }

    // -------------------------
    // Waits and basic interactions
    // -------------------------
    public void waitForElementVisible(By locator) {
        ensureHelpers();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementClickable(By locator) {
        ensureHelpers();
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void click(By locator) {
        waitForElementClickable(locator);
        driver.findElement(locator).click();
    }
    public void clickElement(WebElement element) {
        element.click();
        System.out.println("Clicked element using WebElement object");
    }
///polymorphism!!!!!
    public void type(By locator, String text) {
        waitForElementVisible(locator);
        WebElement el = driver.findElement(locator);
        el.clear();
        el.sendKeys(text);
    }

    public String getText(By locator) {
        waitForElementVisible(locator);
        return driver.findElement(locator).getText();
    }

    public boolean isElementVisible(By locator) {
        try {
            waitForElementVisible(locator);
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // -------------------------
    // Screenshots
    // -------------------------
    public String takeScreenshot(String testCaseName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        File dest = new File(path);
        FileUtils.copyFile(src, dest);
        return path;
    }

    // -------------------------
    // Dropdowns
    // -------------------------
    public void selectDropdownByVisibleText(By locator, String visibleText) {
        waitForElementVisible(locator);
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(visibleText);
    }

    public void selectDropdownByValue(By locator, String value) {
        waitForElementVisible(locator);
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    public void selectDropdownByIndex(By locator, int index) {
        waitForElementVisible(locator);
        Select select = new Select(driver.findElement(locator));
        select.selectByIndex(index);
    }

    public void selectDynamicDropdown(By optionsLocator, String partialText) {
        waitForElementVisible(optionsLocator);
        List<WebElement> options = driver.findElements(optionsLocator);
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(partialText.trim())) {
                option.click();
                return;
            }
        }
        throw new NoSuchElementException("Option containing text '" + partialText + "' not found.");
    }

    // -------------------------
    // Checkboxes & radios
    // -------------------------
    /**
     * Selects / unselects checkbox using input name or value attribute.
     */
    public void selectCheckboxByLocatorState(By locator, boolean shouldBeChecked) {
        waitForElementVisible(locator);
        WebElement checkbox = driver.findElement(locator);
        boolean isSelected = checkbox.isSelected();
        if (isSelected != shouldBeChecked) {
            checkbox.click();
        }
    }

    /**
     * Select checkbox by the checkbox input's name or value attribute
     */
    public void selectCheckboxByName(String labelText, boolean valueToSelect) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Normalize and make case-insensitive
            String normalizedText = labelText.trim().toLowerCase();

            // XPath: Find checkbox that comes before the label
            String xpath = "//label[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" 
                            + normalizedText +"']/preceding-sibling::input[@type='checkbox']";

            // Wait until element is visible
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

            // Scroll into view to avoid element not clickable errors
   //         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);

            // Check or uncheck based on valueToSelect
         //   if (checkbox.isSelected() != valueToSelect) {
            Thread.sleep(10000);
                checkbox.click();
       //     }

            System.out.println("✅ Checkbox '" + labelText + "' set to " + valueToSelect);

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to select checkbox: " + labelText, e);
        }
    }


    
    public void waitForElementVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.err.println("⚠️ Element not visible: " + element);
            throw e;
        }
    }


    /**
     * Selects checkbox by visible label text. Handles cases:
     *  <label><input type='checkbox'> Label text</label>
     *  <input type='checkbox' id='cb1'/><label for='cb1'>Label text</label>
     *  <input type='checkbox'/><label>Label text</label>
     */
    public void selectCheckboxByLabel(String labelText, boolean shouldBeChecked) {
        // Case 1: label contains input
        String xpath1 = "//label[contains(normalize-space(.),'" + labelText + "')]/descendant::input[@type='checkbox']";
        List<WebElement> el1 = driver.findElements(By.xpath(xpath1));
        if (!el1.isEmpty()) {
            WebElement checkbox = el1.get(0);
            if (checkbox.isDisplayed()) {
                if (checkbox.isSelected() != shouldBeChecked) checkbox.click();
                return;
            }
        }

        // Case 2: label following-sibling or label with for attribute
        String xpath2 = "//label[contains(normalize-space(.),'" + labelText + "')]/preceding::input[@type='checkbox'][1] | //label[contains(normalize-space(.),'" + labelText + "')]/following::input[@type='checkbox'][1]";
        List<WebElement> el2 = driver.findElements(By.xpath(xpath2));
        if (!el2.isEmpty()) {
            WebElement checkbox = el2.get(0);
            if (checkbox.isDisplayed()) {
                if (checkbox.isSelected() != shouldBeChecked) checkbox.click();
                return;
            }
        }

        // Case 3: input with sibling label (common)
        String xpath3 = "//input[@type='checkbox' and (following-sibling::label[contains(normalize-space(.),'" + labelText + "')] or preceding-sibling::label[contains(normalize-space(.),'" + labelText + "')])]";
        List<WebElement> el3 = driver.findElements(By.xpath(xpath3));
        if (!el3.isEmpty()) {
            WebElement checkbox = el3.get(0);
            if (checkbox.isDisplayed()) {
                if (checkbox.isSelected() != shouldBeChecked) checkbox.click();
                return;
            }
        }

        throw new NoSuchElementException("Checkbox with label/text '" + labelText + "' not found.");
    }

    public void selectRadioButton(By locator) {
        waitForElementVisible(locator);
        WebElement radio = driver.findElement(locator);
        if (!radio.isSelected()) radio.click();
    }

    // -------------------------
    // Scrolling / Actions / JS
    // -------------------------
    public void scrollToElement(By locator) {
        ensureHelpers();
        WebElement ele = driver.findElement(locator);
        js.executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    public void scrollByPixels(int x, int y) {
        ensureHelpers();
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    public void mouseHover(By locator) {
        ensureHelpers();
        WebElement el = driver.findElement(locator);
        actions.moveToElement(el).perform();
    }

    public void jsClick(By locator) {
        ensureHelpers();
        WebElement el = driver.findElement(locator);
        js.executeScript("arguments[0].click();", el);
    }

    // -------------------------
    // Alerts, windows, frames
    // -------------------------
    public void handleAlert(boolean accept) {
        ensureHelpers();
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text: " + alert.getText());
        if (accept) alert.accept();
        else alert.dismiss();
    }

    public void switchToWindowByTitle(String expectedTitle) {
        String original = driver.getWindowHandle();
        Set<String> all = driver.getWindowHandles();
        for (String h : all) {
            driver.switchTo().window(h);
            if (driver.getTitle().contains(expectedTitle)) return;
        }
        driver.switchTo().window(original); // if not found, switch back
    }

    public void switchToFrame(By locator) {
        waitForElementVisible(locator);
        WebElement frame = driver.findElement(locator);
        driver.switchTo().frame(frame);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // -------------------------
    // Keyboard actions
    // -------------------------
    public void pressEnter(By locator) {
        waitForElementVisible(locator);
        driver.findElement(locator).sendKeys(Keys.ENTER);
    }

    public void pressTab(By locator) {
        waitForElementVisible(locator);
        driver.findElement(locator).sendKeys(Keys.TAB);
    }

    // -------------------------
    // Table utilities
    // -------------------------
    public int getTableRowCount(By rowLocator) {
        return driver.findElements(rowLocator).size();
    }

    public String getTableCellText(By rowLocator, int rowIndex, By cellLocator) {
        List<WebElement> rows = driver.findElements(rowLocator);
        if (rowIndex < 0 || rowIndex >= rows.size()) {
            throw new IndexOutOfBoundsException("Row index out of bounds");
        }
        return rows.get(rowIndex).findElement(cellLocator).getText();
    }

    // -------------------------
    // Pagination helper
    // -------------------------
    public void paginateThroughTable(By rowLocator, By nextButtonLocator, long waitAfterClickMillis) {
        boolean hasNext = true;
        while (hasNext) {
            List<WebElement> rows = driver.findElements(rowLocator);
            for (WebElement row : rows) {
                System.out.println(row.getText());
            }
            try {
                WebElement nextBtn = driver.findElement(nextButtonLocator);
                if (nextBtn.isDisplayed() && nextBtn.isEnabled()) {
                    nextBtn.click();
                    try {
                        Thread.sleep(waitAfterClickMillis);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    hasNext = false;
                }
            } catch (NoSuchElementException e) {
                hasNext = false;
            }
        }
    }

    // -------------------------
    // File upload & download
    // -------------------------
    /**
     * Upload using a file input element
     */
    public void uploadFile(By fileInputLocator, String filePath) {
        waitForElementVisible(fileInputLocator);
        WebElement upload = driver.findElement(fileInputLocator);
        upload.sendKeys(filePath);
    }
    
    

    /**
     * Simple download helper that launches a temporary ChromeDriver with custom
     * download directory, navigates to URL and waits briefly.
     * NOTE: This creates a new driver instance (separate process).
     */
    public void downloadFileWithTempDriver(String downloadUrl, String downloadDir) throws InterruptedException {
        // Setup options
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", downloadDir);
        options.setExperimentalOption("prefs", prefs);

        WebDriver temp = new ChromeDriver(options);
        try {
            temp.get(downloadUrl);
            Thread.sleep(5000); // wait for download to start/complete (adjust as needed)
        } finally {
            temp.quit();
        }
    }

    // -------------------------
    // Utility: safe click if exists
    // -------------------------
    public boolean safeClickIfPresent(By locator) {
        try {
            if (isElementVisible(locator)) {
                driver.findElement(locator).click();
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
