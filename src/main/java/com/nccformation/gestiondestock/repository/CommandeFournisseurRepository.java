package com.nccformation.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nccformation.gestiondestock.model.CommandeFournisseur;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
} 