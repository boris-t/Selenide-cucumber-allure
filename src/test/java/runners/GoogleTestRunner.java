package runners;

import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        format = {"pretty", "json:target/cucumber.json", "html:target/cucumber.html"},
        features = {
                "src/test/cucumber/",
        },
        plugin = {"io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm"},
        glue = {"steps"})

public class GoogleTestRunner {
    @BeforeClass
    public static void before() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        ChromeDriverManager.getInstance().setup();

        Configuration.browserSize = "1600x1024";

    }

}
