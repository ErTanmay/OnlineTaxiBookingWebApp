package com.tsl.taxiapp.service;

import com.tsl.taxiapp.model.ContactForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactFormService {

    public ContactForm saveContactForm(ContactForm contactForm) ;

    public List<ContactForm> readAllContacts() ;

    public void deleteContact(int id) ;
}
