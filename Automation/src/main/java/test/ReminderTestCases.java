package test;

import org.openqa.selenium.WebDriver;


import pages.Apps;
import pages.Reminders;
import test.Login.AppName;
import utility.ExcelUtils;

public class ReminderTestCases {
	private WebDriver driver;
	
	public ReminderTestCases(WebDriver driver){
		this.driver = driver;
	}

	
	public void reminderTestCases() throws Exception{
		//reminderCreation();
		reminderCreationwithCustomTime();
	}
	
	public void reminderCreation() throws Exception{
		Apps apps=new Apps(driver);
		apps.listAllApps();
		apps.selectApp(AppName.Reminders);
		
		String rosterList=ExcelUtils.getCellData(9, 1);
		String remindAbout = ExcelUtils.getCellData(8, 1);
		String reminderTime = ExcelUtils.getCellData(10, 1);
		
		Reminders remind = new Reminders(driver);
		remind.setReminder();
		remind.selectFromRosterList(rosterList);
		remind.addReminderContent(remindAbout);
		remind.setReminderTime(Integer.parseInt(reminderTime));
		//remind.customTimeReminder();
		remind.clicksetReminderBtn();
	}
	
	public void reminderCreationwithCustomTime() throws Exception{
		Apps apps=new Apps(driver);
		apps.listAllApps();
		apps.selectApp(AppName.Reminders);
		
		String remindAbout = ExcelUtils.getCellData(8, 1);
		String reminderTime = ExcelUtils.getCellData(10, 1);
		Reminders remind = new Reminders(driver);
		remind.setReminder();
		remind.selectFromRosterList(reminderTime);
		remind.addReminderContent(remindAbout);
		
		remind.customTimeReminder();
		remind.clicksetReminderBtn();
	}
}
