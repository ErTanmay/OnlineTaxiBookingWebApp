package com.tsl.taxiapp.service;

import com.tsl.taxiapp.model.BookingForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingFormService {

    public BookingForm saveBookingForm(BookingForm bookingForm);

    public List<BookingForm> readAllBookings();

    public void deleteBooking(int id);
}
