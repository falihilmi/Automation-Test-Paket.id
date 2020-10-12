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
import org.assertj.core.api.Assertions as Assertions
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import groovy.json.JsonSlurper as JsonSlurper

for (def row = 1; row <= findTestData('DataAPICreate').getRowNumbers(); row++) {
    dtfull_name = findTestData('DataAPICreate').getValue('full_name', row)
    dtemail = findTestData('DataAPICreate').getValue('email', row)
    dtphone = findTestData('DataAPICreate').getValue('phone', row)
    dtorganization_name = findTestData('DataAPICreate').getValue('organization_name', row)

    ResponseObject response = WS.sendRequest(findTestObject('APIRequestDemo', [('full_name') : dtfull_name, ('email1') : dtemail
                , ('phone') : dtphone, ('organization_name') : dtorganization_name]))

    if (WS.verifyResponseStatusCode(response, 200, FailureHandling.OPTIONAL)) {
        'Parse the Json Response'
        JsonSlurper parser = new JsonSlurper()

        def responseAfterParsing = parser.parseText(response.getResponseBodyContent())
        def success1 = responseAfterParsing.get('model').get('full_name')
        def success2 = responseAfterParsing.get('model').get('email')
        def success3 = responseAfterParsing.get('model').get('phone')
        def success4 = responseAfterParsing.get('model').get('organization_name')
        println(response.getResponseBodyContent())

        String hasil = ((((((((('full_name:' + success1) + ' ') + 'email:') + success2) + ' ') + 'phone:') + success3) + 
        ' ') + 'organization_name:') + success4

        println(hasil)

        if (hasil == findTestData('DataAPICreate').getValue('Expectation', row)) {
            CustomKeywords.'com.utilities.WriteExcelStatus.demoWriteExcel'(row, 'OK (PASS)')
        } else {
            CustomKeywords.'com.utilities.WriteExcelStatus.demoWriteExcel'(row, 'NO')
        }
        
        CustomKeywords.'com.utilities.WriteExcelErrorMess.demoWriteExcel'(row, hasil)

     
    } else {
        JsonSlurper parser = new JsonSlurper()

        def responseAfterParsing = parser.parseText(response.getResponseBodyContent())

        println(response.getResponseBodyContent())

        def errormess = responseAfterParsing.get('message')

        println(errormess)

        if (errormess == findTestData('DataAPICreate').getValue('Expectation', row)) {
            CustomKeywords.'com.utilities.WriteExcelStatus.demoWriteExcel'(row, 'OK(PASS)')
        } else {
            CustomKeywords.'com.utilities.WriteExcelStatus.demoWriteExcel'(row, 'NO')
        }
            CustomKeywords.'com.utilities.WriteExcelErrorMess.demoWriteExcel'(row, errormess)
    }
}


