package com.nccformation.gestiondestock.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Adresse {

    @Column(name="adresse1")
    private String adresse1;

    @Column(name="adresse2")
    private String adresse2;

    @Column(name="ville")
    private String ville;

    @Column(name = "codepostale")
    private String codePostale;

    @Column(name = "pays")
    private String pays;



}
