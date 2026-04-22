package org.example.petwards.bll;

import org.example.petwards.dl.entities.Wizard;

public interface AuthService {
    void register(Wizard wizard);

    Wizard login(String email, String password);
}
