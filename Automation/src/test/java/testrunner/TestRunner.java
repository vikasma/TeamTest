package testrunner;

//import org.junit.runner.RunWith;


//import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import cucumber.api.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"pretty","pretty:target/cucumber-pretty.txt","html:target/cucumber-report","json:target/cucumber.json",
				"junit:target/cucumber-results.xml","usage:target/cucumber-usage.xml","rerun:target/rerun.txt"},
		//dryRun=true,
		monochrome=true,
		features="src/test/features",
		glue="stepDefinitions"
		)

public class TestRunner extends AbstractTestNGCucumberTests{
	public static WebDriver driver=null;
	@BeforeSuite
	public void before_suite() throws IOException{
		System.out.println(System.getProperty("user.dir"));
		Date date = new Date();
		DateFormat dateformat = new SimpleDateFormat("HH:MM:SS");
		System.out.println(dateformat.format(date));
		
//		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		File des = new File("D://");
//		FileUtils.copyFileToDirectory(src, des);
		//driver.manage().deleteAllCookies();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.MILLISECONDS);
	}
	
	@AfterSuite(alwaysRun=true)
	public void afterexecution(){
		
	}

}
