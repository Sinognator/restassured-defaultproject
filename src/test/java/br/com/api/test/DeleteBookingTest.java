package br.com.api.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.api.model.Booking;
import br.com.api.model.BookingResponse;
import br.com.api.utils.AuthUtil;
import br.com.api.utils.BookingBuilder;
import br.com.api.utils.BookingClient;
import io.restassured.RestAssured;

public class DeleteBookingTest {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void updateBooking(){
        String token = AuthUtil.gerarToken();
        System.out.println("token gerado =" + token);
        
        Booking reserva = BookingBuilder.build();
        BookingResponse response = BookingClient.criarReserva(reserva);

        int id = response.bookingid;
        BookingClient.deletarReserva(id, token);
        BookingClient.validaDelecaoReserva(id, token);
        
    }
}
