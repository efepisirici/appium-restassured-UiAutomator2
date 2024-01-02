package tests.android.pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.base.BaseFactoryAndroidTest;

public class ChromePage  extends BaseFactoryAndroidTest {

    private AppiumDriver driver;
    private WebDriverWait wait;

    @AndroidFindBy(id = "com.android.chrome:id/signin_fre_dismiss_button")
    private WebElement dismissButton;

    @AndroidFindBy(id = "com.android.chrome:id/negative_button")
    private WebElement negativeButton;

    @AndroidFindBy(id = "com.android.chrome:id/search_box_text")
    private WebElement searchBox;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']")
    private WebElement urlBar;

    public ChromePage(AppiumDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void dismissSignIn() {
        wait.until(ExpectedConditions.visibilityOf(dismissButton)).click();
    }

    public void clickNegativeButton() {
        wait.until(ExpectedConditions.visibilityOf(negativeButton)).click();
    }

    public void clickSearchBox() {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).click();
    }

    public void enterUrlAndSearch(String url) {
        wait.until(ExpectedConditions.visibilityOf(urlBar)).click();
        Actions actions = new Actions(driver);
        actions
                .sendKeys(url)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    public void goRegisterPage() {
        enterUrlAndSearch(baseURL+"/register");
    }
    public void goLoginPage() {
        enterUrlAndSearch(baseURL+"/login");
    }
}
