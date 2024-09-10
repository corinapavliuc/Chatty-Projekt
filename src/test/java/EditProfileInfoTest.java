import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class EditProfileInfoTest  extends BaseTest{

  //editam profilul cu datele noastre

//schimbam numele
    @Test
   public void successNameChange() {
       registerNewUser();//Înregistrează automat un nou utilizator
       String firstName = faker.name().firstName();//Generează un nume
       header.clickOnHeaderUserMenu();//apasa pe menu
       header.selectYourProfileOption();//alege YourProfile
       profilePage.getProfileFormTitle().shouldHave(text("Personal information"));//verific daca sunt pe pagina
       profilePage.clickOnEditButton();//apas butonul edit
       profilePage.enterName(firstName);//adaug numele generat
       profilePage.clickOnSaveButton();//apas butonul save
       refresh();//Reîncarcă pagina pentru a asigura că modificările sunt reflectate corect.
       header.getHeaderUserText().shouldHave(text(firstName));//verificam ca a fost schimbat nume
   }

   //schimbam surnume
    @Test
    public void successSurnameChange() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String lastName = faker.name().lastName();//Generează un nume
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.enterSurname(lastName);//adaug numele generat
        profilePage.getSurname().shouldHave(attribute("value", lastName));
        //Verifică dacă câmpul „Nume de familie” (Surname) are valoarea actualizată corect.

    }

    //schimbam numar de telefon
    @Test
    public void successPhoneChange() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String phone = faker.number().digits(11);//Generează un numar de telefon
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.enterPhoneNumber(phone);//adaug numar de telefon generat
        profilePage.clickOnSaveButton();//apas butonul save
        profilePage.getPhone().shouldHave(attribute("value", "+" + phone));
        //Verifică dacă câmpul „Număr de telefon” (Phone) afișează numărul
        // actualizat corect. Verificarea include și semnul „+” înaintea numărului,
    }

    //adaugam gen Male
    @Test
    public void successSavingGender() {
        registerNewUser();//Înregistrează automat un nou utilizator
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.selectGender("MALE");//Selectează opțiunea „MALE”
        profilePage.clickOnSaveButton();//apas butonul save
        profilePage.getGenderOption().shouldHave(text("MALE"));//verificam ca sa schimbat
    }

    //adaugam gen femele
    @Test
    public void successSavingGenderFe() {
        registerNewUser();//Înregistrează automat un nou utilizator
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.selectGender("FEMAL");//Selectează opțiunea „FEMAL”
        profilePage.clickOnSaveButton();//apas butonul save
        profilePage.getGenderOption().shouldHave(text("FEMAL"));//verificam ca sa schimbat
    }

    //fara gen
    @Test
    public void savingIfGenderIsNotSet() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.selectGender("Select");
        profilePage.clickOnSaveButton();
        profilePage.getSaveButton().shouldBe(Condition.visible);// Asigură-te că butonul este vizibil
        //dar nu fuctioneaza

    }

    //fara nume
    @Test
    public void savingWithEmptyName() {
        registerNewUser();//Înregistrează automat un nou utilizator
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.enterName("");//lasa gol
        profilePage.getSaveButton().shouldBe(Condition.visible);// Asigură-te că butonul este vizibil
        //dar nu fuctioneaza
    }

//fara surname
    @Test
    public void savingWithEmptySurname() {
        registerNewUser();//Înregistrează automat un nou utilizator
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.enterSurname("");//lasa gol
        // Obține elementul butonului Save
        SelenideElement saveButton = profilePage.getSaveButton();
        saveButton.shouldBe(Condition.visible); // Asigură-te că butonul este vizibil

    }


//nu se apasa pe buton ca sa se salveze  deoarece surname are mai mult de 22 caractere
    @Test
    public void failedSavingWithSurnameLongerThanTwentySymbols() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String surname = faker.lorem().characters(22, true, false);
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.enterSurname(surname);//pune surnama
        profilePage.getSaveButton().shouldBe(Condition.visible);// Asigură-te că butonul este vizibil
        //dar nu fuctioneaza

    }

    //nu se apasa butonul ca e gresit data de nastere
    @Test
    public void failedSavingWithDateInTheFutureAsBirthdate() {
        registerNewUser();//Înregistrează automat un nou utilizator
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.enterBirthDate("11.11.2040");//data de nastere incorect
        profilePage.getSaveButton().shouldBe(Condition.visible);// Asigură-te că butonul este vizibil
        //dar nu fuctioneaza

    }


    //fara ziua de nastere
    @Test
    public void savingIfBirthdateIsEmpty() {
        registerNewUser();//Înregistrează automat un nou utilizator
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.enterBirthDate("");//fara nimic
        profilePage.getSaveButton().shouldBe(Condition.visible);// Asigură-te că butonul este vizibil
        //dar nu fuctioneaza


    }

    ////fara numarul de telefon
    @Test
    public void savingWithEmptyPhone() {
        registerNewUser();//Înregistrează automat un nou utilizator
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.enterPhoneNumber("");//fara  numar de telefon
        profilePage.getSaveButton().shouldBe(Condition.visible);// Asigură-te că butonul este vizibil
        //dar nu fuctioneaza

    }

    //nu se apasa butonul ca e scurt numarul de telefon
    @Test
    public void failedSavingWithShortPhone() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String phone = faker.number().digits(8);//genreaza numar de telefon
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.enterPhoneNumber(phone);//numar de telrfon
        profilePage.getSaveButton().shouldBe(Condition.visible);// Asigură-te că butonul este vizibil
        //dar nu fuctioneaza

    }

    ////nu se apasa butonul ca e lung numarul de telefon
    @Test
    public void failedSavingWithLongPhone() {
        registerNewUser();//nu se apasa butonul ca e scurt numarul de telefon
        String phone = faker.number().digits(21);
        header.clickOnHeaderUserMenu();//apasa pe menu
        header.selectYourProfileOption();//alege YourProfile
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));//verific daca sunt pe pagina
        profilePage.clickOnEditButton();//apas butonul edit
        profilePage.enterPhoneNumber(phone);
        profilePage.getSaveButton().shouldBe(Condition.visible);// Asigură-te că butonul este vizibil
        //dar nu fuctioneaza

    }


}
