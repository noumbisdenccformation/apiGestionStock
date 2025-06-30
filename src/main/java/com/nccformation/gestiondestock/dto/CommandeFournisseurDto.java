package com.nccformation.gestiondestock.dto;

import com.nccformation.gestiondestock.model.CommandeFournisseur;
import com.nccformation.gestiondestock.model.StatutCommande;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {
    private Integer id;
    private String code;
    private Instant dateCommande;
    private StatutCommande statut;
    private FournisseurDto fournisseur;
    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur entity) {
        if (entity == null) return null;
        return CommandeFournisseurDto.builder()
            .id(entity.getId())
            .code(entity.getCode())
            .dateCommande(entity.getDateCommande())
            .statut(entity.getStatut())
            .fournisseur(FournisseurDto.fromEntity(entity.getFournisseur()))
            .ligneCommandeFournisseurs(null) // à compléter si besoin
            .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto dto) {
        if (dto == null) return null;
        CommandeFournisseur entity = new CommandeFournisseur();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setDateCommande(dto.getDateCommande());
        entity.setStatut(dto.getStatut());
        entity.setFournisseur(FournisseurDto.toEntity(dto.getFournisseur()));
        // à compléter pour les lignes
        return entity;
    }
} 