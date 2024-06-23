package AmarAcademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AmarAcademy.PageObjects.CartPage;
import AmarAcademy.PageObjects.CheckoutPage;
import AmarAcademy.PageObjects.ConfirmationPage;
import AmarAcademy.PageObjects.LandingPage;
import AmarAcademy.PageObjects.OrderHistoryPage;
import AmarAcademy.PageObjects.ProductCatalouge;
import AmarAcademy.TestComponents.BaseTest;

public class SubmitOrder2 extends BaseTest {

	
	@Test(dataProvider ="getData" , groups = "Purchase")
	
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	 {
	
		
		
		String countryName = "India";
		
		ProductCatalouge productCatalouge = landingPage.loginAppplication(input.get("email"),input.get("password"));
		
//		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		List<WebElement> products =productCatalouge.getProductList();
		
		productCatalouge.addProductToCart(input.get("product"));
		productCatalouge.goToCartPage();
		
		CartPage cartPage = new CartPage(driver);
		boolean Match = cartPage.verifyProductDisplay(input.get("product"));
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
	
	/*
	@DataProvider
	
		public Object[][] getData()
		{
		
		HashMap<String,String> map = new HashMap<>();
		map.put("email","vowels@gmail.com");
		map.put("password", "Sachin@200");
		map.put("product", "zara coat 3");
		
		HashMap<String,String> map1 = new HashMap<>();
		
		map1.put("email","Register@gmail.com");
		map1.put("password", "Register123");
		map1.put("product", "ADIDAS ORIGINAL");
		
		
		  return  new Object [] []  {{map}, {map1}};
		}
	*/
	
	  @DataProvider 
	   
	   public Object[][] getData() throws IOException
	   
	   {
		 List<HashMap<String,String>>  data = getJsonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\Amaracademy\\data\\PurchaseOrder.json");
		   
		   
		   
		  return  new Object [][]  {{data.get(0)} ,{data.get(1)} };
	   }



}
