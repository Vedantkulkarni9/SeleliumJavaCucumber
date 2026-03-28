package extracodes;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Streams {

    WebDriver driver;

    public static void main(String[] args) {
        Streams obj = new Streams();
        obj.runTest();
    }

    public void runTest() {
        driver = new ChromeDriver();
        driver.get("https://example.com"); // replace with actual URL

        List<WebElement> products = driver.findElements(By.cssSelector(".product-card"));

        // ✅ Click product by name
        products.stream()
                .filter(p -> p.getText().contains("MacBook"))
                .findFirst()
                .ifPresent(p -> p.findElement(By.cssSelector(".add-to-cart")).click());

        // ✅ Get all product names
        List<String> names = products.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        System.out.println(names);

        // ✅ Validate sorting
        List<String> sorted = names.stream().sorted().collect(Collectors.toList());

        if (names.equals(sorted)) {
            System.out.println("List is sorted");
        } else {
            System.out.println("List is NOT sorted");
        }

        driver.quit();
    }

    // ✅ Broken links method
    public long brokenLinks() {
        List<WebElement> links = driver.findElements(By.tagName("a"));

        return links.stream()
                .map(l -> l.getAttribute("href"))
                .filter(href -> href != null && href.startsWith("http"))
                .filter(this::isBroken)
                .count();
    }

    public boolean isBroken(String url) {
        try {
            HttpURLConnection connection =
                    (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();

            return connection.getResponseCode() >= 400;

        } catch (Exception e) {
            return true;
        }
    }
}