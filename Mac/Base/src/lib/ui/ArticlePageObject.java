package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

public abstract class ArticlePageObject extends MainPageObject {

    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        GOT_IT_BUTTON,
        LIST_NAME_INPUT_FIELD,
        SAVE_TO_MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        EXISTING_LIST_NAME_TPL;

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    private static String getListXpathByName (String list_name) {
        return EXISTING_LIST_NAME_TPL.replace("{LIST NAME}", list_name);
    }

    public WebElement waitForTitleElement () {
        return this.waitForElementPresent(TITLE,"Can't find page title!", 25);
    }

    public WebElement waitForTitleElementByString (String title) {
        return this.waitForElementPresent(title,"Can't find page title!", 25);
    }

    public String getArticleTitle () {

        if (Platform.getInstance().isAndroid()){
        return waitForTitleElement().getAttribute("text");}
        else {
            return waitForTitleElement().getAttribute("name");
        }
    }

    public String getArticleTitleByString (String title) {

        if (Platform.getInstance().isAndroid()){
            return waitForTitleElement().getAttribute("text");}
        else {
            return waitForTitleElementByString(title).getAttribute("name");
        }
    }

    public void swipeArticleToFooter () {

        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Can't find footer after swipes!",
                20
            );
        } else {
            swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Can't find footer after swipes!",
                    40
            );
        }
    }

    public void addArticleToMyList (String name_of_folder) throws InterruptedException {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can't find 'More options' button!",
                20
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can't find 'Add to reading list' option!",
                10
        );

        this.waitForElementAndClick(
                 GOT_IT_BUTTON,
                "Can't find 'GOT IT' overlay button!",
                10
        );

        this.waitForElementAndClear(
                LIST_NAME_INPUT_FIELD,
                "Can't find input of 'Name of the list' field",
                10
        );

        this.waitForElementAndSendKeys(
                LIST_NAME_INPUT_FIELD,
                name_of_folder,
                "Can't put text to 'Name of the list' field",
                10
        );

        this.waitForElementAndClick(
                SAVE_TO_MY_LIST_OK_BUTTON,
                "Can't press OK button!",
                10
        );
    }

    public void addArticleToMyExistingList (String name_of_folder) throws InterruptedException {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can't find 'More options' button!",
                10
        );

        Thread.sleep(1000);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can't find 'Add to reading list' option!",
                10
        );

        waitForElementAndClick(
                getListXpathByName(name_of_folder),
                "Can't click on saved articles list for the second article!",
                10
        );}

        public void closeArticle () {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Can't find X button!",
                10
        );
    }

    public void assertTitleIsPresentOnArticlesPage() {
        this.assertElementPresent(TITLE);
    }

    public void addArticleToMySaved(){
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can't find option to add article to reading list!",
                25
        );
    }
}
