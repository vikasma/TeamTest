package testrunner;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetry implements IRetryAnalyzer {
	

		private int retryCount = 0;
		private static final int maxRetryCount = 3;
		
		@Override
		public boolean retry(ITestResult result) {
			if(retryCount < maxRetryCount){
				System.out.println("Hey Hello");
				retryCount ++;
				return true;
			}
			return false;
		}
		
	}


