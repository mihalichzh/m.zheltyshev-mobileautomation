package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
    TO_MY_LIST_BUTTON = "//android.widget.FrameLayout[@content-desc='My lists']",
    ARTICLE_ITEM = "org.wikipedia:id/item_container";

    public NavigationUI (AppiumDriver driver) {
        super(driver);
    }

    public void openMyList () {
        this.waitForElementAndClick(
                By.xpath(TO_MY_LIST_BUTTON),
                "Can't find 'To my lists' button!",
                5
        );
    }

    public void checkFolderIsCreated () {
        this.waitForElementAndClick(
                By.id(ARTICLE_ITEM),
                "Can't find created folder!",
                5
        );
    }
}
