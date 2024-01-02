package tests.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.android.pageObject.ChromePage;
import tests.android.pageObject.RegistrationPage;

import java.time.Duration;

public class BaseFactoryAndroidTest {
    private static AppiumDriverLocalService service;
    private static AppiumDriver driver;
    private static WebDriverWait wait;
    protected static String platformName;
    protected String baseURL="https://demoqa.com";

    // Page objects

    protected static ChromePage chromePage;
    protected static RegistrationPage registrationPage;

    protected UserData userData= new UserData();;
    public static String getPlatformName() {
        return platformName;
    }

    public static void setPlatformName() {
        BaseFactoryAndroidTest.platformName = System.getProperty("device", "Android");
        ;
    }

    @BeforeAll
    public static void setUp() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        setPlatformName();
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


        // Initialize page objects
        chromePage = new ChromePage(driver, wait);
        registrationPage = new RegistrationPage(driver, wait);

        PageFactory.initElements(new AppiumFieldDecorator(driver), chromePage);
        PageFactory.initElements(new AppiumFieldDecorator(driver), registrationPage);

        chromePage.dismissSignIn();
        chromePage.clickNegativeButton();
        chromePage.clickSearchBox();
        chromePage.enterUrlAndSearch("https://demoqa.com/");
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
    public void navigateToMainPage() {
        chromePage.enterUrlAndSearch("https://demoqa.com/");

    }
}
