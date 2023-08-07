package in.reqres;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

class UsersListTests extends TestBase{

    @Test
    void checkTotalUsers() {
        get("users?page=2")
                .then()
                .body("total", is(12));
    }
    @Test
    void checkGETWithLogs() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }


    @Test
    void checkPOSTWithLogs() {
        String authData = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }"; // BAD PRACTICE
        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);
    }

    @Test
    void checkPUTWithLogs() {
        String putData = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}"; // BAD PRACTICE
        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(putData)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"));
    }

    @Test
    void checkPATCHWithLogs() {
        String putData = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}"; // BAD PRACTICE
        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(putData)
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"));
    }

    @Test
    void checkDELETEWithLogs() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .delete("users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
