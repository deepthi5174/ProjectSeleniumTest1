package com.seleniumjava.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;




public class LoginPage {
	
	WebDriver ldriver;
	//WebDriverWait wait;
     public	LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		//wait=new WebDriverWait(rdriver,30);
		PageFactory.initElements(rdriver,this);
	}
     
     
   
	 //@FindBy(how=How.NAME,using="uid")
	@FindBy(xpath="//input[@name='uid']")
	WebElement txtUserName;
	
	/*public void MyFunction(){
        wait.until(ExpectedConditions.visibilityOf(txtUserName));
    }*/
  
	
	@FindBy(name = "password")
	WebElement txtPassword;
    
	
	@FindBy(name = "btnLogin")
	WebElement btnLogin;
	
	public void setUserName(String uname)
	{
		txtUserName.sendKeys(uname);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickSubmit()
	{
		btnLogin.click();
	}
}
