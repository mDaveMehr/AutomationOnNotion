

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/features/LockDatabase.feature",
        glue = "stepDefinition/LockDatabaseSteps",
        plugin= {
                "html:target/cucumber-html-report.html", "json:target/cucumber.json",
                "pretty:target/cucumber-pretty.txt","usage:target/cucumber-usage.json"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
