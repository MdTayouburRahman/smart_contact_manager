package com.droidrocks.smart_contact_manager.Security;

import com.droidrocks.smart_contact_manager.Entitys.ContactUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final ContactUser contactUser;

    public CustomUserDetails(ContactUser contactUser) {
        this.contactUser = contactUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(contactUser.getRole());
     return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return contactUser.getPassword();
    }

    @Override
    public String getUsername() {
        return contactUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
