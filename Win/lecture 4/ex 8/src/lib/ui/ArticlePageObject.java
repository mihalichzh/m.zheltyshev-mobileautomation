package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
        GOT_IT_BUTTON = "org.wikipedia:id/onboarding_button",
        LIST_NAME_INPUT_FIELD = "org.wikipedia:id/text_input",
        SAVE_TO_MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement () {
        return this.waitForElementPresent(By.id(TITLE),"Can't find page title!", 15);
    }

    public String getArticleTitle () {
        return waitForTitleElement().getAttribute("text");
    }

    public void swipeArticleToFooter () {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Can't find footer after swipes!",
                20
        );
    }

    public void addArticleToMyList (String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can't find 'More options' button!",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can't find 'Add to reading list' option!",
                5
        );

        this.waitForElementAndClick(
                By.id(GOT_IT_BUTTON),
                "Can't find 'GOT IT' overlay button!",
                5
        );

        this.waitForElementAndClear(
                By.id(LIST_NAME_INPUT_FIELD),
                "Can't find input of 'Name of the list' field",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(LIST_NAME_INPUT_FIELD),
                name_of_folder,
                "Can't put text to 'Name of the list' field",
                5
        );

        this.waitForElementAndClick(
                By.xpath(SAVE_TO_MY_LIST_OK_BUTTON),
                "Can't press OK button!",
                5
        );
    }

    public void closeArticle () {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Can't find X button!",
                5
        );
    }
}
