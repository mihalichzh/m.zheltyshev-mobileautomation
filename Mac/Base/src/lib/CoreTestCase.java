package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CoreTestCase extends TestCase {

    public AppiumDriver driver;
    public WebDriverWait waitd;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        waitd = new WebDriverWait(driver, 20);
        this.skipwelcomePageForIOSApp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        driver.quit();
    }

    private void skipwelcomePageForIOSApp () {
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.clickSkip();
    }
}
