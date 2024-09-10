import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {
    private SelenideElement myPostsButton = $("label[for='myPostsId']");
    private SelenideElement createPostButton = $(byAttribute("data-test", "post-header__plus"));
    private SelenideElement contactFormLink = $(By.xpath("//a[contains(@href, 'contact')]"));
    private ElementsCollection pElements = $$(By.tagName("p"));
    private SelenideElement firstPostTitle = $(byTagName("h3"));
    private ElementsCollection postTitles = $$(byTagName("h3"));
    private SelenideElement homeButton = $(By.xpath("//a[contains(@href, 'homeblog')]"));
    private SelenideElement title = $(".post-header__plus-box");

    private SelenideElement adminPanelTitle = $("h1[data-test='adminPanelTitle']");

    public ElementsCollection getPElements() {
        return pElements;
    }

    public ElementsCollection getPostTitles() {
        return postTitles;
    }
    private SelenideElement myDraftsButton = $(byAttribute("alt", "Drafts"));
    public SelenideElement getFirstPostTitle() {
        return firstPostTitle;
    }

    public void clickOnCreatePostButton() {
        createPostButton.click();

    }


    public void clickOnContactLink() {
        contactFormLink.click();
    }

    public void clickOnMyPostsButton() {
        myPostsButton.click();
    }

    public void clickOnMyDraftsButton() {
        myDraftsButton.click();
    }

    public void clickOnHomeButton() {
        homeButton.click();
    }
     public SelenideElement getTitle(){
        return  title;
     }
     public SelenideElement getAdminPanel(){
       return adminPanelTitle;
     }
    }

