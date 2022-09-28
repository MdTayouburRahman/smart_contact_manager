package com.droidrocks.smart_contact_manager.Service;

import com.droidrocks.smart_contact_manager.Entitys.ContactUser;
import com.droidrocks.smart_contact_manager.Helper.Message;
import com.droidrocks.smart_contact_manager.Repository.ContactRepository;
import com.droidrocks.smart_contact_manager.Repository.ContactUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class ContactUserService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ContactUserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;


    /**
     * register a new user
     */
    public void registerNewUser(ContactUser contactUser,
                                Boolean agreement,
                                Model model,
                                HttpSession session) throws Exception {

        if (!agreement) {
            System.out.println("You have not agreed with the terms and conditions");
            throw new Exception("You have not agreed with the terms and conditions");
        }
        contactUser.setRole("USER");
        contactUser.setEnabled(true);
        contactUser.setImageUrl("default.png");
        //encrypt password
        contactUser.setPassword(passwordEncoder.encode(contactUser.getPassword()));

        //save user to db
        userRepository.save(contactUser);

        System.out.println("Agreement " + agreement);
        System.out.println("user " + contactUser.toString());

        //after complete registration send a black user to html
        model.addAttribute("contactUser", new ContactUser());
        session.setAttribute("message",
                new Message("Registration Successful",
                        "alert-success"));
    }

    public ContactUser getUserData(String username) {
        ContactUser contactUser = userRepository.findUserByEmail(username);
        return contactUser;
    }
}
