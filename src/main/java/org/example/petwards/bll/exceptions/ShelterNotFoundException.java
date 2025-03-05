package org.example.petwards.bll.exceptions;

public class ShelterNotFoundException extends RuntimeException {
    public ShelterNotFoundException(String message) {
        super(message);
    }
}
