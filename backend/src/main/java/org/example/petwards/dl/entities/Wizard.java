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
@EqualsAndHashCode(of = {"email"})
@ToString(of = {"email", "shelterRole"})
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
    private WizardHouse wizardHouse;

    @OneToMany(mappedBy = "wizard", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Setter
    private List<Adoption> adoptions = new ArrayList<>();

    public Wizard(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Wizard(String firstName, String lastName, String email, String password, WizardHouse wizardHouse) {
        this(firstName, lastName, email, password);
        this.wizardHouse = wizardHouse;
    }

    public Wizard(String firstName, String lastName, String email, String password, WizardHouse wizardHouse, ShelterRole shelterRole) {
        this(firstName, lastName, email, password, wizardHouse);
        this.shelterRole = shelterRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.shelterRole.toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}