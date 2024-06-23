package AmarAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AmarAcademy.AbstractComponents.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent{
	
WebDriver driver;
	
	
	
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> OrderProducts;
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[2]")
	WebElement OrderHeader;

	
	public Boolean verifyOrderDisplay(String productName)
	{
		OrderHeader.click();
		Boolean Match = OrderProducts.stream().anyMatch(Product-> Product.getText().equalsIgnoreCase(productName));
		return Match ;
	}


}
