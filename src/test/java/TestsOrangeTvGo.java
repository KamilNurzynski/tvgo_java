import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.*;
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

        WebElement dismissButton = driver.findElementById("com.orange.pl.orangetvgo:id/menu_login");
        dismissButton.click();
        WebElement agreementCheckbox = driver.findElementById("com.orange.pl.orangetvgo:id/welcome_analytics_checkbox");
        agreementCheckbox.click();
        WebElement letsStart = driver.findElementById("com.orange.pl.orangetvgo:id/welcome_btn_start");
        letsStart.click();


    }

    @Test(priority = 1)
    public void openApp() {
        driver.quit();
    }

    @Test(priority = 2)
    public void verifyIfAllComponentsLoaded() {
        Assert.assertTrue(exist(driver, MobileBy.className("android.widget.FrameLayout")),
                "Layout doesn't exist");
        Assert.assertTrue(exist(driver, MobileBy.id("com.orange.pl.orangetvgo:id/main_content_root")),
                "Main content root doesn't exist");
        Assert.assertTrue(exist(driver, MobileBy.id("com.orange.pl.orangetvgo:id/content")),
                "Content doesn't exist");
        Assert.assertTrue(exist(driver, MobileBy.id("com.orange.pl.orangetvgo:id/main_btn_bar_frame")),
                "Bottom menu bar doesn't exist");
        Assert.assertTrue(exist(driver, MobileBy.id("com.orange.pl.orangetvgo:id/toolbar")),
                "Toolbar doesn't exist");
        Assert.assertTrue(exist(driver, MobileBy.id("com.orange.pl.orangetvgo:id/banner_small")),
                "Banner doesn't exist");


        driver.quit();
    }

    @Test(priority = 3)
    public void countBanners() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        ArrayList<String> titles = new ArrayList<>();
        int offset = 0;
        boolean flag = true;
        while (flag) {
            Thread.sleep(2000);
            WebElement banner = driver.findElement(MobileBy.id("com.orange.pl.orangetvgo:id/banner_small"));

            //////Swipe right to left//////
            int startX = (int) (banner.getLocation().x + banner.getSize().getWidth()) * 8 / 9;
            int endX = (int) (banner.getLocation().x + banner.getSize().getWidth()) / 9;
            int startY = (int) (banner.getLocation().y + banner.getSize().height) / 2;
            int endY = (int) (banner.getLocation().y + banner.getSize().height) / 2;


            banner.click();
            WebElement titleObj = driver.findElementById("com.orange.pl.orangetvgo:id/text_expanded");
            if (!titles.contains(titleObj.getText())) {
                titles.add(titleObj.getText());
                offset += 1;
                driver.pressKey(new KeyEvent(AndroidKey.BACK));
                for (int i = 0; i < offset; i++) {
                    Thread.sleep(2000);
                    touchAction.press(PointOption.point(startX, startY)).waitAction(
                                    WaitOptions.waitOptions(Duration.ofSeconds(2)))
                            .moveTo(PointOption.point(endX, endY)).release().perform();
                }
            } else {
                flag = false;
            }

        }
        System.out.println("In the app is: " + titles.size() + " banners.");
        driver.quit();
    }


    @Test(priority = 4)
    public void enterToFourthRecommendedMovie() throws InterruptedException {
        Assert.assertTrue(exist(driver, MobileBy.xpath("//android.view.ViewGroup[2]/androidx." +
                        "recyclerview.widget.RecyclerView/androidx.appcompat.widget." +
                        "LinearLayoutCompat[4]/android.widget.ImageView")),
                "Fourth movie doesn't exist");
        WebElement fourthMovieImage = driver.findElementByXPath("//android.view.ViewGroup[2]/androidx." +
                "recyclerview.widget.RecyclerView/androidx.appcompat.widget." +
                "LinearLayoutCompat[4]/android.widget.ImageView");
        fourthMovieImage.click();

        driver.quit();
    }

    @Test(priority = 5)
    public void collectCastFromFourthMovie() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);

        Assert.assertTrue(exist(driver, MobileBy.xpath("//android.view.ViewGroup[2]/androidx." +
                        "recyclerview.widget.RecyclerView/androidx.appcompat.widget." +
                        "LinearLayoutCompat[4]/android.widget.ImageView")),
                "Fourth movie doesn't exist");
        WebElement fourthMovieImage = driver.findElementByXPath("//android.view.ViewGroup[2]/androidx." +
                "recyclerview.widget.RecyclerView/androidx.appcompat.widget." +
                "LinearLayoutCompat[4]/android.widget.ImageView");
        fourthMovieImage.click();

        //Scroll to Cast view
        Assert.assertTrue(exist(driver, new MobileBy.ByAndroidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Obsada\"))")),
                "Cast(label) not added to movie.");

        driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Obsada\"))");

        Assert.assertTrue(exist(driver, MobileBy.id("com.orange.pl.orangetvgo:id/actors_content")),
                "Cast not added to movie.");
        WebElement castBar = driver.findElementById("com.orange.pl.orangetvgo:id/actors_content");


        //////Swipe right to left//////
        int startX = (int) castBar.getSize().width * 7 / 8;
        int endX = (int) (castBar.getLocation().x + castBar.getSize().width) / 8;
        int startY = (int) (castBar.getLocation().y + castBar.getSize().height / 2);
        int endY = (int) (castBar.getLocation().y + castBar.getSize().height / 2);

        ArrayList<String> actors = new ArrayList<String>();
        int i = 0;
        while (i < 15) {
            List<MobileElement> cast = driver.findElementsById("com.orange.pl.orangetvgo:id/actor_name");
            for (MobileElement record : cast) {
                actors.add(record.getText());
            }
            touchAction.press(PointOption.point(startX, startY)).waitAction(
                            WaitOptions.waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point(endX, endY)).release().perform();

            i += 1;
        }
        Set<String> castNewSet = new HashSet<>(actors);
        List<String[]> pairs = new ArrayList<>();
        for (String el : castNewSet) {
            pairs.add(el.split(" "));
        }
        pairs.sort(Comparator.comparing(name -> name[1]));
        List<String> sortedNames = new ArrayList<>();
        for (String[] name : pairs) {
            sortedNames.add(name[0] + " " + name[1]);
        }
        System.out.println(sortedNames);

        driver.quit();
    }


    public static boolean exist(AndroidDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
