package com.nccformation.gestiondestock.controller;

import com.nccformation.gestiondestock.dto.MouvementStockDto;
import com.nccformation.gestiondestock.service.MouvementStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/mouvements-stock")
@RequiredArgsConstructor
public class MouvementStockController {

    private final MouvementStockService mouvementStockService;

    @PostMapping
    public ResponseEntity<MouvementStockDto> save(@RequestBody MouvementStockDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mouvementStockService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MouvementStockDto> update(@PathVariable Integer id, @RequestBody MouvementStockDto dto) {
        try {
            dto.setId(id);
            return ResponseEntity.ok(mouvementStockService.save(dto));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MouvementStockDto> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(mouvementStockService.findById(id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<MouvementStockDto>> findAll() {
        return ResponseEntity.ok(mouvementStockService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        mouvementStockService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 