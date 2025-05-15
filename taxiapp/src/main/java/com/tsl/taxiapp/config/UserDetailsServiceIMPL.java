package com.tsl.taxiapp.config;

import com.tsl.taxiapp.dao.AdminDAO;
import com.tsl.taxiapp.model.Admin;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceIMPL implements UserDetailsService {

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

    @PostConstruct
    public void init() {

        long count = adminDAO.count();
        if (count == 0) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            adminDAO.save(admin);
        }

    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> adminRecord = adminDAO.findByUsername(username);

        Admin admin = adminRecord.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.withUsername(admin.getUsername()).password(admin.getPassword()).build();

    }
}
