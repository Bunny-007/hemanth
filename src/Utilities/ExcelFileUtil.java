package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelFileUtil {
	Workbook wb;
	//constructor for read excel path
	public ExcelFileUtil() throws Throwable
	{
		FileInputStream fi=new FileInputStream("D:\\Selenium_Evening\\ERP-Stock\\TestInput\\InputSheet.xlsx");
		wb=WorkbookFactory.create(fi);
	}
//count no of rows in a sheet
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
		
	}
	//count no of coloumns in row
	public int colcount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	//reading data from cell
public String getCellData(String sheetname,int row,int coloumn)
	{
		String data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(coloumn).getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(coloumn).getNumericCellValue();
			//convert celldata into string type
			data=String.valueOf(celldata);
			
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(coloumn).getStringCellValue();
		}
		return data;
		}
//write results into status coloumn
public void setCellData(String sheetname,int row,int coloumn,String status) throws Throwable
{
	//get sheet from wb
	org.apache.poi.ss.usermodel.Sheet ws=wb.getSheet(sheetname);
	//het row from sheet
	Row rownum=ws.getRow(row);
	//create coloumn
	Cell cell=rownum.createCell(coloumn);
	//write status
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("pass"))
	{
		//Create cell style
		CellStyle style=wb.createCellStyle();
		//create a font
		Font font=wb.createFont();
		//apply color to the text
		font.setColor(IndexedColors.GREEN.getIndex());
		//apply color bold
		font.setBold(true);
		//set font
		style.setFont(font);
		//rset cell style
		rownum.getCell(coloumn).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("not Executed"))
	{
		//Create cell style
				CellStyle style=wb.createCellStyle();
				//create a font
				Font font=wb.createFont();
				//apply color to the text
				font.setColor(IndexedColors.BLUE.getIndex());
				//apply color bold
				font.setBold(true);
				//set font
				style.setFont(font);
				//rset cell style
				rownum.getCell(coloumn).setCellStyle(style);
}

FileOutputStream fo=new FileOutputStream("D:\\Selenium_Evening\\ERP-Stock\\TestOutput\\Hybrid.xlsx");
	wb.write(fo);
	fo.close();
}
}
