import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class Test1 {

    private AppiumDriver driver;

    @Before
    public void setUP() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.4");
        capabilities.setCapability("app",
                "/Users/mike/Documents/GitHub/m.zheltyshev-mobileautomation/Mac/Base/apks/Wikipedia.app");

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() { driver.quit(); }

    @Test
    public void firstTest() { System.out.println("First test run"); }

}
