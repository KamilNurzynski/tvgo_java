

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestsOrangeTvGo {

    private AndroidDriver<MobileElement> driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        File app = new File("src/test/resources/apps/orange3-29.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel3XL");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

    }


    @Test
    public void openApp() {
        System.out.println("openapp test");
        //how to chck status 200 code????????
    }

    @Test
    public void verifyIfAllComponentsLoaded() {
        //what is complete app?
    }

    @Test
    public void countBanners() {
        //WebElement dismiss_button = driver
    }
}
