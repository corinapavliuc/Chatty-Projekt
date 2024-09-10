import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

public class PasswordChangeTest extends BaseTest{
    // schimba parola din menu Personal information

    //schimba cu success parola
    @Test
    public void successPasswordChangeWithValidData() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String oldPassword = password;//stocheaza parola veche
        String newPassword = faker.internet().password(8, 100);//genereaza parola noua
        header.clickOnHeaderUserMenu();//apasa meniul utilizatorului
        header.selectYourProfileOption();//selecteaza Profilul tău” (Your Profile)
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verifica ca esti acolo
        profilePage.clickOnPasswordChangeButton();//apasa pe button schimba parola
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));//vad ca sunt pe pagina corecta
        passwordChangeForm.enterOldPassword(oldPassword);//pun parola veche
        passwordChangeForm.enterNewPassword(newPassword);//pun noua
        passwordChangeForm.confirmPassword(newPassword);//confirm noua
        passwordChangeForm.clickOnSaveButton();//apas salvare
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        //vad ca sa schimbat cu succes si sunt pe pagina Personal information
    }

    //fara parola veche da eroarie
    @Test
    public void failedPasswordChangeWithEmptyOldPassword() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String newPassword = faker.internet().password(8, 100);//genereaza parola noua
        header.clickOnHeaderUserMenu();//apasa meniul utilizatorului
        header.selectYourProfileOption();//selecteaza Profilul tău” (Your Profile)
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verifica ca esti acolo
        profilePage.clickOnPasswordChangeButton();//apasa pe button schimba parola
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));//vad ca sunt pe pagina corecta
        passwordChangeForm.enterNewPassword(newPassword);//pun parola  noua
        passwordChangeForm.confirmPassword(newPassword);//confirm noua
        passwordChangeForm.clickOnSaveButton();//apas salvare
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));
    }

    //parola veche incorecta
    @Test
    public void failedPasswordChangeWithInvalidOldPassword() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String oldPassword = faker.internet().password(8, 100);//genereaza veche parola gresita
        String newPassword = faker.internet().password(8, 100);//genereaza parola noua
        header.clickOnHeaderUserMenu();//apasa meniul utilizatorului
        header.selectYourProfileOption();//selecteaza Profilul tău” (Your Profile)
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verifica ca esti acolo
        profilePage.clickOnPasswordChangeButton();//apasa pe button schimba parola
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));//vad ca sunt pe pagina corecta
        passwordChangeForm.enterOldPassword(oldPassword);//pun parola veche incorecta
        passwordChangeForm.enterNewPassword(newPassword);//pun parola noua
        passwordChangeForm.confirmPassword(newPassword);//confirm parola
        passwordChangeForm.clickOnSaveButton();//apas salvare
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));
    }

    //fara parola noua
    @Test
    public void failedPasswordChangeWithEmptyNewPassword() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String oldPassword = password;//Stochează parola veche
        String newPassword = faker.internet().password(8, 100);//genereaza parola noua
        header.clickOnHeaderUserMenu();//apasa meniul utilizatorului
        header.selectYourProfileOption();//selecteaza Profilul tău” (Your Profile)
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verifica ca esti acolo
        profilePage.clickOnPasswordChangeButton();//apasa pe button schimba parola
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));//vad ca sunt pe pagina corecta
        passwordChangeForm.enterOldPassword(oldPassword);//pun parola veche
        passwordChangeForm.confirmPassword(newPassword);//confirm parola
        passwordChangeForm.clickOnSaveButton();//apas salvare
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("The new passwords do not match."));
    }

    //fara confirmpaswword
    @Test
    public void failedPasswordChangeWithEmptyConfirmPassword() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String oldPassword = password;//Stochează parola veche
        String newPassword = faker.internet().password(8, 100);//genereaza parola noua
        header.clickOnHeaderUserMenu();//apasa meniul utilizatorului
        header.selectYourProfileOption();//selecteaza Profilul tău” (Your Profile)
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verifica ca esti acolo
        profilePage.clickOnPasswordChangeButton();//apasa pe button schimba parola
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));//vad ca sunt pe pagina corecta
        passwordChangeForm.enterOldPassword(oldPassword);//pun parola veche
        passwordChangeForm.enterNewPassword(newPassword);//pun parola noua
        passwordChangeForm.clickOnSaveButton();//apas salvare
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("The new passwords do not match."));
    }

    //când noua parolă și parola de confirmare nu coincid
    @Test
    public void failedPasswordChangeIfNewPasswordNotMatchConfirmPassword() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String oldPassword = password;//Stochează parola veche
        String newPassword = faker.internet().password(8, 100);//genereaza parola noua
        String confirmPassword = faker.internet().password(8, 100);//genereaza confirm parola noua
        header.clickOnHeaderUserMenu();//apasa meniul utilizatorului
        header.selectYourProfileOption();//selecteaza Profilul tău” (Your Profile)
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verifica ca esti acolo
        profilePage.clickOnPasswordChangeButton();//apasa pe button schimba parola
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));//vad ca sunt pe pagina corecta
        passwordChangeForm.enterOldPassword(oldPassword);//pun parola veche
        passwordChangeForm.enterNewPassword(newPassword);//pun parola noua
        passwordChangeForm.confirmPassword(confirmPassword);//confirm parola diferita
        passwordChangeForm.clickOnSaveButton();//apas salvare
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("The new passwords do not match."));
    }

    //noua parolă care are mai puțin de opt caractere
    @Test
    public void failedPasswordChangeIfNewPasswordHasLessThanEightSymbols() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String oldPassword = password;//Stochează parola veche
        String newPassword = faker.internet().password(1, 7);//genereaza parola noua
        header.clickOnHeaderUserMenu();//apasa meniul utilizatorului
        header.selectYourProfileOption();//selecteaza Profilul tău” (Your Profile)
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verifica ca esti acolo
        profilePage.clickOnPasswordChangeButton();//apasa pe button schimba parola
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));//vad ca sunt pe pagina corecta
        passwordChangeForm.enterOldPassword(oldPassword);//pun parola veche
        passwordChangeForm.enterNewPassword(newPassword);//pun parola noua
        passwordChangeForm.confirmPassword(newPassword);//confirm parola
        passwordChangeForm.clickOnSaveButton();//apas salvare
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));
    }

    //când noua parolă are mai mult de 100 de caractere
    @Test
    public void failedPasswordChangeIfNewPasswordLongerThanHundredSymbols() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String oldPassword = password;//Stochează parola veche
        String newPassword = faker.internet().password(101, 120);//genereaza parola noua
        header.clickOnHeaderUserMenu();//apasa meniul utilizatorului
        header.selectYourProfileOption();//selecteaza Profilul tău” (Your Profile)
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verifica ca esti acolo
        profilePage.clickOnPasswordChangeButton();//apasa pe button schimba parola
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));//vad ca sunt pe pagina corecta
        passwordChangeForm.enterOldPassword(oldPassword);//pun parola veche
        passwordChangeForm.enterNewPassword(newPassword);//pun parola noua
        passwordChangeForm.confirmPassword(newPassword);//confirm parola
        passwordChangeForm.clickOnSaveButton();//apas salvare
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));
    }

    //noua parolă conține doar cifre și nu include litere
    @Test
    public void failedPasswordChangeIfNewPasswordHasNoLetters() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String oldPassword = password;//Stochează parola veche
        String newPassword = faker.number().digits(10);//genereaza parola cifre
        header.clickOnHeaderUserMenu();//apasa meniul utilizatorului
        header.selectYourProfileOption();//selecteaza Profilul tău” (Your Profile)
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verifica ca esti acolo
        profilePage.clickOnPasswordChangeButton();//apasa pe button schimba parola
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));//vad ca sunt pe pagina corecta
        passwordChangeForm.enterOldPassword(oldPassword);//pun parola veche
        passwordChangeForm.enterNewPassword(newPassword);//pun parola noua
        passwordChangeForm.confirmPassword(newPassword);//confirm parola
        passwordChangeForm.clickOnSaveButton();//apas salvare
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));
    }


}
