package Generic_Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public WebDriver driver;
	Utility_Class c1= new Utility_Class();
	static Logger log= Logger.getLogger(BaseClass.class);
	
	@Parameters("browser")
	@BeforeMethod
	
	public void InitializeBrowser(String browser_type) throws IOException
	{
		
//		DesiredCapabilities cap = new DesiredCapabilities();
//		if(browser_type.equalsIgnoreCase("ff"))
//		{
//			cap.setBrowserName("firefox");
//		}		
//		else if(browser_type.equalsIgnoreCase("ch"))
//		{
//			cap.setBrowserName("chrome");
//		}
//		else if(browser_type.equalsIgnoreCase("ie"))
//		{
//			cap.setBrowserName("internet explorer");
//		}
//		cap.setPlatform(Platform.WINDOWS);
//		URL url =new URL("http://192.168.0.107:4444/wd/hub");
//		 driver = new RemoteWebDriver(url,cap);
		 
		//browser_type = c1.reading_properties("btype");
		
		if(browser_type.equals("ff"))
		{
		driver= new FirefoxDriver();
		}
		
		else if(browser_type.equals("ch"))
		{
			
		System.setProperty("webdriver.chrome.driver",c1.reading_properties("Chrome_Path"));
		driver= new ChromeDriver();
		}
		
		else if(browser_type.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",c1.reading_properties("IE_Path"));
			driver= new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(c1.reading_properties("URL"));
		
		log.info("Successfully initialized the browser");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
		log.info("Successfully closed the Browser");
	}
	

	public void Snapshot(String TCID, String Order) throws IOException
	{
		Date date= new Date();
		SimpleDateFormat dateformat= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		File file= new File(dateformat.format(date) +".png");
		
		
		TakesScreenshot screenshot= (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshotAs, new File("D:\\BSI_Project\\Screenshot\\" +TCID+"-"+Order+"-"+file));
		
	}
	
}
