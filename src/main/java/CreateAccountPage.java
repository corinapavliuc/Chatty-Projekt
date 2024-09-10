import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class CreateAccountPage {
    private SelenideElement emailInputField = $(byAttribute("data-test", "email"));
    private SelenideElement passwordInputField = $(byAttribute("data-test", "password"));
    private SelenideElement confirmPasswordInputField = $(byAttribute("data-test", "confirmPassword"));


    public SelenideElement getEmailInputField() {
        return emailInputField;
    }
    public SelenideElement getPasswordInputField() {
        return passwordInputField;
    }
    public SelenideElement getConfirmPasswordInputField() {
        return confirmPasswordInputField;
    }



    private SelenideElement registrationButton = $(byClassName("registration-btn"));
    private SelenideElement formTitle = $(byTagName("h1"));

    public SelenideElement getFormTitle() {
        return formTitle;
    }
    private SelenideElement errorMessage = $(byClassName("text-error"));

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }
    public SelenideElement getRegistrationButton() {
        return registrationButton;
    }
    public void enterEmail(String usernameValue){
        emailInputField.shouldBe(visible, Duration.ofSeconds(5));
        emailInputField.setValue(usernameValue);
    }
    public void enterPassword(String passwordValue){
        passwordInputField.setValue(passwordValue);
    }
    public void enterConfirmPassword(String confirmPasswordValue){
        confirmPasswordInputField.setValue(confirmPasswordValue);
    }
    public void clickOnRegistrationButton() {
        registrationButton.click();
    }

}