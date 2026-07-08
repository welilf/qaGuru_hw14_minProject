package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsGosPage {

    @Step("Проверить, что текущий URL содержит {expectedUrl}")
    public MtsGosPage verifyCurrentUrl(String expectedUrl) {
        assertTrue(url().contains(expectedUrl),
                "Текущий URL [" + url() + "] не содержит " + expectedUrl);
        return this;
    }
}
