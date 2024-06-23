package AmarAcademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	
	public static ExtentReports getReportObject() {
	String path = System.getProperty("user.dir")+ "//reports//index.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setReportName("Automation Report");
	reporter.config().setDocumentTitle("Web Testing ");
	
	ExtentReports extent = new ExtentReports();
	extent.attachReporter(reporter);
	 extent.setSystemInfo("Tester", "Amardeep");
	 extent.createTest(path);
	 return extent;
	}
}
