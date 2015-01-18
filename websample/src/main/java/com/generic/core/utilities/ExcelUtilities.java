package com.generic.core.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.generic.core.onboarding.exceldto.Excel;
import com.generic.core.onboarding.exceldto.ExcelLocationDto;
import com.generic.core.validation.functions.ValidationFunction;
import com.generic.core.validation.validate.GenericValidation;
import com.generic.rest.dto.ResponseDto;


public class ExcelUtilities {

	public static List<Object> readExcelSheet(String fileName, Class clazz) throws IOException, InstantiationException, IllegalAccessException {
		
		FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\pkonwar.ORADEV\\Desktop\\kirana_onboarding.xlsm"));
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheetAt(1);
		System.out.println("First Sheet: " + workbook.getSheetName(1));
		List<Object> excelSheet = new ArrayList<Object>();
		
		Excel excelObject = (Excel)clazz.newInstance();
		for (Row row : sheet) {
			//creating a new object row
			Object aRow = excelObject.createDataTypeObject(row);
			excelSheet.add(aRow);
			System.out.println(aRow);
		}
		fileInputStream.close();
		return excelSheet;
	}
	
	
	public static List<ResponseDto> validate(Class clazz, List<? extends Object> list, Map<String, List<ValidationFunction>> rules) {
		
		GenericValidation validation  = new GenericValidation(clazz, list, rules);
		List<ResponseDto> results = null;
		try {
			results = validation.validate();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return results;
	}
}
