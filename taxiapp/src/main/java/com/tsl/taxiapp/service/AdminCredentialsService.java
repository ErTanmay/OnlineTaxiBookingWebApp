package com.tsl.taxiapp.service;

import org.springframework.stereotype.Service;

@Service
public interface AdminCredentialsService {

    public String checkAdminCredentials(String oldusername, String oldpassword);

    public String updateAdminCredentials(String oldusername, String newusername, String newpassword);
}
