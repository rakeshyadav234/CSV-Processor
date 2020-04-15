package com.jcg.csv2excel;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.opencsv.CSVReader;

public class CsvToExcel {

	public static final char FILE_DELIMITER = ',';
	public static final String FILE_EXTN = ".xlsx";
	public static final String FILE_NAME = "EXCEL_DATA";

	private static Logger logger = Logger.getLogger(CsvToExcel.class);

	public static String convertCsvToXls(String xlsFileLocation, String csvFilePath) {
		SXSSFSheet sheet = null;
		CSVReader reader = null;
		Workbook workBook = null;
		String generatedXlsFilePath = "";
		FileOutputStream fileOutputStream = null;

		try {

			/****
			 * Get the CSVReader Instance & Specify The Delimiter To Be Used
			 ****/
			//String[] nextLine;
			reader = new CSVReader(new FileReader(csvFilePath), FILE_DELIMITER);

			workBook = new SXSSFWorkbook();
			sheet = (SXSSFSheet) workBook.createSheet("Sheet");

			int rowNum = 0;
			logger.info("Creating New .Xls File From The Already Generated .Csv File");
			CsvUtil.setSapId();
			List<String[]> csvDataList = reader.readAll();
			CsvUtil.sortCsvData(csvDataList);
			for (String[] csvData : csvDataList) {
				if (CsvUtil.sapMap.containsKey(csvData[10])) {
					Row currentRow = sheet.createRow(rowNum++);
					currentRow.createCell(0).setCellValue(csvData[4]);//Sprint No
					currentRow.createCell(1).setCellValue(csvData[2].split(":")[0]);//Story ID
					currentRow.createCell(2).setCellValue(csvData[0]);//Task ID
					currentRow.createCell(3).setCellValue(csvData[1]);//Task detail
					currentRow.createCell(4).setCellValue(CsvUtil.getSapId(csvData[10]));//SAP ID
					currentRow.createCell(5).setCellValue(csvData[10]);//User Name
					currentRow.createCell(6).setCellValue(csvData[5]);// Task Status
					currentRow.createCell(7).setCellValue("3");// Story size
					currentRow.createCell(8).setCellValue("4");// Story clarity Index
					currentRow.createCell(9).setCellValue("Both");// Responsible for SCI
					currentRow.createCell(10).setCellValue(csvData[6]);// Estimated hrs
					currentRow.createCell(11).setCellValue(csvData[6]);// Actual hrs
				}

			}

			generatedXlsFilePath = xlsFileLocation + FILE_NAME + FILE_EXTN;
			logger.info("The File Is Generated At The Following Location?= " + generatedXlsFilePath);

			fileOutputStream = new FileOutputStream(generatedXlsFilePath.trim());
			workBook.write(fileOutputStream);
		} catch (Exception exObj) {
			logger.error("Exception In convertCsvToXls() Method?=  " + exObj);
		} finally {
			try {

				/**** Closing The Excel Workbook Object ****/
				workBook.close();

				/**** Closing The File-Writer Object ****/
				fileOutputStream.close();

				/**** Closing The CSV File-ReaderObject ****/
				reader.close();
			} catch (IOException ioExObj) {
				logger.error("Exception While Closing I/O Objects In convertCsvToXls() Method?=  " + ioExObj);
			}
		}

		return generatedXlsFilePath;
	}

}