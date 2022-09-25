package com.droidrocks.smart_contact_manager.Security;

import com.droidrocks.smart_contact_manager.Entitys.ContactUser;
import com.droidrocks.smart_contact_manager.Repository.ContactUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ContactUserRepository contactUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     ContactUser contactUser = this.contactUserRepository.findUserByEmail(username);
     CustomUserDetails customUserDetails = null;
     if (contactUser != null){
         System.out.println("User -->"+contactUser.toString());
         customUserDetails = new CustomUserDetails(contactUser);
     }else {
         throw new UsernameNotFoundException("User Could not found");
     }

        return customUserDetails;
    }
}
