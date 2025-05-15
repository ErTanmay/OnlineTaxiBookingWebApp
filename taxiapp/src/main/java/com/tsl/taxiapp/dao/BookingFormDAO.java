package com.tsl.taxiapp.dao;

import com.tsl.taxiapp.model.BookingForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingFormDAO extends JpaRepository<BookingForm, Integer> {

    @Override
    public <S extends BookingForm> S save(S entity);

    @Override
    public List<BookingForm> findAll();

    @Override
    public void deleteById(Integer integer);
}
