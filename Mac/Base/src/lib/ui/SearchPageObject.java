package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.regex.Pattern;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_ELEMENT,
        SEARCH_INPUT_WITH_TEXT,
        CLEAR_SEARCH_INPUT_BTN,

        //Темплейт локатора, который находит результат поиска одновременно по заголовку и описанию
        SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL;
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static  String getResultSearchElement (String substring) {
        return  SEARCH_RESULT_BY_SUBSTRING_TPL.replace ("{SUBSTRING}", substring);
    }

    //Метод для подстановки в темплейт
    private static String getResultSearchElementByTitleAndSubstringAndroid(String title, String substring) {
        return SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL.replace("{TITLE}", title).replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        try {
            this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Can't find and click init search input!", 30);
            Thread.sleep(2000);
            } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,"Can't type text to search input!", 30);
    }

    public void waitForSearchResult (String substring) {
        this.waitForElementPresent(getResultSearchElement(substring), "Can't find search result!", 30);
    }

    public void clickByArticleWithSubstring (String substring) {
            this.waitForElementAndClick(getResultSearchElement(substring),
                    "Can't find and click search result by substring!", 30);
    }

/*    //Метод клика по элементу с локатором с указанным title и substring
    public void waitForElementByTitleAndDescription(String title, String substring) {
        if(Platform.getInstance().isAndroid()){
        this.waitForElementPresent(getResultSearchElementByTitleAndSubstringAndroid(title,substring),
                "Can't find search result with title \"" + title + "\" and substring \"" + substring + "\"!",
                10);}
                else {
            String normalized_name = title + " " + substring;
            String[] title_and_substring_to_find = {title,substring};
            String[] title_and_substring_actual = this.splitIOSArticleNameIntoTitleAndSubstring(
                    this.waitForElementPresent(
                            "xpath://*[normalize-space(@name)='"+ normalized_name +"']",
                            "Can't find article with '" + normalized_name + "' string in name!",
                            10).getAttribute("name"));
            //String[] title_and_substring_actual = this.splitIOSArticleNameIntoTitleAndSubstring(driver.findElement(By.xpath("//*[normalize-space(@name)='"+ normalized_name +"']")).getAttribute("name"));
            Assert.assertArrayEquals("Can't find search result with exact title \"" + title + "\" and substring \"" + substring + "\"!",
                    title_and_substring_to_find,title_and_substring_actual);
        }
    }*/

    //Для iOS сделал просто нормализацию символа '\n', т.к. и тайтл и подзаголовок статьи лежат в одном атрибуте @name, но разделены новой строкой.
    public void waitForElementByTitleAndDescription(String title, String substring) {
        if(Platform.getInstance().isAndroid()){
            this.waitForElementPresent(getResultSearchElementByTitleAndSubstringAndroid(title,substring),
                    "Can't find search result with title \"" + title + "\" and substring \"" + substring + "\"!",
                    10);}
        else {
            String name_to_normalize = title + " " + substring;
            this.waitForElementPresent(
                    "xpath://*[normalize-space(@name)='"+ name_to_normalize +"']",
                    "Can't find article with title \"" + title + "\" and substring \"" + substring + "\"!",
                    10);
        }
    }

    public void waitForCancelButtonToAppear () {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,"Can't find cancel button!",5);
    }

    public void waitForCancelButtonToDissappear () {
        this.waitForElementIsNotPresent(SEARCH_CANCEL_BUTTON,"Cancel button is still present!", 10);
    }

    public void clickCancelButton () {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Can't click on cancel button!", 5);
    }

    public void getAmountOfFoundArticles () {
        String search_result_locator = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*{@resource-id='org.wikipedia:id/page_list_item_container']";
        this.waitForElementPresent(
                search_result_locator,
                "Can't find anything by the request",
                15
        );
    }

    public void checkSearchResultsListForFewArticles() {
        this.checkForFewSearchResultsArePresented();
    }

    public void checkSearchResultsListIsNotPresent () {

        this.checkForSearchResultAreEmpty();
    }

    public void clearSearchInput() {
        this.waitForElementAndClear(
                SEARCH_INPUT_WITH_TEXT,
                "Can't clear search button!",
                5
        );
    }

    public void clickCLearSearchInputButton() {
        this.waitForElementAndClick(
                CLEAR_SEARCH_INPUT_BTN,
                "Can't click on clear search field button!",
                10
        );
    }

    public String[] splitIOSArticleNameIntoTitleAndSubstring (String article_name) {
        String[] explored_name = article_name.split(Pattern.quote("\n"),2);
        return explored_name;
    }
}
