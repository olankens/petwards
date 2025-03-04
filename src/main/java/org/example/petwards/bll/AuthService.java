package org.example.petwards.bll;

import org.example.petwards.dl.entities.Wizard;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {


        void register(Wizard wizard);
        Wizard login(Long id, String password);


}
