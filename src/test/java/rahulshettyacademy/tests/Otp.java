package rahulshettyacademy.tests;



import java.io.IOException;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;

public class Otp {
	String apiKey="PPtUgn9vAr8g0fhEapdjiCOK0rERbzB5";
	//String serverId= "obsr3cjg";
	String serverId= "mjflw1of";
	String serverDomain ="obsr3cjg.mailosaur.net";
	
//	public String  getRandomEmail() {
//		return "user"+ System.currentTimeMillis() + "@"+serverDomain;
//	}

	public Message waitForEmail(String emailId,MailosaurClient mailosaur) throws MailosaurException {
		// TODO Auto-generated method stub
      Wait<MailosaurClient> wait =new  FluentWait<> (mailosaur).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofMillis(100)).ignoring(Exception.class);
	return wait.until(mailosaurClient->{
		try {
			
			MessageSearchParams params =new MessageSearchParams();
			params.withServer(serverId);
			SearchCriteria criteria =new SearchCriteria();
		//	criteria.withSentTo("vedantk@" + serverDomain );
			criteria.withSentTo("vedant@mjflw1of.mailosaur.net");
			criteria.withSentFrom("msonlineservicesteam@microsoftonline.com");
			
			Message message = mailosaurClient.messages().get(params,criteria);
			return message;
			
		}catch(MailosaurException | IOException e) {
			return null;
		} 
	
	});
		
		
	}
	//public void MailExample() {
	//	WebDriver driver =new ChromeDriver();
//		driver.get("");
		
		
			
	@Test
	public void TestMailExample() throws InterruptedException, MailosaurException {
		String emailId="vedant@mjflw1of.mailosaur.net";
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vedan\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		 chromeOptions.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver();
		driver.get("https://homolog-ge-ncommand-lite.ionichealthusa.com/CommandCenter");
	//	driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		Thread.sleep(20000);
		//driver.findElement(By.xpath("//input[@type=email]")).click();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("vedantdattatraya.kulkarni+remote@gehealthcare.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Information@1");
		driver.findElement(By.xpath("//button[@id='next']")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[contains(text(),'Vedant')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Change password']")).click();
	//	driver.switchTo().alert().accept();
		Thread.sleep(10000);
		//div[@class='attrEntry']/label[contains(text(),'Email Address')]
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("vedant@mjflw1of.mailosaur.net");
		driver.findElement(By.xpath("//button[@class='sendCode']")).click();
		
		//button[@class='sendCode']
		MailosaurClient  mailosaur =new  MailosaurClient(apiKey); 
		Message message = waitForEmail(emailId, mailosaur);
		String subject =message.subject();
		System.out.println("subject is"+subject);
		String body =message.html().body();
		System.out.println("body is"+body);
		System.out.println("get otp");
		Pattern pattern =Pattern.compile("Your code is: .*([0-9]{6}).*");
		Matcher matcher =pattern.matcher(body);
		matcher.find();
		String otp =matcher.group(1);
		System.out.println("otp is" +otp);
		driver.findElement(By.xpath("//input[@id='emailVerificationCode']")).sendKeys(otp);
		
		
		
		
		
		
		
		
		
	}
	}


