package com.crudapi.dao;
import com.crudapi.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Data access layer for the application.
 *
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
   /**
    * 
    * @param email email
    * @return email 
    */
    User findByEmail(String email);
}
