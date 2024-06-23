package AmarAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AmarAcademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css=".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy (xpath="//button[text()='Checkout']")
	WebElement checkoutEle;
	
	public boolean verifyProductDisplay(String productName)
	{
	
	boolean Match = productTitles.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	return Match;
	}
	
	public void goToCheckout()
	{
		checkoutEle.click();
	}
}
