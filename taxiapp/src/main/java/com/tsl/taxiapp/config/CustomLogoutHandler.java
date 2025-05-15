package com.tsl.taxiapp.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {

            ServletContext context = request.getServletContext();
            context.setAttribute("logout",true);

            response.sendRedirect("admin/dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
