package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    NEXT_BTN = "id:Next",
    STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERED_LANGUAGES = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "id:Learn more about data collected",
    SKIP = "id:Skip",
    GET_STARTED_BTN = "id:Get started";


    public WelcomePageObject (AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK,
                "Can't find 'Learn More' link!",
                5
        );
    }

    public void clickNext() {
        this.waitForElementAndClick(
                NEXT_BTN,
                "Can't click on 'Next' button!",
                5
        );
    }

    public void waitForNewWayToExploreTest() {
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE,
                "Can't find 'New Ways to Explore' text!",
                5
        );
    }

    public void waitForAddOrEditPreferedLanguage() {
        this.waitForElementPresent(
                STEP_ADD_OR_EDIT_PREFERED_LANGUAGES,
                "Can't find 'Add or edit preferred languages' link!",
                5
        );
    }

    public void waitForLearnMoreAboutDataCollected() {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,
                "Can't find 'Learn more about data collected' link!",
                5
        );
    }

    public void clickGetStarted () {
        this.waitForElementPresent(
                GET_STARTED_BTN,
                "Can't click on 'Get started' button!",
                5
        );
    }

    public void clickSkip() {
        this.waitForElementAndClick(
                SKIP,
                "Can't skip welcome screen!",
                5
        );
    }

}
