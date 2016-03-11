package Scenario_Component;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Generic_Component.BaseClass;
import PageObject_Component.PageObject_Login;

public class Scenario_Login extends BaseClass {
	
	//SoftAssert sAssert= new SoftAssert();
	static Logger log=Logger.getLogger(Scenario_Login.class);
	
	@Test(dataProvider="dp_InvalidLogin",dataProviderClass=DataProvider_Component.DataProvider_Login.class, groups={"smoke"})
	public void TestInValidLogin(String TCID, String Order,String Uname, String Pwd, String Exp_Res) throws IOException
	{
		log.info("Executing the Test case " +TCID + " Order id is  " +Order);
		//InitializeBrowser();
		PageObject_Login lpob= new PageObject_Login(driver);
		lpob.Commonprocess_Login(Uname, Pwd);
		String Actual_Res = lpob.getInvalidmsg();
		//Hard assertion will NOT execute next line of code if it fails
		//Assert.assertEquals(Actual_Res, Exp_Res);
		//sAssert.assertEquals(Actual_Res, Exp_Res);
		
		if(Actual_Res.equals(Exp_Res))
		{
			//System.out.println(TCID +  "  Passed ");
			log.info("Passed as Expected msg is matching with Actual msg" );
			Snapshot(TCID, Order);
		}
		else
		{
			//System.out.println(TCID + "Fail");
			log.info("Failed as Expected msg  " +Exp_Res + "  Actual msg is  "+ Actual_Res );
			Snapshot(TCID, Order);
		}
		
		
		teardown();
		//sAssert.assertAll();
			
	}
	
	
	@Test(dataProvider="dp_ValidLogin", dataProviderClass=DataProvider_Component.DataProvider_Login.class,groups={"regression"})
	public void TestValidlogin(String TCID,String Order, String Uname, String Pwd, String Exp_Res) throws IOException
	{
		//InitializeBrowser();
		PageObject_Login lpob= new PageObject_Login(driver);
		lpob.Commonprocess_Login(Uname, Pwd);
		String Actual_Res = lpob.getValidmsg();
		
		if(Actual_Res.equals(Exp_Res))
		{
			System.out.println(TCID+"-"+Order+ "  is passed");
			
		}
		else
		{
			System.out.println(TCID+"-"+Order+ "  is Failed");
		}
		
		lpob.Click_signout();
		//teardown();
		
		
	}
}
