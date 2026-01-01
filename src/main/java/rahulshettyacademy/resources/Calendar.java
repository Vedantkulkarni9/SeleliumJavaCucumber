package rahulshettyacademy.resources;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Calendar {

	
		// TODO Auto-generated method stub
public static void main (String args[]) throws InterruptedException {
	// System.setProperty("webdriver.chrome.driver", "D:\\Browser Binaries\\chromedriver_win32\\chromedriver.exe");
	System.setProperty("webdriver.http.factory", "jdk-http-client");
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\vedan\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	

			String monthNumber = "7";

			String date = "20";

			String targetYear = "2035";
			By yearLabel = By.xpath("//span[@class='react-calendar__navigation__label__labelText react-calendar__navigation__label__labelText--from']");


			String[] expectedList = {monthNumber,date,targetYear};
		//	WebDriverManager.chromedriver().setup();
			
			ChromeOptions chromeOptions = new ChromeOptions();
			 chromeOptions.addArguments("--remote-allow-origins=*");
			WebDriver driver = new ChromeDriver();

			driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

			driver.findElement(By.cssSelector(".react-date-picker__inputGroup")).click();
              
				Thread.sleep(5000);
			
			driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
			Thread.sleep(5000);

	
//add here switch
			 String currentYear = driver.findElement(yearLabel).getText();
			 System.out.println("current year is "+currentYear);
		//	String currentYear = driver.findElement(yearLabel).getText();

			 while (!currentYear.equals(targetYear)) {

			        	driver.findElement(By.xpath("//button[contains(text(),'›')]")).click();
			        
			        	
			        	// Update the year value after each click
			            currentYear = driver.findElement(yearLabel).getText();
			            System.out.println("After clicking, current year: " + currentYear);
			        	
			        }
			
			Thread.sleep(5000);
		String monthName=	driver.findElements(By.cssSelector(".react-calendar__year-view__months__month")).get(Integer.parseInt(monthNumber)-1).getText();
		System.out.println("MonthName is" +monthName);
			driver.findElements(By.cssSelector(".react-calendar__year-view__months__month")).get(Integer.parseInt(monthNumber)-1).click();
		

			Thread.sleep(5000);

			driver.findElement(By.xpath("//abbr[text()='"+date+"']")).click();

			List<WebElement> actualList = driver.findElements(By.cssSelector(".react-date-picker__inputGroup__input"));


			String actualMonth = actualList.get(0).getAttribute("value"); // "6"
			String actualDate  = actualList.get(1).getAttribute("value");
			String actualYear  = actualList.get(2).getAttribute("value");

			// CONVERT MONTH TO +1 (UI shows 0-based month)
			int uiMonth = Integer.parseInt(actualMonth) + 1;

			// EXPECTED VALUES
			int expectedMonth = Integer.parseInt(monthNumber);

			// DEBUG
			System.out.println("Actual month from UI   : " + actualMonth);
			System.out.println("Converted UI month (+1): " + uiMonth);
			System.out.println("Expected month         : " + expectedMonth);

			// ASSERTIONS
			Assert.assertEquals(actualMonth, monthNumber, "Month mismatch!");
			Assert.assertEquals(actualDate, date, "Date mismatch!");
			Assert.assertEquals(actualYear, targetYear, "Year mismatch!");

			
			String check = null;
			//tbody/tr/td[1]
			boolean hasNextPage = false;
			
			int i;
		//	  boolean hasNextPage = false;
			/**
			ArrayList<String> actualists=new ArrayList<String>();
			do {
				List<WebElement> actualisttwo = driver.findElements(By.xpath("//tbody/tr/td[1]"));

			for( i =0; i<actualisttwo.size();i++)

			{
				
				check=actualisttwo.get(i).getText();
			System.out.println("vegetable list is"+actualisttwo.get(i).getText());
			System.out.println("check list is "+check);
			actualists.add(check);
			if(actualists.contains("Carrot")) {
				System.out.println("carrot is present in the main list");
				break;
		//		hasNextPage=true;
		//		System.out.println(hasNextPage);	
			
		//	System.out.println(hasNextPage);	
			}else {
				System.out.println("carrot is not present in the main list");
			}
			
			}
			
			
			
			
			
		//	if (check=="Carrot") {
	     //   	  System.out.println("Carrot is there");
	     //   	  System.out.println("check value is"+check);
	   //     	  break;
		//	}
			// if (check=="Carrot") {
	       // 	  System.out.println("Carrot is there");
	       // 	  System.out.println("check value is"+check);
	        //	  break;
	        	//  Assert.assertTrue(true, "the item is there");
	     //     else  {
	     //   	  System.out.println("Carrot is not there");
	      //  	  System.out.println("check value is"+check);
	     //     }   	  
	        	  driver.findElement(By.cssSelector("[aria-label='Next']")).click();
	          
			
		//	Assert.assertEquals(actualists.get(i).getAttribute("value"), expectedList[i]);
		 	 hasNextPage = driver.findElements(By.cssSelector("[aria-label='Next']")).size() > 0;
         
			
		
			
			}	
			while(hasNextPage); 
			//	driver.findElement(By.cssSelector("[aria-label]='Next']")).click();
			//	      System.out.println("carrot is there");
		        //	  Assert.assertTrue(true, "the item is there");
			
**/	
			
			ArrayList<String> actualLists = new ArrayList<>();

			boolean carrotFound = false;

			do {
			    // Get the column values fresh for each page
			    List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr/td[1]"));

			    actualLists.clear();   // Clear old page data

			    for (WebElement row : rows) {
			        String text = row.getText();
			        System.out.println("vegetable list is: " + text);
			        actualLists.add(text);

			        if (text.equalsIgnoreCase("Mango")) {
			            System.out.println("Mango found!");
			            carrotFound = true;
			            break;
			        }
			    }

			    if (!carrotFound) {
			        System.out.println("Carrot NOT on this page → going to next page");

			        // Check if "Next" is enabled
			        WebElement nextButton = driver.findElement(By.xpath("//a[@aria-label='Next']"));

			        if (nextButton.getAttribute("class").contains("disabled")) {
			            break;   // No more pages
			        }

			        nextButton.click();
			    }

			} while (!carrotFound);

			if (!carrotFound) {
			    System.out.println("Carrot NOT FOUND in entire table!");
			}
}		

}



