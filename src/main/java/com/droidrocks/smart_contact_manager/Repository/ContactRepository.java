package com.droidrocks.smart_contact_manager.Repository;

import com.droidrocks.smart_contact_manager.Entitys.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {


}
