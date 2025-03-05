package org.example.petwards.api.models.shelters.from;


import org.example.petwards.dl.entities.Shelter;

public record ShelterForm (
        String name,
        String description

){
    public Shelter toShelter (){
        return new Shelter(this.name, this.description);
    }
}

