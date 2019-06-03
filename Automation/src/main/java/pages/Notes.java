package pages;


import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.Apps;
import test.Login;

public class Notes {

	WebDriver driver;
	
	
	public Notes(WebDriver driver){
		this.driver=driver;
	}
	
	
	public void clickCreateNotes(){
		Apps apps = new Apps(driver);
		apps.switchToAppSidebarFrame();
		driver.findElement(By.xpath("//span[@class='title' and contains(text(),'Create')]")).click();
	}
	
	public void addContentToNotes(String notesTitle, String notesMessage) throws Exception{
		driver.switchTo().defaultContent();
		//((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
		WebElement frame= driver.findElement(By.xpath("//div[@class='iframe-container-loaded']/iframe"));
		driver.switchTo().frame(frame);
		String Title = notesTitle+"_"+Login.dateAndTime;
		driver.findElement(By.cssSelector("input[class='title'][id='title']")).sendKeys(Title);
		
		
		WebElement notesBodyFrame= driver.findElement(By.cssSelector("iframe[title='Rich Text Editor, editor']"));
		driver.switchTo().frame(notesBodyFrame);
		driver.findElement(By.cssSelector("body[class='cke_editable cke_editable_themed cke_contents_ltr placeholder']")).sendKeys(notesMessage);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(frame);
		driver.findElement(By.cssSelector("button[class='submit-btn']")).click();
		driver.switchTo().defaultContent();
		Apps apps = new Apps(driver);
		apps.refreshApp(Login.AppName.Notes);
		apps.switchToAppSidebarFrame();
		WebElement notesList= driver.findElement(By.xpath("//*[@class='notes']/div"));
		List<WebElement> notes= notesList.findElements(By.tagName("h3"));
		
		for(WebElement list : notes){
			System.out.println(list.getText());
			list.getText().equals(Title);
			
			assertTrue(list.getText().equals(Title), Title);
			
		}
		
	}
	
}
