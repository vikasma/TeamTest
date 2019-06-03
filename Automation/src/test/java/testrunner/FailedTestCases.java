package testrunner;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;

public class FailedTestCases {
	
	@Test
	public void runFailedCases(){
		TestNG runner = new TestNG();
		List<String> list = new ArrayList<String>();
		list.add("/auto_workspace/Automation//test-output//testng-failed.xml");
		runner.setTestSuites(list);
		runner.run();
		
	}
}