package AmarAcademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AmarAcademy.PageObjects.CartPage;
import AmarAcademy.PageObjects.CheckoutPage;
import AmarAcademy.PageObjects.ConfirmationPage;
import AmarAcademy.PageObjects.OrderHistoryPage;
import AmarAcademy.PageObjects.ProductCatalouge;
import AmarAcademy.TestComponents.BaseTest;

public class SubmitOrder extends BaseTest {

	
	@Test(dataProvider ="getData" , groups = "Purchase")
	
	public void SubmitOrder(String email, String password , String productName) throws IOException, InterruptedException
	 {
	
		
		
		String countryName = "India";
		
		ProductCatalouge productCatalouge = landingPage.loginAppplication(email, password);
		
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

	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistory () {
		
		String productName = "zara coat 3";
		ProductCatalouge productCatalouge = landingPage.loginAppplication("vowels@gmail.com", "Sachin@200");
		OrderHistoryPage orderHistory = new OrderHistoryPage(driver);
		Assert.assertTrue(orderHistory.verifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	
		public Object[][] getData()
		{
		
		  return  new Object [] []  {{"vowels@gmail.com","Sachin@200","zara coat 3"},
			  { "Register@gmail.com", "Register123" , "ADIDAS ORIGINAL"}};
		}
	


}
