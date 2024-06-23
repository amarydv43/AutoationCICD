package AmarAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AmarAcademy.PageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDiver() throws IOException
	{
		
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\AmarAcademy\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser"):prop.getProperty("browser");	
//		String browserName = prop.getProperty("browser");
		if (browserName.contains("Chrome"))
		{
		ChromeOptions options = new ChromeOptions();
		if(browserName.contains("headless"))
		{
		options.addArguments("headless");
		}
		 driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
		}
		
		else if (browserName.equalsIgnoreCase("edge"))
				{
			 driver = new EdgeDriver();
				}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		
		driver = initializeDiver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void  closeBrowser()
	{
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDatatoMap(String filePath) throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		
		ObjectMapper mapper = new ObjectMapper (); 
		
		List<HashMap<String , String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String , String>>>() {
		});
		
		return data;
		}

	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File (System.getProperty("user.dir")+"//reports//" +testcaseName +".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir")+"//reports//" +testcaseName +".png";
	}
	

}
