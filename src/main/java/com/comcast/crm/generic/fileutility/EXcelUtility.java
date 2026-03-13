package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class EXcelUtility {
	
	public String getDataFroamExcel(String sheetNAme , int rorNum , int celNum) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./testData/genericPractice1.xlsx");
		
		Workbook wb= WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetNAme).getRow(rorNum).getCell(celNum).getStringCellValue();
		 wb.close();
		return data;
		
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
       FileInputStream fis = new FileInputStream("./testData/genericPractice1.xlsx");
		
		Workbook wb= WorkbookFactory.create(fis);
		
		int rowCount= wb.getSheet(sheetName).getLastRowNum();
		 wb.close();
		return rowCount;
		
	}
	
	public void setDataIntoExcel(String sheetNAme , int rorNum , int celNum, String data) throws EncryptedDocumentException, IOException {
         FileInputStream fis = new FileInputStream("./testData/genericPractice1.xlsx");
		
		Workbook wb= WorkbookFactory.create(fis);
		
		wb.getSheet(sheetNAme).getRow(rorNum).createCell(celNum);
		
		FileOutputStream fos= new FileOutputStream("./testData/genericPractice1.xlsx");
		 wb.write(fos);
		 wb.close();
	}
	
	

}
