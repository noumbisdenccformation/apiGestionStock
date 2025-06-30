package com.nccformation.gestiondestock.dto;

import java.time.Instant;
import java.util.List;

import com.nccformation.gestiondestock.model.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

    private String photo;

    private AdresseDto adresse;

    private Instant dateDeNaissance;

    private List<RolesDto> roles;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .motDePasse(utilisateur.getMotDePasse())
                .photo(utilisateur.getPhoto())
                .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .dateDeNaissance(utilisateur.getDatedenaissance())
                .roles(utilisateur.getRoles() != null ? 
                    utilisateur.getRoles().stream().map(RolesDto::fromEntity).toList() : null)
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
        utilisateur.setDatedenaissance(utilisateurDto.getDateDeNaissance());
        if (utilisateurDto.getRoles() != null) {
            utilisateur.setRoles(utilisateurDto.getRoles().stream().map(RolesDto::toEntity).toList());
        }

        return utilisateur;
    }
} 