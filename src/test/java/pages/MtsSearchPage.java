package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MtsSearchPage {

    //Elements
    private final SelenideElement searchInput = $("input[type='search']");
    private final SelenideElement searchSubmitButton = $x("//button[contains(@class, 'mm-web-action-button') and contains(., 'Найти')]");

    //Actions
    @Step("Открыть страницу поиска")
    public MtsSearchPage openSearchPage() {
        open("https://www.mts.ru/personal/search");
        return this;
    }

    @Step("Проверить доступность поля ввода на странице поиска")
    public MtsSearchPage verifySearchInputIsVisible() {
        searchInput.shouldBe(visible, java.time.Duration.ofSeconds(10));
        return this;
    }

    @Step("Проверить, что кнопка 'Найти' не активна")
    public MtsSearchPage verifySearchButtonIsDisabled() {
        searchSubmitButton.shouldBe(com.codeborne.selenide.Condition.disabled);
        return this;
    }

}
