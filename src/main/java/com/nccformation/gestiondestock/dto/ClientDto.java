package com.nccformation.gestiondestock.dto;

import com.nccformation.gestiondestock.model.Client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    public static ClientDto fromEntity(Client entity) {
        if (entity == null) return null;
        return ClientDto.builder()
            .id(entity.getId())
            .nom(entity.getNom())
            .prenom(entity.getPrenom())
            .email(entity.getMail())
            .telephone(entity.getNumTel())
            .build();
    }

    public static Client toEntity(ClientDto dto) {
        if (dto == null) return null;
        Client entity = new Client();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setMail(dto.getEmail());
        entity.setNumTel(dto.getTelephone());
        return entity;
    }
} 