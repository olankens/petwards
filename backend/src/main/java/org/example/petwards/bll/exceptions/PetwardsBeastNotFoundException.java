package org.example.petwards.bll.exceptions;

public class PetwardsBeastNotFoundException extends RuntimeException {
    public PetwardsBeastNotFoundException(String message) {
        super(message);
    }
}
