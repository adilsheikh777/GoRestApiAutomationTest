package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders {

	@DataProvider(name = "Data")
	public Object[][] getAllData() throws IOException
	{
		String path = System.getProperty("user.dir")
				                        +"//testdata//userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		int rowNum = xl.getRowCount("sheet1");
		int colCount = xl.getCellCount("sheet1", 1);
		
		Object apidata[][] = new String[rowNum][colCount];
		for(int i=1; i<=rowNum; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				apidata[i-1][j] = xl.getCellData("sheet1", i, j);
			}
		}
		
		return apidata;
	}
	
	@DataProvider(name = "Usernames")
	public String[] getUsernames() throws IOException
	{
		String path = System.getProperty("user.dir")
				                        +"//testdata//userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		int rowNum = xl.getRowCount("sheet1");
		
		String apidata[] = new String[rowNum];
		for(int i=1; i<=rowNum; i++)
		{
			apidata[i-1] = xl.getCellData("sheet1", i, 1);
		}
		
		return apidata;
	}
	
	//@Test
	public void getAllData1() throws IOException
	{
		String path = System.getProperty("user.dir")
				                        +"//testdata//userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		int rowNum = xl.getRowCount("sheet1");
		int colCount = xl.getCellCount("sheet1", 1);
		
		System.out.println(rowNum);
		System.out.println(colCount);
		
		String apidata[][] = new String[rowNum][colCount];
		for(int i=1; i<=rowNum; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				apidata[i-1][j] = xl.getCellData("sheet1", i, j);
			}
		}
		
		for(int i=1; i<=rowNum; i++)
		{
			for(int j=0; j<colCount; j++)
			{
			   System.out.print(apidata[i-1][j] +  "   ");
			}
			System.out.println();
		}
		
	}
	
}
