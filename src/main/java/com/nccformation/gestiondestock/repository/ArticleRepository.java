package com.nccformation.gestiondestock.repository;

import com.nccformation.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findByCodeArticle(String codeArticle);

    List<Article> findAllByCategoryId(Integer categoryId);

    List<Article> findByDesignationContainingIgnoreCase(String designation);
} 