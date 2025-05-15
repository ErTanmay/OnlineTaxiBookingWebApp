package com.tsl.taxiapp.service;

import com.tsl.taxiapp.model.ServiceForm;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ServiceFormService {

    public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws IOException;

    List<ServiceForm> readAllServices();

    void deleteService(int id);
}
