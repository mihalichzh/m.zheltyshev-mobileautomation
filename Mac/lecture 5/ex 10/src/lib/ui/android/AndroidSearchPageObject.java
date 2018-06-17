package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '{SUBSTRING}']";

        //Темплейт локатора, который находит результат поиска одновременно по заголовку и описанию
        SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL = "xpath://*[android.widget.TextView[@index=0 and @text='{TITLE}'] and android.widget.TextView[@index=1 and @text='{SUBSTRING}']]";
    }
}
