package lib.tests.iOS;

import lib.IOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTests extends IOSTestCase {

    @Test
    public void testPassThroughWelcome () {

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
