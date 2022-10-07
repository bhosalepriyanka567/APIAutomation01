package api.com.core;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.LogStatus;

import api.com.util.ExtentReport;
import api.com.util.Helper;

public class BaseTest {
	
	public static String baseurl = "";
	
	@Parameters({"ReportName","FlowType"})
	@BeforeSuite(alwaysRun = true)
	public void config(@Optional("Optional name Automation ") String reportname, @Optional("API Report") String sgsdg)
	 throws IOException{
		//baseurl = Helper.propertyReader("baseurl");
		//Create the path in which we will create folder to keep html reports
		String subfolderpath = System.getProperty("user.dir")+"/lib/htmlReports/" + Helper.Timestamp();
		//Create sub folder
		Helper.CreateFolder(subfolderpath);
		
		//Whatever path mentioned here reports gets generated on the same location
		ExtentReport.initialize(subfolderpath + "/" + "API_G15Automation.html");
		
		//Log path
		//com.automation.api.Util.Logging.setLogPath(subfolderpath + "/" + "Training_logs.log");
		
		//Create Logginh instance
		//log = Logging.getInstance();
	}
	
	@BeforeMethod(alwaysRun = true)
	public static void LogBeforeMethod() {
		//final Logging log = Logging.getInstance();
		System.out.println("Test case execution started>>>>>>>>>>>>>>>>>>>>>");
		//log.info("Test case", "********************************************")
	}
	
 @AfterMethod(alwaysRun = true)
 public void getResult(ITestResult result) {
	 if(result.getStatus() == ITestResult.SUCCESS) {
		 ExtentReport.extentlog.log(LogStatus.PASS, "Test Case : "+ result.getName() + " is passed");
	 }
	 else if(result.getStatus() == ITestResult.FAILURE) {
		 ExtentReport.extentlog.log(LogStatus.FAIL, "Test Case : "+ result.getName() + " is failed");
		 ExtentReport.extentlog.log(LogStatus.FAIL, "Test case is failed due to "+ result.getThrowable());
	 }
	 else if(result.getStatus() == ITestResult.SKIP) {
		 ExtentReport.extentlog.log(LogStatus.SKIP, "Test case is Skipped "+ result.getName());
	 }
	 ExtentReport.extentreport.endTest(ExtentReport.extentlog);
 }
 
 @AfterSuite(alwaysRun=true)
 public void endReport() {
	 //ExtentReport.extentreport.flush();
	 ExtentReport.extentreport.close();
	 //Logging.setinstanceNull();
 }
}
