package com.nccformation.gestiondestock.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name ="lignevente")


public class LigneVente extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name ="idvente")
    private Ventes vente;

    @ManyToOne
    @JoinColumn(name="idarticle")
    private Article article;

    private BigDecimal quantite;
}
