package br.com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.api.model.Booking;
import br.com.api.model.BookingResponse;
import br.com.api.utils.BookingBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BookingBuilderTest {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void criarReservaComBuilderDinamico() {
        Booking reserva = BookingBuilder.build();

        Response response = given()
            .contentType("application/json")
            .body(reserva)
            .log().all()
        .when()
            .post("/booking")
        .then()
            .log().body()
            .statusCode(200)
            .body("booking.firstname", equalTo(reserva.firstname))
            .extract().response();

        BookingResponse br = response.as(BookingResponse.class);
        int id = br.bookingid;

        System.out.println("Reserva criada -> ID: " + id + ", Nome: " + br.booking.firstname);

    }
}
