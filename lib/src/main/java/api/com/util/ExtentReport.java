package api.com.util;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class ExtentReport {
	public static ExtentReports extentreport = null;
	public static ExtentTest extentlog;
    
	public static void initialize(String path) {
		if (extentreport == null) {
			extentreport = new ExtentReports(path, true);
			extentreport.addSystemInfo("Host Name", System.getProperty("user.name"));
			extentreport.addSystemInfo("Environment", Helper.propertyReader("executionEnv"));
			extentreport.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		}
	}
	
	public static void setinstanceNull()
	{
		extentreport = null;
	}
	
	//Display response time and status code in Extent " report DO NOT USE "
	public static void ExtentReport_logs(Response resp)
	{
		ExtentReportInfoLog("Reponse time is " + String.valueOf(resp.getTime()) + "ms");
		ExtentReportInfoLog("Reponse of API is " + String.valueOf(resp.getStatusCode()));
	}
	
	//log in extent report
	public static void ExtentReportInfoLog(String message)
	{
		extentlog.log(LogStatus.INFO, message);
	}
	
	public static void ExtentReportErrorLog(String message)
	{
		extentlog.log(LogStatus.ERROR, message);
	}
}
