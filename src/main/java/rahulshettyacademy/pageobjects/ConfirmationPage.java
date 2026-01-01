package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.resources.*;


public class ConfirmationPage extends AbstractComponent{

	
	WebDriver driver;
	Seleniumlib seleniumlib= new Seleniumlib();

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		

	}
	
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	@FindBy(css = ".hero-primary")
	WebElement sortcolumnname;
	
	public String getConfirmationMessage()
	{
		CheckoutPage cp = new CheckoutPage(driver);	
		return confirmationMessage.getText();
	}
	
	public void performsorting(String columnname) {
		
		seleniumlib.sorting(columnname);
	}
	
	
	
	
}
