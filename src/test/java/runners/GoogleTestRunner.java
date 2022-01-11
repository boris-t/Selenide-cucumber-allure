package runners;

import com.codeborne.selenide.Configuration;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {
                "src/test/cucumber/",
        },
        plugin = {"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"},
        glue = {"steps", "hooks"})

public class GoogleTestRunner {
    @BeforeClass
    public static void before() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browserSize = "1600x1024";
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        if ("true".equals(System.getProperty("video.enabled"))) {
            Configuration.browserCapabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities.setCapability("videoFrameRate", 24);
        }
    }

}
