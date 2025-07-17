package br.com.api.test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.api.model.BookingResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateToken {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void generateTokenWithSuccess() {

        given()
                .contentType("application/json")
                .body("{ \"username\" : \"admin\", \"password\" : \"password123\" }")
                .log().all()
                .when()
                .post("/auth")
                .then()
                .log().body()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    public void CreateBooking(){    
        String bodyReserva = """
                {
                "firstname": "Paulo",
                "lastname": "Rocha",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2024-07-01",
                    "checkout": "2024-07-10"
                },
                "additionalneeds": "Breakfast"
                }
                """;
        Response responseBooking =
        given()
            .contentType("application/json")
            .body(bodyReserva)
            .log().all()
        .when()
            .post("/booking")
        .then()
            .log().body()
            .statusCode(200)
            .body("bookingid", notNullValue())
            .body("booking.firstname", equalTo("Paulo"))
            .body("booking.totalprice", equalTo(111))
            .extract().response();

        // 1️⃣ Extração simples com .path()
        int id = responseBooking.path("bookingid");
        System.out.println("Booking ID (path): " + id);

        // 2️⃣ Extração com .jsonPath()
        JsonPath jsonPath = responseBooking.jsonPath();
        String nome = jsonPath.getString("booking.firstname");
        String dataCheckout = jsonPath.getString("booking.bookingdates.checkout");

        System.out.println("Nome (jsonPath): " + nome);
        System.out.println("Checkout (jsonPath): " + dataCheckout);

        // 3️⃣ Mapeamento completo com .as(Class)
        BookingResponse reserva = responseBooking.as(BookingResponse.class);
        System.out.println("Nome (POJO): " + reserva.booking.firstname);
        System.out.println("Total (POJO): " + reserva.booking.totalprice);
    }
}
