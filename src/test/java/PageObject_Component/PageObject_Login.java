package PageObject_Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObject_Login {
	
	public WebDriver driver;
	
	//storage of the elements
	@FindBy(linkText="Sign In")
	WebElement lnk_Sigin;
	
	@FindBy(name="logid")
	WebElement txt_Username;
	
	@FindBy(name="pswd")
	WebElement txt_Password;
	
	@FindBy(css="input[type='submit'][value='Login']")
	WebElement btn_Login;
	
	@FindBy(css="html body table tbody tr td table tbody tr td table tbody tr td table tbody tr td font b")
	WebElement msg_Invalid;
	
	@FindBy(css="html body div#wrap div#header.topborder.relative div.floatR.extra_gap div.floatL div span#username.bold a.proper")
	WebElement msg_Valid;
	
	@FindBy(linkText="Sign Out")
	WebElement lnk_Signout;
	
	
	public PageObject_Login(WebDriver driver)
	{
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void Click_Sigin()
	{
		
		lnk_Sigin.click();
		
	}
	
	public void Enter_Username(String Value)
	{
		txt_Username.sendKeys(Value);
		
	}
	
	
	public void Enter_Password(String Value)
	{
		txt_Password.sendKeys(Value);
		
	}
	
	public void Click_Login()
	{
		
		btn_Login.click();
		
	}
	
	public String getInvalidmsg()
	{
		return msg_Invalid.getText();
		
	}
	
	public String getValidmsg()
	{
		
		return msg_Valid.getText();
	}
	
	public void Click_signout()
	{
		
		lnk_Signout.click();
	}
	
	//***********************************************
	
	public void Commonprocess_Login(String Uname, String Pwd)
	{
		Click_Sigin();
		Enter_Username(Uname);
		Enter_Password(Pwd);
		Click_Login();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
