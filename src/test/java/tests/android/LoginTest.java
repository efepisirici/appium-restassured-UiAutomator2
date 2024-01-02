package tests.android;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import tests.base.BaseAndroidTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class LoginTest extends BaseAndroidTest {
//old code which is looked in Technical Interview
    @ParameterizedTest
    @CsvSource({
            "haberler",
            "test"
    })
    public void launchAppTest(String question) {

        waitAndClick(AppiumBy.xpath("//android.widget.Button[@text=\"Google'da Ara\"]"));

        waitAndSendKeys(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.android.chrome:id/url_bar\"]"), question);

        assertTrue(checkURL(AppiumBy.id("com.android.chrome:id/url_bar")).contains("q=" + question));

    }

    @MethodSource("data")
    @ParameterizedTest
    public void launchAppTestMethodSource(String question) {

        waitAndClick(AppiumBy.xpath("//android.widget.Button[@text=\"Google'da Ara\"]"));

        waitAndSendKeys(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.android.chrome:id/url_bar\"]"), question);

        assertTrue(checkURL(AppiumBy.id("com.android.chrome:id/url_bar")).contains("q=" + question));

    }

    //Pipeline
    public static Stream<Arguments> data() {
        if (getPlatformName().equals("Android")) {
            return Stream.of(
                    Arguments.of("haberler")
            );
        } else if (getPlatformName().equals("io")) {
            return Stream.of(
                    Arguments.of("test")
            );
        } else {
            fail("Not Avaible to exec");
            return null;
        }
    }
}
