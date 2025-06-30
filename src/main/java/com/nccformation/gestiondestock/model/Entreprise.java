package com.nccformation.gestiondestock.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name ="entreprise")


public class Entreprise extends AbstractEntity{

    @OneToMany(mappedBy = "entreprise")
    private List<Article> articles;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;
}
