package tests;

import data.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MtsMainPage;
import pages.MtsSearchPage;

public class MtsTests extends TestBase {

    MtsMainPage mtsPage = new MtsMainPage();
    MtsSearchPage searchPage = new MtsSearchPage();

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
                .checkGosLink(TestData.GOS_TITLE);
    }

    @Test
    @DisplayName("Проверка перехода в раздел 'Госзаказчикам' через верхнее меню")
    void verifyGosLinkTransition() {
        mtsPage.openPage()
                .checkGosLink(TestData.GOS_TITLE)
                .clickGosLink()
                .verifyCurrentUrl("/gos");
    }

    @Test
    @DisplayName("Проверка наличия ссылки на Частных клиентов на главной странице")
    void checkPrivateClientsLinkOnMainPage() {
        mtsPage.openPage()
                .checkPrivateClientsLink(TestData.PRIVATE_CLIENTS_TITLE);
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
