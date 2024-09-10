import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

public class LogoutTest extends BaseTest{

    //intra apoi iesi  deconectare de pe pagina
    @Test
    public void successLogout() {
        String email = "user.test@gmail.com";//adresa de email
        String username = getUsernameFromEmail(email);//Obține numele de utilizator
        loginPage.enterEmail(email);//pune adresa de email
        loginPage.enterPassword("User1234");//pune parola
        loginPage.clickOnLoginButton();//Apasă butonul „Login”
        header.clickOnHeaderUserMenu();// clic pe meniul utilizatorulu
        header.clickOnLogoutButton();//apasa iesire
        loginPage.getFormTitle().shouldHave(Condition.text("Login Form"));
        //vad pagina de logare din nou
    }
}
