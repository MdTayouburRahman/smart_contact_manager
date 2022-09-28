package com.droidrocks.smart_contact_manager.Service;

import com.droidrocks.smart_contact_manager.Entitys.Contact;
import com.droidrocks.smart_contact_manager.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;



    public void saveContactToDB(Contact contact) {
        contactRepository.save(contact);
    }
}
