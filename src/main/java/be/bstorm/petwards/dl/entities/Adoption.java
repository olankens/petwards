package be.bstorm.petwards.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import be.bstorm.petwards.dl.enums.AdoptionStatus;

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
    @Setter
    private Beast beast;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @Setter
    private Wizard wizard;
}
