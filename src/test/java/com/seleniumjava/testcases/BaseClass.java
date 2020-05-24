package com.seleniumjava.testcases;


import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.seleniumjava.utilities.ReadConfig;

public class BaseClass {

	ReadConfig rc=new ReadConfig();
	
	public String baseURL=rc.getApplicationURL();
	public String username=rc.getUsername();
	public String password=rc.getPassword();
	
	public static WebDriver driver;
	
	//public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		 /*Logger logger=Logger.getLogger("ebanking");
	    PropertyConfigurator.configure("Log4j.properties");*/
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",rc.getChromePath());	
	    driver=new ChromeDriver();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",rc.getIEPath());	
		    driver=new InternetExplorerDriver();
		}
	    
		driver.get(baseURL);
	    
	    
	}
	
	@AfterClass
	public void trearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver,String tname) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+ "/Screenshots/" +tname + ".png");
		FileHandler.copy(source,target);
		System.out.println("Screenshot is taken");
		
	}
}
