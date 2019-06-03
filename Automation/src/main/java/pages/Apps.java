package pages;

import static org.testng.Assert.assertTrue;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import test.Login;
import test.Login.AppName;

/**
 * This class will hold on Apps object
 * @author vikas.ma
 *
 */
public class Apps {
	
	 By listapps = By.xpath("//div[@class='app-list__apps app-list__apps--reduced'][@id='globalButtonApps']/div");
	
	WebDriver driver;
	public Apps (WebDriver driver){
		this.driver=driver;
	}
	
	public void listAllApps() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("return document.readyState").equals("complete");
		//List<WebElement> apps = driver.findElements(By.xpath("//div[@class='app-list__apps']/div")); //Before reduced apps list
		List<WebElement> apps = driver.findElements(listapps);
		int numberOfApps = apps.size();
		//numberOfApps = numberOfApps+1;
		System.out.println("Number of Apps: "+numberOfApps);

	}
	
	
	public void selectApp(Login.AppName appName) throws Exception{
		
		//String appName = ExcelUtils.getCellData(2, 0);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("return document.readyState").equals("complete");
		
		try{
		WebElement appsExpand = driver.findElement(By.xpath("//div[@class='app-list__apps app-list__apps--reduced']"));
		Actions act = new Actions(driver);
		act.moveToElement(appsExpand).build().perform();
		act.release();
		} catch(Exception e){
			
		}
		WebElement app= driver.findElement(By.cssSelector("div[data-name*='"+appName+"']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", app);

		
		Thread.sleep(5000);
		app.click();
		Thread.sleep(10000);
		WebElement appTitle = driver.findElement(By.xpath("//div[@class='appSidebar']/div/div"));
		System.out.println(appTitle.getText());
		String appsName= appTitle.getText();

		assertTrue(appsName.contains(AppName.valueOf(""+appName).toString()));
	}
	
	public void refreshApp(Login.AppName appName) throws Exception{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("return document.readyState").equals("complete");
		
		WebElement appsExpand = driver.findElement(By.xpath("//div[@class='app-list__apps app-list__apps--reduced']"));
		Actions act = new Actions(driver);
		act.moveToElement(appsExpand).build().perform();
		act.release();
		WebElement app= driver.findElement(By.cssSelector("div[data-name*='"+appName+"']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", app);

		
		Thread.sleep(5000);
		app.click();
		Thread.sleep(5000);
		app.click();
		WebElement appTitle = driver.findElement(By.xpath("//div[@class='appSidebar']/div/div"));
		System.out.println(appTitle.getText());
		String appsName= appTitle.getText();

		assertTrue(appsName.contains(AppName.valueOf(""+appName).toString()));
	}
	
public void switchToAppSidebarFrame(){
		try{
		//WebElement appSidebarFrame= driver.findElement(By.cssSelector("iframe[data-dojo-attach-point='appSidebarFrame']"));
		WebElement appSidebarFrame= driver.findElement(By.xpath("//div[@class='appSidebarFrameContainer iframe-container-loaded']/iframe"));
		driver.switchTo().frame(appSidebarFrame);
		((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
//		System.out.println("Switched to frame");
		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	
	public void appStore() throws InterruptedException{
		driver.switchTo().defaultContent();
		WebElement appStore= driver.findElement(By.cssSelector(".app-list__store>div"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", appStore);
		appStore.click();
		Thread.sleep(3000);
	}

}
