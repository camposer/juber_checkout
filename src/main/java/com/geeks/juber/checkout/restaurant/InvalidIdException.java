package com.geeks.juber.checkout.restaurant;

// public class InvalidIdException extends Exception { // checked exception
public class InvalidIdException extends RuntimeException { // unchecked exception

    public InvalidIdException(String message) {
        super(message);
    }
}
