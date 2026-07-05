package UI.tests;

import com.codeborne.selenide.Configuration;
import UI.data.TestData;
import UI.helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import UI.pages.MtsMainPage;
import UI.pages.MtsSearchPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class MtsTests {

    MtsMainPage mtsPage = new MtsMainPage();
    MtsSearchPage searchPage = new MtsSearchPage();
    TestData data = new TestData();

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
    @DisplayName("Проверка видимости логотипа на главной странице")
    void checkLogoOnMainPage() {
        mtsPage.openPage()
                .checkLogo();
    }

    @Test
    @DisplayName("Проверка того, что пункт 'Госзаказчикам' виден на главной странице")
    void verifyGosLinkIsVisibleOnMainPage() {
        mtsPage.openPage()
                .checkGosLink(data.gosTitle);
    }

    @Test
    @DisplayName("Проверка перехода в раздел 'Госзаказчикам' через верхнее меню")
    void verifyGosLinkTransition() {
        mtsPage.openPage()
                .checkGosLink(data.gosTitle)
                .clickGosLink()
                .verifyCurrentUrl("/gos");
    }

    @Test
    @DisplayName("Проверка наличия ссылки на Частных клиентов на главной странице")
    void checkPrivateClientsLinkOnMainPage() {
        mtsPage.openPage()
                .checkPrivateClientsLink(data.privateClientsTitle);
    }

    @Test
    @DisplayName("Проверка появления модального окна логина после нажатия на кнопку 'Войти'")
    void checkLoginModalWindow() {
        mtsPage.openPage()
                .clickLogin()
                .verifyLoginModalIsVisible();
    }

    @Test
    @DisplayName("Проверка открытия страницы поиска и доступности поля ввода")
    void checkSearchPageOpening() {
        searchPage.openSearchPage()
                .verifySearchInputIsVisible();
    }

    @Test
    @DisplayName("Проверка того, что при пустом вводе кнопка 'Найти' не активна")
    void checkSearchButtonIsDisabledWhenInputIsEmpty() {
        searchPage.openSearchPage()
                .verifySearchButtonIsDisabled();
    }
}
