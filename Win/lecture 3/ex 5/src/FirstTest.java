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
    private WebDriverWait waitd;

    @Before
    public void setUP() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformNamee", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "C:\\Users\\user\\Documents\\ex2-4\\apks\\org.wikipedia.apk");

        //Capability для установки ориентации экрана по умолчанию:
        //capabilities.setCapability("orientation", "LANDSCAPE");
        //capabilities.setCapability("orientation", "LANDSCAPE");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        waitd = new WebDriverWait(driver,20);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void ex5 () {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find Search Input for the first article!",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't fill in Search Input for the first article!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Can't find Entry for the first article!!",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find first article's title!",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find 'More options' button for the first article!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find 'Add to reading list' option for the first article!",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can't find 'GOT IT' overlay button for the first article!",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find input of 'Name of the list' field for the first article!",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Can't put text to 'Name of the list' field for the first article!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't press OK button for the first article!",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find X button for the first article!",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find Search Input for the second article!",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "C++",
                "Can't fill in Search Input for the second article!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'General purpose high-level programming language']"),
                "Can't find Entry for the second article!",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find second's article title!",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find 'More options' button for the second article!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find 'Add to reading list' option for the second article!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title'][@text = 'Learning programming']"),
                "Can't click on saved articles list for the second article!",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find X button for the second article!",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can't find 'To my lists' button!",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/item_container"),
                "Can't find created folder!",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='object-oriented programming language']"),
                "Can't find first saved article to swipe!"
        );

        waitForElementIsNotPresent(
                By.xpath("//org.wikipedia:id/item_title[@text='Learning programming']"),
                "Can't delete first saved article!",
                5
        );

        String title_in_list = waitForElementPresent(
                By.xpath("//*[@text='C++']"),
                "Can't find second's article entry in saved articles list!",
                5
        ).getAttribute("text");

        waitForElementAndClick (
                By.xpath("//*[@text='C++']"),
                "Can't click on second's article entry in saved articles list!",
                5
        );

        String title_on_articles_page = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find second article's title on the page!",
                10
        ).getAttribute("text");

        Assert.assertEquals("Title on the articles list and title on the second article's page are not equals!",
                title_in_list,
                title_on_articles_page);
    }


    private WebElement waitForElementPresent(By by, String error_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_msg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement
    waitForElementAndClick(By by, String error_msg, long timeoutInSeconds) {
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

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int middleX = (rightX + leftX) / 2;
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX, middleY)
                .waitAction(300)
                .moveTo(leftX,middleY)
                .release()
                .perform();

    }
}