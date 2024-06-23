package AmarAcademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AmarAcademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	
	WebDriver driver;
	public CheckoutPage (WebDriver driver)
	
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//a[text()='Place Order ']")
	WebElement placeOrder;
	
	@FindBy (xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy (xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	
	public void selectCountry(String countryName) throws InterruptedException 
	{
	Actions a = new Actions(driver);
	a.sendKeys(country, countryName).build().perform();
	waitForElementToAppear(results);
	SelectCountry.click();
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,500)");
	Thread.sleep(2000);
	
	
	}
	
	public void placeOrder()
	{
		placeOrder.click();
	}
}
