package com.crudapi.service;

import com.crudapi.dao.UserDao;
import com.crudapi.user.User;
import com.crudapi.util.ApplicationException;
import com.crudapi.util.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation layer.
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * @param user user details.
     * @return user data saved.
     * @throws ApplicationException for validation.
     */
    @Transactional
    public User save(User user) throws ApplicationException {
        validateUser(user);
        emailValidation(user);
        userDao.save(user);
        return user;

    }

    /**
     * @param user user details.
     * @return user data update.
     * @throws ApplicationException for validation.
     */
    @Transactional
    public User update(User user) throws ApplicationException {
        validateUser(user);
        return userDao.save(user);
       
    }
    
    /**
     * @param userid of the user.
     * @throws EmptyResultDataAccessException for no userid.
     */
    @Transactional
    public void delete(int userid) {
        userDao.deleteById(userid);
    }

    /**
     * @return user list all users.
     */
    @Transactional
    public List<User> findAll() {
        return (List<User>) userDao.findAll();

    }

    @Override
    @Transactional
    public User getById(int userid) {
        Optional<User> user = userDao.findById(userid);
        return user.get();
    }

    /**
     * 
     */
    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * 
     * @param user user details.
     * @throws ApplicationException for invalid entry.
     */

    private void validateUser(User user) throws ApplicationException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        String regex = "^[a-zA-Z]+$";
        Matcher mtch = pat.matcher(user.getEmail());

        if (user.getFirstName() == null || user.getFirstName().isEmpty() || user.getLastName() == null
                || user.getLastName().isEmpty() || user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ApplicationException("Field cannot be empty");
        } else if (!user.getFirstName().matches(regex) || !user.getLastName().matches(regex)) {
            throw new ApplicationException("Name should be in alphabets only");
        } else if (!mtch.matches()) {
            throw new ApplicationException("Invalid Email Address");
        } else if (user.getFirstName().length() > 50 || user.getLastName().length() > 50
                || user.getFirstName().length() < 3 || user.getLastName().length() < 2) {
            throw new ApplicationException("Name length is invalid");
        }

    }

    /**
     * 
     * @param user email
     * @throws ApplicationException Email Exists
     */
    private void emailValidation(User user) throws ApplicationException {
        User userExists = userDao.findByEmail(user.getEmail());

        if (userExists != null) {
            throw new ApplicationException("Email already exists");
        } else {
            return;
        }
    }
}
