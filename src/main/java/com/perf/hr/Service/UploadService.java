package com.perf.hr.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.perf.hr.entity.UploadedData;

@Service
public class UploadService {
	
	public void Save() {
		UploadedData upload= new UploadedData();
		
		
	}
	
	public void getData() throws Exception {
		try  
		{  
			
		File file = new File("test.xlsx");   //creating a new file instance  
		FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
		//creating Workbook instance that refers to .xlsx file  
		XSSFWorkbook wb = new XSSFWorkbook(fis);   
		XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
		Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
		int i=0;
		//String[] stringArray = new String[24];
		String myStr=null;
		int j=0;
		while (itr.hasNext())                 
		{  
		Row row = itr.next();  
		Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
	
		while (cellIterator.hasNext())   
		{  
			
		Cell cell = cellIterator.next();  
		if(j!=0) {
			if(i==24) {
				String[] stringArray = new String[24];
			}
			//stringArray[i]=myStr;
			i=i+1;
			
		}
		j=1;
		
	    myStr=null;
			
		switch (cell.getCellType())               
		{ 
		case STRING:    //field that represents string cell type  
			myStr=cell.getStringCellValue();
	
		break;  
		case NUMERIC:    //field that represents number cell type  
			myStr=String.valueOf(cell.getNumericCellValue());
	break;  
		default:  
		}  

		}  
		System.out.println("");  
		}  
		/*
		 * DisplayArray(stringArray); System.out.println(stringArray.length);
		 */
		}  
		catch(Exception e)  
		{  
		e.printStackTrace();  
		}  
	

}
	
	public void DisplayArray(String[] myStr) {
		for(int i=0;i<myStr.length;i++) {
			System.out.println(myStr[i]);
		}
		
		
	}
	
	
	
	
	
}