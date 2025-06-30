package com.nccformation.gestiondestock.dto;

import com.nccformation.gestiondestock.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesDto {

    private Integer id;

    private UtilisateurDto utilisateur;

    public static RolesDto fromEntity(Roles roles) {
        if (roles == null) {
            return null;
        }

        return RolesDto.builder()
                .id(roles.getId())
                .utilisateur(UtilisateurDto.fromEntity(roles.getUtilisateur()))
                .build();
    }

    public static Roles toEntity(RolesDto rolesDto) {
        if (rolesDto == null) {
            return null;
        }

        Roles roles = new Roles();
        roles.setId(rolesDto.getId());
        roles.setUtilisateur(UtilisateurDto.toEntity(rolesDto.getUtilisateur()));

        return roles;
    }
} 