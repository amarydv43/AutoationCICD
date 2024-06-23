package AmarAcademy.TestComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AmarAcademy.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal <ExtentTest>();

			
	@Override
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
		//====== for doing thread Safe we have to Use ExtentTest.set wherever it is test in on Test Failure and ScreenShot
				extentTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		test.log(Status.PASS, "Test Pass");

		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		test.fail(result.getThrowable());
			extentTest.get().fail(result.getThrowable());
		

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		} catch (Exception  e1) {
			
			e1.printStackTrace();
		}
		 String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
//			test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();

		
		
	}


}
