package tests.base;

import io.restassured.config.DecoderConfig;
import io.restassured.config.SSLConfig;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

public class UserData {

    public static RequestSpecification request =null;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        UserData.token = token;
    }

    public static String token;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        UserData.userId = userId;
    }

    public static String userId;

    public static List<String> getBooksIsbn() {
        return booksIsbn;
    }

    public static void setBooksIsbn(List<String> booksIsbn) {
        UserData.booksIsbn = booksIsbn;
    }

    public static List<String> booksIsbn;

    @BeforeAll
    static void setup() {
        request = io.restassured.RestAssured.given();
        request
                .config(io.restassured.RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()))
                .config(io.restassured.RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                .config(io.restassured.RestAssured.config().decoderConfig((DecoderConfig.decoderConfig().defaultContentCharset("UTF-8"))));
        request.baseUri("https://demoqa.com/");
    }

    private String userName;
    private String password;

    public UserData() {
        this.userName = getUserName();
        this.password = "randomPass123!";

    }
    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }
    public String generateRandomNameString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }
    public String getUserName() {
        if (userName==null){
            this.userName=generateRandomString(8);
        }
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}