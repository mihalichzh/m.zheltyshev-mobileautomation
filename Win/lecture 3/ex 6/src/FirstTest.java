import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUP() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformNamee", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "C:\\Users\\mihal\\Documents\\GitHub\\m.zheltyshev-mobileautomation\\Win\\Base\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void  ex6() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find Search Input!",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't fill in Search Input!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Can't find Entry!",
                10
        );

        assertElementPresent(
             By.id("org.wikipedia:id/view_page_title_text")
        );

    }


    private WebElement waitForElementPresent(By by, String error_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_msg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_msg, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_msg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementIsNotPresent(By by, String error_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_msg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_msg, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement checkForTextSearchAndSendKeys(By by, String value, String error_msg, long timeoutInSeconds) {
        WebElement search_field_element = waitForElementPresent(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Can't fill in Search Input!",
                5
        );
        Assert.assertEquals(
                "Text in Search Field before sending keys is not expected",
                "Search…",
                search_field_element.getAttribute("text")
        );
        return waitForElementAndSendKeys(by, value, error_msg, timeoutInSeconds);
    }

    private List<WebElement> getListOfSearchResultTitles() {
        waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "No search result titles available!",
                5
        );
        return driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
    }

    private void checkTitles (List<WebElement> results, String value) {
        for (WebElement element : results) {
            String text = element.getAttribute("text");
            Assert.assertTrue("Title doesn't contain search que text!",
                    text.toLowerCase().contains(value.toLowerCase()));
        }
    }

    private void swipeUp (int timeOfSwipe) {

        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    protected void swipeUpQuick () {

        swipeUp(200);
    }

    protected void swipeUpToFindElement (By by, String error_message, int max_swipes) {

        int already_swiped = 0;


        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Can't find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            already_swiped++;
        }
    }

    protected void swipeElementToLeft (By by, String error_message) {

        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = element.getSize().getWidth() + left_x;
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).waitAction(250).moveTo(left_x,middle_y).perform();

    }

    protected void assertElementPresent (By by) {
        Assert.assertTrue("Can't find article's title!", driver.findElements(by).size() == 1);
    }
}
