package com.tsl.taxiapp.controller;

import com.tsl.taxiapp.model.BookingForm;
import com.tsl.taxiapp.model.ContactForm;
import com.tsl.taxiapp.model.ServiceForm;
import com.tsl.taxiapp.service.BookingFormService;
import com.tsl.taxiapp.service.ContactFormService;
import com.tsl.taxiapp.service.ContactFormServiceIMPL;
import com.tsl.taxiapp.service.ServiceFormService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class Mycontroller {


    private ContactFormService contactFormService;
    private BookingFormService bookingFormService;
    private ServiceFormService serviceFormService;

    @Autowired
    public void setServiceFormService(ServiceFormService serviceFormService) {
        this.serviceFormService = serviceFormService;
    }

    @Autowired
    public void setBookingFormService(BookingFormService bookingFormService) {
        this.bookingFormService = bookingFormService;
    }

    @Autowired
    public void setContactFormService(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }

    @GetMapping("login")
    public String adminLogin(HttpServletRequest request, Model m) {

        Object contextAttribute = request.getServletContext().getAttribute("logout");

        if(contextAttribute instanceof Boolean ){
            m.addAttribute("logout", contextAttribute);
            request.getServletContext().removeAttribute("logout");
        }

        return "/adminLogin";
    }

    @GetMapping(path ={"","welcome","home","index","Home"})
    public String welcomeView(Model m) {
        m.addAttribute("bookingForm", new BookingForm());
        return "Home";
    }

    @GetMapping("About")
    public String aboutView() {
        return "About";
    }

    @GetMapping("Cars")
    public String carsView() {
        return "Cars";
    }

    @GetMapping("Services")
    public String servicesView(Model m) {

        List<ServiceForm> allServices = serviceFormService.readAllServices();
        m.addAttribute("allServices",allServices);
        return "Services";
    }

    @GetMapping("Contacts")
    public String contactsView( Model m) {
        m.addAttribute("contactForm", new ContactForm());
        return "Contacts";
    }

    @PostMapping("contactform")
    public String contactForm(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult,
                              Model m, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            m.addAttribute("bindingResult", bindingResult);
            return "Contacts";
        }
        System.out.println(">>>>>>>>>>>>>>>>" + contactForm.toString());
        ContactForm contactform = contactFormService.saveContactForm(contactForm);
        if(contactform != null){
            redirectAttributes.addFlashAttribute("message", "Contact form submitted successfully!!!");

        }else{
            redirectAttributes.addFlashAttribute("message", "Something went wrong!!!");
        }
        return "redirect:/Contacts";
    }

    @PostMapping("bookingform")
    public String bookingForm(@Valid @ModelAttribute BookingForm bookingForm, BindingResult bindingResult,
                              Model m, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            m.addAttribute("bindingResult", bindingResult);
            return "Home";
        }else if((bookingForm.getAdult() + bookingForm.getChildren()) > 4){
            m.addAttribute("message", "Maximum 4 passengers are allowed");
            return "Home";
        }
        BookingForm bookingform = bookingFormService.saveBookingForm(bookingForm);
        if(bookingform != null){
            redirectAttributes.addFlashAttribute("message", "Booking form submitted successfully!!!");
        }else{
            redirectAttributes.addFlashAttribute("message", "Something went wrong!!!");
        }
        System.out.println(">>>>>>>>>>>>>>>>" + bookingForm.toString());
        return "redirect:/Home";
    }

}
