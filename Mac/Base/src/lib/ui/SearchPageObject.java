package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    private static String getResultSearchElementByTitleAndSubstring (String title, String substring) {
        return SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL.replace("{TITLE}", title).replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        try {
            WebElement search_input = this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Can't find and click init search input!", 5);
            search_input.clear();
            Thread.sleep(2000);
            } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,"Can't type text to search input!", 5);
    }

    public void waitForSearchResult (String substring) {
        this.waitForElementPresent(getResultSearchElement(substring), "Can't find search result!", 5);
    }

    public void clickByArticleWithSubstring (String substring) {
        this.waitForElementAndClick(getResultSearchElement(substring),
                "Can't find and click search result by substring!", 10);
    }

    //Метод клика по элементу с локатором с указанным title и substring
    public void waitForElementByTitleAndDescription(String title, String substring) {
        this.waitForElementPresent(getResultSearchElementByTitleAndSubstring(title,substring),
                "Can't find search result with title \"" + title + "\" and substring \"" + substring + "\"!",
                10);
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
}
