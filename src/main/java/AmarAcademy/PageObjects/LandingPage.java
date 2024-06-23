package AmarAcademy.PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AmarAcademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
//	WebElement UserEmail = driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmail ;
	
	@FindBy(id="userPassword")
	WebElement passwordEle ;
	
	@FindBy(id="login")
	WebElement submit ;
	
	@FindBy (id="toast-container")
	WebElement errorMessage;
	
	
	public ProductCatalouge loginAppplication(String userName, String password)
	{
		
		userEmail.sendKeys(userName);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		return productCatalouge;
	}
	
	public String getErrorMessage() {
		WaitForWebElementAppear(errorMessage);
		return errorMessage.getText();
	}

	
	public void goTo ()
	
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
