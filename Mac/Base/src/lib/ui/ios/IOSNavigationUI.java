package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {

    static {
        TO_MY_LIST_BUTTON = "id:Saved";
        //ARTICLE_ITEM = "id:org.wikipedia:id/item_container";
    }

    public IOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }


}
