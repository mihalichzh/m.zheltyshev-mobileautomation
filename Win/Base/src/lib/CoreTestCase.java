package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    protected WebDriverWait waitd;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    public void setUP() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformNamee", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "C:\\Users\\mihal\\Documents\\GitHub\\m.zheltyshev-mobileautomation\\Win\\Base\\apks\\org.wikipedia.apk");

        //Capability для установки ориентации экрана по умолчанию:
        //capabilities.setCapability("orientation", "LANDSCAPE");
        //capabilities.setCapability("orientation", "LANDSCAPE");

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        waitd = new WebDriverWait(driver,20);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        driver.quit();
    }

}
