package com.nccformation.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nccformation.gestiondestock.model.LigneCommandeClient;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
} 