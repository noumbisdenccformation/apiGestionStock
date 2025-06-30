package com.nccformation.gestiondestock.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mouvement_stock")
public class MouvementStock extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

    @Column(name = "date_mouvement")
    private Instant dateMouvement;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_mouvement")
    private TypeMouvementStock typeMouvement;

    @Column(name = "source_mouvement")
    private String sourceMouvement;
} 