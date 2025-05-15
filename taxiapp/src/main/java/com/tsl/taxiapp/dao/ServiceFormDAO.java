package com.tsl.taxiapp.dao;

import com.tsl.taxiapp.model.ServiceForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceFormDAO extends JpaRepository<ServiceForm, Integer> {

}
