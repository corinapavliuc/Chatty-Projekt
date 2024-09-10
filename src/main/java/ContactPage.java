import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class ContactPage {
    private SelenideElement formTitle = $(byTagName("h1"));
    private SelenideElement nameField = $(byId("name"));
    private SelenideElement emailField = $(byId("email"));
    private SelenideElement contentField = $(byId("content"));//tabel de descriere
    private SelenideElement sendMessageButton = $("[type='submit']");
    private SelenideElement successMessage = $(byClassName("success-message"));
    private SelenideElement errorMessage = $(byClassName("error"));

    //  private SelenideElement validationMessageElement = $("[validationMessage='Please fill out this field.']");

    public SelenideElement nameFieldsShouldHadText() {
        return nameField;
    }

    public SelenideElement emailFieldsShouldHadText(){
        return emailField;
    }
    public SelenideElement contentFieldsShouldHadText(){
        return contentField;
    }
    public void nameFieldShouldHadText() {
        nameField.shouldHave(Condition.attribute("validationMessage", "Please fill in this field."));

    }
    public void emailFieldShouldHadText() {
        emailField.shouldHave(Condition.attribute("validationMessage", "Please fill in this field."));

    }
    public void contentFieldShouldHadText() {
        contentField.shouldHave(Condition.attribute("validationMessage", "Please fill in this field."));

    }
    public SelenideElement getErrorMessage() {
        return errorMessage;
    }

    public SelenideElement getSuccessMessage() {
        return successMessage;
    }

    public SelenideElement getFormTitle() {
        return formTitle;
    }

    public void enterName(String name) {
        nameField.setValue(name);
    }

    public void enterEmail(String email) {
        emailField.setValue(email);
    }

    public void enterMessage(String message) {
        contentField.setValue(message);
    }

    public void clickSendMessageButton() {
        sendMessageButton.click();
    }
}

