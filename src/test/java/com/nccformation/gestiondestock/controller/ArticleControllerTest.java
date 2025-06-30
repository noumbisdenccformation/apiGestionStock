package com.nccformation.gestiondestock.controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nccformation.gestiondestock.dto.ArticleDto;
import com.nccformation.gestiondestock.dto.CategoryDto;
import com.nccformation.gestiondestock.model.Article;
import com.nccformation.gestiondestock.model.Category;
import com.nccformation.gestiondestock.repository.ArticleRepository;
import com.nccformation.gestiondestock.repository.CategoryRepository;

@SpringBootTest
@ActiveProfiles("test")
class ArticleControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
        
        // Nettoyer la base de données avant chaque test
        articleRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void testCreateArticle() throws Exception {
        // Créer une catégorie d'abord
        Category category = new Category();
        category.setCode("CAT001");
        category.setDesignation("Électronique");
        category = categoryRepository.save(category);

        // Créer un article DTO
        ArticleDto articleDto = ArticleDto.builder()
                .codeArticle("ART001")
                .designation("Ordinateur portable")
                .prixUnitaireHt(new BigDecimal("999.99"))
                .tauxTva(new BigDecimal("20.0"))
                .prixUnitaireTtc(new BigDecimal("1199.99"))
                .category(CategoryDto.fromEntity(category))
                .build();

        // Tester la création d'un article
        mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(articleDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.codeArticle").value("ART001"))
                .andExpect(jsonPath("$.designation").value("Ordinateur portable"))
                .andExpect(jsonPath("$.prixUnitaireHt").value(999.99));
    }

    @Test
    void testGetAllArticles() throws Exception {
        // Créer une catégorie
        Category category = new Category();
        category.setCode("CAT001");
        category.setDesignation("Électronique");
        category = categoryRepository.save(category);

        // Créer un article
        Article article = new Article();
        article.setCodeArticle("ART001");
        article.setDesignation("Ordinateur portable");
        article.setPrixUnitaireHt(new BigDecimal("999.99"));
        article.setTauxTva(new BigDecimal("20.0"));
        article.setPrixUnitaireTtc(new BigDecimal("1199.99"));
        article.setCategory(category);
        articleRepository.save(article);

        // Tester la récupération de tous les articles
        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].codeArticle").value("ART001"))
                .andExpect(jsonPath("$[0].designation").value("Ordinateur portable"));
    }

    @Test
    void testGetArticleById() throws Exception {
        // Créer une catégorie
        Category category = new Category();
        category.setCode("CAT001");
        category.setDesignation("Électronique");
        category = categoryRepository.save(category);

        // Créer un article
        Article article = new Article();
        article.setCodeArticle("ART001");
        article.setDesignation("Ordinateur portable");
        article.setPrixUnitaireHt(new BigDecimal("999.99"));
        article.setTauxTva(new BigDecimal("20.0"));
        article.setPrixUnitaireTtc(new BigDecimal("1199.99"));
        article.setCategory(category);
        article = articleRepository.save(article);

        // Tester la récupération d'un article par ID
        mockMvc.perform(get("/api/articles/{id}", article.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codeArticle").value("ART001"))
                .andExpect(jsonPath("$.designation").value("Ordinateur portable"));
    }

    @Test
    void testUpdateArticle() throws Exception {
        // Créer une catégorie
        Category category = new Category();
        category.setCode("CAT001");
        category.setDesignation("Électronique");
        category = categoryRepository.save(category);

        // Créer un article
        Article article = new Article();
        article.setCodeArticle("ART001");
        article.setDesignation("Ordinateur portable");
        article.setPrixUnitaireHt(new BigDecimal("999.99"));
        article.setTauxTva(new BigDecimal("20.0"));
        article.setPrixUnitaireTtc(new BigDecimal("1199.99"));
        article.setCategory(category);
        article = articleRepository.save(article);

        // Créer un DTO de mise à jour
        ArticleDto updateDto = ArticleDto.builder()
                .id(article.getId())
                .codeArticle("ART001-UPDATED")
                .designation("Ordinateur portable mis à jour")
                .prixUnitaireHt(new BigDecimal("1099.99"))
                .tauxTva(new BigDecimal("20.0"))
                .prixUnitaireTtc(new BigDecimal("1319.99"))
                .category(CategoryDto.fromEntity(category))
                .build();

        // Tester la mise à jour d'un article
        mockMvc.perform(put("/api/articles/{id}", article.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codeArticle").value("ART001-UPDATED"))
                .andExpect(jsonPath("$.designation").value("Ordinateur portable mis à jour"));
    }

    @Test
    void testDeleteArticle() throws Exception {
        // Créer une catégorie
        Category category = new Category();
        category.setCode("CAT001");
        category.setDesignation("Électronique");
        category = categoryRepository.save(category);

        // Créer un article
        Article article = new Article();
        article.setCodeArticle("ART001");
        article.setDesignation("Ordinateur portable");
        article.setPrixUnitaireHt(new BigDecimal("999.99"));
        article.setTauxTva(new BigDecimal("20.0"));
        article.setPrixUnitaireTtc(new BigDecimal("1199.99"));
        article.setCategory(category);
        article = articleRepository.save(article);

        // Tester la suppression d'un article
        mockMvc.perform(delete("/api/articles/{id}", article.getId()))
                .andExpect(status().isNoContent());

        // Vérifier que l'article a été supprimé
        mockMvc.perform(get("/api/articles/{id}", article.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetArticleByCode() throws Exception {
        // Créer une catégorie
        Category category = new Category();
        category.setCode("CAT001");
        category.setDesignation("Électronique");
        category = categoryRepository.save(category);

        // Créer un article
        Article article = new Article();
        article.setCodeArticle("ART001");
        article.setDesignation("Ordinateur portable");
        article.setPrixUnitaireHt(new BigDecimal("999.99"));
        article.setTauxTva(new BigDecimal("20.0"));
        article.setPrixUnitaireTtc(new BigDecimal("1199.99"));
        article.setCategory(category);
        articleRepository.save(article);

        // Tester la récupération d'un article par code
        mockMvc.perform(get("/api/articles/code/{code}", "ART001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codeArticle").value("ART001"))
                .andExpect(jsonPath("$.designation").value("Ordinateur portable"));
    }
} 