package org.example.petwards.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.petwards.dl.enums.DangerLevel;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class Beast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @Setter
    private boolean isAvailable;

    @Enumerated(EnumType.STRING)
    @Setter
    private DangerLevel dangerLevel;

    @ManyToOne
    @JoinColumn(name = "adoption")
    @Setter
    private Adoption adoption;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "beast_capabilities",
            joinColumns = @JoinColumn(name = "beast_id"),
            inverseJoinColumns = @JoinColumn(name = "capability_id")
    )
    private Set<Capability> capabilities = new HashSet<>();

    public Beast(String name, boolean isAvailable, DangerLevel dangerLevel, Adoption adoption, Set<Capability> capability) {
        this();
        this.name = name;
        this.isAvailable = isAvailable;
        this.dangerLevel = dangerLevel;
        this.adoption = adoption;
        this.capabilities = capability;
    }
}
