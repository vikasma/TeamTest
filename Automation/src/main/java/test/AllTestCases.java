package test;
import test.Login;
import test.ReminderTestCases;
import test.NotesTestCases;
import test.TodosTestCases;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.TestListenerAdapter;
//import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.ExcelUtils;
import utility.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.log4j.xml.DOMConfigurator;

public class AllTestCases extends BaseSetup {
	
	@BeforeClass
	public void loadLog4j(){
		
		DOMConfigurator.configure("log4j.xml");
		
		
	}

	
	@Test(description="Login into Flock")
	  public void testCase_Login() throws Exception {
		  Login login = new Login(driver);
		  login.LoginTest();
		  Login.takeScreenshot();
		  ExcelUtils.setCellData("Pass", 12, 1);
		  Log.info("Completed executing Login testCases");
		  
	  }
	
	 @Test
	  public void testCases_Notes() throws Exception {
		  NotesTestCases notes = new NotesTestCases(driver);
		  notes.notesTestCases();
		  Login.takeScreenshot();
		  System.out.println("Done with Notes Test Cases");
		  
		  driver.switchTo().defaultContent();
		  Log.info("Completed executing Notes test case");

	  }
  @Test
  public void testCases_Reminder() throws Exception {
	  ReminderTestCases reminder = new ReminderTestCases(driver);
	  reminder.reminderTestCases();
	  Login.takeScreenshot();
	  System.out.println("Done with Reminder Test Cases");
	  
	  driver.switchTo().defaultContent();
	  Log.info("Completed executing Reminder test case");
  }
  
 
  
  @Test
  public void testCases_Todos() throws Exception {
	  TodosTestCases todos = new TodosTestCases(driver);
	  todos.creatNewList();
	  Login.takeScreenshot();
	  System.out.println("Done with Notes Test Cases");
	  driver.switchTo().defaultContent();
	  Log.info("Completed executing Todos test case");
	  
  }

  @AfterMethod
	public void afterMethod(ITestResult result) throws Exception{
	  String methodName = result.getMethod().getMethodName();
	//  Log.info("method name:" + methodName);
	  
	    if(result.getStatus() == ITestResult.FAILURE)
	    {
	    	 Log.error("Failed method name:" + methodName);
	    	throw new SkipException("Previous case Failed");
	    }
	 }
  
  public static void main(String[] args) {
//	  TestListenerAdapter tla = new TestListenerAdapter();
	  TestNG testng = new TestNG();
//	  tla.getPassedTests().toString();
//	  testng.setTestClasses(new Class[] { test.AllTestCases.class });
//	  testng.addListener(tla);	

	// Create a list of String 
	List<String> suitefiles=new ArrayList<String>();

	// Add xml file which you have to execute
	suitefiles.add("testng.xml");

	// now set xml file for execution
	testng.setTestSuites(suitefiles);

	// finally execute the runner using run method
	testng.run();
	  }
}
