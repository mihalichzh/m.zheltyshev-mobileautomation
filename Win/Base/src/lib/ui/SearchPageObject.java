package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '{SUBSTRING}']",
        SEARCH_RESULTS_LIST = "org.wikipedia:id/search_results_list";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static  String getResultSearchElement (String substring) {
        return  SEARCH_RESULT_BY_SUBSTRING_TPL.replace ("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),"Can't find and click init search input!", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Can't find init search element after click!", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,"Can't type text to search input!", 5);
    }

    public void waitForSearchResult (String substring) {
        this.waitForElementPresent(By.xpath(getResultSearchElement(substring)), "Can't find search result!", 5);
    }

    public void clickByArticleWithSubstring (String substring) {
        this.waitForElementAndClick(By.xpath(getResultSearchElement(substring)), "Can't find and click search result!", 10);
    }

    public void waitForCancelButtonToAppear () {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),"Can't find cancel button!",5);
    }

    public void waitForCancelButtonToDissappear () {
        this.waitForElementIsNotPresent(By.id(SEARCH_CANCEL_BUTTON),"Cancel button is still present!", 10);
    }

    public void clickCancelButton () {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),"Can't click on cancel button!", 5);
    }

    public void getAmountOfFoundArticles () {
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*{@resource-id='org.wikipedia:id/page_list_item_container']";
        this.waitForElementPresent(
                By.xpath(search_result_locator),
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

}
