package hooks;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.google.common.io.Files;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CucumberHooks {

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            screenshot();
            String sessionId = getSessionId();
            System.out.println("video.enabled: " + System.getProperty("video.enabled"));
            Selenide.closeWebDriver();
            if ("true".equals(System.getProperty("video.enabled"))) {
                Selenide.sleep(5000);
                attachAllureVideo(sessionId);
            }
        }

    }

    @Attachment(type = "image/png")
    private byte[] screenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
    }


    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }


    public static void attachAllureVideo(String sessionId) {
        try {
            String selenoidUrl = "http://localhost:4444";
            URL videoUrl = new URL(selenoidUrl + "/video/" + sessionId + ".mp4");

            InputStream is = getSelenoidVideo(videoUrl);
            Allure.addAttachment("Video", "video/mp4", is, "mp4");
            deleteSelenoidVideo(videoUrl);
        } catch (Exception e) {
            System.out.println("attachAllureVideo");
            e.printStackTrace();
        }
    }

    public static InputStream getSelenoidVideo(URL url) {
        int lastSize = 0;
        for (int i = 0; i < 20; i++) {
            try {
                int size = url.openConnection().getContentLength();
                System.out.println("Content-Length: " + size);
                System.out.println(i);
                if (size > lastSize || size == 19) {
                    lastSize = size;
                    //Thread.sleep(1000);
                    continue;
                }
                return url.openStream();
            } catch (Exception e) {
                System.out.println("checkSelenoidVideo");
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void deleteSelenoidVideo(URL url) {
        try {
            HttpURLConnection deleteConn = (HttpURLConnection) url.openConnection();
            deleteConn.setDoOutput(true);
            deleteConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            deleteConn.setRequestMethod("DELETE");
            deleteConn.connect();
            deleteConn.disconnect();
        } catch (IOException e) {
            System.out.println("deleteSelenoidVideo");
            e.printStackTrace();
        }
    }
}
