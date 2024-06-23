package AmarAcademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import AmarAcademy.PageObjects.CartPage;
import AmarAcademy.PageObjects.CheckoutPage;
import AmarAcademy.PageObjects.ConfirmationPage;
import AmarAcademy.PageObjects.LandingPage;
import AmarAcademy.PageObjects.ProductCatalouge;

public class StandAlone2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		
		String productName = "zara coat 3";
		String countryName = "India";
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalouge productCatalouge = landingPage.loginAppplication("vowels@gmail.com", "Sachin@200");
		
//		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		List<WebElement> products =productCatalouge.getProductList();
		
		productCatalouge.addProductToCart(productName);
		productCatalouge.goToCartPage();
		
		CartPage cartPage = new CartPage(driver);
		boolean Match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(Match);
		
		cartPage.goToCheckout();
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.selectCountry(countryName);
		checkoutPage.placeOrder();
		
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		String confirmMesage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMesage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
				
		

	}

}
