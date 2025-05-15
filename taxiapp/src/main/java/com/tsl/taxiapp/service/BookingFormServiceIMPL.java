package com.tsl.taxiapp.service;

import com.tsl.taxiapp.dao.BookingFormDAO;
import com.tsl.taxiapp.model.BookingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingFormServiceIMPL implements BookingFormService {

    private BookingFormDAO bookingFormDAO;

    @Autowired
    public void setBookingFormDAO(BookingFormDAO bookingFormDAO) {
        this.bookingFormDAO = bookingFormDAO;
    }

    @Override
    public BookingForm saveBookingForm(BookingForm bookingForm) {
        return bookingFormDAO.save(bookingForm);

    }

    @Override
    public List<BookingForm> readAllBookings() {
        return bookingFormDAO.findAll();
    }

    @Override
    public void deleteBooking(int id) {
        bookingFormDAO.deleteById(id);
    }

}
