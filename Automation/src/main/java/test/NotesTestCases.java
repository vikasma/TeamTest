
  package test;
 

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pages.Apps;
import test.Login.AppName;
import utility.ExcelUtils;



public class NotesTestCases {
	
	private WebDriver driver;
	public NotesTestCases(WebDriver driver){
		this.driver = driver;
	}

	
	@Test
	public void notesTestCases() throws Exception{
		notesCreation();
	}
	
	public void notesCreation() throws Exception{
		Apps apps=new Apps(driver);
		apps.listAllApps();
		apps.selectApp(AppName.Notes);
		
		String notesTitle= ExcelUtils.getCellData(5, 1);
		String notesMessage= ExcelUtils.getCellData(6, 1);
		pages.Notes notes = new pages.Notes(driver);
		notes.clickCreateNotes();
		notes.addContentToNotes(notesTitle,notesMessage);
	}
}
