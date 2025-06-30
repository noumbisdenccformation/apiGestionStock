package com.nccformation.gestiondestock.service;

import com.nccformation.gestiondestock.dto.ArticleDto;
import com.nccformation.gestiondestock.model.Article;
import com.nccformation.gestiondestock.model.Category;
import com.nccformation.gestiondestock.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;

    private Article article;
    private ArticleDto articleDto;
    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1);
        category.setCode("CAT001");
        category.setDesignation("Électronique");

        article = new Article();
        article.setId(1);
        article.setCodeArticle("ART001");
        article.setDesignation("Ordinateur portable");
        article.setPrixUnitaireHt(new BigDecimal("999.99"));
        article.setTauxTva(new BigDecimal("20.0"));
        article.setPrixUnitaireTtc(new BigDecimal("1199.99"));
        article.setCategory(category);

        articleDto = ArticleDto.builder()
                .id(1)
                .codeArticle("ART001")
                .designation("Ordinateur portable")
                .prixUnitaireHt(new BigDecimal("999.99"))
                .tauxTva(new BigDecimal("20.0"))
                .prixUnitaireTtc(new BigDecimal("1199.99"))
                .build();
    }

    @Test
    void save_ShouldReturnSavedArticle() {
        // Given
        when(articleRepository.save(any(Article.class))).thenReturn(article);

        // When
        ArticleDto result = articleService.save(articleDto);

        // Then
        assertNotNull(result);
        assertEquals(articleDto.getCodeArticle(), result.getCodeArticle());
        assertEquals(articleDto.getDesignation(), result.getDesignation());
        verify(articleRepository).save(any(Article.class));
    }

    @Test
    void findById_ShouldReturnArticle() {
        // Given
        when(articleRepository.findById(1)).thenReturn(Optional.of(article));

        // When
        ArticleDto result = articleService.findById(1);

        // Then
        assertNotNull(result);
        assertEquals(article.getCodeArticle(), result.getCodeArticle());
        verify(articleRepository).findById(1);
    }

    @Test
    void findById_ShouldThrowException_WhenArticleNotFound() {
        // Given
        when(articleRepository.findById(999)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> articleService.findById(999));
        verify(articleRepository).findById(999);
    }

    @Test
    void findAll_ShouldReturnAllArticles() {
        // Given
        List<Article> articles = Arrays.asList(article);
        when(articleRepository.findAll()).thenReturn(articles);

        // When
        List<ArticleDto> result = articleService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(article.getCodeArticle(), result.get(0).getCodeArticle());
        verify(articleRepository).findAll();
    }

    @Test
    void delete_ShouldDeleteArticle() {
        // Given
        when(articleRepository.existsById(1)).thenReturn(true);

        // When
        articleService.delete(1);

        // Then
        verify(articleRepository).existsById(1);
        verify(articleRepository).deleteById(1);
    }

    @Test
    void delete_ShouldThrowException_WhenArticleNotFound() {
        // Given
        when(articleRepository.existsById(999)).thenReturn(false);

        // When & Then
        assertThrows(RuntimeException.class, () -> articleService.delete(999));
        verify(articleRepository).existsById(999);
        verify(articleRepository, never()).deleteById(any());
    }
} 