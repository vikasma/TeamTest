package utility;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;



public class ExcelUtils {
	
	public static XSSFSheet ExcelWSheet; 
	public static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	
	
	//Open the Excel File and then access the Worksheet
	public static void setExcelFile(String SheetName) throws Exception{
		try{
		FileInputStream ExcelFile = new FileInputStream(Constant.testDataFile);
		
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
            
		}catch (Exception e){
			throw (e);
		}
	}
	
	public static String getCellData(int RowNum, int ColNum) throws Exception{
		try{
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		String CellData = Cell.getStringCellValue();
		return CellData;
		}catch(Exception e){
			return "";
		}
		}
	
	
	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception{
	

		try{
		Row = ExcelWSheet.getRow(RowNum);
		Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
		if(Cell==null){
			Cell= Row.createCell(ColNum);
			Cell.setCellValue(Result);
			
		}else {
			Cell.setCellValue(Result);
			
		}
		
		
		FileOutputStream fileout = new FileOutputStream(Constant.testDataFile);
		ExcelWBook.write(fileout);
		fileout.flush();
		fileout.close();
		} catch (Exception e){
			throw (e);
		}
	}
	
	
	
	
}
