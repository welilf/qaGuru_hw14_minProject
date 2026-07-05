package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MtsPage {

    // Elements
    private final SelenideElement searchButton = $(".header-search__icon");
    private final SelenideElement privateClientsLink = $("a[eventid='vntMtsCrossTopLinks'][href='/personal']");
    private final SelenideElement loginButton = $("#profile-widget-app a[href*='login']");
    private final SelenideElement gosLink = $("a[eventid='vntMtsCrossTopLinks'][href='/gos']");
    private final SelenideElement logo = $(".middle-menu__logo");
    private final SelenideElement locationTooltip = $(".tooltip-location__wrapper");
    private final SelenideElement confirmLocationButton = $(".tooltip-location__wrapper").$(byText("Да, верно"));
    private final SelenideElement searchModal = $(".search-modal-widgets");
    private final SelenideElement loginModal = $(".mts-universal-modal__content");
    private final SelenideElement searchInput = $("input[name='q']");

    // Actions
    @Step("Открыть главную страницу МТС")
    public MtsPage openPage() {
        open("https://www.mts.ru");
        return this;
    }

    @Step("Проверить видимость логотипа")
    public MtsPage checkLogo() {
        logo.shouldBe(visible);
        return this;
    }

    @Step("Проверить наличие ссылки 'Госзаказчикам'")
    public MtsPage checkGosLink(String title) {
        gosLink.shouldHave(text(title));
        return this;
    }

    @Step("Кликнуть на ссылку 'Госзаказчикам'")
    public MtsPage clickGosLink() {
        gosLink.click();
        return this;
    }

    @Step("Проверить наличие ссылки на Частных клиентов")
    public MtsPage checkPrivateClientsLink(String title) {
        privateClientsLink.shouldHave(text(title));
        return this;
    }

    @Step("Нажать на кнопку поиска")
    public MtsPage clickSearch() {
        locationTooltip.shouldBe(visible);
        confirmLocationButton.click();
        locationTooltip.shouldNotBe(visible);
        searchButton.click();
        return this;
    }

    @Step("Проверить появление модального окна поиска")
    public MtsPage verifySearchModalIsVisible() {
        searchModal.shouldBe(Condition.visible);
        return this;
    }

    @Step("Нажать на кнопку Войти")
    public MtsPage clickLogin() {
        if (locationTooltip.isDisplayed()) {
            confirmLocationButton.click();
        }
        loginButton.click();
        return this;
    }

    @Step("Проверить появление модального окна логина")
    public MtsPage verifyLoginModalIsVisible() {
        loginModal.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить, что текущий URL содержит {expectedUrl}")
    public MtsPage verifyCurrentUrl(String expectedUrl) {
        com.codeborne.selenide.WebDriverRunner.url().contains(expectedUrl);
        return this;
    }

    @Step("Ввести в поле поиска текст: {text} и нажать Enter")
    public MtsPage enterSearchText(String text) {
        searchInput.setValue(text).pressEnter();
        return this;
    }
}
