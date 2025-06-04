package org.example.petwards.bll.exceptions;

public class PetwardsStaffNotFoundException extends RuntimeException {
    public PetwardsStaffNotFoundException(String message) {
        super(message);
    }
}
