package org.example.petwards.bll.exceptions;

public class PetwardsAdoptionNotFoundException extends RuntimeException {
    public PetwardsAdoptionNotFoundException(String message) {
        super(message);
    }
}
