package br.com.api.utils;

import com.github.javafaker.Faker;
import br.com.api.model.Booking;
import br.com.api.model.BookingDates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingBuilder {
    private static final Faker faker = new Faker();

    public static Booking build() {
        Booking booking = new Booking();
        booking.firstname = faker.name().firstName();
        booking.lastname = faker.name().lastName();

        booking.totalprice = faker.number().numberBetween(50, 500);
        booking.depositpaid = faker.bool().bool();

        // Datas: hoje e daqui 7 dias
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate later = today.plusDays(7);

        BookingDates bd = new BookingDates();
        bd.checkin = fmt.format(today);
        bd.checkout = fmt.format(later);
        booking.bookingdates = bd;

        booking.additionalneeds = faker.options().option("Breakfast", "Lunch", "Dinner", "None");

        return booking;
    }
}
