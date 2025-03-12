package org.example.petwards.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.petwards.dl.enums.DangerLevel;

import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "beast", cascade = CascadeType.ALL)
    @Setter
    private List<Adoption> adoptions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "beast_capabilities",
            joinColumns = @JoinColumn(name = "beast_id"),
            inverseJoinColumns = @JoinColumn(name = "capability_id")
    )
    private Set<Capability> capabilities = new HashSet<>();

    public Beast(String name, boolean isAvailable, DangerLevel dangerLevel) {
        this();
        this.name = name;
        this.isAvailable = isAvailable;
        this.dangerLevel = dangerLevel;
    }

    public Beast(String name, boolean isAvailable, DangerLevel dangerLevel, List<Adoption> adoptions, Set<Capability> capability) {
        this(name, isAvailable, dangerLevel);
        this.adoptions = adoptions;
        this.capabilities = capability;
    }
}
