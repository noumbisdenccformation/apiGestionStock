package com.nccformation.gestiondestock.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nccformation.gestiondestock.dto.ArticleDto;
import com.nccformation.gestiondestock.model.Article;
import com.nccformation.gestiondestock.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleDto save(ArticleDto articleDto) {
        log.info("Saving article: {}", articleDto);
        Article article = ArticleDto.toEntity(articleDto);
        if (article.getId() != null) {
            Article existing = articleRepository.findById(article.getId()).orElse(null);
            if (existing != null) {
                article.setCreationDate(existing.getCreationDate());
            }
        }
        Article savedArticle = articleRepository.save(article);
        return ArticleDto.fromEntity(savedArticle);
    }

    public ArticleDto findById(Integer id) {
        log.info("Finding article by id: {}", id);
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
        return ArticleDto.fromEntity(article);
    }

    public ArticleDto findByCodeArticle(String codeArticle) {
        log.info("Finding article by code: {}", codeArticle);
        Article article = articleRepository.findByCodeArticle(codeArticle)
                .orElseThrow(() -> new RuntimeException("Article not found with code: " + codeArticle));
        return ArticleDto.fromEntity(article);
    }

    public List<ArticleDto> findAll() {
        log.info("Finding all articles");
        return articleRepository.findAll()
                .stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ArticleDto> findByCategory(Integer categoryId) {
        log.info("Finding articles by category id: {}", categoryId);
        return articleRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ArticleDto> findByDesignation(String designation) {
        log.info("Finding articles by designation: {}", designation);
        return articleRepository.findByDesignationContainingIgnoreCase(designation)
                .stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        log.info("Deleting article with id: {}", id);
        if (!articleRepository.existsById(id)) {
            throw new RuntimeException("Article not found with id: " + id);
        }
        articleRepository.deleteById(id);
    }
} 