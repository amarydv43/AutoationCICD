package AmarAcademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import AmarAcademy.PageObjects.CartPage;
import AmarAcademy.PageObjects.CheckoutPage;
import AmarAcademy.PageObjects.ConfirmationPage;
import AmarAcademy.PageObjects.LandingPage;
import AmarAcademy.PageObjects.ProductCatalouge;
import AmarAcademy.TestComponents.BaseTest;
import AmarAcademy.TestComponents.Retry;

public class ErrorValidation extends BaseTest {

	
	@Test(groups= {"ErrorCheck"},retryAnalyzer= Retry.class)
	
	public void LoginErrorValidation() 
	 {

		landingPage.loginAppplication("vowels@gmail.com", "Sachin@100");
		Assert.assertEquals("Incorrect email or passwor.", landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	 {
	
		
		String productName = "zara coat 3";

		
		ProductCatalouge productCatalouge = landingPage.loginAppplication("vowels@gmail.com", "Sachin@200");
		List<WebElement> products =productCatalouge.getProductList();
		
		productCatalouge.addProductToCart(productName);
		productCatalouge.goToCartPage();
		
		CartPage cartPage = new CartPage(driver);
		boolean Match = cartPage.verifyProductDisplay("zara coat 30");
		Assert.assertFalse(Match);
	 }
}
