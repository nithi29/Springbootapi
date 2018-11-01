package com.crudapi.controller;

import com.crudapi.service.UserService;
import com.crudapi.user.User;
import com.crudapi.util.ApplicationException;
import com.crudapi.util.ErrorDetails;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller.
 *
 */
@RestController
public class UserController {

    /** Instance variable user service. */
    @Autowired
    UserService userService;

    /**
     * Get all the users.
     * @throws Exception USER VALIDATION
     * @return List of users
     */
    @GetMapping("/users")
    public List<User> getAllUsers() throws Exception {
        return userService.findAll();
    }

    /**
     * 
     * @param user - CREATE A NEW USER
     * @return saved user
     * @throws Exception USER VALIDATION
     */
    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) throws Exception {
        return userService.save(user);
    }

    /**
     * 
     * @param id GET THE USER BY ID
     * @return USERID
     * @throws Exception USERID DOESNT EXIST
     */
    @RequestMapping(value = "/users/{userid}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userid") int id) throws Exception {
        return userService.getById(id);
    }

    /**
     * 
     * @param userid the userid.
     * @param user user details.
     * @return USER
     * @throws Exception VALIDATION
     */
    @PutMapping("/users/{userid}")
    public ResponseEntity<User> update(@PathVariable(value = "userid") int userid, @Valid @RequestBody User user)
            throws Exception {

        User existingUser = userService.getById(userid);
        if (existingUser.getUserid() == user.getUserid()) {
            User updated = userService.save(user);
            return ResponseEntity.ok(updated);
        } else {
            throw new ApplicationException("Userid doesnt match");
        }
    }

    /**
     * 
     * @param userid User id value.
     * @throws ResourceNotFoundException NO USERID
     */
    @DeleteMapping("/users/{userid}")
    public void delete(@PathVariable(value = "userid") int userid) throws ResourceNotFoundException {
        userService.delete(userid);

    }

    /**
     * 
     * @param exception if userid doesnt exist.
     * @return UserId doesnt exist.
     */

    @ResponseStatus(value = HttpStatus.NOT_FOUND) //
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ErrorDetails exception(EmptyResultDataAccessException exception) {
        return new ErrorDetails(exception.getMessage());
    }

    /**
     * 
     * @param ex Exception if validation failed.
     * @return Precondition validations failed.
     */

    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED) // 412
    @ExceptionHandler(ApplicationException.class)
    public ErrorDetails exception(ApplicationException ex) {
        return new ErrorDetails(ex.getMessage());
    }

    /**
     * 
     * @param ex Exception.
     * @return No Data
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND) // 412
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorDetails exception(NoSuchElementException ex) {
        return new ErrorDetails(ex.getMessage());
    }

}
