package com.tsl.taxiapp.service;

import com.tsl.taxiapp.dao.ServiceFormDAO;
import com.tsl.taxiapp.model.ServiceForm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ServiceFormServiceIMPL implements ServiceFormService{

    private ServiceFormDAO serviceFormDAO;

    @Autowired
    public void setServiceFormDAO(ServiceFormDAO serviceFormDAO) {
        this.serviceFormDAO = serviceFormDAO;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws IOException {
        ServiceForm form = null;
        try{
            form = serviceFormDAO.save(serviceForm);

            if(form!=null){

                String path = "D:\\Workspaces\\IntelliJ Workspace\\taxiapp\\taxiapp\\src\\main\\resources\\static\\myserviceimg\\"+ multipartFile.getOriginalFilename();
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> " + path);

                byte[] bytes = multipartFile.getBytes();

                FileOutputStream fos = new FileOutputStream(path);
                fos.write(bytes);
            }

        } catch (Exception e) {
            form=null;
            throw e;
        }

        return form;
    }

    @Override
    public List<ServiceForm> readAllServices() {
        return serviceFormDAO.findAll();
    }

    @Override
    public void deleteService(int id) {
        serviceFormDAO.deleteById(id);
    }
}
