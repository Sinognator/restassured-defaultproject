package br.com.api.utils;

import static io.restassured.RestAssured.given;

import br.com.api.model.Booking;
import br.com.api.model.BookingResponse;

public class BookingClient {
    public static BookingResponse criarReserva(Booking reserva){
        return given()
                .contentType("application/json")
                .body(reserva)
            .when()
                .post("/booking")
            .then()
                .statusCode(200)
                .extract()
                .as(BookingResponse.class);
    }

    public static Booking atualizarReserva(int id, Booking reserva, String token){
        return given()
                .contentType("application/json")
                .accept("application/json")
                .cookie("token", token)
                .body(reserva)
            .when()
                .put("/booking/" + id)
            .then()
                .statusCode(200)
                .extract()
                .as(Booking.class);
    }

    public static void deletarReserva(int id, String token){
            given()
                .contentType("application/json")
                .accept("application/json")
                .cookie("token", token)
            .when()
                .delete("/booking/" + id)
            .then()
                .statusCode(201);
    }

    public static void validaDelecaoReserva(int id, String token){
         given()
                .contentType("application/json")
                .accept("application/json")
                .cookie("token", token)
            .when()
                .get("/booking/" + id)
            .then()
                .statusCode(404);
    }

    
}
