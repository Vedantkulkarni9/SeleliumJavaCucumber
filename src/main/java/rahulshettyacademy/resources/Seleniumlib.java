package rahulshettyacademy.resources;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Seleniumlib { 
	
	// System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
	  
	  public void baseTest() {
		  System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
		  

}
	  // Initialize the ChromeDriver
	  WebDriver driver = new ChromeDriver();
	   public void dropDown() throws InterruptedException {
		
		driver.findElement(By.id("divpaxinfo")).click();

		   Thread.sleep(2000);

		   /*int i=1;

		while(i<5)

		{

		driver.findElement(By.id("hrefIncAdt")).click();

		i++;

		}*/

		   System.out.println(driver.findElement(By.id("divpaxinfo")).getText());

		for(int i=1;i<5;i++)

		{

		driver.findElement(By.id("hrefIncAdt")).click();

		}

		driver.findElement(By.id("btnclosepaxoption")).click();

		Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "5 Adult");

		System.out.println(driver.findElement(By.id("divpaxinfo")).getText());
		
	}

	public void dynamicdoropdown() {
		/*Select dropdown=new Select(driver.findElement(By.xpath(".//*[@id='ctl00_mainContent_ddl_Adult']")));

		dropdown.selectByIndex(4);

		dropdown.selectByVisibleText("9 Adults");

		dropdown.selectByValue("8");
		**/
}
	
	  public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
			{
				TakesScreenshot ts = (TakesScreenshot)driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
				FileUtils.copyFile(source, file);
				return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
				
				
			}

	
	public void pagination() throws InterruptedException{
		
	                // Set the path to your WebDriver
		       
		     //   driver.get("https://example.com/paginated"); // Replace with your target URL

		        boolean hasNextPage = true;

		        while (hasNextPage) {
		            // Get the items on the current page
		            List<WebElement> items = driver.findElements(By.cssSelector(".item-selector")); // Update the selector
		            for (WebElement item : items) {
		                System.out.println(item.getText()); // Process each item
		            }

		            // Check for the presence of the "Next" button
		            try {
		                WebElement nextButton = driver.findElement(By.cssSelector(".next-button-selector")); // Update the selector
		                if (nextButton.isDisplayed() && nextButton.isEnabled()) {
		                    nextButton.click(); // Click the "Next" button
		                    Thread.sleep(2000); // Wait for the next page to load
		                } else {
		                    hasNextPage = false; // No more pages
		                }
		            } catch (NoSuchElementException e) {
		                hasNextPage = false; // No "Next" button found
		            }
		        }

		    }
		

            public void calendar() {
	
                // Set the path to your WebDriver executable
	        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

	        // Create a new instance of the Chrome driver
	        WebDriver driver = new ChromeDriver();

	        try {
	            // Navigate to the webpage containing the calendar
	            driver.get("URL_OF_YOUR_WEBPAGE");

	            // Click on the date input to open the calendar
	            WebElement dateInput = driver.findElement(By.id("dateInputId")); // Change to your element's ID
	            dateInput.click();

	            // Wait for the calendar to be visible
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("calendarClass"))); // Change to your calendar's class

	            // Select the desired date
	            String desiredDate = "15"; // Change to your desired date
	            WebElement dateElement = driver.findElement(By.xpath("//td[text()='" + desiredDate + "']")); // Update the XPath if needed
	            dateElement.click();

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close the browser
	            driver.quit();
	        }
	    }


	
	public void alert() {
		String text="Rahul";

		System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		driver.findElement(By.id("name")).sendKeys(text);

		driver.findElement(By.cssSelector("[id='alertbtn']")).click();

		System.out.println(driver.switchTo().alert().getText());

		driver.switchTo().alert().accept();

		driver.findElement(By.id("confirmbtn")).click();

		System.out.println(driver.switchTo().alert().getText());



		driver.switchTo().alert().dismiss();

}
	
	public String sorting (String sortcolumn) {
		
		

		
		
		        // Set the path to your WebDriver
		        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

		        // Initialize WebDriver
		        WebDriver driver = new ChromeDriver();
		        driver.get("URL_OF_THE_PAGE_WITH_TABLE"); // Replace with your URL

		        // Wait until the table is present
		    //    WebDriverWait wait = new WebDriverWait(driver, 10);
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tableID"))); // Change to your table's locator

		        // Click on the header of the column you want to sort
		        WebElement sortColumnHeader = driver.findElement(By.xpath("//th[text()='Column Name']")); // Change to your column header
		        sortColumnHeader.click();

		        // Wait for sorting to complete
		        wait.until(ExpectedConditions.stalenessOf(sortColumnHeader));

		        // Optionally, verify if the sorting worked by retrieving and printing the sorted values
		        List<WebElement> sortedElements = driver.findElements(By.xpath("//table[@id='tableID']//tr/td[1]")); // Change the index for the column you want to verify

		        // Print sorted values
		        for (WebElement element : sortedElements) {
		            System.out.println(element.getText());
		        }

		        // Close the driver
		        
		        return sortcolumn;
		    }
		
public void filter() {
	
                // Set the path for the ChromeDriver
	       

	        try {
	            // Maximize the browser window
	            driver.manage().window().maximize();

	            // Implicit wait
	            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	            // Navigate to a website
	            driver.get("https://example.com");

	            // Find an element by its name attribute and send keys
	            WebElement inputField = driver.findElement(By.name("q")); // Change to an actual input name
	            inputField.sendKeys("Hello, Selenium!");

	            // Find a button and click it
	            WebElement searchButton = driver.findElement(By.name("btnK")); // Change to an actual button name
	            searchButton.click();

	            // Add more interactions as needed...

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close the browser
	            driver.quit();
	        }
	    }
	
	
	public void uploadDownloadFile() {
		
		// TODO Auto-generated method stub

		String downloadPath=System.getProperty("user.dir");

		System.setProperty("webdriver.chrome.driver","C:\\work\\chromedriver.exe");

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

		chromePrefs.put("profile.default_content_settings.popups", 0);

		chromePrefs.put("download.default_directory", downloadPath);

		ChromeOptions options=new ChromeOptions();

		options.setExperimentalOption("prefs", chromePrefs);

		WebDriver driver=new ChromeDriver(options);

		driver.get("https://altoconvertpdftojpg.com/");

		driver.findElement(By.cssSelector("[class*='btn--choose']")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Runtime.getRuntime().exec("C:\\Users\\rahul\\Documents\\check\\fileupload.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	
	public void download() throws InterruptedException {
		
	//	WebDriverWait wait=new WebDriverWait(driver,10);
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*='medium']")));

		driver.findElement(By.cssSelector("button[class*='medium']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Download Now")));

		driver.findElement(By.linkText("Download Now")).click();

		Thread.sleep(5000);
		String downloadPath = null;

		File f=new File(downloadPath+"/converted.zip");

		if(f.exists())

		{

		Assert.assertTrue(f.exists());

		if(f.delete())

		System.out.println("file deleted");

		}

		}



	
	
	
	public void addtoCart() {
		
		





	
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");



		



		String[] itemsNeeded= {"Cucumber","Brocolli","Beetroot"};





	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addItems(driver,itemsNeeded);

		}

		public static  void addItems(WebDriver driver,String[] itemsNeeded)

		{

		int j=0;

		List<WebElement> products=driver.findElements(By.cssSelector("h4.product-name"));

		for(int i=0;i<products.size();i++)

		{

		//Brocolli - 1 Kg

		//Brocolli,    1 kg

		String[] name=products.get(i).getText().split("-");

		String formattedName=name[0].trim();

		//format it to get actual vegetable name

		//convert array into array list for easy search

		//  check whether name you extracted is present in arrayList or not-

		List itemsNeededList = Arrays.asList(itemsNeeded);

		if(itemsNeededList.contains(formattedName))

		{

		j++;

		//click on Add to cart

		driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();

		if(j==itemsNeeded.length)

		{

		break;

		}

		}
		}

		}
		
		
		public String checkbox(String checkboxname) {
			
			System.setProperty("webdriver.chrome.driver", "C://work//chromedriver.exe");

			WebDriver driver =new ChromeDriver();

			driver.get("http://spicejet.com"); //URL in the browser

			Assert.assertFalse(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

			//Assert.assertFalse(true);System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

		  checkboxname=driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).getText();

			System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

			Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
		return  checkboxname;
		}
		
		           public void paginations() {
			
	                 // Set the path to your WebDriver executable
			        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
			        
			       

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
		
		
		public void selectCalendarDateMonthYear() {
			
			String monthNumber = "6";

			String date = "15";

			String year = "2027";

			String[] expectedList = {monthNumber,date,year};

			WebDriver driver = new ChromeDriver();

			driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

			driver.findElement(By.cssSelector(".react-date-picker__inputGroup")).click();

			driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();

			driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();

			driver.findElement(By.xpath("//button[text()='"+year+"']")).click();

			driver.findElements(By.cssSelector(".react-calendar__year-view__months__month")).get(Integer.parseInt(monthNumber)-1).click();

			driver.findElement(By.xpath("//abbr[text()='"+date+"']")).click();

			List<WebElement> actualList = driver.findElements(By.cssSelector(".react-date-picker__inputGroup__input"));

			for(int i =0; i<actualList.size();i++)

			{

			System.out.println(actualList.get(i).getAttribute("value"));

			Assert.assertEquals(actualList.get(i).getAttribute("value"), expectedList[i]);

			}
			
		}
		
			
		

		}
		
		
		
		



		


	
	


		       



		       



		     

		   
	
	
	

	


	

