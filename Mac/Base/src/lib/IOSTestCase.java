package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class IOSTestCase extends TestCase {

    public AppiumDriver driver;
    public WebDriverWait waitd;
    public static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.4");
        capabilities.setCapability("app",
                "/Users/mike/Documents/GitHub/m.zheltyshev-mobileautomation/Mac/Base/apks/Wikipedia.app");

        //Capability для установки ориентации экрана по умолчанию:
        //capabilities.setCapability("orientation", "LANDSCAPE");
        //capabilities.setCapability("orientation", "LANDSCAPE");

        driver = new IOSDriver(new URL(AppiumURL), capabilities);
        waitd = new WebDriverWait(driver,20);
    }

    @Override
    public void tearDown() throws Exception {
        //super.tearDown();
        driver.quit();
    }
}
