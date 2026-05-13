package tests;

import com.codeborne.selenide.Configuration;
import data.TestData;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.SuccessfulFillFormPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class FirstFormTest {

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
    void successfulFillFormTest_dsl() {

        SuccessfulFillFormPage successfulFillFormPage = new SuccessfulFillFormPage();
        TestData data = new TestData();

        successfulFillFormPage
                .openPage()
                .typeFirstName(data.firstName)
                .typeLastName(data.lastName)
                .typeUserEmail(data.userEmail)
                .setGender(data.gender)
                .typeUserNumber(data.userNumber)
                .setDateOfBirth(data.dayOfBirth, data.monthOfBirth, data.yearOfBirth)
                .setSubject(data.subject)
                .setHobby(data.hobbie)
                .uploadPicture(data.picture)
                .setAddress(data.currentAddress)
                .setState(data.state)
                .setCity(data.selectCity)
                .submitForm();

        successfulFillFormPage
                .checkField("Student Name", data.firstName + " " + data.lastName)
                .checkField("Student Email", data.userEmail)
                .checkField("Gender", data.gender)
                .checkField("Mobile", data.userNumber)
                .checkField("Date of Birth", data.dayOfBirth + " " + data.monthOfBirth + "," + data.yearOfBirth)
                .checkField("Subjects", data.subject)
                .checkField("Hobbies", data.hobbie)
                .checkField("Picture", data.picture)
                .checkField("Address", data.currentAddress)
                .checkField("State and City", data.state + " " + data.selectCity);
    }
}
