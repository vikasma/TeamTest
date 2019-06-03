package testrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "@target/rerun.txt", plugin = {"rerun:target/rerun.txt"})


public class FailedRunner extends AbstractTestNGCucumberTests {

}
