package com.droidrocks.smart_contact_manager.Repository;


import com.droidrocks.smart_contact_manager.Entitys.ContactUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface ContactUserRepository extends JpaRepository<ContactUser, Integer> {

    @Query("select u from ContactUser u where u.email = :email")
    public ContactUser findUserByEmail(@Param("email")String email);


}
