package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
plugin = { "pretty", "html:target/cucumber-reports/cucumber.html","json:target/cucumber-reports/cucumber.json" }, 
features = "src/test/java/features", 
glue = { "stepdefinations" })
public class TestRunner {

}