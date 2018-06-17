package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

import lib.Platform;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_msg, long timeoutInSeconds) {
        System.out.println("Current locator is " + locator);
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_msg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }



    public WebElement waitForElementAndClick(String locator, String error_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_msg, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_msg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementIsNotPresent(String locator, String error_msg, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_msg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String error_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_msg, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement checkForTextSearchAndSendKeys(String locator, String value, String error_msg, long timeoutInSeconds) {
        WebElement search_field_element = waitForElementPresent(
                "xpath://*[contains(@text, 'Search…')]",
                "Can't fill in Search Input!",
                5
        );
        Assert.assertEquals(
                "Text in Search Field before sending keys is not expected",
                "Search…",
                search_field_element.getAttribute("text")
        );
        return waitForElementAndSendKeys(locator, value, error_msg, timeoutInSeconds);
    }

    public List<WebElement> getListOfSearchResultTitles() {
        waitForElementPresent(
                "id:org.wikipedia:id/page_list_item_title",
                "No search result titles available!",
                5
        );
        return driver.findElements(this.getLocatorByString("id:org.wikipedia:id/page_list_item_title"));
    }

    public void checkTitles (List<WebElement> results, String value) {
        for (WebElement element : results) {
            String text = element.getAttribute("text");
            Assert.assertTrue("Title doesn't contain search que text!",
                    text.toLowerCase().contains(value.toLowerCase()));
        }
    }

    public void swipeUp (int timeOfSwipe) {

        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    public void swipeUpQuick () {

        swipeUp(200);
    }

    public void swipeUpToFindElement (String locator, String error_message, int max_swipes) {

        int already_swiped = 0;


        while (driver.findElements(this.getLocatorByString(locator)).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Can't find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            already_swiped++;
        }
    }

    public void swipeUpTillElementAppear (String locator, String error_message, int max_swipes) {
        int already_swiped = 0;

        while(!this.isElementLocatedOnTheScreen(locator)) {
            if(already_swiped > max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator){

        int element_location_by_y = this.waitForElementPresent(
                locator,
                "Can't find element by locator!",
                5
        ).getLocation().getY();

        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void clickElementToTheRightUpperCorner(String locator, String error_message) {
        WebElement element = this.waitForElementPresent(locator + "/..",error_message,5);
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction(driver);
        action.tap(point_to_click_x, point_to_click_y).perform();

    }

    public void swipeElementToLeft (String locator, String error_message) {

        WebElement element = waitForElementPresent(locator, error_message, 10);

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int middleX = (rightX + leftX) / 2;
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(rightX, middleY);
        action.waitAction(300);

        if (Platform.getInstance().isAndroid()){
            action.moveTo(leftX,middleY);
        } else {
            int offset_x = (-1 * element.getSize().getWidth());
            action.moveTo(offset_x,0);
        }
        action.release();
        action.perform();

    }

    public void checkForFewSearchResultsArePresented() {
        int numberOfSearchResults = driver.findElements(By.id("org.wikipedia:id/page_list_item_container")).size();
        Assert.assertTrue("Number of search results is less than a few!",numberOfSearchResults >=2 );
    }

    public void checkForSearchResultAreEmpty() {
        waitForElementIsNotPresent(
                "id:org.wikipedia:id/search_results_list",
                "Search result list is present!",
                5
        );
    }

    protected void assertElementPresent (String locator) {
        Assert.assertTrue("Can't find article's title!", driver.findElements(this.getLocatorByString(locator)).size() >= 1);
    }

    public By getLocatorByString (String locator_with_type) {
        String[] explored_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = explored_locator[0];
        String locator = explored_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("name")) {
            return By.name(locator);
        } else {
            throw new IllegalMonitorStateException("Cannot get type of locator " + locator_with_type);
        }
    }
}
