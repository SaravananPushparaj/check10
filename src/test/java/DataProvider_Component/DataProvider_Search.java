package DataProvider_Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

import Generic_Component.ExcelReadWrite;

public class DataProvider_Search {

	@DataProvider(name="dp_Invalidsearch")
	public static Iterator<String[]> InvalidSearch() throws IOException
	{
		
		List<String[]> Obj = flagRowCount("Invalid_Search");
		return Obj.iterator();
		
	}
	
	@DataProvider(name="dp_validsearch")
	public static Iterator<String[]> ValidSearch() throws IOException
	{
		
		List<String[]> Obj = flagRowCount("Valid_Search");
		return Obj.iterator();
		
	}
	
	//******************************
	public static List<String[]> flagRowCount(String scriptname) throws IOException
	{
		ExcelReadWrite xl= new ExcelReadWrite("D:\\BSI_Project\\Test_Data\\TestData.xls");
		HSSFSheet Book_Search = xl.Setsheet("Book_Search");
		
		int RowCount_search = xl.getrowcount(Book_Search);
		
		List<String[]> List_search= new ArrayList<String[]>();
		
		for(int xlRow=1;xlRow<=RowCount_search;xlRow++)
		{
			String Execute_Flag = xl.Readvalue(Book_Search, xlRow, "Execute_Flag");
			String Script_name = xl.Readvalue(Book_Search, xlRow, "Script_name");
			
			if((Execute_Flag.equals("Y")) && (Script_name.equals(scriptname)))
			{
				String[] arr_search= new String[4];
				
				arr_search[0]= xl.Readvalue(Book_Search, xlRow, "TCID");
				arr_search[1]= xl.Readvalue(Book_Search, xlRow, "Order");
				arr_search[2]= xl.Readvalue(Book_Search, xlRow, "Search_Item");
				arr_search[3]= xl.Readvalue(Book_Search, xlRow, "Exp_Res");
				arr_search[3]=arr_search[3].replace(".0", "");
				
				List_search.add(arr_search);
				
			}
			
		}
		
		return List_search;
		
		
		
	}
	
	
	
}
