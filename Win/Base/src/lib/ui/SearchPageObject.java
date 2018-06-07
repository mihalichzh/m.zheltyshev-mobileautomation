package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '{SUBSTRING}']";

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
}
