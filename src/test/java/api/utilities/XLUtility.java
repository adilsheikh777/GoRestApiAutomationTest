package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	
	String path;
	
	public XLUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			//Returns the formatted value of a cell as a String regardless of the cell type.
             data = formatter.formatCellValue(cell);
		    }
	   catch(Exception e){
			                 data = "";
		                 }
		workbook.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException
	{
		File xlfile = new File(path);
		//if file not exist create new file
		if(!xlfile.exists())
		{
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			     workbook.write(fo);
		}
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		
		//if sheet not exist then create new sheet
		if(workbook.getSheetIndex(sheetName)==-1)
		{
			workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);
		}
		
		//if row not exist then create new row
		if(sheet.getRow(rowNum)==null)
		{
			sheet.createRow(rowNum);
			row = sheet.getRow(rowNum);
			cell = row.createCell(colNum);
			cell.setCellValue(data);
			fo = new FileOutputStream(path);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
		}
	}
}
