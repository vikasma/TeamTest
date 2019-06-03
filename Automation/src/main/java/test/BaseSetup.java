package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import okio.Timeout;
//import test.Login;
import pages.Homepage;
import utility.ExcelUtils;

public class BaseSetup {

	public static WebDriver driver = null;
	
	
	@Parameters("browser")
	@BeforeClass
	 public void initialize(String browser) throws Exception{
	//	System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
		
		if(browser.equalsIgnoreCase("chrome")){
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		
		} else if(browser.equalsIgnoreCase("firefox")){

			WebDriverManager.firefoxdriver().version("0.19.0").setup();
	//		System.setProperty("webdriver.gecko.driver", "lib\\geckodriver.exe");
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile myprofile = profile.getProfile("QA1");
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(myprofile);
			driver = new FirefoxDriver(options);
			
			 
		}
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 ExcelUtils.setExcelFile("BaseData");
	 String url = ExcelUtils.getCellData(0, 1);
//	 WebDriverWait wait = new WebDriverWait(driver,30);
//	 wait.until(ExpectedConditions.vis)
	 driver.get(url);
	}
	

	
	@AfterClass(alwaysRun=true)
	public void teardown() throws IOException{
		
		
		try{
		Homepage homepage = new Homepage(driver);
		homepage.logOut();
		Thread.sleep(5000);
		} catch (Exception e)
		{
			Login.takeScreenshot();
			System.out.println(e);
		}
		driver.quit();
	}
}
