package hooks;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.google.common.io.Files;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import io.qameta.allure.Attachment;

import java.io.File;
import java.io.IOException;

public class CucumberHooks {
    @After
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            screenshot();
        }

        Selenide.clearBrowserCookies();
        Selenide.close();
    }


    @Attachment(type = "image/png")
    private byte[] screenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
    }
}
