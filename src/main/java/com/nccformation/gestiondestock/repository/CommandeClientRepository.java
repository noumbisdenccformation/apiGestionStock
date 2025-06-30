package com.nccformation.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nccformation.gestiondestock.model.CommandeClient;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
} 