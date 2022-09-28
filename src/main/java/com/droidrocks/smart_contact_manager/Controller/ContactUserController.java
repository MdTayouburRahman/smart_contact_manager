package com.droidrocks.smart_contact_manager.Controller;

import com.droidrocks.smart_contact_manager.Entitys.Contact;
import com.droidrocks.smart_contact_manager.Entitys.ContactUser;
import com.droidrocks.smart_contact_manager.Service.ContactService;
import com.droidrocks.smart_contact_manager.Service.ContactUserService;
import com.droidrocks.smart_contact_manager.Utils.DefConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class ContactUserController {

    /**
     * @Principal is a property from spring security which is provided username to check logged user data
     * @ModelAttribute is use for set attribute to all page
     */

    private ContactUserService contactUserService;
    private ContactService contactService;

    @Autowired
    public ContactUserController(ContactUserService contactUserService, ContactService contactService) {
        this.contactUserService = contactUserService;
        this.contactService = contactService;
    }

    @ModelAttribute //common data to all page
    private void addCommonData(Model model, Principal principal) {
        String username = principal.getName();
        ContactUser contactUser = contactUserService.getUserData(username);
        //send data to screen
        model.addAttribute("contactUser", contactUser);
    }


    @RequestMapping("/index")
    public String indexPage(Model model) {
        model.addAttribute("title", DefConfig.INDEX_PAGE_TITLE);
        return "index";
    }

    @RequestMapping("/my_account")
    public String myAccountPage(Model model) {
        model.addAttribute("title", DefConfig.MY_ACCOUNT_PAGE_TITLE);
        return "my_account";
    }


    @GetMapping("/add_contact")
    public String addContactPage(Model model) {
        model.addAttribute("title", DefConfig.ADD_CONTACT_PAGE_TITLE);
        model.addAttribute("contact", new Contact());
        return "add_contact";
    }


    @PostMapping("/processContact")
    public String processContact(@ModelAttribute("contact") Contact contact,
                                 Principal principal,
                                 Model model,
                                 HttpSession session) {

        ContactUser contactUser = this.contactUserService.getUserData(principal.getName());
        Contact contact1 = new Contact();
        contact1.setName(contact.getName());
        contact1.setSecondName(contact.getSecondName());
        contact1.setWork(contact.getWork());
        contact1.setEmail(contact.getEmail());
        contact1.setPhone(contact.getPhone());
        contact1.setImage(contact.getImage());
        contact1.setDescription(contact.getDescription());
        contact1.setContactUser(contactUser);
        this.contactService.saveContactToDB(contact1);

        return "add_contact";
    }


}
