package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MtsMainPage {

    // Elements
    private final SelenideElement privateClientsLink = $("a[eventid='vntMtsCrossTopLinks'][href='/personal']");
    private final SelenideElement loginButton = $("#profile-widget-app a[href*='login']");
    private final SelenideElement gosLink = $("a[eventid='vntMtsCrossTopLinks'][href='/gos']");
    private final SelenideElement logo = $(".middle-menu__logo");
    private final SelenideElement locationTooltip = $(".tooltip-location__wrapper");
    private final SelenideElement confirmLocationButton = $(".tooltip-location__wrapper").$(byText("Да, верно"));
    private final SelenideElement loginModal = $(".mts-universal-modal__content");
    private final SelenideElement supportLink = $(".header__top-text").$(com.codeborne.selenide.Selectors.byText("Поддержка"));

    //Actions
    @Step("Открыть главную страницу МТС")
    public MtsMainPage openPage() {
        open("https://www.mts.ru");
        return this;
    }

    @Step("Проверить видимость логотипа")
    public MtsMainPage checkLogo() {
        logo.shouldBe(visible);
        return this;
    }

    @Step("Проверить наличие ссылки 'Госзаказчикам'")
    public MtsMainPage checkGosLink(String title) {
        gosLink.shouldHave(text(title));
        return this;
    }

    @Step("Кликнуть на ссылку 'Госзаказчикам'")
    public MtsGosPage clickGosLink() {
        gosLink.click();
        return new MtsGosPage();
    }

    @Step("Проверить наличие ссылки на Частных клиентов")
    public MtsMainPage checkPrivateClientsLink(String title) {
        privateClientsLink.shouldHave(text(title));
        return this;
    }
    @Step("Нажать на кнопку Войти")
    public MtsMainPage clickLogin() {
        if (locationTooltip.isDisplayed()) {
            confirmLocationButton.click();
        }
        loginButton.click();
        return this;
    }

    @Step("Проверить появление модального окна логина")
    public MtsMainPage verifyLoginModalIsVisible() {
        loginModal.shouldBe(Condition.visible);
        return this;
    }

    @Step("Кликнуть по ссылке 'Поддержка' в верхнем меню")
    public MtsSupportPage clickSupportLink() {
        supportLink.shouldBe(visible).click();
        try {
            switchTo().window(1);
        } catch (IndexOutOfBoundsException e) {
        }
        return new MtsSupportPage();
    }
}
