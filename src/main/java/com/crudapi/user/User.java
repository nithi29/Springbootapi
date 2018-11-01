package com.crudapi.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User Entity class.
 *
 */

@Entity
@Table(name = "user_table")
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email",nullable = false,unique = true)
    private String email;
    /**
     * 
     * @return userid.
     */

    public Integer getUserid() {
        return userid;
    }
    /**
     * 
     * @param userid set userid.
     */

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    /**
     * 
     * @return firstname.
     */

    public String getFirstName() {
        return firstName;
    }
    /**
     * 
     * @param firstName set firstname.
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * 
     * @return lastname.
     */

    public String getLastName() {
        return lastName;
    }
    /**
     * 
     * @param lastName set lastname.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * 
     * @return email.
     */

    public String getEmail() {
        return email;
    }
    /**
     * 
     * @param email set email.
     */

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserEntity [userid=" + userid + ", firstName=" + firstName + ", "
                + "lastName=" + lastName + ", email=" + email + "]";
    }

}
