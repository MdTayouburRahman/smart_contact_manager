package com.droidrocks.smart_contact_manager.Controller;

import com.droidrocks.smart_contact_manager.Entitys.ContactUser;
import com.droidrocks.smart_contact_manager.Helper.Message;
import com.droidrocks.smart_contact_manager.Service.ContactUserService;
import com.droidrocks.smart_contact_manager.Utils.DefConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {


    private ContactUserService contactUserService;
    @Autowired
    public HomeController(ContactUserService contactUserService) {
        this.contactUserService = contactUserService;
    }


    @RequestMapping("/")
    public String home2(Model model) {
        model.addAttribute("title", DefConfig.HOME_PAGE_TITLE);
        return "home";
    }

    @RequestMapping("/home")
    public String Home(Model model) {
        model.addAttribute("title", DefConfig.HOME_PAGE_TITLE);
        return "home";
    }

    @RequestMapping("/about")
    public String About(Model model) {
        model.addAttribute("title", DefConfig.ABOUT_PAGE_TITLE);
        return "about";
    }

    @RequestMapping("/signup")
    public String Signup(Model model) {
        model.addAttribute("title", DefConfig.SIGNUP_PAGE_TITLE);
        model.addAttribute("contactUser", new ContactUser());
        return "signup";
    }

    @RequestMapping(value = "/signin")
    public String customLogin(Model model) {
        model.addAttribute("title", DefConfig.LOGIN_PAGE_TITLE);
        return "signin";
    }


    //registerNewUser

    /**
     * @BindindResult for post and get the result of validation.
     * --------------------------------------------------
     * @ModelAttribute is for passing full model
     * @RequestParam is to get a extra data from form
     * @Model is for adding Attribute
     * @HttpSession is for session setAttribute
     */
    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("contactUser") ContactUser contactUser,
                               @RequestParam(value = "agreement", defaultValue = "false") Boolean agreement,
                               Model model, HttpSession session) {

        try {
            contactUserService.registerNewUser(contactUser, agreement, model, session);
            return "signup";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("contactUser", contactUser);
            session.setAttribute("message",
                    new Message("Something went wrong" + e.getMessage(),
                            "alert-error"));

            return "signup";
        }
    }



}
