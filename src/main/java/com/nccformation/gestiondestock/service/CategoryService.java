package com.nccformation.gestiondestock.service;

import com.nccformation.gestiondestock.dto.CategoryDto;
import com.nccformation.gestiondestock.model.Category;
import com.nccformation.gestiondestock.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto save(CategoryDto categoryDto) {
        log.info("Saving category: {}", categoryDto);
        Category category = CategoryDto.toEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryDto.fromEntity(savedCategory);
    }

    public CategoryDto findById(Integer id) {
        log.info("Finding category by id: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return CategoryDto.fromEntity(category);
    }

    public CategoryDto findByCode(String code) {
        log.info("Finding category by code: {}", code);
        Category category = categoryRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Category not found with code: " + code));
        return CategoryDto.fromEntity(category);
    }

    public List<CategoryDto> findAll() {
        log.info("Finding all categories");
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        log.info("Deleting category with id: {}", id);
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
} 