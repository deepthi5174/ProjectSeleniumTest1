package com.seleniumjava.utilities;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Reporting  extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	 public ExtentReports extent=null;
	 public ExtentTest logger;
	 
	 @BeforeTest
	 public void startReport(ITestContext testContext) {
		 
		 String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 String repName="Test-Report-"+timestamp+".html";
		 
		 //specify the location of Report
	 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" +repName);
	 htmlReporter.loadConfig(System.getProperty("user.dir") + "/extent-config.xml");
	 
	         // Create an object of Extent Reports
	 extent = new ExtentReports(); 
	 
	 extent.attachReporter(htmlReporter);
	 extent.setSystemInfo("Host Name", "Selenium");
	 extent.setSystemInfo("Environment", "QA");
	 extent.setSystemInfo("User Name", "Deepthimai");
	 
	 htmlReporter.config().setDocumentTitle("Title of the Report Comes here "); //Title of report
	               
	 htmlReporter.config().setReportName("Name of the Report Comes here ");  // Name of the report
	                
	 htmlReporter.config().setTheme(Theme.STANDARD); // Dark Theme
	 }
	 
	 @Test
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));//send the passed info
		
	}
	
	 @Test
	public void onTestFailure(ITestResult tr) 
	{
		logger=extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));//send the failed info
		
		String screenshotPath =System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f=new File(screenshotPath);
		if(f.exists())
		{
			try {
				logger.fail("Screenshot is below:" +logger.addScreenCaptureFromPath(screenshotPath));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	 @Test
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));//send the passed info
		
	}
	
	 @AfterTest
	public void onFinish(ITestContext testContext)
	{
		extent.flush();

	}
	 
}
	

