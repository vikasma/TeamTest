package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.Apps;

public class Todos {
	
	WebDriver driver;
	public Todos(WebDriver driver){
		this.driver=driver;
	}

	public void allTodos(){
		try{
		driver.findElement(By.cssSelector("//li[@class='dib active']/span[contains(text(),'All')]")).click();
		}catch (Exception e){
			driver.findElement(By.cssSelector("//li[@class='dib ']/span[contains(text(),'All')]")).click();
		}
	}
	
	public void myTodos(){
		try{
		driver.findElement(By.cssSelector("//li[@class='dib ']/span[contains(text(),'My')]")).click();
		} catch(Exception e){
			
			driver.findElement(By.cssSelector("//li[@class='dib active']/span[contains(text(),'My')]")).click();
		}
		}
	public void createNewList(){
		Apps app = new Apps(driver);
		app.switchToAppSidebarFrame();
		driver.findElement(By.cssSelector("svg[aria-label='create a to-do list']")).click();
	}
/** List name by default will be "New List 1" **/
	public void changeListName(){
		driver.findElement(By.xpath("//div[contains(text(),'New List 1')]")).clear();
		driver.findElement(By.xpath("//div[contains(text(),'New List 1')]")).sendKeys("test list added");
		driver.findElement(By.xpath("//div[@class='controls cf list-container relative']//button[contains(text(),'Save')]")).click();
		//driver.findElement(By.xpath("//div[@class='controls cf list-container relative']//button[contains(text(),'Cancel')]").click();
		
	}
	public void addTodoText(){
		//driver.findElement(By.xpath("(//div[@class='todo-wrapper']//pre[contains(text(),"t")])[1]")).
	}
}
