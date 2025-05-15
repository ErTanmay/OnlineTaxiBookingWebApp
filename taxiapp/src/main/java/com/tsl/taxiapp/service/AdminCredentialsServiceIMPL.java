package com.tsl.taxiapp.service;

import com.tsl.taxiapp.dao.AdminDAO;
import com.tsl.taxiapp.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminCredentialsServiceIMPL implements AdminCredentialsService{

    private AdminDAO adminDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public String checkAdminCredentials(String oldusername, String oldpassword) {

        Optional<Admin> admin = adminDAO.findByUsername(oldusername);

        if(admin.isPresent()){

            Admin admin1 = admin.get();
            String password = admin1.getPassword();

            if(passwordEncoder.matches(oldpassword, password)){
                return "SUCCESS";
            }else{
                return "Wrong Credentials";
            }
        }else{
            return "Wrong Credentials";
        }
    }

    @Override
    public String updateAdminCredentials(String oldusername, String newusername, String newpassword) {
       int resultCount = adminDAO.updateCredentials(oldusername, newusername, passwordEncoder.encode(newpassword));

       if(resultCount==1){
           return "SUCCESS";
       }else{
           return "FAILURE";
       }
    }
}
