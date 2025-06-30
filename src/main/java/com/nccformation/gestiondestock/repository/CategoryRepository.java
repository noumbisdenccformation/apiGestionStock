package com.nccformation.gestiondestock.repository;

import com.nccformation.gestiondestock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByCode(String code);

    Optional<Category> findByDesignation(String designation);
} 