package com.nccformation.gestiondestock.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name ="fournisseur")
public class Fournisseur extends AbstractEntity {

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Embedded // c'est un champ composé qu'on va pouvoir utiliser plusieurs fois
    private Adresse adresse;

    @Column(name="photo")
    private String photo;

    @Column(name="mail")
    private String mail;

    @Column(name="numtel")
    private String numTel;

    @OneToMany(mappedBy = "")
    private List<CommandeFournisseur> commandeFournisseurs;
}
