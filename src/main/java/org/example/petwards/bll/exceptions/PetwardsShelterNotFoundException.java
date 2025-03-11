package org.example.petwards.bll.exceptions;

public class PetwardsShelterNotFoundException extends RuntimeException {
    public PetwardsShelterNotFoundException(String message) {
        super(message);
    }
}
