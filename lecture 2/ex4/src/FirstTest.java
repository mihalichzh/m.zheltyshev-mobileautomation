import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

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
                "C:\\Users\\mihal\\Documents\\GitHub\\m.zheltyshev-mobileautomation\\lecture 1\\ex1\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void ex2() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't click on Search Field!",
                5
        );

        //Далее идет функция, проверяющая наличие текста "Search..." в строке поиска перед вводом текста:
        checkForTextSearchAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't fill in Search Input!",
                5
        );

        String text_in_search_field = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't get text from Search Field!",
                5
        ).getAttribute("text");
        Assert.assertEquals(
                "Text in Search Field if unexpected!",
                "Java",
                text_in_search_field
        );
    }

    @Test
    public void ex3() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't click on Search Field!",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Test",
                "Can't fill in Search Input!",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search result list is not present!",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't click on X button!",
                5
        );

        waitForElementIsNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search result list is present!",
                5
        );

        Assert.assertNotEquals(
                "Search empty message is not displayed!",
                0,
                driver.findElements(By.id("org.wikipedia:id/search_empty_message")).size()
        );
    }

    @Test
    public void firstTest() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't click on Search Field!",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't fill in Search Input!",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Can't find the entry!",
                15
        );

        String search_query_text = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't get text from search field!",
                5
        ).getAttribute("text");

        Assert.assertEquals(
                "Text in search filed is not expected!",
                "Java",
                search_query_text
        );
    }

    @Test
    public void testCancelSearch() {
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

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't clear Input",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't click on X button!",
                5
        );
        waitForElementIsNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Button X is still presented!",
                5
        );

        WebElement search_field_on_main_page = waitForElementPresent(
                By.xpath("//*[contains(@resource-id, 'org.wikipedia:id/search_container')]//*[@index = '1']"),
                "Can't find Search Field!",
                10
        );
        Assert.assertEquals(
                "Can't get text from main page search field!",
                "Search Wikipedia",
                search_field_on_main_page.getAttribute("text")
        );
    }

    @Test
    public void testCompareArticleTitle() {
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
                5
        );

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title!",
                20
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "Title is Wrong!",
                "Java (programming language)",
                article_title
        );

    }


    private WebElement waitForElementPresent(By by, String error_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_msg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_msg) {
        return waitForElementPresent(by, error_msg, 5);
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
}
