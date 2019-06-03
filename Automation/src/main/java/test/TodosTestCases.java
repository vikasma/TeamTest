package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pages.Apps;

import test.Login.AppName;

public class TodosTestCases {

	private WebDriver driver;
	public TodosTestCases(WebDriver driver){
		this.driver = driver;
	}
	
	@Test
	public void todosTestCases() throws Exception{
		creatNewList();
		
	}
	
	public void creatNewList() throws Exception{
		Apps apps=new Apps(driver);
		
		apps.selectApp(AppName.Dos);
		pages.Todos todos = new pages.Todos(driver);
		todos.createNewList();
	}
}
