package DriverFactory;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDriven {
	WebDriver driver;
	@BeforeTest
	public void setUp() throws Throwable{
		String launch=ERP_Functions.launchApp("http://webapp.qedge.com");
		Reporter.log(launch,true);
		String login=ERP_Functions.verifylogin("admin", "master");
		Reporter.log(login,true);
		
	}
	@org.testng.annotations.Test
	public void suplierCreation() throws Throwable{
		ExcelFileUtil xl=new ExcelFileUtil();
		int rc=xl.rowCount("supplier");
		int cc=xl.colcount("supplier");
		Reporter.log("no of rows are::"+rc+"   "+"no of coloumns are::"+cc,true);
		for(int i=1;i<=rc;i++){
			String sname=xl.getCellData("Supplier", i, 0);
			String address=xl.getCellData("Supplier", i, 1);
			String city=xl.getCellData("Supplier", i, 2);
			String country=xl.getCellData("Supplier", i, 3);
			String cperson=xl.getCellData("Supplier", i, 4);
			String pnumber=xl.getCellData("Supplier", i, 5);
			String email=xl.getCellData("Supplier", i, 6);
			String mnumber=xl.getCellData("Supplier", i, 7);
			String notes=xl.getCellData("Supplier", i, 8);
		
			String results=ERP_Functions.verifySupplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
			Reporter.log(results,true);
			xl.setCellData("supplier", i, 9, results);
			
			
		}
		}
	@AfterTest
	public void tearDown(){
		ERP_Functions.verifylogout();
}


}

