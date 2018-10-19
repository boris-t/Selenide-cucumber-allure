package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "json:target/cucumber.json", "html:target/cucumber.html"},
        features = {
                "src/test/cucumber/",
        },
        glue = {"steps"})
public class GoogleTestRunner {
}
