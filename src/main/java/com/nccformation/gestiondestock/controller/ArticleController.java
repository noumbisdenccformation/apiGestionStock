package com.nccformation.gestiondestock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nccformation.gestiondestock.dto.ArticleDto;
import com.nccformation.gestiondestock.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDto> save(@RequestBody ArticleDto articleDto) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(articleService.save(articleDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(articleService.findById(id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/code/{codeArticle}")
    public ResponseEntity<ArticleDto> findByCodeArticle(@PathVariable String codeArticle) {
        return ResponseEntity.ok(articleService.findByCodeArticle(codeArticle));
    }

    @GetMapping
    public ResponseEntity<List<ArticleDto>> findAll() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ArticleDto>> findByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(articleService.findByCategory(categoryId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArticleDto>> findByDesignation(@RequestParam String designation) {
        return ResponseEntity.ok(articleService.findByDesignation(designation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDto> update(@PathVariable Integer id, @RequestBody ArticleDto articleDto) {
        try {
            articleDto.setId(id);
            return ResponseEntity.ok(articleService.save(articleDto));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
} 