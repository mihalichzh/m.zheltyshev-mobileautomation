package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {

    protected static String
    /*TO_MY_LIST_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='My lists']",
    ARTICLE_ITEM = "id:org.wikipedia:id/item_container";*/

    TO_MY_LIST_BUTTON,
    ARTICLE_ITEM;

    public NavigationUI (AppiumDriver driver) {
        super(driver);
    }

    public void openMyList () {
        this.waitForElementAndClick(
                 TO_MY_LIST_BUTTON,
                "Can't find 'To my lists' button!",
                5
        );
    }

    public void checkFolderIsCreated () {
        this.waitForElementAndClick(
                 ARTICLE_ITEM,
                "Can't find created folder!",
                5
        );
    }
}
