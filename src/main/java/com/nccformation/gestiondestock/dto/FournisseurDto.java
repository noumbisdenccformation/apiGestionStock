package com.nccformation.gestiondestock.dto;

import com.nccformation.gestiondestock.model.Fournisseur;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FournisseurDto {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    public static FournisseurDto fromEntity(Fournisseur entity) {
        if (entity == null) return null;
        return FournisseurDto.builder()
            .id(entity.getId())
            .nom(entity.getNom())
            .prenom(entity.getPrenom())
            .email(entity.getMail())
            .telephone(entity.getNumTel())
            .build();
    }

    public static Fournisseur toEntity(FournisseurDto dto) {
        if (dto == null) return null;
        Fournisseur entity = new Fournisseur();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setMail(dto.getEmail());
        entity.setNumTel(dto.getTelephone());
        return entity;
    }
} 