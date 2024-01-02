package tests.android.pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.base.BaseFactoryAndroidTest;

public class RegistrationPage extends BaseFactoryAndroidTest {

    private AppiumDriver driver;
    private WebDriverWait wait;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='firstname']")
    private WebElement firstNameInput;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='lastname']")
    private WebElement lastNameInput;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='userName']")
    private WebElement userNameInput;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='password']")
    private WebElement passwordInput;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement firstCheckBox;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='register']")
    private WebElement registerButton;

    @AndroidFindBy(id = "com.android.chrome:id/positive_button")
    private WebElement positiveButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='gotologin']")
    private WebElement goToLoginButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='login']")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='submit' and @text='Log out']")
    private WebElement logoutButton;

    public RegistrationPage(AppiumDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    public void fillRegistrationForm(String firstName, String lastName, String userName, String password) {
        Actions action = new Actions(driver);
        action.clickAndHold(lastNameInput).moveToElement(firstNameInput);
        action.perform();
        action.perform();

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
    }

    public void checkAndRegister() {
        try {
            firstCheckBox.click();
            Thread.sleep(3000);
            submitRegistration();
        } catch (Exception ignored) {
        }
    }

    public void clearAndEnterNewPassword(String newPassword) {
        passwordInput.clear();
        passwordInput.sendKeys(newPassword);
    }

    public void submitRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public void confirmAction() {
        positiveButton.click();
    }

    public void goToLogin() {
        goToLoginButton.click();
    }

    public void login(String userName, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(userNameInput)).sendKeys(userName);
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void logout() {
        logoutButton.click();
    }
    public boolean logoutIsVisible() {
            try {
                return logoutButton.isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
        }
}
