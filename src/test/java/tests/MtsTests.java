package tests;

import com.codeborne.selenide.Configuration;
import data.TestData;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MtsPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class MtsTests {

     @BeforeAll
      static void setUpConfig() {

          Configuration.browser = System.getProperty("browser", "chrome");
          Configuration.browserVersion = System.getProperty("browserVersion", "127.0");
          Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
          Configuration.browserSize = System.getProperty("browserScreenSize", "1920x1080");
          Configuration.baseUrl = System.getProperty("baseUrl");

          String selenoidUrl = System.getProperty("selenoidURL");
          String selenoidLogin = System.getProperty("selenoidLogin");
          String selenoidPass = System.getProperty("selenoidPass");

          Configuration.remote = "https://" + selenoidLogin + ":" + selenoidPass + "@" + selenoidUrl;

          DesiredCapabilities capabilities = new DesiredCapabilities();
          capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                  "enableVNC", true,
                  "enableVideo", true
          ));
          Configuration.browserCapabilities = capabilities;
      }

      @AfterEach
      void addAttachments() {
          Attach.screenshotAs("Last screenshot");
          Attach.pageSource();
          Attach.browserConsoleLogs();
          Attach.addVideo();
          closeWebDriver();
      }

    @Test
    void mtsMainPageTests_dsl() {

        MtsPage mtsPage = new MtsPage();
        TestData data = new TestData();

        mtsPage.openPage()
                .checkLogo()
                .verifyMobileMenuIsVisible(data.mobileMenuTitle)
                .checkPrivateClientsLink(data.privateClientsTitle)
                .clickSearch()
                .back()
                .clickLogin();
    }
}
