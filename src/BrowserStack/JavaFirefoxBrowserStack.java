package BrowserStack;
import Common.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class JavaFirefoxBrowserStack {

  public static final String USERNAME = "user";
  public static final String AUTOMATE_KEY = "key";
  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

  public static void main(String[] args) throws Exception {

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("browser", "firefox");
    caps.setCapability("os", "WINDOWS");
    caps.setCapability("browserstack.debug", "true");

    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
    
    String expectedTitle = "Dashboard";
    String actualTitle = "";
	String baseurl = "https://www.browserstack.com/";
	
	Waiter _waiter = new Waiter(driver);
    
    //Step 1: Open the BrowserStack site
	driver.get(baseurl);
	driver.manage().window().maximize();
	_waiter.waitForMe(By.linkText("Sign in"), 5);
	
	//Step 2: Search for Sign In link and click on it
	driver.findElement(By.linkText("Sign in")).click();
	_waiter.waitForMe(By.id("user_email_login"), 5);
    
	//Step 3: Enter the email and password and click on Sign in button
    driver.findElement(By.id("user_email_login")).sendKeys("email");
    driver.findElement(By.id("user_password")).sendKeys("password");
    driver.findElement(By.id("user_submit")).click(); 
    _waiter.waitForMe(By.xpath("//*[@id=\"rf-browsers\"]/div/div[2]/div[4]/ul/li[1]/a"), 5);
    
    
    //Step 5: Select the browser you want to open live session for and click on it
    driver.findElement(By.xpath("//*[@id=\"rf-browsers\"]/div/div[2]/div[4]/ul/li[1]/a")).click(); 
    _waiter.waitForMe(By.id("dock"), 10);
    
    actualTitle = driver.getTitle();
    
    //Step 6: Check whether the live session was successful by checking the title of the Live session page
    if (actualTitle.contentEquals(expectedTitle)){
        System.out.println("Test Passed!");
    } else {
        System.out.println("Test Failed");
    } 
    
    /*driver.get("http://www.google.com");
    WebElement element = driver.findElement(By.name("q"));

    element.sendKeys("BrowserStack Automate");
    element.submit();

    System.out.println(driver.getTitle());
    System.out.println("Success");*/
    driver.quit();

  }
}