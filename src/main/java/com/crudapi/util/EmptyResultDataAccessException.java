package com.crudapi.util;
/**
 * Exception arise when there is no expected data in the database.
 */

public class EmptyResultDataAccessException extends Exception {

    private static final long serialVersionUID = 1L;
    /**
     * 
     * @param message No Data Found.
     */
    public EmptyResultDataAccessException(String message) {
        super(message);
    }

}
