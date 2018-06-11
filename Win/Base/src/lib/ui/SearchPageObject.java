package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '{SUBSTRING}']",

        //Темплейт локатора, который находит результат поиска одновременно по заголовку и описанию
        SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL = "//*[android.widget.TextView[@index=0 and @text='{TITLE}'] and android.widget.TextView[@index=1 and @text='{SUBSTRING}']]";
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
        this.waitForElementAndClick(By.xpath(getResultSearchElement(substring)),
                "Can't find and click search result by substring!", 10);
    }

    //Метод клика по элементу с локатором с указанным title и substring
    public void waitForElementByTitleAndDescription(String title, String substring) {
        this.waitForElementPresent(By.xpath(getResultSearchElementByTitleAndSubstring(title,substring)),
                "Can't find search result with title \"" + title + "\" and substring \"" + substring + "\"!",
                10);
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
