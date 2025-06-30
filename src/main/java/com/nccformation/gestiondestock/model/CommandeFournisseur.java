package com.nccformation.gestiondestock.model;


import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name ="commandefournisseur")
public class CommandeFournisseur extends AbstractEntity{

    @Column(name="code")
    private String code;

    @Column(name="datecommande")
    private Instant dateCommande;

    @ManyToOne
    @JoinColumn(name ="idFournisseur")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commandeFournisseur")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutCommande statut;

}
