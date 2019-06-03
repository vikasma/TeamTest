package stepDefinitions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.DOMConfiguration;

import testrunner.*;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import utility.Log;
import utility.ExcelUtils;
public class Steps2  extends TestRunner {

	//plugin http://cucumber.github.com/cucumber-eclipse/update-site
	
	
	@Given("^Enter username \"([^\"]*)\"$")
	public void enter_username(String username) throws Throwable {
		ExcelUtils.setExcelFile("BaseData");
	username=ExcelUtils.getCellData(0, 1);
	    System.out.println(username);
	  
	}

	@Given("^Enter password \"([^\"]*)\"$")
	public void enter_password(String password) throws Throwable {
	   System.out.println("Check for the password");
	}

	@When("^Click on Login button$")
	@Test(retryAnalyzer=testrunner.MyRetry.class)
	public void click_on_Login_button() throws Throwable {
		
		Assert.assertTrue(true);
		
	    
	}

	@Then("^Flock home page opens$")
	@Test(description="test case for flock_home page")
	public void flock_home_page_opens() throws Throwable {
	    
	}
	
	@Given("^methods are all correct$")
	public void methods_are_all_correct() throws Throwable {
	    System.out.println("--------Background method to run----");
	}
	
	@Given("^All apps are listed in the sidebar$")
	public void all_apps_are_listed_in_the_sidebar() throws Throwable {
	    System.out.println("Working on Apps");
	}
	
	@Before("@Login")
	public void runAfterLogin(){
		
		System.out.println("This is run before Login scenario");
	}
	@After("@Apptest")
	public void afterAppTest() throws IOException{
		System.out.println("this will be triggered after App test scenario");
		int a = 10;
		Integer ab = new Integer(a);
		System.out.println(ab);
		int h = ab;
		System.out.println(h);
		File file = new File("D://test.properties");
		//file.createNewFile();
		FileOutputStream out = new FileOutputStream("D://test.properties");
		Properties config = new Properties();
		//config.load(this.getClass().getResourceAsStream("D://test.properties"));;
		config.setProperty("URL", "https://www.gmail.com");
		config.store(out, "test comments");
		System.out.println(config.getProperty("URL"));
		Log.info("Hi Hello");
		
	}
	//Before with order 0 runs first while After with order 1 runs first
	@Before(order=1)
    public void beforeScenario(){
        System.out.println("This will run before the every Scenario");
        DOMConfigurator.configure("log4j.xml");
    } 
	@Before(order=0)
    public void beforeScenarioStart(){
        System.out.println("-----------------Start of Scenario-----------------");
    } 
	 @After(order=0)
	    public void afterScenarioFinish(){
	        System.out.println("-----------------End of Scenario-----------------");
	    } 
	 @After(order=1)
	    public void findGivenNumberInArray(){
	        System.out.println("This will run after the every Scenario");
	        int array[] = {10,7,9,3,5,25,89};
	        Arrays.sort(array);
	        int key = 25;
	        System.out.println("Found item 25 in index"+Arrays.binarySearch(array, key));
	    } 
	 
	 @Test
	 public void sumOfTwoNumbersIsGivenNumber(){
		 
		 int arr[]={1,4,45,6,10,8,8};
		 int sum = 16;
	//	 List<int[]> list = Arrays.asList(arr);
	//	 Set<Integer> s = new HashSet<Integer>(list);
		 HashSet<Integer> s = new HashSet<Integer>();
		 for(int i=0;i<arr.length;++i){
			 int temp = sum-arr[i];
			 
			 if(temp>=0 && s.contains(temp)){
				 System.out.println("Pair with given sum "+sum +
						 " is ("+arr[i]+","+temp+")");
			 }
			 s.add(arr[i]);
		 }	 
	 }
	 
	 @Test
	 public void givenNumberPresent(){
		 Integer[] a = {1,2,34,89,90};
		 List<Integer> list = Arrays.asList(a);
		Set<Integer> s = new HashSet<Integer>(Arrays.asList(a));
		 Arrays.sort(a);
		 System.out.println("Length of array: "+a.length);
		 System.out.println("Size of array: "+s.size());
		System.out.println("Present:" + s.contains(2)+" with Binary search "+ Arrays.binarySearch(a, 20));
	 }
}
  