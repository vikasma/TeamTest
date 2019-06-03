package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Homepage {
	
	 WebDriver driver;
	public Homepage (WebDriver driver){
		this.driver = driver;
	}
	
	
	
	public void setLoginName(String UserName){
				
		WebElement loginFrame = driver.findElement(By.xpath("//div[@id='dijit__WidgetsInTemplateMixin_37']/iframe"));	
				driver.switchTo().frame(loginFrame);
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(UserName);
		}

	public void setLanguage (String Language) throws InterruptedException{
		if(Language != "English"){
			WebElement loginFrame = driver.findElement(By.xpath("//div[@id='dijit__WidgetsInTemplateMixin_37']/iframe"));
			driver.switchTo().frame(loginFrame);
			WebElement table = driver.findElement(By.xpath("//div[@class='language-selector']/table"));

			WebElement customer = table.findElement(By.xpath("//*[contains(text(), 'English-US')]"));

			// click on the row
			customer.click();
			WebElement lang = driver.findElement(By.xpath("//*[contains(text(),'"+Language+"')]"));

			Actions actions = new Actions(driver);
			actions.moveToElement(lang).click().perform();
			Thread.sleep(3000);
			
			//actions.sendKeys(Keys.ARROW_DOWN);
			//actions.sendKeys(Keys.RETURN).build().perform();
			
				/*driver.switchTo().frame(0);
				Homepage hp = new Homepage(driver);
				hp.setLoginName(String UserName);*/
				
		} else {
			System.out.println("Default language English");
		}
	}
	
	public void clickSubmit(){
		driver.findElement(By.xpath("//button[@class='btn btn--action btn--block']")).click();
	}
	
	public void enterPasswordAndClickLogin(String password) throws InterruptedException{
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class='btn btn--action btn--block']")).click();
		try{
		WebElement notification = driver.findElement(By.xpath("//*[@class='notification-approval']"));
		if (notification.isDisplayed())
		{
			driver.findElement(By.xpath("//a[contains(text(),'Remind me later')]")).click();
			//driver.findElement(By.xpath("//a[contains(text(),'Enable browser notifications')]")).click();
		}}catch(NoSuchElementException e){
			//Log.info("Notification dialog after signing in is not shown");
		}
		
		WebElement loginFrame = driver.findElement(By.xpath("//iframe[@class='appframe']"));
		driver.switchTo().frame(loginFrame);
		driver.findElement(By.xpath("//textarea[@id='whatsNewHookEditorArea']"));
		driver.switchTo().defaultContent();
		
		Thread.sleep(3000);
		
	}
		public void flockTeam(String userTeamName) throws InterruptedException{
			String teamName;
			 teamName = driver.findElement(By.xpath("//div[@class='app_menu_team']/div")).getText();
			System.out.println("Default Team name: "+teamName);
			
			List<WebElement> teamList= driver.findElements(By.xpath("//*[@class='team-list']/div"));
		int teamCount= teamList.size();
		System.out.println(teamList.size());
				
			for(int i=2;i<=teamCount;i++){
				try {
					Assert.assertEquals(teamName, userTeamName);
					System.out.println("In correct team: "+teamName);
					Thread.sleep(3000);
					break;
				} catch (AssertionError e) {
					WebElement team= driver.findElement(By.xpath("//div//*[@class='team-switcher']/div/div["+i+"]"));
					Actions builder = new Actions(driver);
					builder.moveToElement(team).build().perform();
					team.click();
					teamName =driver.findElement(By.xpath("//div[@class='app_menu_team']/div")).getText();
					System.out.println("Team name: "+teamName);
					continue;
				}
			}
		}
		
		public void logOut() throws Exception{
			System.out.println("To logout");
			driver.switchTo().defaultContent();
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("return document.readyState").equals("complete");
//			
			driver.findElement(By.xpath("//div[@class='app_menu dijitDownArrowButton']/div[@class='app_menu_body']")).click();
			driver.findElement(By.xpath("//div[@class='app_menu_item_label'][contains(text(),'Sign out')]")).click();
			try{
				WebElement sigoutModal = driver.findElement(By.cssSelector("div[role='dialog'][id='widgets_Dialog_1']"));
				if(sigoutModal.isDisplayed()){
					driver.findElement(By.xpath("//div[@class='action__buttons']/button[contains(text(),'Sign')]")).click();
				}
				
			} catch (Exception e){
				System.out.println(e);
			}
			
			Thread.sleep(5000);
		}

		}
		
	

