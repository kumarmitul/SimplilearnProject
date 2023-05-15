/*
Login to Amazon
 
Search 'watch'
Locate the item
Click on item

Move control to different tab
Select the quantity from drop down
Click on 'Add to Cart' button

Locate the 'Skip' button
Click on 'Skip' button

Click on 'Go to Cart' button

Verify the name of item
Verify the quantity of item

item = TIMEWEAR Analog Day Date Functioning Stainless Steel Chain Watch for Men

span[contains(text(),'Analog Day Date Functioning Stainless Steel Chain Watch for Men')]

*/

package com.testcases;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AmazonBook {

	WebDriver driver = new ChromeDriver();
	Actions act = new Actions(driver);
	
	public static void main(String args[]) {
		
		AmazonBook azb = new AmazonBook();
		azb.launchSite();
		azb.login();
		azb.searchItem();
		azb.selectItem();
		azb.diffTab();
		azb.selectQty();
		azb.goCart();
		azb.verifyItem();
		azb.verifyQty();
		azb.closeApp();
		
	}
	public void launchSite() {
		// Launch the browser and maximize 
		System.setProperty("webdriver.chrome.driver","C:\\Users\\mitul\\OneDrive\\Documents\\MITUL KUMAR\\Automation Test\\chromedriver_win32\\chromedriver.exe");
		driver.manage().window().maximize();
		// Open the URL
		driver.get("https://amazon.in");
		
	}
	
public void login() {
		
		WebElement yourAccount = driver.findElement(By.xpath("//a[text()='Your Account']"));
		act.click(yourAccount).build().perform();
		
		// Locate the 'Account & Lists' and hover the mouse
		WebElement acli = driver.findElement(By.cssSelector("a#nav-link-accountList"));
		act.moveToElement(acli).build().perform();
		
		//Locate the 'Sign In' button and perform mouse click
		WebElement signin1 = driver.findElement(By.xpath("//span[@class='nav-action-inner']"));
		act.click(signin1).build().perform();
		
		//Locate the 'Enter mobile phone number or email' textbox and enter username
		WebElement email = driver.findElement(By.id("ap_email"));
		email.sendKeys("mituldotcom@gmail.com");
		
		//Locate the 'Continue' button and perform mouse click
		WebElement cont = driver.findElement(By.xpath("//span[@id='continue']"));
		act.click(cont).build().perform();
		
		//Locate the 'Password' field and enter the password
		WebElement pwd = driver.findElement(By.id("ap_password"));
		pwd.sendKeys("Harekrishna");
		
		//Locate the 'Sign In' button and perform mouse click
		WebElement signin2 = driver.findElement(By.xpath("//span[@class='a-button-inner']"));
		act.click(signin2).build().perform();
		
	}
	
  public void searchItem() {
	
	       //Locate the 'Search' textbox and enter the item to search
	
			WebElement srcbox = driver.findElement(By.xpath("//div[@class='nav-search-field ']//following :: input[@id='twotabsearchtextbox']"));
			srcbox.sendKeys("gaur gopal das");
			
		    //Locate the Search button on textbox and perform mouse click	
			WebElement ac = driver.findElement(By.id("nav-search-submit-button"));
			act.click(ac).build().perform();
}
   
  public void selectItem() {
	  
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  try {
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));	
			WebElement addCart = driver.findElement(By.xpath("//span[contains(text(),'LIFES AMAZING SECRETS')]"));
			js.executeScript("arguments[0].scrollIntoView();", addCart);
			act.click(addCart).build().perform();

			}
			catch(Exception e) {
				
			System.out.println("The item is not available.");
	//		closeApp();
			e.printStackTrace();
			}
	  
  }
  
  public void diffTab() {
	  
	    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Number of tabs opened: " +tabs.size());
		
		driver.switchTo().window((String)tabs.get(1));
	
}
  
  public void selectQty() {
	  
	  try {
			
			WebElement qty = driver.findElement(By.xpath("//select[@id='quantity']"));
			Select count = new Select(qty);
			count.selectByValue("3");
			WebElement cartButton = driver.findElement(By.xpath("//span[@id='submit.add-to-cart']//following ::input[@id='add-to-cart-button']"));
			act.click(cartButton).build().perform();
	  }
	  catch(Exception e) {
		  System.out.println("Quantity is not available");
		  e.printStackTrace();
	  }
  }
  
 /* public void skip() {
	  
		WebElement skip = driver.findElement(By.xpath("(//span[@class='a-button-inner']/input[@class='a-button-input'])[1]"));
		act.click(skip).build().perform();
  }
 */ 
  public void goCart() {
	  WebElement gotoCart = driver.findElement(By.xpath("//a[@id='nav-cart']"));
	  act.click(gotoCart).build().perform();
  }
  public void verifyItem() {
	  String label = "LIFES AMAZING SECRETS";
	  WebElement itemName = driver.findElement(By.xpath("(//span[contains(text(),'LIFES AMAZING SECRETS')])[1]"));
	  if (itemName.getAttribute("innerHTML").equals(label)) {
		  System.out.println("Item Name is correct");
	  }
	  else {
		  System.out.println("Item Name is wrong");
	  }
  }
  
  public void verifyQty() {
	  
	  String quant = "3";
	  WebElement qty = driver.findElement(By.xpath("(//span[@class='a-button-inner'])[7]//following :: input"));
	  if (qty.getAttribute("value").equals(quant)){
		  System.out.println("Quantity is matching and quantity is ="+qty.getAttribute("value"));
	  }
	  else {
		  System.out.println("Quantity is not correct and value is ="+qty.getAttribute("value"));
	  }
  }
  
	public void closeApp() {
		System.out.println("Printed after execution");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		WebElement acli = driver.findElement(By.cssSelector("a#nav-link-accountList"));
		act.moveToElement(acli).build().perform();
		WebElement signout = driver.findElement(By.xpath("(//span[@class='nav-text'])[18]"));
		act.click(signout).build().perform();
		driver.close();
	}
}
