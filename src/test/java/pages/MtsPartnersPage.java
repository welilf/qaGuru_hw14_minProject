package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsPartnersPage {

    // Elements
    private final SelenideElement tendersLink = $("[data-test-id='main-page-child-segment-mts_tenders']");

    // Actions
    @Step("Проверить, что открылся URL раздела 'Партнёрам'")
    public MtsPartnersPage verifyPartnersUrl() {
        assertTrue(url().contains(data.TestData.PARTNERS_URL),
                "Ожидался URL партнеров, но открылся: " + url());
        return this;
    }

    @Step("Кликнуть по плитке 'Закупки'")
    public MtsTendersPage clickTendersLink() {
        tendersLink.shouldBe(visible).click();
        return new MtsTendersPage();
    }
}
