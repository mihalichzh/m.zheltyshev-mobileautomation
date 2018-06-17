package lib.tests.iOS;

import lib.CoreTestCase;

import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTests extends CoreTestCase {

    @Test
    public void testPassThroughWelcome () {

        if(Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNext();
        welcomePageObject.waitForNewWayToExploreTest();
        welcomePageObject.clickNext();
        welcomePageObject.waitForAddOrEditPreferedLanguage();
        welcomePageObject.clickNext();
        welcomePageObject.waitForLearnMoreAboutDataCollected();
        welcomePageObject.clickGetStarted();

    }
}
