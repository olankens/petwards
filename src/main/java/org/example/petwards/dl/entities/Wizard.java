package org.example.petwards.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.petwards.dl.enums.ShelterRole;
import org.example.petwards.dl.enums.WizardHouse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class Wizard implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 123)
    @Setter
    private String firstName;

    @Column(nullable = false, length = 80)
    @Setter
    private String lastName;

    @Setter
    private String email;

    @Setter
    private String password;

    @Enumerated(EnumType.STRING)
    @Setter
    private ShelterRole shelterRole;

    @Enumerated(EnumType.STRING)
    @Setter
    private WizardHouse house;

    @OneToMany(mappedBy = "wizard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter
    private List<Adoption> adoptions = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.shelterRole.toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}