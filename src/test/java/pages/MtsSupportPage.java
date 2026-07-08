package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MtsSupportPage {

    // Elements
    private final SelenideElement supportSearchInput = $("[data-test-id='search-input'] input");
    private final SelenideElement partnersTab = $("[data-test-id='partners'] a");
    private final ElementsCollection searchResults = $$("[data-test-id^='search-result-']");

    // Actions

    @Step("Проверить, что открылся URL поддержки")
    public MtsSupportPage verifySupportUrl() {
        org.junit.jupiter.api.Assertions.assertTrue(
                com.codeborne.selenide.WebDriverRunner.url().equals(data.TestData.SUPPORT_BASE_URL),
                "Ожидался URL страницы поддержки, но открылся: " + com.codeborne.selenide.WebDriverRunner.url()
        );
        return this;
    }

    @Step("Кликнуть по табу 'Партнёрам' в верхнем меню")
    public MtsPartnersPage clickPartnersTab() {
        partnersTab.shouldBe(visible).click();
        return new MtsPartnersPage(); // Передаем управление новой странице партнеров
    }

    @Step("Ввести в поиск поддержки '{query}' и нажать Enter")
    public MtsSupportPage searchInSupport(String query) {
        supportSearchInput.shouldBe(visible)
                .setValue(query)
                .pressEnter();
        return this;
    }

    @Step("Проверить, что в результатах поиска есть статья '{articleTitle}'")
    public MtsSupportPage verifyArticleInResults(String articleTitle) {
        searchResults.findBy(text(articleTitle)).shouldBe(visible, Duration.ofSeconds(8));
        return this;
    }
}
