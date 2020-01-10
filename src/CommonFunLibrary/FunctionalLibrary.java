package CommonFunLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;

public class FunctionalLibrary {
	static WebDriver driver;
	//Method for Launching Browser
	public static WebDriver startBrowser() throws Throwable{
		if(PropertyFileUtil.getVAlueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Evening\\ERP-Stock\\CommonDriver\\chromedriver.exe");
			driver=new ChromeDriver();	
		}
		return driver;
	}
	public static void openApplication(WebDriver driver) throws Throwable{
		driver.get(PropertyFileUtil.getVAlueForKey("Url"));
		driver.manage().window().maximize();
	
	}
//method for wait for element
	public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,String timewait){
		WebDriverWait mywait=new WebDriverWait(driver,Integer.parseInt(timewait));
		if(locatortype.equalsIgnoreCase("id")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		}else if(locatortype.equalsIgnoreCase("name")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		}else if(locatortype.equalsIgnoreCase("xpath")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
		}
				
			}
		
		
	
// method for closer browser
public static void closeBrowser(WebDriver driver){
	driver.quit();
}

//type action method
public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,String testdata){
	if(locatortype.equalsIgnoreCase("id")){
		driver.findElement(By.id(locatorvalue)).clear();
		driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
	}
	else if(locatortype.equalsIgnoreCase("name")){
		driver.findElement(By.name(locatorvalue)).clear();
		driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
	}
	else if(locatortype.equalsIgnoreCase("xpath")){
		driver.findElement(By.xpath(locatorvalue)).clear();
		driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
	}
	}
		//click action method
		public static void clickAction(WebDriver driver,String locatortype,String locatorvalue){
			if(locatortype.equalsIgnoreCase("id")){
				driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
			}else if(locatortype.equalsIgnoreCase("name")){
				driver.findElement(By.name(locatorvalue)).sendKeys(Keys.ENTER);}
			else if(locatortype.equalsIgnoreCase("xpath")){
				
				driver.findElement(By.xpath(locatorvalue)).click();
			}
		}
		public static String generateDtae(){
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
			return sdf.format(date);
				
			

		
		
		
	}
		//method for capture data
		public static void captureData(WebDriver driver,String locatortype,String locatorvalue) throws Throwable{
		String snumber="";
		if(locatortype.equalsIgnoreCase("id")){
			snumber=driver.findElement(By.id(locatorvalue)).getAttribute("value");
		}
		else if(locatortype.equalsIgnoreCase("name")){
			snumber=driver.findElement(By.name(locatorvalue)).getAttribute("value");
		}
		else if(locatortype.equalsIgnoreCase("xpath")){
			snumber=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
		}
		//write supplier number into note pad
		FileWriter fw=new FileWriter("D:\\Selenium_Evening\\ERP-Stock\\CaptureData\\supplier.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(snumber);
		bw.flush();
		bw.close();
		}
		public static void tableValidation(WebDriver driver,String testdat) throws Throwable{
			FileReader fr=new FileReader("D:\\Selenium_Evening\\ERP-Stock\\CaptureData\\supplier.txt");
		BufferedReader br=new BufferedReader(fr);
		String expdata=br.readLine();
		//convert coloumndata into integer
		int coloumn=Integer.parseInt(testdat);
		if(!driver.findElement(By.xpath(PropertyFileUtil.getVAlueForKey("search-box"))).isDisplayed()){
			driver.findElement(By.xpath(PropertyFileUtil.getVAlueForKey("search-panel"))).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath(PropertyFileUtil.getVAlueForKey("search-box"))).clear();
			Thread.sleep(5000);
			driver.findElement(By.xpath(PropertyFileUtil.getVAlueForKey("search-box"))).sendKeys(expdata);
			Thread.sleep(5000);
			driver.findElement(By.xpath(PropertyFileUtil.getVAlueForKey("search-button"))).click();
			WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getVAlueForKey("supp-table")));
List<WebElement>rows=table.findElements(By.tagName("tr"));
System.out.println("no of rows are::"+rows.size());
for(int i=1;i<rows.size();i++){
	String act_data=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr["+1+"]/td["+coloumn+"]/div/span/span")).getText();
Thread.sleep(5000);
System.out.println(expdata+"   "+act_data);
Assert.assertEquals(act_data, expdata,"snumber is not matching");
break;
}


		}
		}
}



