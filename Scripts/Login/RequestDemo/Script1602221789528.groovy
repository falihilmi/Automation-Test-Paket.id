import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.Keys as Keys

for (def row = 1; row <= findTestData('DataExcelDemo').getRowNumbers(); row++) {
    WebUI.openBrowser('https://dev.mile.app/request-demo')
    WebUI.maximizeWindow()
    WebUI.setText(findTestObject('Object Repository/PageRequestDemo/input_Full name_name'), findTestData('DataExcelDemo').getValue(
            'Nama', row))
    WebUI.setText(findTestObject('Object Repository/PageRequestDemo/input_Email_email'), findTestData('DataExcelDemo').getValue(
            'Email', row))
    WebUI.setText(findTestObject('Object Repository/PageRequestDemo/input_Phone number_phone'), findTestData('DataExcelDemo').getValue(
            'NoHp', row))
    WebUI.setText(findTestObject('Object Repository/PageRequestDemo/input_Company name_organization_name'), findTestData(
            'DataExcelDemo').getValue('CompanyName', row))
	WebUI.takeScreenshot()
    WebUI.click(findTestObject('Object Repository/PageRequestDemo/button_Request Demo'))
	
	 if(WebUI.verifyElementPresent(findTestObject('PageRequestDemo/Txt_WarningField'),2,FailureHandling.OPTIONAL))
		{
		String textToWrite = WebUI.getText(findTestObject ('PageRequestDemo/Txt_WarningField'))
			if(textToWrite==(findTestData('DataExcelDemo').getValue('Expectation', row)))
				{
				CustomKeywords.'com.utilites.WriteExcelStatus.demoWriteExcel'(row, 'OK Match (PASSED)')}
			else{
				CustomKeywords.'com.utilites.WriteExcelStatus.demoWriteExcel'(row, 'NOT Match (FAIL)')
				}
		WebUI.takeScreenshot()
		CustomKeywords.'com.utilites.WriteExcelActual.demoWriteExcel'(row, textToWrite )
		}
		
		
	else if(WebUI.verifyElementPresent(findTestObject('PageRequestDemo/h1_Thank you for contacting us'), 0,FailureHandling.CONTINUE_ON_FAILURE))
		{
		String textToWrite = WebUI.getText(findTestObject ('PageRequestDemo/h1_Thank you for contacting us'))
		WebUI.takeScreenshot()
		
			if(textToWrite==(findTestData('DataExcelDemo').getValue('Expectation', row)))
			{
				CustomKeywords.'com.utilites.WriteExcelStatus.demoWriteExcel'(row, 'OK Match (PASSED)')}
			else{
				CustomKeywords.'com.utilites.WriteExcelStatus.demoWriteExcel'(row, 'NOT Match (FAIL)')
			}
			CustomKeywords.'com.utilites.WriteExcelActual.demoWriteExcel'(row, textToWrite )
		}
		//assert WebUI.getWindowTitle() == 'https://dev.mile.app/request-success'}
	WebUI.closeBrowser()
}
	  
	 
	 