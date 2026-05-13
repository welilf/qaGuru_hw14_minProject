package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {

    private final SelenideElement outputResults = $(".table-responsive");    // Actions


    public ResultTableComponent checkField (String key, String value) {
        outputResults.
                $$("tr").
                findBy(text(key)).
                shouldHave(text(value));
        return this;
    }
}
