import Common.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaLocalFirefoxDriver {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
    	WebDriver driver ;
    	System.setProperty("webdriver.gecko.driver","/Applications/geckodriver");
    	driver = new FirefoxDriver();
    	
        String expectedTitle = "Dashboard";
        String actualTitle = "";
        
        Waiter _waiter = new Waiter(driver);
        
    	try
    	{
    		
	        //Step 1: Open the BrowserStack site
	    	driver.get("https://www.browserstack.com/");
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
	        
    	}
    	catch(Exception e)
    	{
            System.out.println("Exception");

    	}
    	finally{
            
    		//close Chrome driver
            driver.quit();
    		
    	}
       
        // exit the program explicitly
        System.exit(0);
    }

}