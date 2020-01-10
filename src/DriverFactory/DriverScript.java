package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.FunctionalLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {

	static WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	@Test
	public void ERP_Account() throws Throwable{
		//creating reference object for excel util methods
		ExcelFileUtil xl=new ExcelFileUtil();
		////iterating all row in MasterTestCases Sheet
		for(int i=1;i<=xl.rowCount("MasterTestCases");i++){
			if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y")){
				//store module name into TCModule
				String TCmodule=xl.getCellData("MasterTestCases", i, 1);
				//generate user define html report
				report=new ExtentReports("./Reports//"+TCmodule+FunctionalLibrary.generateDtae()+".html");
						
				//iterate all rows in TcModule sheet
				for(int j=1;j<=xl.rowCount(TCmodule); j++)
{test=report.startTest(TCmodule);
					//read all coloumns from TC MOdule
					String Description=xl.getCellData(TCmodule, j, 0);
					String Function_Name=xl.getCellData(TCmodule, j, 1);
					String Locator_Type=xl.getCellData(TCmodule, j, 2);
					String Locator_Value=xl.getCellData(TCmodule, j, 3);
					String Test_Data=xl.getCellData(TCmodule, j, 4);
					try{
						if(Function_Name.equalsIgnoreCase("startBrowser")){
							driver=FunctionalLibrary.startBrowser();
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("openApplication")){
							FunctionalLibrary.openApplication(driver);
							test.log(LogStatus.INFO, Description);
							}
						else if(Function_Name.equalsIgnoreCase("waitForElement")){
							FunctionalLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("typeAction")){
							FunctionalLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);}
						else if(Function_Name.equalsIgnoreCase("clickAction")){
							FunctionalLibrary.clickAction(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);}
						else if(Function_Name.equalsIgnoreCase("closeBrowser")){
							FunctionalLibrary.closeBrowser(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("capturedata")){
							FunctionalLibrary.captureData(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("tablevalidation")){
							FunctionalLibrary.tableValidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						//write as pass into status coloumn
						xl.setCellData(TCmodule, j, 5, "pass");
						xl.setCellData("MasterTestCases", i, 3, "pass");
						test.log(LogStatus.PASS, Description);
}catch(Throwable t){
	System.out.println("exception handled");
	xl.setCellData(TCmodule, j, 5, "fail");
	xl.setCellData("MasterTestCases", i, 3, "fail");
	test.log(LogStatus.PASS, Description);
}
					report.endTest(test);
					report.flush();
}			
}
		else
	{
		//write as not executed into status coloumn in Master Test Cases
			xl.setCellData("MasterTestCases", i, 3,"Not Executed");
	}
		}
	}

}
