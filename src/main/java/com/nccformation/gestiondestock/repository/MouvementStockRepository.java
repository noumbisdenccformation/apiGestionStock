package com.nccformation.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nccformation.gestiondestock.model.MouvementStock;

public interface MouvementStockRepository extends JpaRepository<MouvementStock, Integer> {
} 