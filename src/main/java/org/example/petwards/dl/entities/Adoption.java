package org.example.petwards.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.petwards.dl.enums.AdoptionStatus;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Setter
    private AdoptionStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "beast_id")
    @Setter
    private Beast beast;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "wizard_id")
    @Setter
    private Wizard wizard;

    public Adoption(AdoptionStatus adoptionStatus) {
        this();
        this.status = status;
    }
}
