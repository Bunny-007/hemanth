package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERP_Functions {
	public static WebDriver driver;


//launch browser and url
public static String launchApp(String url)
{
String res="";
System.setProperty("webdriver.chrome.driver","D:\\Selenium_Evening\\ERP-Stock\\CommonDriver\\chromedriver.exe");
driver=new ChromeDriver();
driver.get(url);
driver.manage().window().maximize();
if(driver.findElement(By.id("btnsubmit")).isDisplayed())
{res="Application Launch Success";
}
else
{
	res="Application Launch fail";
	
}
return res;
}
//logi9n method
public static String verifylogin(String username,String password) throws Throwable{
	
String res="";
WebElement objuser=driver.findElement((By.name("username")));
objuser.clear();
Thread.sleep(3000);
objuser.sendKeys(username);
WebElement objpass=driver.findElement(By.name("password"));
objpass.clear();
Thread.sleep(3000);
objpass.sendKeys(password);
driver.findElement(By.id("btnsubmit")).click();
Thread.sleep(5000);
if(driver.findElement(By.id("logout")).isDisplayed())
{
	res="Login Success";
}
else
{
res="Login Fail";
}
return res;
}
public static void verifylogout()
{
	driver.close();
}


public static String verifySupplier(String sname,String address,String city,String country,String cperson,String pnumber,String email,String mnumber,String notes) throws Throwable
{
	String res="";
	driver.findElement(By.id("mi_a_suppliers")).click();
	Thread.sleep(4000);
	driver.findElement(By.xpath("//div[@class='panel-heading ewGridUpperPanel']//span[@class='glyphicon glyphicon-plus ewIcon']")).click();
	Thread.sleep(4000);
	String expdata=driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
	driver.findElement(By.name("x_Supplier_Name")).sendKeys(sname);
driver.findElement(By.name("x_Address")).sendKeys(address);
driver.findElement(By.name("x_City")).sendKeys(city);
driver.findElement(By.name("x_Country")).sendKeys(country);
driver.findElement(By.name("x_Contact_Person")).sendKeys(cperson);
driver.findElement(By.name("x_Phone_Number")).sendKeys(pnumber);
driver.findElement(By.name("x__Email")).sendKeys(email);
driver.findElement(By.name("x_Mobile_Number")).sendKeys(mnumber);
driver.findElement(By.name("x_Notes")).sendKeys(notes);
driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
Thread.sleep(4000);
driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
driver.findElement(By.xpath("//button[@class='ajs-button btn btn-primary']")).click();
Thread.sleep(4000);
if(!driver.findElement(By.name("psearch")).isDisplayed())
//click on search panel
driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon']")).click();
Thread.sleep(2000);
driver.findElement(By.name("psearch")).clear();
Thread.sleep(2000);
driver.findElement(By.name("psearch")).sendKeys(expdata);
Thread.sleep(4000);
driver.findElement(By.name("btnsubmit")).click();
Thread.sleep(4000);
//get supplier number from table
String Actdata=driver.findElement(By.xpath("//tbody//td[6]//div[1]")).getText();
Thread.sleep(4000);
System.out.println(expdata+"   "+Actdata);
if(Actdata.equals(expdata))
{
	res="pass";
}
else
{
	res="fail";
}
return res;
}



}




