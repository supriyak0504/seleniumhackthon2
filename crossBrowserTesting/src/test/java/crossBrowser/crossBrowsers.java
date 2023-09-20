package crossBrowser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class crossBrowsers {
	WebDriver driver;
	
	@Parameters("browser")
	@BeforeTest
	public void setup(String browser) throws Exception
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("WebDriver.chrome.driver", "C:\\Users\\bhava\\eclipse-workspace\\crossBrowserTesting\\src\\test\\resources\\Drivers\\chromedriver.exe");
			driver=new ChromeDriver();
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("WebDriver.gecko.driver", "C:\\Users\\bhava\\eclipse-workspace\\crossBrowserTesting\\src\\test\\resources\\Drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
			
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			System.setProperty("WebDriver.Edge.driver", "C:\\Users\\bhava\\eclipse-workspace\\crossBrowserTesting\\src\\test\\resources\\Drivers\\edgedriver.exe");
			driver=new EdgeDriver();
		}
		else
		{
			throw new Exception("Browser is not Correct");
			
		}
	
	     
	}


@SuppressWarnings("deprecation")
@Test
public void login()throws InterruptedException 
{
	driver.get("https://www.facebook.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement username=driver.findElement(By.name("uid"));
	username.sendKeys("guru99");
	WebElement pwd=driver.findElement(By.name("password"));
	pwd.sendKeys("guru99");
	
}
@AfterTest
public void closeBrowser() {
   driver.close();
}
}
