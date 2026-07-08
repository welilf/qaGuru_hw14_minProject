package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsTendersPage {

    // Elements
    private final SelenideElement tendersSearchInput = $("[data-test-id='search-input'] input");
    private final ElementsCollection searchResults = $$("[data-test-id='search-result-Закупки']");

    // Actions
    @Step("Проверить, что открылся URL раздела 'Закупки'")
    public MtsTendersPage verifyTendersUrl() {
        assertTrue(url().equals(data.TestData.TENDERS_URL),
                "Ожидался URL закупок, но открылся: " + url());
        return this;
    }

    @Step("Ввести в поиск закупок '{query}' и нажать Enter")
    public MtsTendersPage searchInTenders(String query) {
        tendersSearchInput.shouldBe(visible)
                .setValue(query)
                .pressEnter();
        return this;
    }

    @Step("Проверить, что в результатах поиска закупок есть статья '{articleTitle}'")
    public MtsTendersPage verifyArticleInTendersResults(String articleTitle) {
        searchResults.findBy(text(articleTitle)).shouldBe(visible, java.time.Duration.ofSeconds(8));
        return this;
    }
}
