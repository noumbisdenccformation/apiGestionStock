package com.nccformation.gestiondestock.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name ="role")

public class Roles extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name="idutilisateur")
    private Utilisateur utilisateur;

    @Column(name = "role_name")
    private String roleName;
}
