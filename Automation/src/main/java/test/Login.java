package test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import pages.Homepage;
import utility.ExcelUtils;



public class Login  {
	
	private static WebDriver driver;
	
	public Login(WebDriver driver){
		this.driver = driver;
	}

	public enum AppName {Notes, Reminders, Calendar, Drive, Dos }
	static String CreateFolder="true";//Create folder to store the screenshots.
	public static Date date=new Date();
	public static DateFormat dateformat = new SimpleDateFormat("hh.mm.ss"+"_dd.MMM.yyyy");
	
	public static final String dateAndTime= dateformat.format(date);

	
	public void LoginTest() throws Exception{
//		ExcelUtils.setExcelFile(Constant.testDataFile, "Sheet1");
		
		String userName = ExcelUtils.getCellData(1, 1);
		String password = ExcelUtils.getCellData(2, 1);
		String teamName = ExcelUtils.getCellData(3, 1);
		
		Homepage login = new Homepage(driver);
//		login.setLanguage("Russian");
		login.setLoginName(userName);
		login.clickSubmit();
		login.enterPasswordAndClickLogin(password);
		login.flockTeam(teamName);
		

	}


		public static String createFolder(){

			String folderName="Screenshots_"+Login.dateAndTime;
			
				 if(CreateFolder=="true")
				 {
				 File folder = new File("Screenshots_"+Login.dateAndTime);
				 folder.mkdir();
				 }
				 CreateFolder="false";
				 return folderName;
			 }	
		 
		
		
		public static void takeScreenshot() throws IOException{
		
			File srcFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File desFile= new File(Login.createFolder());
			FileUtils.copyFileToDirectory(srcFile, desFile);
			
			}
		}
		

