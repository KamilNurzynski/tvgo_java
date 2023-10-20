

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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
//
//
//    @Test
//    public void openApp() {
//        System.out.println("open app test");
//        //how to chck status 200 code????????
//    }
//
//    @Test
//    public void verifyIfAllComponentsLoaded() {
//        //what is complete app?
//    }

    //    @Test
//    public void countBanners() throws InterruptedException {
//        WebElement dismiss_button = driver.findElementById("com.orange.pl.orangetvgo:id/menu_login");
//        dismiss_button.click();
//        WebElement agreement_checkbox = driver.findElementById("com.orange.pl.orangetvgo:id/welcome_analytics_checkbox");
//        agreement_checkbox.click();
//        WebElement lets_start = driver.findElementById("com.orange.pl.orangetvgo:id/welcome_btn_start");
//        lets_start.click();
//        TouchAction touchAction = new TouchAction(driver);
//        WebDriver.Window device_size = driver.manage().window();
//        float screenWidth = device_size.getSize().width;
//        float screenHeight = device_size.getSize().height;
//
//
//        //////Swipe right to left//////
//        int startx = (int) screenWidth * 8 / 9;
//        int endx = (int) screenWidth / 9;
//        int starty = 670;
//        int endy = 670;
//
//        ArrayList<String> titles = new ArrayList<String>();
//        int offset = 0;
//        boolean flag = true;
//        while (flag) {
//            Thread.sleep(2000);
//            WebElement banner = driver.findElementByAndroidUIAutomator("resourceId(\"com.orange.pl.orangetvgo:id/banner_small\")");
//            //WebElement banner = driver.findElementById("com.orange.pl.orangetvgo:id/banner_small");
//            banner.click();
//            WebElement title_obj = driver.findElementById("com.orange.pl.orangetvgo:id/text_expanded");
//            if (!titles.contains(title_obj.getText())) {
//                titles.add(title_obj.getText());
//                offset += 1;
//                driver.pressKey(new KeyEvent(AndroidKey.BACK));
//                for (int i = 0; i < offset; i++) {
//                    Thread.sleep(2000);
//                    touchAction.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endx, endy)).release().perform();
//                }
//            } else {
//                flag = false;
//            }
//
//        }
//        System.out.println("In the app is: " + titles.size() + "banners.");
//
//    }
    @Test
    public void enterToFourthRecommendedMovies() throws InterruptedException {
        WebElement dismiss_button = driver.findElementById("com.orange.pl.orangetvgo:id/menu_login");
        dismiss_button.click();
        WebElement agreement_checkbox = driver.findElementById("com.orange.pl.orangetvgo:id/welcome_analytics_checkbox");
        agreement_checkbox.click();
        WebElement lets_start = driver.findElementById("com.orange.pl.orangetvgo:id/welcome_btn_start");
        lets_start.click();
        TouchAction touchAction = new TouchAction(driver);
        WebDriver.Window device_size = driver.manage().window();
        float screenWidth = device_size.getSize().width;
        float screenHeight = device_size.getSize().height;


        //////Swipe right to left//////
        int startx = (int) screenWidth * 8 / 9;
        int endx = (int) screenWidth / 9;
        int starty = 670;
        int endy = 670;

        ArrayList<String> titles = new ArrayList<String>();
        int offset = 0;
        boolean flag = true;
        while (flag) {
            Thread.sleep(2000);
            WebElement banner = driver.findElementByAndroidUIAutomator("resourceId(\"com.orange.pl.orangetvgo:id/banner_small\")");
            //WebElement banner = driver.findElementById("com.orange.pl.orangetvgo:id/banner_small");
            banner.click();
            WebElement title_obj = driver.findElementById("com.orange.pl.orangetvgo:id/text_expanded");
            if (!titles.contains(title_obj.getText())) {
                titles.add(title_obj.getText());
                offset += 1;
                driver.pressKey(new KeyEvent(AndroidKey.BACK));
                for (int i = 0; i < offset; i++) {
                    Thread.sleep(2000);
                    touchAction.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endx, endy)).release().perform();
                }
            } else {
                flag = false;
            }

        }
        System.out.println("In the app is: " + titles.size() + "banners.");

    }
}
