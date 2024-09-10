import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.refresh;

public class CreatePostTest extends BaseTest {
    //cream o postare in pagina de Create Post Test

    //testul simulează crearea unei postări de către un utilizator
    // nou înregistrat și verifică dacă postarea apare corect
    // în lista postărilor acestuia cu adaugarea pozei
    @Test
    public void successPostCreation() {
        registerNewUser();//Înregistrează automat un nou utilizator
        homePage.clickOnCreatePostButton();//apas pe buttonul
        String title = generateRandomTitle();//generez totul
        String description = generateRandomDescription();//generez descriee
        String content = generateRandomContent();//generez contex adica tabel text
        createPost.enterTitle(title);//Introduce titlul
        createPost.enterDescription(description);
        //Introduce descrierea generată în câmpul „descriere”
        createPost.enterContent(content);//Introduce conținutul generat
        createPost.uploadImage("C:\\Users\\Tel-ran.de\\Downloads\\cat.jpg");//adaug poza format jpg
        createPost.clickSubmitButton();//creaza buton
        refresh();//Reîncarcă pagina pentru a asigura că modificările recente
        homePage.clickOnMyPostsButton();//Navighează la pagina „Postările mele”
        myPostsPage.getFirstPostTitle().shouldHave(text(title));
        //Verifică dacă titlul primei postări afișate în pagina „Postările mele”
        // corespunde titlului generat și introdus anterior.
    }

//creez o postare fara niciun titlu, descriere, conținut
    @Test
    public void failedPostCreationWithEmptyFields() {
        registerNewUser();//Înregistrează automat un nou utilizator
        homePage.clickOnCreatePostButton();//apas button
        createPost.clickSubmitButton();//apas butonul creaza postarea
        createPost.getTitleField().shouldBe(Condition.visible);//Verifică dacă câmpul „Titlu” devine vizibil,
        // evidențiind că acest câmp este obligatoriu și nu a fost completat.
        createPost.getDescriptionField().shouldBe(Condition.visible);//la fel
        createPost.getContentField().shouldBe(Condition.visible);//la fel
        createPost.getErrorMessage().shouldHave(text("Please fill the field"));//apare textul
    }

    //generez o postare cu titlu  care depășește lungimea maximă permisă
    // mai lung de 40 simbole
    @Test
    public void failedPostCreationWithTitleLongerThan40Symbols() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String title = faker.lorem().characters(45);//generez text 45 caracter
        String description = generateRandomDescription();//generez decriere
        String content = generateRandomContent();//generez context
        homePage.clickOnCreatePostButton();//apas creat button
        createPost.enterTitle(title);//Introduce title generată în câmpul „descriere”
        createPost.enterDescription(description);//descrirea
        createPost.enterContent(content);//contect
        createPost.clickSubmitButton();//apas creat
        createPost.getErrorMessage().shouldHave(text("Please fill the field"));//apare eroarie
    }

    //generez o postare cu descriere  care depășește lungimea maximă permisă
    // mai lung de 100 simbole
    @Test
    public void failedPostCreationWithDescriptionLongerThan100Symbols() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String title = generateRandomTitle();//genereaza titlu
        String description = faker.lorem().characters(101);//generez descriptie 101
        String content = generateRandomContent();//generez context
        homePage.clickOnCreatePostButton();//apas creat button
        createPost.enterTitle(title);//Introduce title generată în câmpul „descriere”
        createPost.enterDescription(description);//descrirea
        createPost.enterContent(content);//content
        createPost.clickSubmitButton();//apas creat
        createPost.getErrorMessage().shouldHave(text("Please fill all fields"));//apare eroarie
    }

    //crem postarea cu format git  gresit de poza si nu se creaza postarea
    @Test
    public void failedPostCreationWithImageInWrongFormat() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String title = generateRandomTitle();//genereaza titlu
        String description = generateRandomDescription();//genereaza descriere
        String content = generateRandomContent();//generez contect
        homePage.clickOnCreatePostButton();//click button
        createPost.enterTitle(title);//Introduce title generată în câmpul „descriere”
        createPost.enterDescription(description);//descrirea
        createPost.enterContent(content);//content
        createPost.uploadImage("C:\\Users\\Tel-ran.de\\Downloads\\imagineGift.gif");//imagine gif care nu corespunde cu termeni
        createPost.getImageError().shouldHave(text("No file selected"));//apare greaseala

    }


    //crearea postari cu gile mare de 2mb
    @Test
    public void failedPostCreationWithImageMoreThan2MB() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String title = generateRandomTitle();//genereaza titlu
        String description = generateRandomDescription();//genereaza descriere
        String content = generateRandomContent();//generez contect
        homePage.clickOnCreatePostButton();//click button
        createPost.enterTitle(title);//Introduce title generată în câmpul „descriere”
        createPost.enterDescription(description);//descrirea
        createPost.enterContent(content);//content
        createPost.uploadImage("C:\\Users\\Tel-ran.de\\Downloads\\flug2mb.jpg");
        createPost.getImageError().shouldHave(text("File size exceeds the 2MB limit"));
        createPost.clickSubmitButton();

    }


//creez o postare o salvez in schite apoi o public si verific daca e inca in schite
    @Test
    public void successSavingPostAsDraft() {
        registerNewUser();//Înregistrează automat un nou utilizator
        String title = generateRandomTitle();//genereaza titlu
        String description = generateRandomDescription();//genereaza descriere
        String content = generateRandomContent();//generez contect
        homePage.clickOnCreatePostButton();//click button
        createPost.enterTitle(title);//Introduce title generată în câmpul „descriere”
        createPost.enterDescription(description);//descrirea
        createPost.enterContent(content);//content
        createPost.clickDraftButton();//Apasă butonul „Salvează ca schiță” (Draft) pentru a salva postarea fără a o publica încă.
        createPost.clickSubmitButton();//apasa creaza
        homePage.clickOnMyDraftsButton();//apas butonul Schițele mele
        myDraftsPage.getFirstPostTitle().shouldHave(text(title));//Verifică dacă titlul
        // primei postări din lista de schițe corespunde titlului generat.
    }


    }



