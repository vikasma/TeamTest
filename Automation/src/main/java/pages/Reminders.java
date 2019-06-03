package pages;
  
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pages.Apps;
public class Reminders {
	
	WebDriver driver;
	public Reminders (WebDriver driver){
		this.driver=driver;
	}
	
	public void setReminder(){
		Apps apps = new Apps(driver);
		apps.switchToAppSidebarFrame();
		try{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("return document.readyState").equals("complete");
		WebElement reminder = driver.findElement(By.xpath("//button[contains(text(),'Reminder')]"));
		reminder.click();
		driver.findElement(By.cssSelector("input[class='input search-roster']")).click();
		}catch(Exception e){
			driver.findElement(By.xpath("//span[contains(text(),'reminder')]")).click();
			driver.findElement(By.cssSelector("input[class='input search-roster']")).click();
		}
		}
	
	
	public void selectFromRosterList(String userOrChannelName) throws InterruptedException{
		WebElement rosterList = driver.findElement(By.xpath("//div[@class='search_result']/ul"));
		List<WebElement> rosterName= rosterList.findElements(By.tagName("li"));
		System.out.println(rosterName.size());
		
		if (userOrChannelName==null||userOrChannelName.isEmpty()){
			rosterName.get(1).click();
		for (WebElement link : rosterName) {
			System.out.println(link.getText());
			if (link.getText().contains("Hub")) {
			     link.click();
			   }
		}
			
		} else{
              for (WebElement link : rosterName) {
			 			System.out.println(link.getText());
			if (link.getText().contains(userOrChannelName)) {
			     link.click();
			   }
		}
              
	}
Thread.sleep(5000);
Actions action = new Actions(driver);
//action.moveToElement(rosterList).perform();
action.sendKeys(Keys.ESCAPE).build().perform();

}
	
	public void addReminderContent(String remindAbout){
		WebElement reminder = driver.findElement(By.xpath("//div[@class='reminder-content-wrapper']//textarea"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reminder);
		reminder.click();
		reminder.sendKeys(remindAbout);
	}
	
	/**Method can take input from following<br>
	 * 15 mins <br>
	 * 30 mins <br>
	 * 1 hour<br>
	 * 5 PM Today<br>
	 * 10 AM Tomorrow<br>
	 * @throws InterruptedException 
	 * */
	public void setReminderTime(int time) throws InterruptedException{

 DateFormat dateformat = new SimpleDateFormat("hh");
 Date date= new Date();
 DateFormat dateformat2 = new SimpleDateFormat("hh:mm");
 Date date2= new Date();
 String date1= dateformat.format(date);
 String dateWithMinutes=dateformat2.format(date2);
 int HH1 = Integer.valueOf(date1.toString());
		
		switch(time){
		
		case 15:
		time=1;
		break;
		case 30:
			time=2;
			break;
		case 1: time=3; 
            break;
		case 5: 
			
			List<WebElement> time1=driver.findElements(By.xpath("//div[@class='quick-time-wrapper']/div"));
			
			System.out.println("Current Time in Hours:"+HH1);
			int time2 = time1.size();
			System.out.println("Only" +time2+" options are available");
			if(time2<5){
				System.out.println("Current time is past 5 PM for the day");
				System.out.println("Current time:"+dateWithMinutes);
				
				break;
			}else{
				//time=4;
			}
		
            break;
		case 10: 
			
			List<WebElement> time3=driver.findElements(By.xpath("//div[@class='quick-time-wrapper']/div"));
			System.out.println("Time:"+HH1);
				int time4 = time3.size();
				System.out.println(time4);
				if(time4<5){
					System.out.println("5 PM option is removed");
					System.out.println("Current time:"+date1);
					time=4;
				}else{
					time=5; 
				}
			
            break;
		}
		
		try{
		WebElement timer= driver.findElement(By.xpath("(//div[@class='quick-time-wrapper']//div[@class='time-num'])["+time+"]"));
		timer.click();
		Thread.sleep(10000);
		}catch(NoSuchElementException e){
			
		}
	}
		
	public void clicksetReminderBtn() throws InterruptedException{
		WebElement remind=driver.findElement(By.xpath("//button[@class='footer-btn btn btn-primary']"));
		if(remind.isEnabled()==true){
		String remind1=remind.getText();
		assertTrue(remind1.contains("Remind"));
		remind.click();
		Thread.sleep(10000);
		}
	}
	
	public void customTimeReminder(){
		//WebElement customDateAndTime = driver.findElement(By.cssSelector(".active-text"));
		WebElement customDateAndTime = driver.findElement(By.xpath("//span[@class='active-text']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", customDateAndTime);
		assertTrue(customDateAndTime.getText().contains("custom"));
		customDateAndTime.click();
		
		driver.findElement(By.cssSelector("div[placeholder='Select Date']")).click();
		
		WebElement currentMonth = driver.findElement(By.xpath("//div[@class='datepicker-popup']//div[@class='datepicker-body']/p"));
		String Month= currentMonth.getText();
		
		WebElement currentDate= driver.findElement(By.xpath("//div[@class='datepicker-popup']//div[@class='datepicker-dateRange']/span[@class='day-cell datepicker-dateRange-item-active']"));
		System.out.println("Current Selected Date: "+currentDate.getText()+" "+Month);
		driver.findElement(By.xpath("(//div[@class='datepicker-popup']//div[@class='datepicker-dateRange']/span[@class='day-cell'])[1]")).click();
		driver.findElement(By.xpath("(//div[@class='dd-btn'])[1]")).click();
		WebElement timeSelected = driver.findElement(By.xpath("//div[@class='dropdown dib ip-date time-btn']//div[@class='dropdown-popup']//div[@class='dd-list itemSelected']/span"));
		System.out.println("timeSelected: "+timeSelected.getText());
		driver.findElement(By.xpath("//div[@class='dropdown dib ip-date time-btn']//div[@class='dropdown-popup']//div[@class='dd-list']/span[contains(text(),'10')]")).click();
		
		driver.findElement(By.xpath("(//div[@class='dd-btn'])[2]")).click();
	}
}
