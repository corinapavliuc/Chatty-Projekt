import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;

public class ContactUsTest extends BaseTest{
    //dupa inregistrare si logare reusita intram in  ContectUs ca se poate trimite feddkback
    // completez tot corect  imi  apare textul
    //Feedback submitted successfully!
    @Test
    public void successFeedbackWithValidData() {
        registerNewUser();
        String name = faker.name().fullName();//genram nume
        String email = faker.internet().emailAddress();//genram email
        String message = generateRandomContent();//generam descrierea
        homePage.clickOnContactLink();//apasam pe contact
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);//punem numele
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldHave(Condition.text("Feedback submitted successfully!"));
    }

    //dupa inregistrare si logare reusita intram in  ContectUs ca se poate trimite feddkback
    //trimitem fara nume si apare textul de eroarie care dispare se afla in proprietes
    @Test
    public void failedFeedbackWithEmptyName() {
        registerNewUser();
        String email = faker.internet().emailAddress();
        String message = generateRandomContent();
        homePage.clickOnContactLink();//apasam pe contact
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.nameFieldsShouldHadText().shouldHave(Condition.attribute("validationMessage", "Please fill in this field."));

    }
    //dupa inregistrare si logare reusita intram in  ContectUs ca se poate trimite feddkback
    //trimitem fara email si apare textul de eroarie
    @Test
    public void failedFeedbackWithEmptyEmail() {
        registerNewUser();
        String name = faker.name().fullName();//generam nume
        String message = generateRandomContent();//generam descriere
        homePage.clickOnContactLink();//apasam pe contact
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));//vedem daca am ajus la pagina de contact
        contactPage.enterName(name);//punem numele
        contactPage.enterMessage(message);//scrim text
        contactPage.clickSendMessageButton();
        contactPage.emailFieldsShouldHadText().shouldHave(Condition.attribute("validationMessage", "Please fill in this field."));

    }

    //dupa inregistrare si logare reusita intram in  ContectUs ca se poate trimite feddkback
    // fara descrie trimitem sia pare eroarie si tesxtul de jos
    @Test
    public void failedFeedbackWithEmptyContent() {
        registerNewUser();
        String name = faker.name().fullName();//generam nume
        String email = faker.internet().emailAddress();//genram emai
        homePage.clickOnContactLink();//apasam pe contact
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);
        contactPage.enterEmail(email);
        contactPage.clickSendMessageButton();
        contactPage.contentFieldsShouldHadText().shouldHave(Condition.attribute("validationMessage", "Please fill in this field."));


    }
    //dupa inregistrare si logare reusita intram in  ContectUs ca se poate trimite feddkback
    //Când numele utilizatorului conține mai mult de treizeci de caractere.
    @Test
    public void failedFeedbackWithNameLongerThanThirtySymbols() {
        registerNewUser();//inregistreaza automat nou user
        //un nume aleatoriu cu 35 de caractere.
        String name = faker.lorem().characters(35);//genereaza automat nume 35
        String email = faker.internet().emailAddress(); //genereaza email automat
        String message = generateRandomContent(); //genereaza text automat
        homePage.clickOnContactLink();//apasam pe contact
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);//punem numele
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);//scrim text
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
        //nu se apasa butonul este blocatăt de sistem
    }

    ////dupa inregistrare si logare reusita intram in  ContectUs
    // ca se poate trimite feddkback ca se poate trimite feddkback cu un un email ce nu contine
    //nu conține simbolul „@”,apare acest mesaj („Invalid email format”).
    @Test
    public void failedFeedbackWithEmailWithoutAt() {
        registerNewUser();//inregistreaza automat nou user
        String name = faker.name().fullName();//generam nume
        String email = faker.name().username();//generam email
        String message = generateRandomContent();//generam decriere
        homePage.clickOnContactLink();//apasam pe contact
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);//punem numele
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);//scrim text
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
        contactPage.getErrorMessage().shouldHave(text("Invalid email format"));
    }

    ////dupa inregistrare si logare reusita intram in  ContectUs ca se poate trimite feddkback
    // ca se poate trimite feddkback cu un caracter in descriere
    @Test
    public void failedFeedbackWithContentLessThanTwoSymbols() {
        registerNewUser();
        String name = faker.name().fullName(); //genereaza nume
        String email = faker.internet().emailAddress();//genereaza email
        String message = faker.lorem().characters(1);
        //genereaza descrierea un element 1
        homePage.clickOnContactLink();//apasam pe contact
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);//punem numele
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);//scrim text
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
        //este blocat de sistem nu se apasa send
    }

    ////dupa inregistrare si logare reusita intram in  ContectUs ca se poate trimite feddkback
    // ca se poate trimite feddkback cu 1500 caracter in descriere
    @Test
    public void failedFeedbackWithContentLongerThan1000Symbols() {
        registerNewUser();
        String name = faker.name().fullName();//generam nume
        String email = faker.internet().emailAddress();//geneream email
        String message = faker.lorem().characters(1500);
        //generam in descriere 1500 de carectere
        homePage.clickOnContactLink();//apasam pe contact
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);//punem numele
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);//scrim text
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
        //nu se apasa send ca e blocat de sistem
    }
}
