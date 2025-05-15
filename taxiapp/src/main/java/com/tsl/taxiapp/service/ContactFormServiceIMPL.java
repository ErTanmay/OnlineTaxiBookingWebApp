package com.tsl.taxiapp.service;

import com.tsl.taxiapp.dao.ContactFormDAO;
import com.tsl.taxiapp.model.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactFormServiceIMPL implements ContactFormService {

    private ContactFormDAO contactFormDAO;
    @Autowired
    public void setContactFormDAO(ContactFormDAO contactFormDAO) {
        this.contactFormDAO = contactFormDAO;
    }

    @Override
    public ContactForm saveContactForm(ContactForm contactForm) {
        return contactFormDAO.save(contactForm);
    }

    @Override
    public List<ContactForm> readAllContacts() {
        return contactFormDAO.findAll();
    }

    @Override
    public void deleteContact(int id) {
        contactFormDAO.deleteById(id);
    }
}
