package com.utilites

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.google.common.collect.ArrayTable.Row
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook


import com.kms.katalon.core.configuration.RunConfiguration


public class WriteExcelStatus {

	public void demoWriteExcel (int indexRow, String Strtest) throws IOException {
		String path = RunConfiguration.getProjectDir()
		FileInputStream fis = new FileInputStream (path+'\\DataPaketId.xlsx')
		XSSFWorkbook workbook = new XSSFWorkbook(fis)
		XSSFSheet sheet = workbook.getSheet("Sheet1")
		XSSFRow row = sheet.getRow(indexRow)

		if (row==null){
			sheet.createRow(indexRow)
			println("row masuk if")
		}
	
		XSSFCell cell = null

		if (cell==null)
			//sheet.createRow(indexRow)
			for (int i=1; i<2; i++){
				sheet.getRow(indexRow).createCell(7).setCellValue(Strtest)
			}

		FileOutputStream fos = new FileOutputStream (path+'\\DataPaketId.xlsx')
		workbook.write(fos)
		fos.close()
	}


}
