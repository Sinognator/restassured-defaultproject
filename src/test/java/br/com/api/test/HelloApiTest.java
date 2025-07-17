package br.com.api.test;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HelloApiTest {

    @Test
    public void validarListaDePosts() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
            .log().all()
        .when()
            .get("/posts")
        .then()
            .log().body()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].title", not(emptyString()));
    }
}
