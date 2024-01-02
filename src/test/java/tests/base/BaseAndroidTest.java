package tests.base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseAndroidTest {
    // interview code not valid
    private static AppiumDriverLocalService service;
    private static AppiumDriver driver;
    private static WebDriverWait wait;
    protected static String platformName;

    public static String getPlatformName() {
        return platformName;
    }

    public static void setPlatformName(String platformName) {
        BaseAndroidTest.platformName = platformName;
    }

    @BeforeAll
    public static void setUp() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        setPlatformName("Android");

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformVersion("14");
        options.setDeviceName("sdk_gphone64_x86_64");
        options.setPlatformName(getPlatformName());
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.setAutomationName("UiAutomator2");

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(30))
        ;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed

        waitAndClick((getDriver().findElement(AppiumBy.id("com.android.chrome:id/signin_fre_dismiss_button"))));

        waitAndClick(getDriver().findElement(AppiumBy.id("com.android.chrome:id/negative_button")));

        waitAndClick(AppiumBy.id("com.android.chrome:id/search_box_text"));

        waitAndSendKeys(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.android.chrome:id/url_bar\"]"),
                "google.com");

    }

    public static void waitAndClick(WebElement locator) {
        wait.until(ExpectedConditions.visibilityOf(locator)).click();
    }

    public static void waitAndClick(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }
    public String checkURL(By locator) {
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public static void waitAndSendKeys(By locator, String keys) {
        waitAndClick(locator);
        Actions actions = new Actions(driver);
        actions
                .sendKeys(keys)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
    @AfterEach
    public void navigateToMainPage(){
        waitAndClick(AppiumBy.xpath("//android.widget.Image[@text=\"Google\"]"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.Image[@resource-id=\"hplogo\"]")));
    }

    public static AppiumDriver getDriver() {
        return driver;
    }
}
