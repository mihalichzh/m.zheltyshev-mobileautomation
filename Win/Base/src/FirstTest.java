import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FirstTest extends CoreTestCase {

    MainPageObject mainPageObject;

    protected void setUp() throws Exception {
        super.setUP();
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearchArticle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testex7() {

        //Ориентация экрана будет установлена при инициализации драйвера в соответствии с заданным capability "orientation"
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't click on Search Field!",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't fill in Search Input!",
                5
        );
        mainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Can't find the entry!",
                15
        );

        String search_query_text = mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't get text from search field!",
                5
        ).getAttribute("text");

        Assert.assertEquals(
                "Text in search filed is not expected!",
                "Java",
                search_query_text
        );
    }

    @Test
    public void testCancelSearch() {
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find Search Input!",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't fill in Search Input!",
                5
        );

        mainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't clear Input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't click on X button!",
                5
        );
        mainPageObject.waitForElementIsNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Button X is still presented!",
                5
        );

        WebElement search_field_on_main_page = mainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@resource-id, 'org.wikipedia:id/search_container')]//*[@index = '1']"),
                "Can't find Search Field!",
                10
        );
        Assert.assertEquals(
                "Can't get text from main page search field!",
                "Search Wikipedia",
                search_field_on_main_page.getAttribute("text")
        );
    }

    @Test
    public void testCompareArticleTitle() {
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find Search Input!",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't fill in Search Input!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Can't find Entry!",
                5
        );

        WebElement title_element = mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title!",
                20
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "Title is Wrong!",
                "Java (programming language)",
                article_title
        );

    }

    @Test
    public void testSwipeArticle() {
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find Search Input!",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium",
                "Can't fill in Search Input!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Appium']"),
                "Can't find Entry!",
                5
        );

        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title!",
                20
        );

        mainPageObject.swipeUpToFindElement(
               By.xpath("//*[@text='View page in browser']"),
               "Can't find the end of the article",
               20
       );

    }

    @Test
    public void testsaveFirstArticleToMyList () {
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find Search Input!",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't fill in Search Input!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Can't find Entry!",
                5
        );

        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find 'More options' button!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find 'Add to reading list' option!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can't find 'GOT IT' overlay button!",
                5
        );

        mainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find input of 'Name of the list' field",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Can't put text to 'Name of the list' field",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't press OK button!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find X button!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can't find 'To my lists' button!",
                5
        );

/*        waitForElementAndClick(
                By.xpath("//*[@text='Learning programming']"),
                "Can't find created folder!",
                5
        );*/

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_container"),
                "Can't find created folder!",
                5
        );

        mainPageObject.swipeElementToLeft(
                By.id("//*[@text='Java (programming language)']"),
                "Can't find saved article!"
        );

        mainPageObject.waitForElementIsNotPresent(
                By.xpath("//org.wikipedia:id/item_title[@resource-id='Learning programming']"),
                "Can't delete saved article!",
                5
        );
    }

    @Test
    public void testex5 () {
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find Search Input!",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't fill in Search Input!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Can't find Entry!",
                5
        );

        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find 'More options' button!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find 'Add to reading list' option!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can't find 'GOT IT' overlay button!",
                5
        );

        mainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find input of 'Name of the list' field",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Can't put text to 'Name of the list' field",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't press OK button!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find X button!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find Search Input!",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "C++",
                "Can't fill in Search Input!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'General purpose high-level programming language']"),
                "Can't find Entry!",
                5
        );

        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find 'More options' button!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find 'Add to reading list' option!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title'][@text = 'Learning programming']"),
                "Can't click on saved articles list!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find X button!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can't find 'To my lists' button!",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_container"),
                "Can't find created folder!",
                5
        );

        mainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='object-oriented programming language']"),
                "Can't find saved article!"
        );

        mainPageObject.waitForElementIsNotPresent(
                By.xpath("//org.wikipedia:id/item_title[@text='Learning programming']"),
                "Can't delete saved article!",
                5
        );

        String title_in_list = mainPageObject.waitForElementPresent(
                By.xpath("//*[@text='C++']"),
                "Can't find second's article entry in saved articles list!",
                5
        ).getAttribute("text");

        mainPageObject.waitForElementAndClick (
                By.xpath("//*[@text='general purpose high-level programming language']"),
                "Can't click on second's article entry in saved articles list!",
                5
        );

        String title_on_articles_page = mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find second article's title on the page!",
                10
        ).getAttribute("text");

        Assert.assertEquals("Title on the articles list and title on the article's page are not equals!",
                title_in_list,
                title_on_articles_page);
    }
}
