package tests.api;

import com.google.gson.JsonArray;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import tests.base.UserData;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

class UserTests extends UserData {

    @Test
    void testCreateUser() {

        Response response = request.given()
                .contentType("application/json")
                .body(new UserData())
                .when().log()
                .all()
                .post("/Account/v1/User");

        response.then().statusCode(201);
        response.prettyPrint();
        setUserId(response.then().extract().path("userID"));
        setUserName(response.then().extract().path("username"));
    }

    @Test
    void testGenerateToken() {
        testCreateUser();
        Response response = request.given()
                .contentType("application/json")
                .body(Map.of(
                        "userName", getUserName(),
                        "password", getPassword()))
                .when().log()
                .all()
                .post("/Account/v1/GenerateToken");

        response.then().statusCode(200);
        response.prettyPrint();

        setToken(response.then().extract().path("token"));
    }

    @Test
    void testGetBookList() {
        Response response = request.given()
                .when().log()
                .all()
                .get("/BookStore/v1/Books");

        response.then().statusCode(200);
        response.prettyPrint();

        setBooksIsbn(response.then().extract().path("books.isbn"));
    }

    @Test
    void testAddBookListToUser() throws JSONException {
        testGenerateToken();
        testGetBookList();

        String jsonBody = "{ \"userId\": \"" + getUserId() + "\"" +
                ", \"collectionOfIsbns\": [";

        jsonBody += "{ \"isbn\": \"" + getBooksIsbn().get(0) + "\"}";

        jsonBody += ", { \"isbn\": \"" + getBooksIsbn().get(1) + "\"}";

        jsonBody += "] }";

        Response response = request.given()
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/BookStore/v1/Books");

        response.prettyPrint();

        response.then()
                .statusCode(201)
                .and()
                .body("books[0].isbn", equalTo(getBooksIsbn().get(0)))
                .and()
                .body("books[1].isbn", equalTo(getBooksIsbn().get(1)));
    }
}



