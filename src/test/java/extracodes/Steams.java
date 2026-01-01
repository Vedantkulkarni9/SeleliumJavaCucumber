package extracodes;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Steams {

	public static void main(String[] args) {
		WebDriver driver =new ChromeDriver();
		// TODO Auto-generated method stub
		List<WebElement> products = driver.findElements(By.cssSelector(".product-card"));

		/**
		products.stream()
		        .filter(p -> p.findElement(By.cssSelector("h2")).getText().equals("MacBook Air"))
		        .findFirst()
		        .ifPresent(p -> p.findElement(By.cssSelector(".add-to-cart")).click());
		        **/
		products.stream().filter(p->p.findElement(By.cssSelector("")).getText().equals("")).findFirst().ifPresent(p->p.findElement(By.cssSelector(".addTOcart")).click());
		
	}
	private Collection<WebElement> rows;
	
	public void brokenlinks() {
		WebDriver driver=new ChromeDriver();
		List<WebElement> links = driver.findElements(By.tagName("a"));
		long brokenCount = links.stream()
			    .map(l -> l.getAttribute("href"))
			    .filter(href -> href != null && href.contains("http"))
			    .filter(href -> isBroken(href))
			    .count();

		
		
	}
	//Broken links
	public boolean isBroken(String url) {
	    try {
	        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	        connection.setRequestMethod("HEAD"); // faster than GET
	        connection.connect();

	        int responseCode = connection.getResponseCode();
	        return responseCode >= 400;   // true → link is broken

	    } catch (Exception e) {
	        return true; // if exception = broken link
	    }
	}
	//sorting
	List<WebElement> row= driver.findElement()>
	///  . Validate table sorting
	List<String> ui = row.stream()
		    .map(r -> r.getText())
		    .collect(Collectors.toList());

		List<String> sorted = ui.stream().sorted().collect(Collectors.toList());

		Assert.assertEquals(ui, sorted);
		
		
	//	Click product by name
		products.stream()
		    .filter(p -> p.getText().contains("Samsung"))
		    .findFirst()
		    .ifPresent(WebElement::click);
		
		//2. Get all product names
		List<String> names = elements.stream()
			    .map(WebElement::getText)
			    .collect(Collectors.toList());

	
	

}
