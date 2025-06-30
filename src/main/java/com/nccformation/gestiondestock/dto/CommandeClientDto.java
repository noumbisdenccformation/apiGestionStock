package com.nccformation.gestiondestock.dto;

import com.nccformation.gestiondestock.model.StatutCommande;
import lombok.Builder;
import lombok.Data;
import com.nccformation.gestiondestock.model.CommandeClient;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {
    private Integer id;
    private String code;
    private Instant dateCommande;
    private StatutCommande statut;
    private ClientDto client;
    private List<LigneCommandeClientDto> ligneCommandeClients;

    public static CommandeClientDto fromEntity(CommandeClient entity) {
        if (entity == null) return null;
        return CommandeClientDto.builder()
            .id(entity.getId())
            .code(entity.getCode())
            .dateCommande(entity.getDateCommande())
            .statut(entity.getStatut())
            .client(ClientDto.fromEntity(entity.getClient()))
            .ligneCommandeClients(null) // à compléter si besoin
            .build();
    }

    public static CommandeClient toEntity(CommandeClientDto dto) {
        if (dto == null) return null;
        CommandeClient entity = new CommandeClient();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setDateCommande(dto.getDateCommande());
        entity.setStatut(dto.getStatut());
        entity.setClient(ClientDto.toEntity(dto.getClient()));
        // à compléter pour les lignes
        return entity;
    }
} 