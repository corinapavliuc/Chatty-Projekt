import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;

public class LoginTest extends BaseTest{

    //Login form te logezi
    //Test sucess login
    @Test
    public void successLogin() {
        String email = "admin.admin@gmail.com";
        String username = getUsernameFromEmail(email);
        loginPage.enterEmail(email);
        loginPage.enterPassword("Admin123456");
        loginPage.clickOnLoginButton();
        homePage.getAdminPanel().shouldHave(text("Admin panel"));
        //am verificat ca am a fuctionat tot si ca sunt pe  alta pagiina

    }
    //test cu emailul incorret
    @Test
    public void failedLoginIfEmailHasNoAtSign() {
        loginPage.enterEmail("admin.admingmail.com");
        loginPage.enterPassword("Admin123456");
        loginPage.getFormTitle().shouldHave(text("Login Form"));
        loginPage.getErrorMessage().shouldHave(text("Incorrect email"));

    }

//fara parola
@Test
    public void failedLoginWithEmptyPassword() {
        loginPage.enterEmail("admin.admin@gmail.com");
        loginPage.clickOnLoginButton();
        loginPage.getFormTitle().shouldHave(text("Login Form"));
        loginPage.getErrorMessage().shouldHave(text("Invalid email or password. Please try again."));
    }

//cu datele gresite
    @Test
    public void failedLoginWithNotExistingUser() {
        loginPage.enterEmail("admin.admin345@gmail.com");
        loginPage.enterPassword("User12345");
        loginPage.clickOnLoginButton();
        loginPage.getFormTitle().shouldHave(text("Login Form"));
        loginPage.getErrorMessage().shouldHave(text("User not found. Please register."));

    }
        //sa fac test ca parola este gresita
        @Test
        public void failedLoginWithInvalidPassword() {
        loginPage.enterEmail("admin.admin@gmail.com");
        loginPage.enterPassword("Admin1234");
        loginPage.clickOnLoginButton();
       }

    }

