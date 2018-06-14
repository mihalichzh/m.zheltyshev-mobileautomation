package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
            TITLE = "id:org.wikipedia:id/view_page_title_text";
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
            GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
            LIST_NAME_INPUT_FIELD = "id:org.wikipedia:id/text_input";
            SAVE_TO_MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
            EXISTING_LIST_NAME_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/item_title'][@text = '{LIST NAME}']";
    }

    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
