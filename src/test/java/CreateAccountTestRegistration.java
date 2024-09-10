import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;

public class CreateAccountTestRegistration extends BaseTest{

    //Creez noua persoana din log form in Sign in
    @Test
    public void successRegistration() {
        loginPage.clickOnRegistrationLink();//apas pe sign in
        createaccountpage.getFormTitle().shouldHave(text("Create Account"));//am ajus pe pagina
        String email = faker.internet().emailAddress();//genereaza email
        String password = faker.internet().password(8, 100);
        //8: Lungimea minimă-100: Lungimea maximă parolei
        createaccountpage.enterEmail(email);//automat se pune email
        createaccountpage.enterPassword(password);//automat se pune password
        createaccountpage.enterConfirmPassword(password);//automat se pune confirm password
        createaccountpage.clickOnRegistrationButton();//apasam butonul
        homePage.getTitle().shouldHave(text("Create a post"));//verificam ca am ajuns la pagina Home

    }
    //test fara email
    @Test
    public void failedRegistrationWithEmptyEmail() {
        loginPage.clickOnRegistrationLink();//apas pe sign in
        createaccountpage.getFormTitle().shouldHave(text("Create Account"));//am ajus pe pagina
        String password = faker.internet().password(8, 100);
        //8: Lungimea minimă-100: Lungimea maximă parolei
        createaccountpage.enterPassword(password);//automat se pune password
        createaccountpage.enterConfirmPassword(password);//automat se pune confirm password
        createaccountpage.getErrorMessage().shouldHave(text("Email cannot be empty"));

    }
//test cu email gresit fara @
    @Test
    public void failedRegistrationWithEmailWithoutAt() {
        loginPage.clickOnRegistrationLink();//apas pe sign in
        createaccountpage.getFormTitle().shouldHave(text("Create Account"));//am ajus pe pagina
        createaccountpage.enterEmail("user1gmail.com");//email gresit fara @
        String password = faker.internet().password(8, 100);
        //8: Lungimea minimă-100: Lungimea maximă parolei
        createaccountpage.enterPassword(password);//automat se pune password
        createaccountpage.enterConfirmPassword(password);//automat se pune confirm password
        createaccountpage.getErrorMessage().shouldHave(text("Incorrect email format"));

    }
//test fara parola
    @Test
    public void failedRegistrationWithEmptyPassword() {
        loginPage.clickOnRegistrationLink();//apas pe sign in
        createaccountpage.getFormTitle().shouldHave(text("Create Account"));//am ajus pe pagina
        String email = faker.internet().emailAddress();//genereaza email
        createaccountpage.enterEmail(email);//automat se pune email
        createaccountpage.getErrorMessage().shouldHave(text("Password cannot be empty"));

    }
//Test cu acelasi cont
    @Test
    public void failedRegistrationWithExistingEmail() {
        loginPage.clickOnRegistrationLink();//apas pe sign in
        createaccountpage.getFormTitle().shouldHave(text("Create Account"));//am ajus pe pagina
        createaccountpage.enterEmail("admin.admin@gmail.com");
        String password = faker.internet().password(8, 100);
        //8: Lungimea minimă-100: Lungimea maximă parolei
        createaccountpage.enterPassword(password);//automat se pune parola
        createaccountpage.enterConfirmPassword(password);//automat se pune parola
        createaccountpage.clickOnRegistrationButton();
        createaccountpage.getErrorMessage().shouldHave(text("Email already exists!"));
    }

    //Parola scurta mai putin de 7 lugimea
    @Test
    public void failedRegistrationWithInvalidShortPassword() {
        loginPage.clickOnRegistrationLink();//apas pe sign in
        createaccountpage.getFormTitle().shouldHave(text("Create Account"));//am ajus pe pagina
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(1, 7);
        //1: Lungimea minimă-7: Lungimea maximă parolei
        createaccountpage.enterEmail(email);//automat se pune email
        createaccountpage.enterPassword(password);//automat se pune parola
        createaccountpage.enterConfirmPassword(password);//automat se pune parola
        createaccountpage.getErrorMessage().shouldHave(text("Password must be 8-100 characters and include at least one letter and one digit"));

    }
    //Parola lunga mai mult  de 110 lugimea
    @Test
    public void failedRegistrationWithInvalidLongPassword() {
        loginPage.clickOnRegistrationLink();//apas pe sign in
        createaccountpage.getFormTitle().shouldHave(text("Create Account"));//am ajus pe pagina
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(101, 110);
        //101: Lungimea minimă-110: Lungimea maximă parolei
        createaccountpage.enterEmail(email);//automat se pune email
        createaccountpage.enterPassword(password);;//automat se pune parola
        createaccountpage.enterConfirmPassword(password);//automat se pune parola
        createaccountpage.getErrorMessage().shouldHave(text("Password must be 8-100 characters and include at least one letter and one digit"));

    }

    //Test fara confirmpassword
    @Test
    public void failedRegistrationWithEmptyConfirmPassword() {
        loginPage.clickOnRegistrationLink();//apas pe sign in
        createaccountpage.getFormTitle().shouldHave(text("Create Account"));//am ajus pe pagina
        String email = faker.internet().emailAddress();///genereaza email
        String password = faker.internet().password(8, 100);
        //8: Lungimea minimă-100: Lungimea maximă parolei
        createaccountpage.enterEmail(email);//automat se pune email
        createaccountpage.enterPassword(password);//automat se pune parola
        createaccountpage.getErrorMessage().shouldHave(text("Confirm password cannot be empty"));

    }

    //Test cu parola diferita de ConfirmPassword
    @Test
    public void failedRegistrationIfConfirmPasswordDiffersFromPassword() {
        loginPage.clickOnRegistrationLink();//apas pe sign in
        createaccountpage.getFormTitle().shouldHave(text("Create Account"));//am ajus pe pagina
        String email = faker.internet().emailAddress();//generam email
        String password = faker.internet().password(8, 100);
        //8: Lungimea minimă-100: Lungimea maximă parolei
        String confirmPassword = faker.internet().password(8, 100);
        createaccountpage.enterEmail(email);////automat se pune email
        createaccountpage.enterPassword(password);//automat se pune parola
        createaccountpage.enterConfirmPassword(confirmPassword);
        //automat se pune diferita  parola automat
        createaccountpage.getErrorMessage().shouldHave(text("Passwords do not match"));

    }

}
