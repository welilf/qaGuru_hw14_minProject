package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SuccessfulFillFormPage {

    CalendarComponent calendar = new CalendarComponent();
    ResultTableComponent resultsTable = new ResultTableComponent();

    // Elements
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmail = $("#userEmail");
    private final SelenideElement genderContainer = $("#genterWrapper");
    private final SelenideElement userNumber = $("#userNumber");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement dateOfBirth = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateDropdown = $("#state");
    private final SelenideElement cityDropdown = $("#city");

    // Actions

    @Step("Открываем страницу формы регистрации")
    public SuccessfulFillFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    @Step("Вводим имя: {value}")
    public SuccessfulFillFormPage typeFirstName (String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Вводим фамилию: {value}")
    public SuccessfulFillFormPage typeLastName (String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Вводим email: {value}")
    public SuccessfulFillFormPage typeUserEmail (String value) {
        userEmail.setValue(value);
        return this;
    }

    @Step("Выбираем пол: {value}")
    public SuccessfulFillFormPage setGender (String value) {
        genderContainer.$(byText(value)).click();
        return this;
    }

    @Step("Вводим номер телефона: {value}")
    public SuccessfulFillFormPage typeUserNumber (String value) {
        userNumber.setValue(value);
        return this;
    }

    @Step("Устанавливаем дату рождения: {day} {month} {year}")
    public SuccessfulFillFormPage setDateOfBirth (String day, String month, String year) {
        dateOfBirth.click();
        calendar.setDate(day, month, year);
        return this;
    }

    @Step("Выбираем предмет: {value}")
    public SuccessfulFillFormPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбираем хобби: {value}")
    public SuccessfulFillFormPage setHobby(String value) {
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Загружаем изображение: {fileName}")
    public SuccessfulFillFormPage uploadPicture(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    @Step("Вводим адрес: {value}")
    public SuccessfulFillFormPage setAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    @Step("Выбираем штат: {value}")
    public SuccessfulFillFormPage setState(String value) {
        stateDropdown.scrollIntoView(true).click();
        $("#react-select-3-input").setValue(value).pressEnter();
        return this;
    }

    @Step("Выбираем город: {value}")
    public SuccessfulFillFormPage setCity(String value) {
        cityDropdown.click();
        $("#react-select-4-input").setValue(value).pressEnter();
        return this;
    }

    @Step("Отправляем форму (Submit)")
    public SuccessfulFillFormPage submitForm() {
        submitButton.click();
        return this;
    }

    @Step("Проверяем, что поле {key} имеет значение {value}")
    public SuccessfulFillFormPage checkField (String key, String value) {
        resultsTable.checkField(key, value);
        return this;
    }
}
