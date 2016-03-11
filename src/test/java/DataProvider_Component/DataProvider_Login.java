package DataProvider_Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

import Generic_Component.ExcelReadWrite;

public class DataProvider_Login {
	
	
	

	@DataProvider(name="dp_InvalidLogin")
	public static Iterator<String[]> getInvalidLogindata() throws IOException
	{
		
		List<String[]> Obj = flagRowCount("Invalid_Login");
		return Obj.iterator();
		
				
	}
	
	@DataProvider(name="dp_ValidLogin")
	public static Iterator<String[]> getvalidLogindata() throws IOException
	{
		
		List<String[]> obj = flagRowCount("Valid_Login");
		return obj.iterator();
				
	}
	
	//********************************************************8
	public static List<String[]> flagRowCount(String scriptname) throws IOException
	{
		ExcelReadWrite xl= new ExcelReadWrite("D:\\BSI_Project\\Test_Data\\TestData.xls");
		HSSFSheet Login_Scenario = xl.Setsheet("Login_Scenario");
		
		int RowCount = xl.getrowcount(Login_Scenario);
		
		//Create a List
		List<String[]> List_login= new ArrayList<String[]>();
		
		for(int xlRow=1;xlRow<=RowCount;xlRow++)
		{
			
			String Executeflag = xl.Readvalue(Login_Scenario, xlRow, "Execute_Flag");
			String Script_name = xl.Readvalue(Login_Scenario, xlRow, "Script_name");
			
			if((Executeflag.equals("Y"))&& (scriptname.equals(Script_name)))
					{
				
						String [] arr_login= new String[5];
						arr_login[0]= xl.Readvalue(Login_Scenario, xlRow, "TCID");
						arr_login[1]= xl.Readvalue(Login_Scenario, xlRow, "Order");
						arr_login[2]= xl.Readvalue(Login_Scenario, xlRow, "Uname");
						arr_login[3]= xl.Readvalue(Login_Scenario, xlRow, "Pwd");
						arr_login[4]= xl.Readvalue(Login_Scenario, xlRow, "Exp_Res");
				
						List_login.add(arr_login);
						
					}
			
			
		}
		
		
		return List_login;
		
	}
	
	

}
