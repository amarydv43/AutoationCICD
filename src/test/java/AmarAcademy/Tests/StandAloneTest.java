package AmarAcademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		
		WebDriver driver = new ChromeDriver();
		
		String productName = "zara coat 3";
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("userEmail")).sendKeys("vowels@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sachin@200");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts =  driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean Match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(Match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		
		String confirmMesage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMesage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMesage);
		driver.close();
		


	}

}
