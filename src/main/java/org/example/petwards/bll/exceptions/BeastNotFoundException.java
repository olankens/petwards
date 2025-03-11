package org.example.petwards.bll.exceptions;

public class BeastNotFoundException extends RuntimeException {
    public BeastNotFoundException(String message) {
        super(message);
    }
}
