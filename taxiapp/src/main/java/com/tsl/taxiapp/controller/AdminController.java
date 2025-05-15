package com.tsl.taxiapp.controller;

import com.tsl.taxiapp.model.BookingForm;
import com.tsl.taxiapp.model.ContactForm;
import com.tsl.taxiapp.model.ServiceForm;
import com.tsl.taxiapp.service.AdminCredentialsService;
import com.tsl.taxiapp.service.BookingFormService;
import com.tsl.taxiapp.service.ContactFormService;
import com.tsl.taxiapp.service.ServiceFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    private ContactFormService contactFormService;
    public BookingFormService bookingFormService;
    public AdminCredentialsService adminCredentialsService;
    public ServiceFormService serviceFormService;

    @Autowired
    public void setServiceFormService(ServiceFormService serviceFormService) {
        this.serviceFormService = serviceFormService;
    }

    @Autowired
    public void setAdminCredentialsService(AdminCredentialsService adminCredentialsService) {
        this.adminCredentialsService = adminCredentialsService;
    }

    @Autowired
    public void setBookingFormService(BookingFormService bookingFormService) {
        this.bookingFormService = bookingFormService;
    }

    @Autowired
    public void setContactFormService(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }

    @GetMapping("dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("readallcontacts")
    public String readAllContacts(Model model) {

        List<ContactForm> allContactsList = contactFormService.readAllContacts();

        model.addAttribute("allcontacts", allContactsList);

        return "admin/readallcontacts";
    }

    @GetMapping("deletecontact/{id}")
    public String deleteContact(@PathVariable int id, RedirectAttributes redirectAttributes) {

        contactFormService.deleteContact(id);
        redirectAttributes.addFlashAttribute("message", "CONTACT DELETED SUCCESSFULLY!!!!!!");
        return "redirect:/admin/readallcontacts";
    }

    @GetMapping("changecredentials")
    public String changeCredentialsView() {
        return "admin/changecredentials";
    }

    @PostMapping("changecredentials")
    public String changeCredentials(
            @RequestParam("oldusername") String oldusername,
            @RequestParam("oldpassword") String oldpassword,
            @RequestParam("newusername") String newusername,
            @RequestParam("newpassword") String newpassword,
            RedirectAttributes redirectAttributes) {

        String result = adminCredentialsService.checkAdminCredentials(oldusername, oldpassword);

        System.out.println("result: " + result);

        if (result.equals("SUCCESS")) {
            String resultStr = adminCredentialsService.updateAdminCredentials(oldusername, newusername, newpassword);
                if(resultStr.equals("SUCCESS")){
                redirectAttributes.addFlashAttribute("message", "CREDENTIALS CHANGED SUCCESSFULLY!!!!!!");
                }else{
                redirectAttributes.addFlashAttribute("message", "SOMETHING WENT WRONG!!!!!!");
                     }
        } else {
            redirectAttributes.addFlashAttribute("message", "WRONG CREDENTIALS!!!!!!");
               }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("readallbookings")
    public String readAllBookings(Model model) {

        List<BookingForm> allBookingsList = bookingFormService.readAllBookings();

        model.addAttribute("allbookings", allBookingsList);

        return "admin/readallbookings";
    }

    @GetMapping("deletebooking/{id}")
    public String deleteBooking(@PathVariable int id, RedirectAttributes redirectAttributes) {

        bookingFormService.deleteBooking(id);
        redirectAttributes.addFlashAttribute("message", "BOOKING DELETED SUCCESSFULLY!!!!!!");
        return "redirect:/admin/readallbookings";
    }

    @GetMapping("addservice")
    public String addServiceView() {
        return "admin/addservice";
    }

    @InitBinder
    public void stopBinding(WebDataBinder binder) {
        binder.setDisallowedFields("image");
    }

    @PostMapping("addservice")
    public String addService(@ModelAttribute ServiceForm serviceForm,
                             @RequestParam("image") MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes) {

        String originalFileName = multipartFile.getOriginalFilename();
        serviceForm.setImage(originalFileName);
        try {
            ServiceForm form = serviceFormService.addService(serviceForm, multipartFile);

            if(form != null){
                redirectAttributes.addFlashAttribute("message","SERVICE ADDED SUCCESSFULLY!!!!!");
            }else{
                redirectAttributes.addFlashAttribute("message","SOMETHING WENT WRONG!!!!!!!");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/addservice";
    }

    @GetMapping("readallservices")
    public String readAllServices(Model model) {

        List<ServiceForm> allServicesList = serviceFormService.readAllServices();

        model.addAttribute("allservices", allServicesList);

        return "admin/readallservices";
    }

    @GetMapping("deleteservice/{id}")
    public String deleteService(@PathVariable int id, RedirectAttributes redirectAttributes) {

        serviceFormService.deleteService(id);
        redirectAttributes.addFlashAttribute("message", "SERVICE DELETED SUCCESSFULLY!!!!!!");
        return "redirect:/admin/readallservices";
    }
}