package br.com.api.utils;

import static io.restassured.RestAssured.*;

import br.com.api.model.AuthRequest;

public class AuthUtil {
    public static String gerarToken() {

        AuthRequest body = new AuthRequest("admin", "password123");

        return given()
            .contentType("application/json")
            .body(body)
        .when()
            .post("/auth")
        .then()
            .statusCode(200)
            .extract()
            .path("token");
    }
}
